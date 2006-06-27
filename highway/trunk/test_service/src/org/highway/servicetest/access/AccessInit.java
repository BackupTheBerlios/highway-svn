package org.highway.servicetest.access;

import java.util.Properties;

import net.sf.hibernate.cfg.Configuration;

import org.highway.database.Database;
import org.highway.database.DatabaseAccessBase;
import org.highway.database.hibernate.HibernateDatabase;
import org.highway.debug.DebugHome;
import org.highway.helper.PropertiesHelper;
import org.highway.helper.ResourceHelper;
import org.highway.legacy.ConverterMap;
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
			configuration.configure("/" + ResourceHelper.getResourcePath(AccessInit.class, "hibernate.cfg.xml"));
			
			// For EAI
			configuration.addClass(org.highway.eai.wbi.database.Event.class);

			Database database = new HibernateDatabase(configuration);
			DatabaseAccessBase.setDatabase(database);
		}
		catch (Throwable throwable)
		{
			throw new InitException("Failed to configure Hibernate", throwable);
		}

		// Legacy access
		try
		{
			ConverterMap converterMap = new ConverterMap1();
			
			Properties properties = new Properties();
			properties.setProperty(CtgServer.SOCLE_CTG_JNDI, "java:comp/env/jca/eciResourceAdapter");
//			properties.setProperty(CtgServer.SOCLE_CTG_JNDI, "jca/eciResourceAdapter");
//			properties.setProperty(CtgServer.SOCLE_CTG_CONNECTIONURL, "tcp://10.0.32.96");
//			properties.setProperty(CtgServer.SOCLE_CTG_PORTNUMBER, "11521");
//			properties.setProperty(CtgServer.SOCLE_CTG_SERVERNAME, "CID6");
			CtgServer server = new CtgServer(properties, converterMap);
			
			LegacyAccessImplAbstract.setLegacyServer(server);
			LegacyAccessImplAbstract.setConverterMap(converterMap);
		}
		catch (Throwable throwable)
		{
			DebugHome.error("Failed to configure CTG", throwable);
			throw new InitException("Failed to configure CTG", throwable);
		}
	}
}
