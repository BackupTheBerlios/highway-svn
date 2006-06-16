/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.supervise;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple implementation of {@link SupervisionMessage}.
 *
 * @author Christian de Bevotte
 * @since 1.1
 */
public final class SimpleSupervisionMessage implements SupervisionMessage
{
	/**
	 * All messages are stored in this map.
	 */
	private static Map messages = new HashMap();
	private String messageId;
	private Severity severity;
	private String[] keys;
	private String info;

	/**
	 * Returns the message identified by the specified <tt>messageId</tt>.
	 *
	 * @param messageId the identifier of message to find.
	 * @return the message identified by <tt>messageId</tt>.
	 */
	public static SupervisionMessage getMessage(String messageId)
	{
		return (SupervisionMessage) messages.get(messageId);
	}

	/**
	 * Returns the {@link Collection} of registered messages.
	 *
	 * @return the {@link Collection} of registered messages.
	 */
	public static Collection getAll()
	{
		return Collections.unmodifiableCollection(messages.values());
	}

	/**
	 * Default constructor.
	 * The new <tt>SimpleSupervisionMessage</tt> instance is registered, and
	 * may be retrieved later through the {@link #getMessage(String)} method.
	 * {@link #getAll()} returns the {@link Collection} of registered
	 * messages.
	 *
	 * @throws IllegalArgumentException if any of these conditions is encountered :
	 * <ul>
	 * <li>messageId is <tt>null</tt></li>
	 * <li>severity is <tt>null</tt></li>
	 * <li>the same <tt>messageId</tt> is registered twice</li>
	 * </ul>
	 */
	public SimpleSupervisionMessage(
		String messageId, Severity severity, String[] keys, String info)
	{
		if (messageId == null)
		{
			throw new IllegalArgumentException("messageId is null");
		}

		if (severity == null)
		{
			throw new IllegalArgumentException("severity is null");
		}

		if (messages.containsKey(messageId))
		{
			throw new IllegalArgumentException(
				"Already used messageId : " + messageId);
		}

		this.messageId = messageId;
		this.severity = severity;
		this.keys = keys;
		this.info = info;

		messages.put(messageId, this);
	}

	public String getMessageId()
	{
		return messageId;
	}

	public Severity getSeverity()
	{
		return severity;
	}

	public String[] getKeys()
	{
		return keys;
	}

	public String getInfo()
	{
		return info;
	}

	/**
	 * Compares this message with the specified object.
	 *
	 * @param object the reference object with which to compare.
	 * @return <tt>true</tt> if this object is the same as the <tt>object</tt>
	 *         argument, <tt>false</tt> otherwise.
	 */
	public boolean equals(Object object)
	{
		return object instanceof SimpleSupervisionMessage
		&& messageId.equals(((SupervisionMessage) object).getMessageId());
	}

	/**
	 * Returns a hash code value for this message.
	 *
	 * @return the hash code of <tt>messageId</tt>.
	 */
	public int hashCode()
	{
		return messageId.hashCode();
	}
}
