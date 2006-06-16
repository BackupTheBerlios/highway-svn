/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.jdbc;


/**
 * Do not use, still under construction.
 *
 * @author attias
 */
public class DummyConfiguration
{
	/**
	 * Field driver
	 */
	private String driver;

	/**
	 * Field url
	 */
	private String url;

	/**
	 * Field userName
	 */
	private String userName;

	/**
	 * Field password
	 */
	private String password;

	/**
	 * Constructor for DummyConfiguration
	 * @param driver String
	 * @param url String
	 * @param userName String
	 * @param password String
	 */
	public DummyConfiguration(
		String driver, String url, String userName, String password)
	{
		this.driver = driver;
		this.url = url;
		this.userName = userName;
		this.password = password;
	}

	/**
	 * Returns the driver.
	 * @return String
	 */
	public String getDriver()
	{
		return driver;
	}

	/**
	 * Returns the login.
	 * @return String
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * Returns the password.
	 * @return String
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * Returns the url.
	 * @return String
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 * Sets the driver.
	 * @param driver The driver to set
	 */
	public void setDriver(String driver)
	{
		this.driver = driver;
	}

	/**
	 * Sets the login.
	 * @param userName The login to set
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * Sets the password.
	 * @param password The password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * Sets the url.
	 * @param url The url to set
	 */
	public void setUrl(String url)
	{
		this.url = url;
	}
}
