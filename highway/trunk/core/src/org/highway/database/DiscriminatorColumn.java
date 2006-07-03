package org.highway.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines the discriminator column.
 * A discriminator column can also define the discriminator type
 * @DiscriminatorColumn should only be defined at the top of the entity hierarchy.
 * force and insert properties can also be set.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
@Inherited
public @interface DiscriminatorColumn
{
	String type() default "string";

	String column() default "class";

	boolean force() default false;

	boolean insert() default true;
}
