package org.highway.database.hibernate;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.cfg.Configuration;
import org.highway.database.Database;
import org.highway.database.SelectQuery;
import org.highway.debug.DebugHome;
import org.highway.debug.Log4jDebugLog;
import org.highway.helper.Wrapper;




/**
 * @author David Attias
 */
public class SelectQueryTest extends TestCase
{
	private Database database;

	protected void dontSetUp() throws Exception
	{
		super.setUp();
		
		DebugHome.setDebugLog(new Log4jDebugLog());
		
		Configuration conf = new Configuration();
		conf.setProperty("hibernate.show_sql","true");
		conf.setProperty("hibernate.dialect","net.sf.hibernate.dialect.DB2Dialect");
		conf.setProperty("hibernate.default_schema","db2admin");
		conf.setProperty("hibernate.connection.driver_class","COM.ibm.db2.jdbc.app.DB2Driver");
		conf.setProperty("hibernate.connection.url","jdbc:db2:cgb");
		conf.setProperty("hibernate.connection.username","db2admin");
		conf.setProperty("hibernate.connection.password","db2admin");
		conf.setProperty("hibernate.connection.isolation","2");
		conf.configure("hibernate.cfg.xml");
		
		database = new HibernateDatabase(conf);
	}

	public void test()
	{
	}
	
	public void dontTest3()
	{
		SelectQuery query = database.openSession().createSelectQuery();
		query.addQueryText("from Firme firme where id = ?");
		query.setParameters(Wrapper.toList(new Integer(201)));
		query.setReturnIdOnly(false);
		for (Iterator iter = query.iterate(); iter.hasNext();)
		{
			DebugHome.getDebugLog().debugValue("firme", iter.next());
		}
	}

	private void dontTest2()
	{
		SelectQuery query = database.openSession().createSelectQuery();
		query.addQueryText("from Firme firme");
		query.setFetchMax(2);
		query.setFetchFirst(20);
		query.setCheckTooManyResults(false);
		query.setReturnIdOnly(true);
		for (Iterator iter = query.iterate(); iter.hasNext();)
		{
			DebugHome.getDebugLog().debugValue("firme", iter.next());
		}
	}

	private void dontTest1()
	{
		SelectQuery query = database.openSession().createSelectQuery();
		query.addQueryText("from Firme firme");
		query.setFetchMax(2);
		query.setFetchFirst(20);
		query.setCheckTooManyResults(false);
		query.setReturnIdOnly(true);
		List list = query.list();
		for (Iterator iter = list.iterator(); iter.hasNext();)
		{
			DebugHome.getDebugLog().debugValue("firme", iter.next());
		}
	}
}
