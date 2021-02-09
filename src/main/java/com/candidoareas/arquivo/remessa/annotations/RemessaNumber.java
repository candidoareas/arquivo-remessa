/*
 * Código desenvolvido por candido.areas
 * Para maiores informações, candido.areas@gmail.com
 */
/*
 * Desenvolvido por Cândido Areas
 * http://www.candidoareas.com
 *
 * Todos os direitos reservados
 */
package com.candidoareas.arquivo.remessa.annotations;

import com.candidoareas.arquivo.remessa.annotations.enums.RemessaComma;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Annotation que representa um tipo de dado "número"
 *
 * @author   candido.areas
 * @version  1.0
 * @since    1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RemessaNumber
{
    int length();

    int position();

    RemessaComma applyComma() default RemessaComma.WITHOUT_COMMA;

    String commaType() default ";";
}
