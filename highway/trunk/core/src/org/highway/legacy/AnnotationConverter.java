/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.legacy;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Interface for converter based on javadoc annotation. Classes implementing
 * this interface will read or write a JavaBean property value from or to a
 * stream. <br>
 * All information needed - like length, padding strategy... - will be read from
 * javadoc tag defined on the value Object. <br>
 * All javadoc tags used and defined on the value object must be defined as a
 * <code>public static</code> constant in the specific converter. In other
 * words, each implementation of this interface must defined the necessary
 * javadoc tags as constants (see implementation for details).
 * 
 * <br>
 * <br>
 * <b>Caution:</b><br>
 * All implementations must be <b>stateless</b> and <b>thread safe</b>.
 * 
 * @see BasicConverter
 * 
 * @author Olivier Mallassi
 * @since 1.3
 * 
 */
public interface AnnotationConverter
{
    /**
     * Converts a part of a message to an <code>java.lang.Object</code>.
     * 
     * @param propertyValue
     *            the value
     * @param propertyType
     *            the type of property
     * @param InputStream
     *            the stream to read from
     * 
     * @return the object instanciate with the propertyValue
     * @throws ConvertException
     *             if pb occurs during convertion
     * 
     */
    Object read(InputStream stream, Object bean, String propertyName) throws ConvertException;

    /**
     * Writes the javabean propertyValue to the stream.
     * 
     * @param ownerBeanClass
     *            the class of the property owner bean
     * @param propertyName
     *            the property name
     * @param propertyValue
     *            the property value
     * @return the padded String
     * @throws ConvertException
     *             if pb occurs during convertion
     * 
     * 
     */
    int write(OutputStream outputStream, Object bean, String propertyName) throws ConvertException;
}
