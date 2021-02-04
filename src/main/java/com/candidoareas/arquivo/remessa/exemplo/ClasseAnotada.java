/*
 * Desenvolvido por Cândido Areas
 * http://www.candidoareas.com
 *
 * Todos os direitos reservados
 */
package com.candidoareas.arquivo.remessa.exemplo;


import com.candidoareas.arquivo.remessa.annotations.RemessaDate;
import com.candidoareas.arquivo.remessa.annotations.RemessaNumber;
import com.candidoareas.arquivo.remessa.annotations.RemessaText;

import java.util.Date;


/**
 * Classe que contém as anotações desenvolvidas.
 *
 * @author   candido.areas
 * @version  1.0
 * @since    1.0
 */
public class ClasseAnotada
{

    /** DOCUMENT ME! */
    @RemessaDate(
        pattern = "ddMMyyyy",
        position = 4
    )
    private Date data;

    /** DOCUMENT ME! */
    @RemessaNumber(
        length = 10,
        position = 2
    )
    private int numero;

    /** DOCUMENT ME! */
    @RemessaText(
        length = 2,
        position = 1
    )
    private String texto1;

    /** DOCUMENT ME! */
    @RemessaText(
        length = 5,
        position = 3
    )
    private String texto2;

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public Date getData()
    {
        return data;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public int getNumero()
    {
        return numero;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public String getTexto1()
    {
        return texto1;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public String getTexto2()
    {
        return texto2;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  data  DOCUMENT ME!
     */
    public void setData(Date data)
    {
        this.data = data;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  numero  DOCUMENT ME!
     */
    public void setNumero(int numero)
    {
        this.numero = numero;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  texto1  DOCUMENT ME!
     */
    public void setTexto1(String texto1)
    {
        this.texto1 = texto1;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  texto2  DOCUMENT ME!
     */
    public void setTexto2(String texto2)
    {
        this.texto2 = texto2;
    }


}
