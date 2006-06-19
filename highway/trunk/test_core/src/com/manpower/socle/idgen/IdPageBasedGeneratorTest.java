package com.manpower.socle.idgen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

import junit.framework.TestCase;

import org.highway.exception.TechnicalException;
import org.highway.idgen.DBIdPageSupplier;
import org.highway.idgen.IdGenHome;
import org.highway.idgen.IdPageBasedGenerator;
import org.highway.idgen.IdPageSupplier;

public class IdPageBasedGeneratorTest extends TestCase
{
	public void testEmpty()
	{
		
	}
	
	public void donottestDBIdPageSupplier() throws Exception
	{
		Properties properties = new Properties();
		properties.setProperty(DBIdPageSupplier.TABLE_NAME_PROPERTY, "IDGEN");
		properties.setProperty(DBIdPageSupplier.TABLE_OWNER_PROPERTY, "DB2ADMIN");
		properties.setProperty(DBIdPageSupplier.COUNTER_NAME_COLUMN_PROPERTY, "CNAME");
		properties.setProperty(DBIdPageSupplier.COUNTER_VALUE_COLUMN_PROPERTY, "CVALUE");
		properties.setProperty(DBIdPageSupplier.DEFAULT_COUNTER_NAME_PROPERTY, "DEFAULT");
		properties.setProperty(DBIdPageSupplier.JDBC_DRIVER_CLASS_PROPERTY, "COM.ibm.db2.jdbc.app.DB2Driver");
		properties.setProperty(DBIdPageSupplier.JDBC_URL_PROPERTY, "jdbc:db2:test");
		properties.setProperty(DBIdPageSupplier.JDBC_USER_NAME_PROPERTY, "db2admin");
		properties.setProperty(DBIdPageSupplier.JDBC_PASSWORD_PROPERTY, "db2admin");
		
		Connection connection = DriverManager.getConnection("jdbc:db2:test", "db2admin", "db2admin");
		Statement statement = connection.createStatement();
		
		statement.executeUpdate(
				// "insert into db2admin.IDGEN values ('DEFAULT', 1)"
				"update db2admin.IDGEN set CVALUE = 1 where CNAME = 'DEFAULT'");
				
		connection.close();
		
		IdPageSupplier idPageSupplier = new DBIdPageSupplier(properties);
		IdGenHome.setSimpleIdGenerator(new IdPageBasedGenerator(10, idPageSupplier));
		
		assertTrue(IdGenHome.getNextSimpleId() == 1);
		
		idPageSupplier.getNextPage(10, null);
		
		for (int i = 2; i < 11; i++)
		{
			assertTrue(IdGenHome.getNextSimpleId() == i);
		}
		
		assertTrue(IdGenHome.getNextSimpleId() == 21);
		
		try
		{
			IdGenHome.getNextSimpleId("INVALID");
			// works fine only if it throws an exception
			fail();
		}
		catch (TechnicalException e)
		{
		}
	}
	
	private static final String MY_COUNTER = "mycounter";
	
	public void donottestPageManagement()
	{
		IdGenHome.setSimpleIdGenerator(
			new IdPageBasedGenerator(10, new IdPageSupplierMock()));
		
		assertEquals(IdGenHome.getNextSimpleId(), 0);
		assertEquals(IdGenHome.getNextSimpleId(MY_COUNTER), 0);
		
		for (int i = 0; i < 23; i++)
		{
			assertEquals(IdGenHome.getNextSimpleId(),
					IdGenHome.getNextSimpleId(null) - 1);
			
			assertEquals(IdGenHome.getNextSimpleId(MY_COUNTER),
					IdGenHome.getNextSimpleId(MY_COUNTER) - 1);
		}
		
		assertEquals(IdGenHome.getNextSimpleId(), 47);
		assertEquals(IdGenHome.getNextSimpleId(MY_COUNTER), 47);
	}
}
