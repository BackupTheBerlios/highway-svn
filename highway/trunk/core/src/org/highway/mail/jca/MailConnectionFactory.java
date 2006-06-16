/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.mail.jca;

import java.io.Serializable;
import java.util.Properties;
import javax.resource.Referenceable;

/**
 * <p>Provides an interface for getting connection to a Transactional Mail
 * Resource Adapter instance.</p>
 *
 * <p>Application code looks up a <tt>MailConnectionFactory</tt> instance from
 * JNDI namespace.</p>
 *
 * <p>An implementation class for <tt>MailConnectionFactory</tt> is required to
 * implement <tt>java.io.Serializable</tt> and
 * <tt>javax.resource.Referenceable</tt> interfaces to support JNDI
 * registration.</p>
 *
 * @author Christian de Bevotte
 * @since 1.1
 */
public interface MailConnectionFactory extends Serializable, Referenceable
{
	/**
	 * Gets a connection to a Transactional Mail Resource Adapter instance.
	 *
	 * @param properties connection parameters.
	 */
	MailConnection getConnection(Properties properties);
}
