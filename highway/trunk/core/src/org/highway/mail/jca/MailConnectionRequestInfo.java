/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.mail.jca;

import java.util.Properties;
import javax.resource.spi.ConnectionRequestInfo;

/**
 * <p>Implementation of the <tt>ConnectionRequestInfo</tt> interface for the
 * Transactional Mail Resource Adapter.</p>
 *
 * <p>The <tt>ConnectionRequestInfo</tt> interface enables a resource adapter
 * to pass its own request specific data structure across the connection
 * request flow.</p>
 *
 * @author Christian de Bevotte
 * @since 1.1
 */
public class MailConnectionRequestInfo extends Properties
	implements ConnectionRequestInfo
{
	/**
	 * Default constructor.
	 *
	 * @param properties connection parameters.
	 */
	public MailConnectionRequestInfo(Properties properties)
	{
		putAll(properties);
	}
}
