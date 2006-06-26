package org.highway.servicetest;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.highway.servicetest.application.ApplicationInit;

public class ServiceTestListener implements ServletContextListener
{
	public void contextInitialized(ServletContextEvent event)
	{
		ApplicationInit.init();
	}
	
	public void contextDestroyed(ServletContextEvent event)
	{
	}
}
