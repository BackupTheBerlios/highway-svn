package org.highway.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates how the identifier is generated
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Target( {ElementType.METHOD, ElementType.FIELD})
@Inherited
public @interface IdentityGenerator
{
	String value();
}
