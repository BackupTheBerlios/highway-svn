/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.jdbc;

import org.highway.exception.TechnicalException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * Do not use, still under construction.
 *
 * @author dattias
 */
public class DummyDataSource implements DataSource
{
	/**
	 * Field m_autoCommit
	 */
	private boolean m_autoCommit;

	/**
	 * Field m_config
	 */
	private DummyConfiguration m_config;

	/**
	 * Field m_logWriter
	 */
	private PrintWriter m_logWriter;

	/**
	 * Constructor for DummyDataSource
	 * @param config DummyConfiguration
	 * @param autoCommit boolean
	 */
	public DummyDataSource(DummyConfiguration config, boolean autoCommit)
	{
		m_config = config;
		m_logWriter = new PrintWriter(System.out);
		m_autoCommit = autoCommit;

		try
		{
			Class.forName(m_config.getDriver()).newInstance();
		}
		catch (Exception e)
		{
			throw new TechnicalException(e);
		}
	}

	/**
	 * Method getConnection
	 * @return Connection
	 * @throws SQLException
	 * @see javax.sql.DataSource#getConnection()
	 */
	public Connection getConnection() throws SQLException
	{
		return getConnection(m_config.getUserName(), m_config.getPassword());
	}

	/**
	 * Method getConnection
	 * @param userName String
	 * @param password String
	 * @return Connection
	 * @throws SQLException
	 * @see javax.sql.DataSource#getConnection(String, String)
	 */
	public Connection getConnection(String userName, String password)
		throws SQLException
	{
		Connection connection =
			DriverManager.getConnection(m_config.getUrl(), userName, password);
		connection.setAutoCommit(m_autoCommit);

		return connection;
	}

	/**
	 * Method getLoginTimeout
	 * @return int
	 * @throws SQLException
	 * @see javax.sql.DataSource#getLoginTimeout()
	 */
	public int getLoginTimeout() throws SQLException
	{
		return 0;
	}

	/**
	 * Method getLogWriter
	 * @return PrintWriter
	 * @throws SQLException
	 * @see javax.sql.DataSource#getLogWriter()
	 */
	public PrintWriter getLogWriter() throws SQLException
	{
		return m_logWriter;
	}

	/**
	 * Method setLoginTimeout
	 * @param seconds int
	 * @throws SQLException
	 * @see javax.sql.DataSource#setLoginTimeout(int)
	 */
	public void setLoginTimeout(int seconds) throws SQLException
	{
	}

	/**
	 * Method setLogWriter
	 * @param out PrintWriter
	 * @throws SQLException
	 * @see javax.sql.DataSource#setLogWriter(PrintWriter)
	 */
	public void setLogWriter(PrintWriter out) throws SQLException
	{
	}
}
