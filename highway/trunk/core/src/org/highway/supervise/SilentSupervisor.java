/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.supervise;

import java.util.Collection;
import java.util.Collections;

/**
 * This Supervisor implementation is silent. It does nothing.
 *
 * @author attias
 * @author Christian de Bevotte
 */
public class SilentSupervisor implements Supervisor
{
	public void log(String messageId)
	{
	}

	public void log(String messageId, String debugInfo)
	{
	}

	public void log(String messageId, String debugInfo, Throwable throwable)
	{
	}

	public void log(SupervisionMessage message)
	{
	}

	public void log(SupervisionMessage message, String debugInfo)
	{
	}

	public void log(
		SupervisionMessage message, String debugInfo, Throwable throwable)
	{
	}

	public Collection getCatalog()
	{
		return Collections.EMPTY_LIST;
	}
}
