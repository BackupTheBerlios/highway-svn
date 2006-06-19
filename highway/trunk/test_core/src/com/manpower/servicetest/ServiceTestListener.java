package com.manpower.servicetest;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.manpower.servicetest.application.ApplicationInit;

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
