package com.dexia.sofaxis.referentieltiers.application;

import org.hibernate.cfg.Configuration;
import org.highway.database.Database;
import org.highway.database.DatabaseAccessBase;
import org.highway.database.hibernate.HibernateDatabase;
import org.highway.database.hibernate.SimpleHibernateTransactionManager;
import org.highway.debug.DebugHome;
import org.highway.debug.DebugLog;
import org.highway.debug.Log4jDebugLog;
import org.highway.helper.PropertiesHelper;
import org.highway.helper.ResourceHelper;
import org.highway.lifecycle.InitException;
import org.highway.transaction.TransactionHome;

public class AccessInit
{
	public static void init()
	{
		DebugHome.debugEnter();
		
		// Database access
		try
		{
			Configuration configuration = new Configuration();
			configuration.setProperties(PropertiesHelper.loadAsResource(AccessInit.class, "hibernate.properties"));
//			configuration.configure("/" + ResourceHelper.getResourcePath(AccessInit.class, "hibernate.cfg.xml"));
			configuration.configure("/" + ResourceHelper.getResourcePath(null, "hibernate.cfg.xml"));
			Database database = new HibernateDatabase(configuration);
			DatabaseAccessBase.setDatabase(database);
			System.setProperty(DebugLog.ENABLE_SYSTEM_PROPERTY, "true");
			DebugHome.debugEnter();
			DebugHome.setDebugLog(new Log4jDebugLog(false));
			TransactionHome.setTransactionManager(new SimpleHibernateTransactionManager());
		}
		catch (Throwable throwable)
		{
			throw new InitException("Failed to configure Hibernate", throwable);
		}
	}
}
