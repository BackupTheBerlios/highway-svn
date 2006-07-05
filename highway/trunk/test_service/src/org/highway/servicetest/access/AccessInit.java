package org.highway.servicetest.access;



import org.hibernate.cfg.Configuration;
import org.highway.database.Database;
import org.highway.database.DatabaseAccessBase;
import org.highway.database.hibernate.HibernateDatabase;
import org.highway.debug.DebugHome;
import org.highway.helper.PropertiesHelper;
import org.highway.helper.ResourceHelper;
import org.highway.lifecycle.InitException;

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
		}
		catch (Throwable throwable)
		{
			throw new InitException("Failed to configure Hibernate", throwable);
		}
	}
}
