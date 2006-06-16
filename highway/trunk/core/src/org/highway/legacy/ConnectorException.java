/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.legacy;

import javax.resource.ResourceException;

/**
 * Represents all the problems linked with the communication with Legacy System
 * (connection problem, resource Exception...). <br>
 * This exception can have an optional error code in the case it encapsulates
 * another exception like <code>javax.resource.ResourceException</code>.
 * 
 * @author Olivier Mallassi
 * @since 1.3
 * 
 */
public class ConnectorException extends LegacyException
{
    /**
     * An optional error code.
     */
    private String errorCode;

    /**
     * 
     * 
     */
    public ConnectorException()
    {
        super();
    }

    /**
     * 
     */
    public ConnectorException(String arg0, Throwable e)
    {
        super(arg0, e);
        //
        checkErrorCode(e);
    }

    /**
     * 
     */
    public ConnectorException(String arg0)
    {
        super(arg0);
    }

    /**
     * 
     */
    public ConnectorException(Throwable e)
    {
        super(e);
        //
        this.checkErrorCode(e);
    }

    /**
     * Returns the error code or <code>null</code> there is no error code.
     */
    public String getErrorCode()
    {
        return errorCode;
    }

    /**
     * 
     * @param e
     */
    private void checkErrorCode(Throwable e)
    {
        if (e instanceof ResourceException)
        {
            this.errorCode = ((ResourceException) e).getErrorCode();
        }
    }

}
