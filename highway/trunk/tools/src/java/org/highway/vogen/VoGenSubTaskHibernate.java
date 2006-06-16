/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.vogen;

import xdoclet.XDocletException;
import xdoclet.XmlSubTask;
import xjavadoc.XClass;
import java.io.File;
import java.text.MessageFormat;

/**
 * This task generates Hibernate xml mapping file for a given class. Supports
 * Hibernate 2.
 */
public class VoGenSubTaskHibernate extends XmlSubTask implements VoGenConstantsHibernate2,
	VoGenTags
{
	public VoGenSubTaskHibernate()
	{
		setSubTaskName("GEN-HIBERNATE-MAPPING");
		setHavingClassTag(VO_MAPPING_TAG);
		setTemplateURL(
			getClass().getResource(HIBERNATE_MAPPING_TEMPLATE_FILE_NAME));
		setDestinationFile(HIBERNATE_MAPPING_OUTPUT_FILE_NAME);
		setPublicId(HIBERNATE_MAPPING_PUBLIC_ID);
		setSystemId(HIBERNATE_MAPPING_SYSTEM_ID);
		setDtdURL(getClass().getResource(HIBERNATE_MAPPING_DTD_FILE_NAME));
	}

	protected String getGeneratedFileName(XClass xclass)
		throws XDocletException
	{
		// get concrete class name
		String name = VoGenHelper.getEntityClassName(xclass);

		// replace . with / to get a file path
		name = name.replace('.', '/');

		// replace the {0} by the name to get the definitive file name
		name =
			MessageFormat.format(getDestinationFile(), new Object[] { name });

		// put / the right way if necessary with File
		return new File(name).toString();
	}
}
