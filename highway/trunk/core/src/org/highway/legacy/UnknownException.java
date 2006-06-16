/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.legacy;

/**
 * Encapsulates all the exceptions that cannot be encapsulated in another sub
 * classes of <code>LegacyException</code>.
 * 
 * @author Olivier Mallassi
 * @since 1.3
 * 
 */
public class UnknownException extends LegacyException
{
    /**
     * 
     */
    public UnknownException(String arg0, Throwable arg1)
    {
        super(arg0, arg1);
    }

    /**
     * 
     */
    public UnknownException(Throwable arg0)
    {
        super(arg0);
    }
}
