package org.highway.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
public @interface TransactionOption {
	TransactionOptions value() default TransactionOptions.SUPPORTS;
	public static enum TransactionOptions {MANDATORY, NEVER, NOT_SUPPORTED, REQUIRED, REQUIRES_NEW, SUPPORTS};
}
