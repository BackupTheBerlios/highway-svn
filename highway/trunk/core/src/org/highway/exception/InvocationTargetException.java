package org.highway.exception;

/**
 * Use this technical exception when you would like to use a regular
 * <tt>java.lang.reflect.InvocationTargetException</tt> but not checked. Usefull for
 * exemple to wrap a checked exception thrown in the run of a Runnable.
 * 
 * @author David Attias
 * @since 1.4.6
 */
public class InvocationTargetException extends TechnicalException
{
	public InvocationTargetException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public InvocationTargetException(Throwable cause)
	{
		super(cause);
	}
}
