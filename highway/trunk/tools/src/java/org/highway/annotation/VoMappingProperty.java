package org.highway.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
@Inherited
public @interface VoMappingProperty {
	String column();
	String type();
	boolean update() default true;
	boolean insert() default true;
}
