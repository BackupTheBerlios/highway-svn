/*
 * Copyright (c) 2005. All rights reserved.
 */

/*
 * Created on 29 sept. 2004
 */
package org.highway.service.ejb;

import org.highway.exception.TechnicalException;
import org.highway.helper.ClassHelper;
import org.highway.service.Service;
import org.highway.service.ServiceLocator;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJBHome;
import javax.ejb.EJBObject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * EJB implementation of ServiceLocator interface.
 *
 * 
 */
public class EjbLocator implements ServiceLocator
{
	/**
	 * Field EJBHOME_JNDI_CONTEXT_NAME<br>
	 * (value is ""ejbhome"")
	 */
	private static final String EJBHOME_JNDI_CONTEXT_NAME = "ejbhome";

	/**
	 * Field EJB_PROXY_SUFFIX<br>
	 * (value is ""EjbProxy"")
	 */
	private static final String EJB_PROXY_SUFFIX = "EjbProxy";

	/**
	 * Field EJB_SUB_PACKAGE_NAME<br>
	 * (value is ""ejb"")
	 */
	private static final String EJB_SUB_PACKAGE_NAME = "ejb";

	/**
	 * Field context
	 */
	private Context context;

	/**
	 * Field proxyMap
	 */
	private HashMap proxyMap = new HashMap();

	/**
	 * Field interceptors
	 */
	private List interceptors;

	/**
	 * Constructs an EjbLocator.
	 */
	public EjbLocator()
	{
		this(Collections.EMPTY_LIST, null);
	}

	/**
	 * Constructs an EjbLocator with the specified JNDI properties.
	 *
	 * @param jndiProperties JNDI properties
	 */
	public EjbLocator(Properties jndiProperties)
	{
		this(Collections.EMPTY_LIST, jndiProperties);
	}

	/**
	 * Constructs an EjbLocator with the specified client side interceptors.
	 *
	 * @param interceptors client side interceptors
	 */
	public EjbLocator(List interceptors)
	{
		this(interceptors, null);
	}

	/**
	 * Constructs an EjbLocator with the specified client side interceptors
	 * and JNDI properties.
	 *
	 * @param interceptors client side interceptors
	 * @param jndiProperties JNDI properties
	 */
	public EjbLocator(List interceptors, Properties jndiProperties)
	{
		this.interceptors = interceptors;

		try
		{
			if (jndiProperties == null)
			{
				context = new InitialContext();
			}
			else
			{
				context = new InitialContext(jndiProperties);
			}
		}
		catch (NamingException exc)
		{
			throw new TechnicalException(
				"Failed to create initial context", exc);
		}
	}

	public synchronized Service getService(Class serviceClass)
	{
		if (! EjbService.class.isAssignableFrom(serviceClass))
		{
			throw new TechnicalException(
				EjbLocator.class
				+ " only provide access to EJB services: class " + serviceClass
				+ " does not extend interface " + EjbService.class);
		}

		EjbProxy proxy = (EjbProxy) proxyMap.get(serviceClass);

		if (proxy == null)
		{
			proxy = createEjbProxy(serviceClass);
			proxyMap.put(serviceClass, proxy);
		}

		return (EjbService) proxy;
	}

	/**
	 * Method createEjbProxy
	 * @param serviceClass Class
	 * @return EjbProxy
	 */
	private EjbProxy createEjbProxy(Class serviceClass)
	{
		try
		{
			// get home
			EJBHome home = getEjbHome(serviceClass);

			// create remote
			Method method = home.getClass().getMethod("create", null);
			EJBObject remote = (EJBObject) method.invoke(home, null);

			// create proxy
			String ejbProxyClassName = getEjbProxyClassName(serviceClass);
			EjbProxy proxy =
				(EjbProxy) ClassHelper.newInstance(ejbProxyClassName);

			// test proxy
			Class proxyClass = proxy.getClass();

			if (! EjbProxy.class.isAssignableFrom(proxyClass))
			{
				throw new TechnicalException(
					"Invalid EJB proxy class: " + proxyClass
					+ " does not extend " + EjbProxy.class);
			}

			if (! serviceClass.isAssignableFrom(proxyClass))
			{
				throw new TechnicalException(
					"Invalid EJB proxy class: " + proxyClass
					+ " does not implement " + serviceClass);
			}

			// set proxy properties
			proxy.setInterceptors(this.interceptors);
			proxy.setRemote(remote);

			return proxy;
		}
		catch (Exception e)
		{
			throw new TechnicalException(
				"Failed to create EJB proxy for service " + serviceClass, e);
		}
	}

	/**
	 * Method getEjbProxyClassName
	 * @param serviceClass Class
	 * @return String
	 */
	private String getEjbProxyClassName(Class serviceClass)
	{
		// max fully qualified ejb class name size ? let's use 100
		StringBuffer buffer = new StringBuffer(100);
		buffer.append(serviceClass.getPackage().getName());
		buffer.append('.').append(EJB_SUB_PACKAGE_NAME).append('.');
		buffer.append(ClassHelper.getClassName(serviceClass, false));

		return buffer.append(EJB_PROXY_SUFFIX).toString();
	}

	/**
	 * Method getEjbHome
	 * @param serviceClass Class
	 * @return EJBHome
	 */
	private EJBHome getEjbHome(Class serviceClass)
	{
		StringBuffer buffer =
			new StringBuffer(serviceClass.getName().length() + 8);
		buffer.append(EJBHOME_JNDI_CONTEXT_NAME).append('/');

		String homeJndiName = buffer.append(serviceClass.getName()).toString();

		try
		{
			return (EJBHome) context.lookup(homeJndiName);
		}
		catch (Exception exc)
		{
			throw new TechnicalException(
				"Home JNDI lookup failed, JNDI name = " + homeJndiName, exc);
		}
	}
}
