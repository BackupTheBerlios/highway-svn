package org.highway.vogen;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * associates a serial version UID to the annotated type.
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface SerialVersionUID {
	long value();
}
