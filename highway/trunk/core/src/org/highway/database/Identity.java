package org.highway.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Must be associated with all the properties forming the value object primary code in the base.
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Target( {ElementType.METHOD, ElementType.FIELD})
@Inherited
public @interface Identity
{
}
