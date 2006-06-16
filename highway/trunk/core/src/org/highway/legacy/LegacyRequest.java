/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.legacy;

/**
 * Creates a commarea and to call a mainframe transaction with this commarea.
 * The way to retrieve a request is:
 * 
 * <pre>
 *                   ...
 *                   LegacyRequest request = serverInstance.newRequest();
 *                   ...
 * </pre>
 * 
 * Different values must be setted on <code>request</code> object like
 * transaction name... <br>
 * <br>
 * Values can be added to the request using the <code>write()</code> methods.
 * The two main ways of using the <code>write()</code> methods are:
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
public interface LegacyRequest
{
    /**
     * Returns the owner session.
     */
    LegacySession getSession();

    /**
     * Sets the transaction name.
     */
    void setTranName(String tranName);

    /**
     * Returns the transaction name or <code>null</code> if none has been
     * setted.
     */
    String getTranName();

    /**
     * Sets the TPNName (also known as transaction mirror).
     */
    void setTPNName(String name);

    /**
     * Returns the TPNName or <code>null</code>if none has been setted.
     */
    String getTPNName();

    /**
     * Sets the function name that must be called on the legacy server.
     */
    void setFunctionName(String functionName);

    /**
     * Returns the function name or <code>null</code> if none has been setted.
     */
    String getFunctionName();

    /**
     * Sets the type of interaction. Can have the constant value defined on the
     * interface <code>javax.resource.cci.InteractionSpec</code>.<br>
     * <b>Only supports synchronous interaction</b>
     */
    void setInteractionVerb(int interactionVerb);

    /**
     * Returns the type of interaction or <code>null</code>if none has been
     * setted.
     */
    int getInteractionVerb();

    /**
     * Writes the property value.
     */
    int write(Object bean, String propertyName);

    /**
     * Writes the <code>value</code> using the <code>converter</code>.
     * 
     * @param value
     *            the value to write
     * @param converter
     *            the converter to use
     */
    int write(Object value, BasicConverter converter);

    /**
     * Writes a byte array.
     * 
     * @param subMessages
     *            the byte array
     */
    void write(byte[] subMessages);

    /**
     * Writes the specified <code>value</code> using the
     * <code>BasicConverter</code> setted for <code>value</code> java type.
     * 
     * @param value
     *            the value to write
     */
    void write(Object value);

    /**
     * Sets the expected response length.
     */
    void setCommareaLength(int length);

    /**
     * Calls the CICS server with the encapsulated message and return a
     * <code>LegacyResponse</code>
     * 
     */
    LegacyResponse execute();

    /**
     * Calls the CICS server but in an asynchronous way.
     * 
     * @param asynchronousResponse
     *            the expected response.
     */
    void execute(LegacyResponseCallback asynchronousResponse);

    /**
     * Cleans only the message (input and output). So the datas that have been
     * added using the methods write/read. Details like
     * <code>functionName</code> are not erased.
     * 
     */
    void reset();

    /**
     * Returns the length of the request being constructed.
     * 
     */
    int getRequestLength();
}
