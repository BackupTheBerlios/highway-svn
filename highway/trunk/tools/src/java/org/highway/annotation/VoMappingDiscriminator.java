package org.highway.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
@Inherited
public @interface VoMappingDiscriminator {
	String type() default "string";
	String column() default "class";
	boolean force() default false;
	boolean insert() default true;
}