/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.supervise;

import org.apache.log4j.helpers.DateLayout;
import org.apache.log4j.spi.LoggingEvent;
import java.io.IOException;
import java.io.Writer;

/**
 * {@link org.apache.log4j.Layout} implementation used by
 * {@link Log4jSupervisor}.
 *
 * @author Christian de Bevotte
 * @since 1.1
 */
public class Log4jSupervisionLayout extends DateLayout
{
	/**
	 * Separator (value is {@value #FIELD_SEP}).
	 */
	public static final char FIELD_SEP = ';';

	/**
	 * @value "yyyy/MM/dd;HH:mm:ss:SSS"
	 */
	private static final String DATE_FORMAT =
		"yyyy/MM/dd" + FIELD_SEP + "HH:mm:ss:SSS";

	/**
	 * @value #INITIAL_BUFFER_LENGTH
	 */
	private static final int INITIAL_BUFFER_LENGTH = 250;

	/**
	 * Default constructor.
	 */
	public Log4jSupervisionLayout()
	{
		setDateFormat(DATE_FORMAT);
	}

	/**
	 * @see org.apache.log4j.Layout#format(org.apache.log4j.spi.LoggingEvent)
	 */
	public String format(LoggingEvent event)
	{
		StringBuffer buffer = new StringBuffer(INITIAL_BUFFER_LENGTH);

		dateFormat(buffer, event);
		buffer.delete(DATE_FORMAT.length() - 1, DATE_FORMAT.length() + 1);

		buffer.append(FIELD_SEP);
		buffer.append(event.getRenderedMessage());
		buffer.append(LINE_SEP);

		return buffer.toString();
	}

	/**
	 * @see org.apache.log4j.Layout#format(java.io.Writer, org.apache.log4j.spi.LoggingEvent)
	 */
	public void format(Writer out, LoggingEvent event)
		throws IOException
	{
		out.write(format(event));
	}

	/**
	 * This method is required since we moved from log4j 1.3 alpha to 1.2.11.
	 * @since 1.2
	 */
	public boolean ignoresThrowable()
	{
		return false;
	}
}
