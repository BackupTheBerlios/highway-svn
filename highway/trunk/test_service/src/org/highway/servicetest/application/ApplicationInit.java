package org.highway.servicetest.application;

import java.util.Properties;

import org.highway.debug.DebugHome;
import org.highway.debug.DebugLog;
import org.highway.debug.Log4jDebugLog;
import org.highway.helper.PropertiesHelper;
import org.highway.servicetest.access.AccessInit;
import org.highway.transaction.TransactionHelper;
import org.highway.transaction.TransactionHome;

public class ApplicationInit
{
	public static void init()
	{
		DebugHome.debugEnter();
		
		System.setProperty(DebugLog.ENABLE_SYSTEM_PROPERTY, "true");
		
		// DebugLog
		Properties properties = PropertiesHelper.loadAsResource(ApplicationInit.class, "log4j.properties");
		DebugHome.setDebugLog(new Log4jDebugLog(properties, false));

		// TransactionManager
		TransactionHome.setTransactionManager(TransactionHelper.lookupWebSphereTransactionManagerV6());
		
		// Access
		AccessInit.init();
	}
}
