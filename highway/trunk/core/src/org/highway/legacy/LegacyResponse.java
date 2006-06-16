/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.legacy;

/**
 * Represents a reponse from a request execution. The response object is sent
 * back by the request execution:
 * 
 * <pre>
 *    ...
 *    LegacyResponse response = theRequest.execute();
 *    ...
 * </pre>
 * 
 * The response can be read using <code>read()</code> methods by two main
 * ways:
 * <ul>
 * <li>using a specified converter. you give the converter to use to the
 * method.
 * <li>using javadoc annotations. you give the java bean object and the
 * property name. All needed information will be retrieved from the javadoc
 * annotation. That can be the converter name or things like padding value,
 * length or padding strategy...
 * </ul>
 * 
 * @author Olivier Mallassi
 * @since 1.3
 * 
 */
public interface LegacyResponse
{
    /**
     * Reads the response and sets the javabean property.
     * 
     * @param bean
     *            the bean object
     * @param propertyName
     *            the property name
     */
    void read(Object bean, String propertyName);

    /**
     * Returns the object based upon the <code>converter</code>.
     * 
     */
    Object read(BasicConverter converter);

    /**
     * Returns <code>true</code> if there are still elements to read.
     * 
     */
    boolean hasMoreToRead();
}
