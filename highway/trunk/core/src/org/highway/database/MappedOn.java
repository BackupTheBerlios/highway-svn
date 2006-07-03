package org.highway.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * describes where the value object (table name) or its properties (column name)
 * which must be kept constantly in the base.
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Target( {ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Inherited
public @interface MappedOn
{
	String value();
}