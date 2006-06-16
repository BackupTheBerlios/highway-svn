/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.supervise;


/**
 * Defines a supervision message that may be logged through a
 * {@link Supervisor} instance.
 *
 * @author Christian de Bevotte
 * @since 1.1
 */
public interface SupervisionMessage
{
	/**
	 * Returns the message identifier.
	 *
	 * @return the message identifier.
	 */
	String getMessageId();

	/**
	 * Returns the message severity.
	 *
	 * @return a {@link Severity} instance.
	 */
	Severity getSeverity();

	/**
	 * Returns the (optional) application specific keys.
	 * There may be up to 10 keys.
	 *
	 * @return the application specific keys, may be null.
	 */
	String[] getKeys();

	/**
	 * Returns the message information field.
	 * @return the message information field.
	 */
	String getInfo();
}
