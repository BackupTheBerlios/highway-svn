package org.highway.servicetest.application;

import java.util.Collections;
import java.util.Properties;

import org.highway.servicetest.access.AccessInit;
import org.highway.servicetest.access.candidat.Candidat;
import org.highway.debug.DebugHome;
import org.highway.debug.DebugLog;
import org.highway.debug.Log4jDebugLog;
import org.highway.eai.EAIHome;
import org.highway.eai.wbi.WBIEAIProvider;
import org.highway.helper.PropertiesHelper;
import org.highway.mail.MailHome;
import org.highway.mail.MailService;
import org.highway.mail.XAMailService;
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
