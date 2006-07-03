/**
 * 
 */
package org.highway.vogen;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Requires the generation of an abstract class Xxxx from the XxxxDef interface. 
 * Useful for indicating the value object abstract character.
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface GenerateAbstract {
}
