package org.highway.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
@Inherited
public @interface VoMappingId {
	String column();
	String type();
	// TODO : normalement il ne devrait pas etre utile de definir le type. 
	// en le recuperant par le return type de la methode annotée
	String generatorClass() default "assigned";
}
