/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.exception;


/**
 * Use this exception in classes where instances are useless.<br>
 * Example of classes of this type are helper and home classes.<br>
 * They only contain static methods and instances are useless.<br>
 * <br>
 * To disable construction of instances, add a private default (no
 * arguments) constructor and throw this exception in case construction
 * is done by reflection :<br>
 * <pre>
 *     private DebugHome()
 *     {
 *         throw new DoNoInstantiateException();
 *     }
 * </pre>
 *
 * @author attias
 */
public final class DoNotInstantiateException extends TechnicalException
{
	/**
	 * Constructs a <code>DoNotInstantiateException</code>.
	 */
	public DoNotInstantiateException()
	{
		super("Do not instantiate this class");
	}
}
