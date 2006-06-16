/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.legacy;

import org.highway.exception.TechnicalException;

/**
 * Parent class of all exceptions that can occur during an interaction with a
 * Legacy System.
 * 
 * @author Olivier Mallassi
 * @since 1.3
 * 
 */
public class LegacyException extends TechnicalException
{
    /**
     * 
     * 
     */
    public LegacyException()
    {
        super();
    }

    /**
     * 
     * @param arg0
     */
    public LegacyException(String arg0)
    {
        super(arg0);
    }

    /**
     * 
     * @param arg0
     */
    public LegacyException(Throwable arg0)
    {
        super(arg0);
    }

    /**
     * 
     * @param arg0
     * @param arg1
     */
    public LegacyException(String arg0, Throwable arg1)
    {
        super(arg0, arg1);
    }
}
