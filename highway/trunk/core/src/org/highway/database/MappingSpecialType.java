package org.highway.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * overrides the default hibernate type used.
 * This is generally not necessary since the type is correctly inferred by Hibernate
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Target( {ElementType.METHOD, ElementType.FIELD})
@Inherited
public @interface MappingSpecialType
{
	Class value();
}
