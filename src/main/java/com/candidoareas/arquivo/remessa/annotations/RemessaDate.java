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
 * Annotation que representa um tipo de dado "data"
 *
 * @author   candido.areas
 * @version  1.0
 * @since    1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RemessaDate
{
    String pattern();

    int position();
    
    RemessaComma applyComma() default RemessaComma.WITHOUT_COMMA;
    
    String commaType() default ";";
}
