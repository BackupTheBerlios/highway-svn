/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.vogen;


/**
 * 
 */
public interface VoGenConstantsHibernate2
{
	String HIBERNATE_MAPPING_PUBLIC_ID =
		"-//Hibernate/Hibernate Mapping DTD 2.0//EN";
	String HIBERNATE_MAPPING_SYSTEM_ID =
		"http://hibernate.sourceforge.net/hibernate-mapping-2.0.dtd";
	String HIBERNATE_MAPPING_DTD_FILE_NAME = "hibernate-mapping_2_0.dtd";
	String HIBERNATE_MAPPING_TEMPLATE_FILE_NAME = "hibernate.xml.xdt";
	String HIBERNATE_MAPPING_OUTPUT_FILE_NAME = "{0}.hbm.xml";
	String HIBERNATE_CONFIGURATION_PUBLIC_ID =
		"-//Hibernate/Hibernate Configuration DTD 2.0//EN";
	String HIBERNATE_CONFIGURATION_SYSTEM_ID =
		"http://hibernate.sourceforge.net/hibernate-configuration-2.0.dtd";
	String HIBERNATE_CONFIGURATION_DTD_FILE_NAME =
		"hibernate-configuration-2.0.dtd";
	String HIBERNATE_CONFIGURATION_TEMPLATE_FILE_NAME = "hibernate.cfg.xml.xdt";
	String HIBERNATE_CONFIGURATION_OUTPUT_FILE_NAME = "hibernate.cfg.xml";
	String VALUE_OBJECT_INTERFACE_NAME = "org.highway.vo.ValueObject";
	String VALUE_OBJECT_ABSTRACT_CLASS_NAME =
		"org.highway.vo.ValueObjectAbstract";
	String VALUE_OBJECT_TEMPLATE_FILE_NAME = "valueobject.java.xdt";
	String VALUE_OBJECT_OUTPUT_FILE_NAME = "{0}.java";
}
