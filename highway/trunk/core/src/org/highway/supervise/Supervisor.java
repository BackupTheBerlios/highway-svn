/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.supervise;

import java.util.Collection;

/**
 * Defines an interface to send messages to a supervision sub system. Developers
 * can choose the implementation that suits their needs. Use package home class
 * to set and get the default supervisor object.
 *
 * @author attias
 * @author Christian de Bevotte
 */
public interface Supervisor
{
	/**
	 * Logs a message. The user supplied parameter <tt>messageId</tt> is
	 * replaced by the message from the catalog.
	 *
	 * @param messageId the message identifier.
	 * @since 1.1
	 */
	void log(String messageId);

	/**
	 * Logs a message with additional debug information. The user supplied
	 * parameter <tt>messageId</tt> is replaced by the message from the
	 * catalog. Debug information is logged below the supervision message.
	 *
	 * @param messageId the message identifier.
	 * @param debugInfo debug information, may be null.
	 * @since 1.1
	 */
	void log(String messageId, String debugInfo);

	/**
	 * Logs a message with additional debug information and stack trace of the
	 * {@link Throwable} passed as parameter. The user supplied parameter
	 * <tt>messageId</tt> is replaced by the message from the catalog. Debug
	 * information and stack trace are logged below the supervision message.
	 *
	 * @param messageId the message identifier.
	 * @param debugInfo debug information, may be null.
	 * @param throwable a {@link Throwable}, may be null.
	 * @since 1.1
	 */
	void log(String messageId, String debugInfo, Throwable throwable);

	/**
	 * Logs a message. The message should exist in the catalog.
	 *
	 * @param message the message to log.
	 * @since 1.1
	 */
	void log(SupervisionMessage message);

	/**
	 * Logs a message with additional debug information. The message should
	 * exist in the catalog. Debug information is logged below the supervision
	 * message.
	 *
	 * @param message the message to log.
	 * @param debugInfo debug information, may be null.
	 * @since 1.1
	 */
	void log(SupervisionMessage message, String debugInfo);

	/**
	 * Logs a message with additional debug information and stack trace of the
	 * {@link Throwable} passed as parameter. The message should exist in the
	 * catalog. Debug information and stack trace are logged below the
	 * supervision message.
	 *
	 * @param message the message to log.
	 * @param debugInfo debug information, may be null.
	 * @param throwable a {@link Throwable}, may be null.
	 * @since 1.1
	 */
	void log(SupervisionMessage message, String debugInfo, Throwable throwable);

	/**
	 * Returns the messages catalog. Implementations may provide a constructor
	 * with a catalog parameter.
	 *
	 * @return a {@link Collection} of {@link SupervisionMessage} instances.
	 * @since 1.1
	 */
	Collection getCatalog();
}
