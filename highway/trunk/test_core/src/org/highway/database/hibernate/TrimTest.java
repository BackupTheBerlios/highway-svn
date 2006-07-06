package org.highway.database.hibernate;

import java.sql.SQLException;

import junit.framework.TestCase;

import org.hibernate.cfg.Configuration;
import org.highway.database.Database;
import org.highway.database.DatabaseSession;
import org.highway.debug.DebugHome;
import org.highway.debug.DebugLog;
import org.highway.debug.Log4jDebugLog;
import org.highway.exception.TechnicalException;



/**
 * Unit tests for
 * {@link org.highway.database.hibernate.TrimmedStringHibernateType} and
 * {@link org.highway.database.hibernate.TrimInterceptor}.
 */
public class TrimTest extends TestCase
{
	/**
	 * The Hibernate configuration.
	 */
	private Configuration configuration;

	protected void setUp() throws Exception
	{
		// DebugLog
		System.setProperty(DebugLog.ENABLE_SYSTEM_PROPERTY, "true");
		DebugHome.setDebugLog(new Log4jDebugLog());
		
		// Hibernate configuration
		configuration = new Configuration();
		configuration.setProperty("hibernate.show_sql",                "true");
		configuration.setProperty("hibernate.dialect",                 "net.sf.hibernate.dialect.DB2Dialect");
		configuration.setProperty("hibernate.default_schema",          "db2admin");
		configuration.setProperty("hibernate.connection.driver_class", "com.ibm.db2.jcc.DB2Driver");
		configuration.setProperty("hibernate.connection.url",          "jdbc:db2://w0005354:50000/test");
		configuration.setProperty("hibernate.connection.username",     "db2admin");
		configuration.setProperty("hibernate.connection.password",     "db2admin");
		configuration.setProperty("hibernate.connection.isolation",    "2");
		configuration.configure("/hibernate.cfg.xml");
	}
	
	public void test1()
	{
		// Trim should not occur
		configuration.setInterceptor(new TrimInterceptor(false, false));
		
		insert("   toto  titi   ");
		
		TrimTestVo vo = select();
		
		assertEquals(vo.getProperty1(), "   toto  titi       ");
		assertEquals(vo.getProperty2(), "   toto  titi");
		assertEquals(vo.getProperty3(), "   toto  titi       ");
		
		// Trim should occur on load
		configuration.setInterceptor(new TrimInterceptor(true, false));
		
		vo = select();
		
		assertEquals(vo.getProperty1(), "   toto  titi       ");
		assertEquals(vo.getProperty2(), "   toto  titi");
		assertEquals(vo.getProperty3(), "toto  titi");
	}
	
	public void test2()
	{
		// Trim should occur on save
		configuration.setInterceptor(new TrimInterceptor(false, true));
		
		insert("   toto  titi   ");
		
		TrimTestVo vo = select();
		
		assertEquals(vo.getProperty1(), "   toto  titi       ");
		assertEquals(vo.getProperty2(), "   toto  titi");
		assertEquals(vo.getProperty3(), "toto  titi          ");
		
		// Trim should occur on load
		configuration.setInterceptor(new TrimInterceptor(true, false));
		
		vo = select();
		
		assertEquals(vo.getProperty1(), "   toto  titi       ");
		assertEquals(vo.getProperty2(), "   toto  titi");
		assertEquals(vo.getProperty3(), "toto  titi");
	}
	
	/**
	 * Deletes all TrimTestVo from database, then inserts a new TrimTestVo with
	 * id set to 1L and other properties set to the given value.
	 */
	private void insert(String value)
	{
		Database database = new HibernateDatabase(configuration);
		DatabaseSession session = database.openSession();
		
		session.delete("from TrimTestVo");
		
		TrimTestVo vo = new TrimTestVo();
		vo.setId(1L);
		vo.setProperty1(value);
		vo.setProperty2(value);
		vo.setProperty3(value);
		
		session.insert(vo);
		
		try
		{
			session.getConnection().commit();
		}
		catch (SQLException exc)
		{
			throw new TechnicalException(exc);
		}
	}
	
	/**
	 * Returns the TrimTestVo read from database with 1L as id.
	 */
	private TrimTestVo select()
	{
		Database database = new HibernateDatabase(configuration);
		DatabaseSession session = database.openSession();
		
		TrimTestVo result = (TrimTestVo) session.select(TrimTestVo.class, new Long(1L));
		
		try
		{
			session.getConnection().commit();
		}
		catch (SQLException exc)
		{
			throw new TechnicalException(exc);
		}
		
		return result;
	}
}
