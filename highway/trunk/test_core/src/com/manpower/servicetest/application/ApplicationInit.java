package com.manpower.servicetest.application;

import java.util.Collections;
import java.util.Properties;

import com.manpower.servicetest.access.AccessInit;
import com.manpower.servicetest.access.candidat.Candidat;
import com.manpower.socle.debug.DebugHome;
import com.manpower.socle.debug.DebugLog;
import com.manpower.socle.debug.Log4jDebugLog;
import com.manpower.socle.eai.EAIHome;
import com.manpower.socle.eai.wbi.WBIEAIProvider;
import com.manpower.socle.helper.PropertiesHelper;
import com.manpower.socle.mail.MailHome;
import com.manpower.socle.mail.MailService;
import com.manpower.socle.mail.XAMailService;
import com.manpower.socle.transaction.TransactionHelper;
import com.manpower.socle.transaction.TransactionHome;

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
		
		// MailService
		properties = new Properties();
		properties.setProperty(XAMailService.CONNECTION_FACTORY, "jca/testMailAdapter");
		properties.setProperty(XAMailService.SESSION, "mail/testMailSession");
		MailService mailService = new XAMailService(properties);
		MailHome.setMailService(mailService);

		// Access
		AccessInit.init();
		
		// EAI
		WBIEAIProvider wbiEaiProvider = new WBIEAIProvider(Collections.singletonList(Candidat.class));
		EAIHome.setEAIProvider(wbiEaiProvider);
	}
}
