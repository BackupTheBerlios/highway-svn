package org.highway.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used in service interfaces to assign service interceptors.<br>
 * <br>
 * The service interceptor framework looks for this annotation in the service
 * interface and all its super interfaces. The first annotation found is used.
 * The value of the annotation is a list of classes.<br>
 * <br>
 * Example:<br>
 * <br>
 * <tt>@Interceptors([org.highway.debug.DebugInterceptor.class, org.highway.transaction.TransactionInterceptor.class])
 * </tt>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface ServiceInterceptors
{
	Class[] value();
}
