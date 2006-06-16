/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.supervise;


/**
 * This interface enables an application to provide the application specific
 * keys for a supervision message.
 *
 * @author Christian de Bevotte
 * @since 1.1
 * @see Log4jSupervisor
 */
public interface AppSpecificKeyProvider
{
	/**
	 * Returns the application specific keys for the specified supervision
	 * message. The returned array should contain all the defined keys, even
	 * if they are already set in the message passed as parameter.
	 *
	 * @return application specific keys, may be <tt>null</tt>.
	 */
	String[] getKeys(SupervisionMessage message);
}
