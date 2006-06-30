/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.idgen;

import org.highway.exception.TechnicalException;
import org.highway.transaction.TransactionHome;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import javax.transaction.Transaction;

/**
 * <p> Database implementation of {@link IdPageSupplier} to provide {@link IdPage}s
 * for the {@link IdPageBasedGenerator} implementation of a {@link SimpleIdGenerator}.</p>
 * <p> This class uses a database repository to manage several generators.<br>
 * Based on a table of counters, it can be use either in a J2EE or in a non-J2EE environment.<br>
 * The choice between the J2EE and the non J2EE mode is made by providing respectively
 * the datasource or the plain JDBC related properties.</p>
 * <p> The generated {@link IdPage}s are guaranteed to be unique for a given counter.</p>
 * <H3>Common properties</H3>
 * <H4>Required common properties</H4>
 * Used to describe the table hosting the counters, its name and columns.
 * <ul>
 * <li>highway.dbidsupplier.table.name</li>
 * <li>highway.dbidsupplier.table.owner</li>
 * <li>highway.dbidsupplier.counter.name.column</li>
 * <li>highway.dbidsupplier.counter.value.column</li>
 * </ul>
 * <H4>Optional common properties</H4>
 * This default counter name will be used if no other name is speficied when requesting an IdPage
 * <ul>
 * <li>highway.dbidsupplier.default.counter.name</li>
 * </ul>
 * <H3>J2EE mode specific properties</H3>
 * <H4>Required J2EE mode properties</H4>
 * The DataSource to be used to access the table of counters.
 * <ul>
 * <li>highway.dbidsupplier.datasource.name</li>
 * </ul>
 * <H4>Optional J2EE mode properties</H4>
 * The optional username/password to use with the DataSource, if not managed by the container.
 * <ul>
 * <li>highway.dbidsupplier.datasource.user.name</li>
 * <li>highway.dbidsupplier.datasource.password</li>
 * </ul>
 * <H3>Non-J2EE mode specific properties</H3>
 * <H4>Required Non-J2EE mode properties</H4>
 * JDBC driver, url, username/password to be used to access the table of counters.
 * <ul>
 * <li>highway.dbidsupplier.jdbc.driver.class</li>
 * <li>highway.dbidsupplier.jdbc.url</li>
 * <li>highway.dbidsupplier.jdbc.user.name</li>
 * <li>highway.dbidsupplier.jdbc.password</li>
 * </ul>
 * <H3>J2EE Example using the default counter</h3>
 * <pre>
 * Properties properties = new Properties();
 * properties.setProperty("highway.dbidsupplier.table.name","IDGEN");
 * properties.setProperty("highway.dbidsupplier.table.owner","DB2ADMIN");
 * properties.setProperty("highway.dbidsupplier.counter.name.column","CNAME");
 * properties.setProperty("highway.dbidsupplier.counter.value.column","CVALUE");
 * properties.setProperty("highway.dbidsupplier.default.counter.name", "DEFAULT");
 * properties.setProperty("highway.dbidsupplier.datasource.name","jdbc/DemoDS");
 * properties.setProperty("highway.dbidsupplier.datasource.user.name","DB2ADMIN");
 * properties.setProperty("highway.dbidsupplier.datasource.password","DB2ADMIN");
 * IdGenHome.setSimpleIdGenerator(new IdPageBasedGenerator(10, new DBIdPageSupplier(properties)));
 * Long idTest = IdGenHome.getNextSimpleId();
 * </pre>
 * <H3>Non-J2EE Example using a specific counter</h3>
 * <pre>
 * Properties properties = new Properties();
 * properties.setProperty("highway.dbidsupplier.table.name","IDGEN");
 * properties.setProperty("highway.dbidsupplier.table.owner","DB2ADMIN");
 * properties.setProperty("highway.dbidsupplier.counter.name.column","CNAME");
 * properties.setProperty("highway.dbidsupplier.counter.value.column","CVALUE");
 * properties.setProperty("highway.dbidsupplier.jdbc.driver.class","COM.ibm.db2.jdbc.app.DB2Driver");
 * properties.setProperty("highway.dbidsupplier.jdbc.url","jdbc:db2:SOCLDEMO");
 * properties.setProperty("highway.dbidsupplier.jdbc.user.name","db2admin");
 * properties.setProperty("highway.dbidsupplier.jdbc.password","db2admin");
 * IdGenHome.setSimpleIdGenerator(new IdPageBasedGenerator(10, new DBIdPageSupplier(properties)));
 * Long idTest = IdGenHome.getNextSimpleId("test");
 * </pre>
 *
 * 
 * 
 *
 */
public class DBIdPageSupplier implements IdPageSupplier
{
	/**
	 * Constant for the table name property.<br>
	 * (value is "<tt>highway.dbidsupplier.table.name</tt>")
	 *
	 * @value "<tt>highway.dbidsupplier.table.name</tt>"
	 * 
	 */
	public static final String TABLE_NAME_PROPERTY =
		"highway.dbidsupplier.table.name";

	/**
	 * Constant for the table owner property.<br>
	 * (value is "<tt>highway.dbidsupplier.table.owner</tt>")
	 *
	 * @value "<tt>highway.dbidsupplier.table.owner</tt>"
	 * 
	 */
	public static final String TABLE_OWNER_PROPERTY =
		"highway.dbidsupplier.table.owner";

	/**
	 * Constant for the counter name column property.<br>
	 * (value is "<tt>highway.dbidsupplier.counter.name.column</tt>")
	 *
	 * @value "<tt>highway.dbidsupplier.counter.name.column</tt>"
	 * 
	 */
	public static final String COUNTER_NAME_COLUMN_PROPERTY =
		"highway.dbidsupplier.counter.name.column";

	/**
	 * Constant for the counter value column property.<br>
	 * (value is "<tt>highway.dbidsupplier.counter.value.column</tt>")
	 *
	 * @value "<tt>highway.dbidsupplier.counter.value.column</tt>"
	 * 
	 */
	public static final String COUNTER_VALUE_COLUMN_PROPERTY =
		"highway.dbidsupplier.counter.value.column";

	/**
	 * Constant for the default counter name property.<br>
	 * (value is "<tt>highway.dbidsupplier.default.counter.name</tt>")
	 *
	 * @value "<tt>highway.dbidsupplier.default.counter.name</tt>"
	 * 
	 */
	public static final String DEFAULT_COUNTER_NAME_PROPERTY =
		"highway.dbidsupplier.default.counter.name";

	/**
	 * In J2EE mode, constant for the datasource name property.<br>
	 * (value is "<tt>highway.dbidsupplier.datasource.name</tt>")
	 *
	 * @value "<tt>highway.dbidsupplier.datasource.name</tt>"
	 * 
	 */
	public static final String DATASOURCE_NAME_PROPERTY =
		"highway.dbidsupplier.datasource.name";

	/**
	 * In J2EE mode, constant for the optional datasource username property.<br>
	 * (value is "<tt>highway.dbidsupplier.datasource.user.name</tt>")
	 *
	 * @value "<tt>highway.dbidsupplier.datasource.user.name</tt>"
	 * 
	 */
	public static final String DATASOURCE_USER_NAME_PROPERTY =
		"highway.dbidsupplier.datasource.user.name";

	/**
	 * In J2EE mode, constant for the optional datasource password property. Required if username supplied.<br>
	 * (value is "<tt>highway.dbidsupplier.datasource.password</tt>")
	 *
	 * @value "<tt>highway.dbidsupplier.datasource.password</tt>"
	 * 
	 */
	public static final String DATASOURCE_PASSWORD_PROPERTY =
		"highway.dbidsupplier.datasource.password";

	/**
	 * In non-J2EE mode, constant for the jdbc dirver class property.<br>
	 * (value is "<tt>highway.dbidsupplier.jdbc.driver.class</tt>")
	 *
	 * @value "<tt>highway.dbidsupplier.jdbc.driver.class</tt>"
	 * 
	 */
	public static final String JDBC_DRIVER_CLASS_PROPERTY =
		"highway.dbidsupplier.jdbc.driver.class";

	/**
	 * In non-J2EE mode, constant for the JDBC url property.<br>
	 * (value is "<tt>highway.dbidsupplier.jdbc.url</tt>")
	 *
	 * @value "<tt>highway.dbidsupplier.jdbc.url</tt>"
	 * 
	 */
	public static final String JDBC_URL_PROPERTY =
		"highway.dbidsupplier.jdbc.url";

	/**
	 * In non-J2EE mode, constant for the jdbc username property.<br>
	 * (value is "<tt>highway.dbidsupplier.jdbc.user.name</tt>")
	 *
	 * @value "<tt>highway.dbidsupplier.jdbc.user.name</tt>"
	 * 
	 */
	public static final String JDBC_USER_NAME_PROPERTY =
		"highway.dbidsupplier.jdbc.user.name";

	/**
	 * In non-J2EE mode, constant for the table name property.<br>
	 * (value is "<tt>highway.dbidsupplier.jdbc.password</tt>")
	 *
	 * @value "<tt>highway.dbidsupplier.jdbc.password</tt>"
	 * 
	 */
	public static final String JDBC_PASSWORD_PROPERTY =
		"highway.dbidsupplier.jdbc.password";
	String tableName;
	String tableOwner;
	String counterNameColumn;
	String counterValueColumn;
	String defaultCounterName;
	String dataSourceName;
	String dataSourceUserName;
	String dataSourcePassword;
	String jdbcUrl;
	String jdbcDriverClass;
	String jdbcUserName;
	String jdbcPassword;
	javax.sql.DataSource ds;

	/**
	 * Constructs a <tt>DBIdPageSupplier</tt> with the specified properties.
	 *
	 * @param properties the {@link Properties} to configure this
	 *        <tt>DBIdPageSupplier</tt>.
	 * 
	 */
	public DBIdPageSupplier(Properties properties)
	{
		tableName = properties.getProperty(TABLE_NAME_PROPERTY);
		tableOwner = properties.getProperty(TABLE_OWNER_PROPERTY);
		counterNameColumn =
			properties.getProperty(COUNTER_NAME_COLUMN_PROPERTY);
		counterValueColumn =
			properties.getProperty(COUNTER_VALUE_COLUMN_PROPERTY);
		defaultCounterName =
			properties.getProperty(DEFAULT_COUNTER_NAME_PROPERTY);
		dataSourceName = properties.getProperty(DATASOURCE_NAME_PROPERTY);
		dataSourceUserName =
			properties.getProperty(DATASOURCE_USER_NAME_PROPERTY);
		dataSourcePassword =
			properties.getProperty(DATASOURCE_PASSWORD_PROPERTY);
		jdbcUrl = properties.getProperty(JDBC_URL_PROPERTY);
		jdbcDriverClass = properties.getProperty(JDBC_DRIVER_CLASS_PROPERTY);
		jdbcUserName = properties.getProperty(JDBC_USER_NAME_PROPERTY);
		jdbcPassword = properties.getProperty(JDBC_PASSWORD_PROPERTY);

		if ((dataSourceName == null) || dataSourceName.equals(""))
		{
			if ((jdbcUrl == null) || jdbcUrl.equals(""))
			{
				throw new TechnicalException(
					"Neither J2EE nor non-J2EE properties have been specified");
			}
			else
			{
				try
				{
					Class.forName(jdbcDriverClass);
				}
				catch (Exception e)
				{
					throw new TechnicalException(
						"Error looking for jdbc driver :\n", e);
				}
			}
		}
		else
		{
			try
			{
				javax.naming.Context jndictx =
					new javax.naming.InitialContext();
				ds = (javax.sql.DataSource) jndictx.lookup(dataSourceName);
			}
			catch (Exception e)
			{
				throw new TechnicalException(
					"Error looking for datasource :\n", e);
			}
		}
	}

	/**
	 * Returns the next available unique IdPage, given its length and counter.
	 *
	 * @param length the desired {@link IdPage} length
	 * @param counterName the counter to use
	 * @return a new IdPage
	 * @throws TechnicalException if a technical problem occured.
	 */
	public IdPage getNextPage(int length, String counterName)
	{
		String cName = (counterName == null) ? defaultCounterName : counterName;

		if (isJ2EEMode())
		{
			return getNextPageJ2EE(length, cName);
		}
		else
		{
			return getNextPageNonJ2EE(length, cName);
		}
	}

	private IdPage getNextPage(int length, String counterName, Connection con)
	{
		PreparedStatement pst = null;
		java.sql.ResultSet rs = null;
		long inf = 0;
		long sup = 0;

		try
		{
			pst = con.prepareStatement(
					"select " + counterValueColumn + " from " + tableOwner
					+ "." + tableName + " where " + counterNameColumn
					+ " = ? for update");
			pst.setString(1, counterName);
			rs = pst.executeQuery();

			if (rs.next())
			{
				inf = rs.getLong(1);
				sup = (inf + length) - 1;
				pst = con.prepareStatement(
						" update " + tableOwner + "." + tableName + " set "
						+ counterValueColumn + " = ? where "
						+ counterNameColumn + " = ?");
				pst.setLong(1, (sup + 1));
				pst.setString(2, counterName);
				pst.executeUpdate();
			}
			else
			{
				throw new TechnicalException(
					"No valid counter has been specified");
			}
		}
		catch (Exception e)
		{
			throw new TechnicalException("Error using connection", e);
		}

		IdPage pageRetour = new IdPage(inf, sup);

		return pageRetour;
	}

	private Connection getJ2EEConnection()
	{
		Connection con = null;

		try
		{
			if ((dataSourceUserName == null) || dataSourceUserName.equals(""))
			{
				con = ds.getConnection();
			}
			else
			{
				con = ds.getConnection(dataSourceUserName, dataSourcePassword);
			}
		}
		catch (Exception e)
		{
			throw new TechnicalException(
				"Error obtaining datasource connection", e);
		}

		return con;
	}

	private Connection getNonJ2EEConnection()
	{
		Connection con = null;

		try
		{
			con = DriverManager.getConnection(
					jdbcUrl, jdbcUserName, jdbcPassword);
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
		}
		catch (Exception e)
		{
			throw new TechnicalException(
				"Error establishing database connection", e);
		}

		return con;
	}

	private IdPage getNextPageJ2EE(int length, String name)
	{
		Transaction trx = null;

		try
		{
			trx = TransactionHome.suspend();
			TransactionHome.begin();

			Connection con = null;

			try
			{
				con = getJ2EEConnection();

				return getNextPage(length, name, con);
			}
			catch (Exception exc)
			{
				TransactionHome.setRollbackOnly();
				throw exc;
			}
			finally
			{
				TransactionHome.commit();

				if (con != null)
				{
					con.close();
				}
			}
		}
		catch (Exception exc)
		{
			throw new TechnicalException(exc);
		}
		finally
		{
			if (trx != null)
			{
				try
				{
					TransactionHome.resume(trx);
				}
				catch (Exception exc)
				{
					throw new TechnicalException(
						"Error handling transaction", exc);
				}
			}
		}
	}

	private IdPage getNextPageNonJ2EE(int length, String name)
	{
		Connection con = getNonJ2EEConnection();

		try
		{
			IdPage pageRetour = getNextPage(length, name, con);
			con.commit();

			return pageRetour;
		}
		catch (Exception e)
		{
			try
			{
				con.rollback();
			}
			catch (SQLException e1)
			{
				throw new TechnicalException("Error getting next idPage", e1);
			}

			throw new TechnicalException("Error getting next idPage", e);
		}
		finally
		{
			closeConnection(con);
		}
	}

	private void closeConnection(Connection con)
	{
		try
		{
			con.close();
		}
		catch (Exception e)
		{
			throw new TechnicalException("Error closing connection", e);
		}
	}

	private boolean isJ2EEMode()
	{
		return (ds != null);
	}
}
