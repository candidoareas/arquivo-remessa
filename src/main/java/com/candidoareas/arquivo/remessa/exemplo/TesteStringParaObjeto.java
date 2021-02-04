/*
 * Desenvolvido por Cândido Areas
 * http://www.candidoareas.com
 *
 * Todos os direitos reservados
 */
package com.candidoareas.arquivo.remessa.exemplo;

import com.candidoareas.arquivo.remessa.tratamento.TratadorRemessa;

import java.text.ParseException;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Teste simples de geração de um objeto populado a partir de uma string.
 *
 * @author   candido.areas
 * @version  1.0
 * @since    1.0
 */
public class TesteStringParaObjeto
{

    /**
     * DOCUMENT ME!
     *
     * @param  args  DOCUMENT ME!
     */
    public static void main(String[] args)
    {
        String str = "AA00000001241234524051983";
        ClasseAnotada antc = null;
        TratadorRemessa trt = new TratadorRemessa();


        try
        {
            antc = trt.obterClasse(str, ClasseAnotada.class);
            System.out.println(antc.getNumero());
            System.out.println(antc.getTexto1());
            System.out.println(antc.getTexto2());
            System.out.println(antc.getData().toString());
        }
        catch (ParseException | InstantiationException | IllegalAccessException ex)
        {
            Logger.getLogger(TesteStringParaObjeto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
