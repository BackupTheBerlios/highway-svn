package org.highway.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * describes the foreign key column name
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
@Inherited
public @interface MappingKey
{
	String value();
}
