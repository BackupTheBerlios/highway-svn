/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.exception;


/**
 * Formalise un probleme d'execution occasionnee par une erreur de codage
 * ou par une incoherence des donnees (de la base notamment).
 * @author attias
 */
public class BugException extends TechnicalException
{
	/**
	 * Constructor for BugException
	 * @param throwable Throwable
	 */
	public BugException(Throwable throwable)
	{
		super(throwable);
	}

	/**
	 * Constructor for BugException
	 * @param message String
	 */
	public BugException(String message)
	{
		super(message);
	}

	/**
	 * Constructor for BugException
	 * @param message String
	 * @param throwable Throwable
	 */
	public BugException(String message, Throwable throwable)
	{
		super(message, throwable);
	}
}
