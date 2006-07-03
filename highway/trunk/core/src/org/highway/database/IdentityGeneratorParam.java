package org.highway.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * enables you to specify the configuration or initialization parameters for the ID generator when this has them;
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Target( {ElementType.METHOD, ElementType.FIELD})
@Inherited
public @interface IdentityGeneratorParam
{
	String name();

	String value();
}
