/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.supervise;

import org.apache.log4j.or.ObjectRenderer;

/**
 * {@link ObjectRenderer} implementation used by {@link Log4jSupervisor}.
 *
 * @author Christian de Bevotte
 * @since 1.1
 */
public class Log4jSupervisionRenderer implements ObjectRenderer
{
	/**
	 * @value 6
	 */
	private static final int MESSAGE_ID_LENGTH = 6;

	/**
	 * @value '#'
	 */
	private static final char INFO_PREFIX = '#';

	/**
	 * @value 250
	 */
	private static final int INITIAL_BUFFER_LENGTH = 250;
	
	static String hostname;
	
	static String applicationCode;
	
	static String applicationName;

	/**
	 * Renders the object passed as parameter as a <tt>String</tt>.
	 *
	 * @see ObjectRenderer#doRender(java.lang.Object)
	 */
	public String doRender(Object object)
	{
		if (object instanceof SupervisionMessage)
		{
			SupervisionMessage message = (SupervisionMessage) object;
			StringBuffer buffer = new StringBuffer(INITIAL_BUFFER_LENGTH);
			buffer.append(applicationCode);

			String messageId = message.getMessageId();

			for (int i = 0; i < (MESSAGE_ID_LENGTH - messageId.length());
					i++)
			{
				buffer.append('0');
			}

			buffer.append(messageId);

			Severity severity = message.getSeverity();
			buffer.append(severity.getCode());
			buffer.append(Log4jSupervisionLayout.FIELD_SEP);
			buffer.append(hostname);
			buffer.append(Log4jSupervisionLayout.FIELD_SEP);
			buffer.append(applicationName);

			String[] keys = message.getKeys();

			if (keys != null)
			{
				for (int i = 0; i < keys.length; i++)
				{
					buffer.append(Log4jSupervisionLayout.FIELD_SEP);
					buffer.append((keys[i] == null) ? "" : keys[i]);
				}
			}

			buffer.append(Log4jSupervisionLayout.FIELD_SEP);
			buffer.append(INFO_PREFIX);

			String info = message.getInfo();

			if (info != null)
			{
				buffer.append(info.trim());
			}

			return buffer.toString();
		}
		else
		{
			return object.toString();
		}
	}
}
