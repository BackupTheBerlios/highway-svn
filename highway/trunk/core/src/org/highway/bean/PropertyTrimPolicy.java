package org.highway.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.highway.helper.StringHelper.TrimPolicy;
/**
 * Associates a TrimPolicy with the annotated property.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface PropertyTrimPolicy
{
	TrimPolicy value();
}
