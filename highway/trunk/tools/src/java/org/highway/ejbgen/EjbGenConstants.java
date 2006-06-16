/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.ejbgen;


/**
 * @author attias
 */
public interface EjbGenConstants
{
	String EJB_SERVICE_TAG = "highway.service.generate.ejb";
	String ZIP_EJB_SERVICE_TAG = "highway.service.generate.ejbzip";
	String EJB_SERVICE_INTERFACE = "org.highway.service.ejb.EjbService";
	String EJB_DD_TEMPLATE_File = "ejb-jar.xml.xdt";
	String EJB_PACKAGE_NAME = "ejb";
	String EJBHOME_JNDI_CONTEXT_NAME = "ejbhome";
	String HOME_TEMPLATE_FILE = "home.java.xdt";
	String HOME_SUFFIX = "EjbHome";
	String HOME_FILE_NAME = EJB_PACKAGE_NAME + "/{0}" + HOME_SUFFIX + ".java";
	String REMOTE_TEMPLATE_FILE = "remote.java.xdt";
	String ZIP_REMOTE_TEMPLATE_FILE = "remoteZip.java.xdt";
	String REMOTE_SUFFIX = "EjbRemote";
	String REMOTE_FILE_NAME =
		EJB_PACKAGE_NAME + "/{0}" + REMOTE_SUFFIX + ".java";
	String BEAN_TEMPLATE_FILE = "bean.java.xdt";
	String ZIP_BEAN_TEMPLATE_FILE = "beanZip.java.xdt";
	String BEAN_SUFFIX = "EjbBean";
	String BEAN_FILE_NAME = EJB_PACKAGE_NAME + "/{0}" + BEAN_SUFFIX + ".java";
	String PROXY_TEMPLATE_FILE = "proxy.java.xdt";
	String ZIP_PROXY_TEMPLATE_FILE = "proxyZip.java.xdt";
	String PROXY_SUFFIX = "EjbProxy";
	String PROXY_FILE_NAME = EJB_PACKAGE_NAME + "/{0}" + PROXY_SUFFIX + ".java";
	String WEBSPHERE_BND_TEMPLATE_FILE = "ibm-ejb-jar-bnd.xmi.xdt";
	String WEBSPHERE_BND_FILE_NAME = "ibm-ejb-jar-bnd.xmi";
	String WEBSPHERE_EXT_TEMPLATE_FILE = "ibm-ejb-jar-ext.xmi.xdt";
	String WEBSPHERE_EXT_FILE_NAME = "ibm-ejb-jar-ext.xmi";
}
