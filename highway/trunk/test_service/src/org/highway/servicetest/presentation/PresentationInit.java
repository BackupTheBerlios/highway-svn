package org.highway.servicetest.presentation;

import java.util.Properties;

import org.highway.debug.DebugHome;
import org.highway.debug.DebugLog;
import org.highway.debug.Log4jDebugLog;
import org.highway.helper.PropertiesHelper;
import org.highway.idgen.IdGenHome;
import org.highway.idgen.TimeBasedSimpleIdGenerator;

public class PresentationInit
{
	public static void init()
	{
		System.setProperty(DebugLog.ENABLE_SYSTEM_PROPERTY, "true");
		
		// DebugLog
		Properties properties = PropertiesHelper.loadAsResource(PresentationInit.class, "log4j.properties");
		DebugHome.setDebugLog(new Log4jDebugLog(properties, false));

		// SimpleIdGenerator
		IdGenHome.setSimpleIdGenerator(new TimeBasedSimpleIdGenerator());
	}
}
