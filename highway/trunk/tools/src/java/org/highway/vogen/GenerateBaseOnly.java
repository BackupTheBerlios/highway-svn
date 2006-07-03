package org.highway.vogen;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Requires the generation of an abstract class XxxxBase from the XxxxDef interface instead of class Xxxx. 
 * It is up to you to supply the class Xxxx that must extend XxxxBase. 
 * Useful for adding specific code to your value object. 
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface GenerateBaseOnly {
}
