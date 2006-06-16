/*
 * Copyright (c) 2005. All rights reserved.
 */

/*
 * Created on 28 oct. 2004
 */
package org.highway.init;


/**
 * Do not use, still under construction.<br>
 * <br>
 * Subclasses will contain init code for the web app.<br>
 * Must extend some Websphere API class.
 *
 * @author attias
 */
public abstract class WebAppInit
{
	/**
	 * Method startup
	 */
	public abstract void startup();

	/**
	 * Method shutdown
	 */
	public abstract void shutdown();
}
