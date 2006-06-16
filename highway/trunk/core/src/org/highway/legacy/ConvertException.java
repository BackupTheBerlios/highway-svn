/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.legacy;

/**
 * This exception represents all the problem linked to a conversion problem.
 * 
 * @author Olivier Mallassi
 * @since 1.3
 * 
 */
public class ConvertException extends LegacyException
{
    /**
     * @see org.highway.legacy.LegacyException
     * 
     */
    public ConvertException()
    {
        super();
    }

    /**
     * @see org.highway.legacy.LegacyException
     * 
     */
    public ConvertException(Throwable arg0)
    {
        super(arg0);
    }

    /**
     * @see org.highway.legacy.LegacyException
     * 
     */
    public ConvertException(String arg0)
    {
        super(arg0);
    }

    /**
     * @see org.highway.legacy.LegacyException
     * 
     */
    public ConvertException(String arg0, Throwable arg1)
    {
        super(arg0, arg1);
    }
}
