/*
 * Desenvolvido por Cândido Areas
 * http://www.candidoareas.com
 *
 * Todos os direitos reservados
 */
package com.candidoareas.arquivo.remessa.tratamento;


import com.candidoareas.arquivo.remessa.annotations.Date;
import com.candidoareas.arquivo.remessa.annotations.Number;
import com.candidoareas.arquivo.remessa.annotations.Text;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * Utilitário de tratamento das strings ou classes geradoras, que executam o movimento de obtenção de dados ou geração.
 *
 * @author   candido.areas
 * @version  1.0
 * @since    1.0
 */
public class Tratador
{


    /**
     * Obtém o objeto a partir de uma matriz em formato String, de acordo com as anotações declaradas.
     *
     * @param   str          Valor da linha lida.
     * @param   targetClass  Tipo de dado que representa o objeto que será gerado a partir da linha.
     *
     * @return  Objeto populado com os dados contidos na linha.
     *
     * @throws  InstantiationException  Lançada nos casos onde é impossível instanciar o objeto via reflection.
     * @throws  IllegalAccessException  Lançada nos casos onde a invocação de um método ou a leitura/gravação de um campo é proibida devido as regras
     *                                  de visibilidade do campo/método definida.
     * @throws  ParseException          Lançada nos casos onde é impossível converter o dado.
     */
    public <T> T obterClasse(String str, Class targetClass) throws InstantiationException, IllegalAccessException, ParseException
    {
        String[] orderFields = null;
        Field[] fields = targetClass.getDeclaredFields();
        int counterAnts = 0;

        for (Field field : fields)
        {
            field.setAccessible(true);

            if (field.getDeclaredAnnotations() != null)
            {
                Annotation[] ants = field.getDeclaredAnnotations();

                for (Annotation ant : ants)
                {

                    if ((ant instanceof Date) || (ant instanceof Number) || (ant instanceof Text))
                    {
                        counterAnts++;
                    }
                }
            }
        }

        orderFields = new String[counterAnts];

        Object obj = null;

        if (counterAnts > 0)
        {

            for (int i = 0; i < fields.length; i++)
            {
                Field field = fields[i];
                field.setAccessible(true);

                if (field.getDeclaredAnnotations() != null)
                {
                    Annotation[] ants = field.getDeclaredAnnotations();

                    for (Annotation ant : ants)
                    {

                        if (ant instanceof Text)
                        {
                            orderFields[((Text) ant).position() - 1] = field.getName();
                        }
                        else if (ant instanceof Number)
                        {
                            orderFields[((Number) ant).position() - 1] = field.getName();
                        }
                        else if (ant instanceof Date)
                        {
                            orderFields[((Date) ant).position() - 1] = field.getName();
                        }
                    }
                }
            }

            int lastCountStr = 0;
            obj = targetClass.newInstance();

            for (int i = 0; i < orderFields.length; i++)
            {

                for (Field field : obj.getClass().getDeclaredFields())
                {
                    field.setAccessible(true);

                    if (field.getName().equals(orderFields[i]))
                    {
                        Object value = null;
                        Annotation[] ants = field.getDeclaredAnnotations();

                        for (Annotation ant : ants)
                        {

                            if (ant instanceof Text)
                            {
                                value = str.substring(lastCountStr, (lastCountStr + ((Text) ant).length()));
                                lastCountStr = lastCountStr + ((Text) ant).length();
                            }
                            else if (ant instanceof Number)
                            {
                                value = Integer.parseInt(str.substring(lastCountStr, (lastCountStr + ((Number) ant).length())));
                                lastCountStr = lastCountStr + ((Number) ant).length();
                            }
                            else if (ant instanceof Date)
                            {
                                SimpleDateFormat fmt = new SimpleDateFormat(((Date) ant).pattern());
                                value = fmt.parse(str.substring(lastCountStr, (lastCountStr + ((Date) ant).pattern().length())));
                            }
                        }

                        field.set(obj, value);
                    }
                }
            }
        }

        return (T) obj;
    }

    /**
     * Transforma os dados contidos no objeto em string, de acordo com as anotações declaradas.
     *
     * @param   obj  Objeto contendo os dados a serem convertidos em uma string.
     *
     * @return  String contendo os dados do objeto, na ordem definida e de acordo com as anotações declaradas.
     *
     * @throws  IllegalArgumentException  Lançada caso os parâmetros passados sejam incorretos (quantidade, tipo, etc).
     * @throws  IllegalAccessException    Lançada nos casos onde a invocação de um método ou a leitura/gravação de um campo é proibida devido as
     *                                    regras de visibilidade do campo/método definida.
     */
    public <T> String obterString(T obj) throws IllegalArgumentException, IllegalAccessException
    {
        Field[] fields = obj.getClass().getDeclaredFields();
        int counterAnts = 0;

        for (Field field : fields)
        {
            field.setAccessible(true);

            if (field.getDeclaredAnnotations() != null)
            {
                Annotation[] ants = field.getDeclaredAnnotations();

                for (Annotation ant : ants)
                {

                    if ((ant instanceof Date) || (ant instanceof Number) || (ant instanceof Text))
                    {
                        counterAnts++;
                    }
                }
            }
        }

        String[] orderFields = null;
        orderFields = new String[counterAnts];


        StringBuilder builder = new StringBuilder();

        if (counterAnts > 0)
        {

            for (int i = 0; i < fields.length; i++)
            {
                Field field = fields[i];
                field.setAccessible(true);

                if (field.getDeclaredAnnotations() != null)
                {
                    Annotation[] ants = field.getDeclaredAnnotations();

                    for (Annotation ant : ants)
                    {

                        if (ant instanceof Text)
                        {
                            orderFields[((Text) ant).position() - 1] = field.getName();
                        }
                        else if (ant instanceof Number)
                        {
                            orderFields[((Number) ant).position() - 1] = field.getName();
                        }
                        else if (ant instanceof Date)
                        {
                            orderFields[((Date) ant).position() - 1] = field.getName();
                        }
                    }
                }
            }

            for (int i = 0; i < orderFields.length; i++)
            {
                String fieldName = orderFields[i];

                for (Field fieldTo : obj.getClass().getDeclaredFields())
                {
                    fieldTo.setAccessible(true);

                    if (fieldTo.getName().equals(fieldName))
                    {
                        Annotation[] antf = fieldTo.getDeclaredAnnotations();

                        for (Annotation att : antf)
                        {

                            if (att instanceof Text)
                            {
                                builder.append(String.format("%-" + ((Text) att).length() + "s",
                                        ((fieldTo.get(obj) == null) ? "" : fieldTo.get(obj))).substring(0, ((Text) att).length()));
                            }

                            if (att instanceof Number)
                            {
                                builder.append(String.format("%0" + ((Number) att).length() + "d",
                                        ((fieldTo.get(obj) == null) ? 0 : fieldTo.get(obj))));
                            }

                            if (att instanceof Date)
                            {

                                if (fieldTo.get(obj) != null)
                                {
                                    SimpleDateFormat fmt = new SimpleDateFormat(((Date) att).pattern());
                                    builder.append(fmt.format((java.util.Date) fieldTo.get(obj)));
                                }
                                else
                                {
                                    builder.append((String.format("%-" + ((Date) att).pattern().length() + "s", "")));
                                }
                            }
                        }
                    }
                }
            }
        }

        return builder.toString();
    }
}
