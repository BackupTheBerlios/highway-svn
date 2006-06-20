package org.highway.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author N. Lainé
 * 
 * Indicates the interceptors associated to the annotated type.
 * Interceptor will be reference with the full path class name.
 * example : 
 * 	@Interceptors("org.highway.transaction.TransactionInterceptor") 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Interceptors {
	String[] value();
}
