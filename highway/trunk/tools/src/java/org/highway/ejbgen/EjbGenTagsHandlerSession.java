/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.ejbgen;

import org.highway.ejbgen.EjbGenConstants;
import xdoclet.DocletSupport;
import xdoclet.XDocletException;
import xjavadoc.XClass;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author GRIZARD
 */
public class EjbGenTagsHandlerSession
	extends xdoclet.modules.ejb.session.SessionTagsHandler
{
	/**
	 * Returns true if clazz is a session bean, false otherwise.
	 *
	 * @param clazz  Description of Parameter
	 * @return       The Session value
	 */
	public static boolean isSession(XClass clazz)
	{
		return clazz.isA(EjbGenConstants.EJB_SERVICE_INTERFACE)
		&& (clazz.getDoc().getTag(EjbGenConstants.EJB_SERVICE_TAG) != null || clazz.getDoc().getTag(EjbGenConstants.ZIP_EJB_SERVICE_TAG) != null);
	}

	/**
	 * Evaluates the body block for each class considered as a session bean.
	 * Redefined to call the "isSession" method from this class
	 *
	 */
	public void forAllSessionBeans(String template) throws XDocletException
	{
		Collection classes = getXJavaDoc().getSourceClasses();

		for (Iterator i = classes.iterator(); i.hasNext();)
		{
			XClass clazz = (XClass) i.next();

			setCurrentClass(clazz);

			if (DocletSupport.isDocletGenerated(getCurrentClass()))
			{
				continue;
			}

			if (isSession(getCurrentClass()))
			{
				generate(template);
			}
		}
	}

	/**
	 * Calculates the interface full class name
	 */
	public String interfaceFullClassName() throws XDocletException
	{
		XClass clazz = getCurrentClass();

		return clazz.getContainingPackage() + "." + clazz.getName();
	}

	/**
	 * Calculates the Session Bean full class name
	 */
	public String beanFullClassName() throws XDocletException
	{
		XClass clazz = getCurrentClass();

		return clazz.getContainingPackage() + "."
		+ EjbGenConstants.EJB_PACKAGE_NAME + "." + clazz.getName()
		+ EjbGenConstants.BEAN_SUFFIX;
	}

	/**
	 * Calculates the Session Bean remote home interface full class name
	 */
	public String homeFullClassName() throws XDocletException
	{
		XClass clazz = getCurrentClass();

		return clazz.getContainingPackage() + "."
		+ EjbGenConstants.EJB_PACKAGE_NAME + "." + clazz.getName()
		+ EjbGenConstants.HOME_SUFFIX;
	}

	/**
	 * Calculates the name of the Session Bean remote component interface full class name
	 */
	public String remoteFullClassName() throws XDocletException
	{
		XClass clazz = getCurrentClass();

		return clazz.getContainingPackage() + "."
		+ EjbGenConstants.EJB_PACKAGE_NAME + "." + clazz.getName()
		+ EjbGenConstants.REMOTE_SUFFIX;
	}

	/**
	 * Calculates the JNDI name of the Session Bean remote home interface
	 */
	public String homeJNDIName() throws XDocletException
	{
		return EjbGenConstants.EJBHOME_JNDI_CONTEXT_NAME + "/"
		+ interfaceFullClassName();
	}
}
