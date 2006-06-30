/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.transaction;

import org.highway.exception.TechnicalException;
import javax.transaction.TransactionManager;

/**
 * 
 */
public class TransactionHelper
{

	/**
	 * Method lookupWebSphereTransactionManagerV6
	 * @return TransactionManager
	 * 
	 */
	public static TransactionManager lookupWebSphereTransactionManagerV6()
	{
		return lookupWebSphereTransactionManager(
			"com.ibm.ws.Transaction.TransactionManagerFactory");
	}
	
	/**
	 * Method lookupWebSphereTransactionManagerV5
	 * @return TransactionManager
	 */
	public static TransactionManager lookupWebSphereTransactionManagerV5()
	{
		return lookupWebSphereTransactionManager(
			"com.ibm.ejs.jts.jta.TransactionManagerFactory");
	}

	/**
	 * Method lookupWebSphereTransactionManagerV51
	 * @return TransactionManager
	 */
	public static TransactionManager lookupWebSphereTransactionManagerV51()
	{
		return lookupWebSphereTransactionManager(
			"com.ibm.ws.Transaction.TransactionManagerFactory");
	}

	/**
	 * Method lookupWebSphereTransactionManagerV4
	 * @return TransactionManager
	 */
	public static TransactionManager lookupWebSphereTransactionManagerV4()
	{
		return lookupWebSphereTransactionManager("com.ibm.ejs.jts.jta.JTSXA");
	}

	/**
	 * Method lookupJOTMTransactionManager
	 * @return TransactionManager
	 */
	public static TransactionManager lookupJOTMTransactionManager()
	{
		try
		{
			Class clazz = Class.forName("org.objectweb.jotm.Current");
			Object object = clazz.newInstance();

			return (TransactionManager) clazz.getMethod(
				"getTransactionManager", null).invoke(object, null);
		}
		catch (Exception exc)
		{
			throw new TechnicalException(exc);
		}
	}

	/**
	 * Method lookupWebSphereTransactionManager
	 * @param factoryClassName String
	 * @return TransactionManager
	 */
	private static TransactionManager lookupWebSphereTransactionManager(
		String factoryClassName)
	{
		try
		{
			Class clazz = Class.forName(factoryClassName);

			return (TransactionManager) clazz.getMethod(
				"getTransactionManager", null).invoke(null, null);
		}
		catch (Exception exc)
		{
			throw new TechnicalException(exc);
		}
	}
}
