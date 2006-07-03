package org.highway.vogen;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Provides the name for the value object super-class for the type annotated.
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Superclass {
	Class value();
}
