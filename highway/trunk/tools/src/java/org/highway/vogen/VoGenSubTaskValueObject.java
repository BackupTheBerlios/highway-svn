/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.vogen;

import xdoclet.TemplateSubTask;
import xdoclet.XDocletException;
import xjavadoc.XClass;
import java.io.File;
import java.text.MessageFormat;

public class VoGenSubTaskValueObject extends TemplateSubTask
	implements VoGenConstantsHibernate2, VoGenTags
{
	public VoGenSubTaskValueObject()
	{
		setSubTaskName("GEN-VALUE-OBJECT");
		setDestinationFile(VALUE_OBJECT_OUTPUT_FILE_NAME);
		setOfType(VALUE_OBJECT_INTERFACE_NAME);
		setTemplateURL(getClass().getResource(VALUE_OBJECT_TEMPLATE_FILE_NAME));
	}

	protected String getGeneratedFileName(XClass xclass)
		throws XDocletException
	{
		// get concrete class name
		String name = VoGenHelper.getGeneratedClassName(xclass);

		// replace . with / to get a file path
		name = name.replace('.', '/');

		// replace the {0} by the name to get the definitive file name
		name =
			MessageFormat.format(getDestinationFile(), new Object[] { name });

		// put / the right way if necessary with File
		return new File(name).toString();
	}
}
