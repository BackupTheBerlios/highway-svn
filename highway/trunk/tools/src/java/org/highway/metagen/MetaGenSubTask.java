/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.metagen;

import xdoclet.TemplateSubTask;
import xdoclet.XDocletException;
import xjavadoc.XClass;

public class MetaGenSubTask extends TemplateSubTask implements MetaGenConstants
{
	public MetaGenSubTask()
	{
		setSubTaskName("METAGEN");
		setDestinationFile(METAGEN_OUTPUT_FILE_NAME);
		setTemplateURL(getClass().getResource(METAGEN_TEMPLATE_FILE_NAME));
	}

	public void validateOptions() throws XDocletException
	{
	}

	protected boolean matchesGenerationRules(XClass xclass)
		throws XDocletException
	{
		if (super.matchesGenerationRules(xclass))
		{
			return true;

			/**
			 * @toto attias: only generate javadoc class for classes with at
			 * least some comments or tags. use the following kind of code.
			 *
			 * if (xclass.getDoc().getCommentText().length() > 0) return true;
			 * if (!xclass.getDoc().getTags().isEmpty()) return true;
			 * for (Iterator iter = xclass.getMethods().iterator(); iter.hasNext();)
			 * {
			 *         XMethod method = (XMethod) iter.next();
			 *         if (method.getDoc().getCommentText().length() > 0) return true;
			 *         if (!method.getDoc().getTags().isEmpty()) return true;
			 * }
			 */
		}

		return false;
	}
}
