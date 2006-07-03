package org.highway.service.ejb;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that an EJB must be generated with the annotated type.
 * You can also indicate to generate Zip Ejb with the boolean value 'useCompression'.
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface GenerateEjb
{
	boolean useCompression() default false;
}