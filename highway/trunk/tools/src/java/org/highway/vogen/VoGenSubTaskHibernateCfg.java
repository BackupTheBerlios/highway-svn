/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.vogen;

import xdoclet.XmlSubTask;

/**
 * This task generates Hibernate xml configuration file.
 */
public class VoGenSubTaskHibernateCfg extends XmlSubTask
	implements VoGenConstantsHibernate2, VoGenTags
{
	public VoGenSubTaskHibernateCfg()
	{
		setSubTaskName("GEN-HIBERNATE-CONFIGURATION");
		setHavingClassTag(VO_MAPPING_TAG);
		setTemplateURL(
			getClass().getResource(HIBERNATE_CONFIGURATION_TEMPLATE_FILE_NAME));
		setDestinationFile(HIBERNATE_CONFIGURATION_OUTPUT_FILE_NAME);
		setPublicId(HIBERNATE_CONFIGURATION_PUBLIC_ID);
		setSystemId(HIBERNATE_CONFIGURATION_SYSTEM_ID);
		setDtdURL(
			getClass().getResource(HIBERNATE_CONFIGURATION_DTD_FILE_NAME));
	}
}
