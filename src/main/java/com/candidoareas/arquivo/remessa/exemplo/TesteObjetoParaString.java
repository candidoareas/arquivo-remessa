/*
 * Desenvolvido por Cândido Areas
 * http://www.candidoareas.com
 *
 * Todos os direitos reservados
 */
package com.candidoareas.arquivo.remessa.exemplo;

import com.candidoareas.arquivo.remessa.tratamento.TratadorRemessa;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Teste simples de geração de string a partir de um objeto populado.
 *
 * @author   candido.areas
 * @version  1.0
 * @since    1.0
 */
public class TesteObjetoParaString
{

    /**
     * DOCUMENT ME!
     *
     * @param  args  DOCUMENT ME!
     */
    public static void main(String[] args)
    {
        ClasseAnotada nta = new ClasseAnotada();
        nta.setTexto1("CAFA       dfgdfdf");
        nta.setData(new Date());
        nta.setNumero(890);
        nta.setTexto2("GET");


        TratadorRemessa trt = new TratadorRemessa();

        try
        {
            System.out.println("'" + trt.obterString(nta) + "'");
        }
        catch (IllegalArgumentException | IllegalAccessException ex)
        {
            Logger.getLogger(TesteObjetoParaString.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
