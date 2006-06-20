/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.ejbgen;

import java.util.ArrayList;
import java.util.Collection;

import com.sun.mirror.declaration.InterfaceDeclaration;


public class EjbGenSessionHelper{
	private Collection<InterfaceDeclaration> sessions = new ArrayList<InterfaceDeclaration>();
	

	public Collection getSessionBeans()
	{
		return sessions;
	}

	/**
	 * Calculates the interface full class name
	 */
	public String interfaceFullClassName(InterfaceDeclaration declaration)
	{
		return declaration.getQualifiedName();
	}

	/**
	 * Calculates the Session Bean full class name
	 */
	public String beanFullClassName(InterfaceDeclaration declaration)
	{
		return declaration.getPackage().getQualifiedName() + "."
		+ EjbGenConstants.EJB_PACKAGE_NAME + "." + declaration.getSimpleName()
		+ EjbGenConstants.BEAN_SUFFIX;
	}

	/**
	 * Calculates the Session Bean remote home interface full class name
	 */
	public String homeFullClassName(InterfaceDeclaration declaration)
	{
		return declaration.getPackage().getQualifiedName() + "."
		+ EjbGenConstants.EJB_PACKAGE_NAME + "." + declaration.getSimpleName()
		+ EjbGenConstants.HOME_SUFFIX;
	}

	/**
	 * Calculates the name of the Session Bean remote component interface full class name
	 */
	public String remoteFullClassName(InterfaceDeclaration declaration)
	{
		return declaration.getPackage().getQualifiedName() + "."
		+ EjbGenConstants.EJB_PACKAGE_NAME + "." + declaration.getSimpleName()
		+ EjbGenConstants.REMOTE_SUFFIX;
	}

	/**
	 * Calculates the JNDI name of the Session Bean remote home interface
	 */
	public String homeJNDIName(InterfaceDeclaration declaration)
	{
		return EjbGenConstants.EJBHOME_JNDI_CONTEXT_NAME + "/"
		+ interfaceFullClassName(declaration);
	}

	public boolean add(InterfaceDeclaration o) {
		return sessions.add(o);
	}
}
