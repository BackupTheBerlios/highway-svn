package com.manpower.servicetest.presentation;

import java.util.Properties;

import com.manpower.socle.debug.DebugHome;
import com.manpower.socle.debug.DebugLog;
import com.manpower.socle.debug.Log4jDebugLog;
import com.manpower.socle.helper.PropertiesHelper;
import com.manpower.socle.idgen.IdGenHome;
import com.manpower.socle.idgen.TimeBasedSimpleIdGenerator;

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
