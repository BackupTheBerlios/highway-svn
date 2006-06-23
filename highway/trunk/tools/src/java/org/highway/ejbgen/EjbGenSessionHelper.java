/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.ejbgen;

import com.sun.mirror.declaration.InterfaceDeclaration;


public class EjbGenSessionHelper{
	/**
	 * Calculates the interface full class name
	 */
	public static String interfaceFullClassName(InterfaceDeclaration declaration)
	{
		return declaration.getQualifiedName();
	}

	/**
	 * Calculates the Session Bean full class name
	 */
	public static String beanFullClassName(InterfaceDeclaration declaration)
	{
		return declaration.getPackage().getQualifiedName() + "."
		+ EjbGenConstants.EJB_PACKAGE_NAME + "." + declaration.getSimpleName()
		+ EjbGenConstants.BEAN_SUFFIX;
	}

	/**
	 * Calculates the Session Bean remote home interface full class name
	 */
	public static String homeFullClassName(InterfaceDeclaration declaration)
	{
		return declaration.getPackage().getQualifiedName() + "."
		+ EjbGenConstants.EJB_PACKAGE_NAME + "." + declaration.getSimpleName()
		+ EjbGenConstants.HOME_SUFFIX;
	}

	/**
	 * Calculates the name of the Session Bean remote component interface full class name
	 */
	public static String remoteFullClassName(InterfaceDeclaration declaration)
	{
		return declaration.getPackage().getQualifiedName() + "."
		+ EjbGenConstants.EJB_PACKAGE_NAME + "." + declaration.getSimpleName()
		+ EjbGenConstants.REMOTE_SUFFIX;
	}

	/**
	 * Calculates the JNDI name of the Session Bean remote home interface
	 */
	public static String homeJNDIName(InterfaceDeclaration declaration)
	{
		return EjbGenConstants.EJBHOME_JNDI_CONTEXT_NAME + "/"
		+ interfaceFullClassName(declaration);
	}

}
