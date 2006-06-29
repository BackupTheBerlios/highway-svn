package org.highway.transaction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used in service interface to indicate the transaction
 * timeout. It is onlyt used if a transaction is started by the service. The
 * timeout is in milliseconds. Default value is 5 seconds<br>
 * <br>
 * Example:<br>
 * <br>
 * <tt>@TransactionTimeout(3000)
 * </tt>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( {ElementType.METHOD, ElementType.TYPE})
@Inherited
public @interface TransactionTimeout
{
	int value() default 5000;
}
