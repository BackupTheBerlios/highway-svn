/*
 * Copyright (c) 2005. All rights reserved.
 */

/*
 * Created on 1 oct. 2004
 */
package org.highway.exception;

import java.io.Serializable;
import java.util.List;

/**
 * @author attias
 */
public class UserMessage implements Serializable
{
	private String message;
	private List parameterList;

	/**
	 * Field warning
	 */
	private boolean warning;

	/**
	 * Constructor for UserMessage
	 */
	public UserMessage()
	{
	}

	/**
	 * Constructor for UserMessage
	 * @param message String
	 * @param parameterList List
	 * @param warning boolean
	 */
	public UserMessage(String message, List parameterList, boolean warning)
	{
		this.message = message;
		this.parameterList = parameterList;
		this.warning = warning;
	}

	/**
	 * Method getMessage
	 * @return String
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * Method setMessage
	 * @param message String
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	/**
	 * Method getParameterList
	 * @return List
	 */
	public List getParameterList()
	{
		return parameterList;
	}

	/**
	 * Method setParameterList
	 * @param parameterList List
	 */
	public void setParameterList(List parameterList)
	{
		this.parameterList = parameterList;
	}

	/**
	 * Method isWarning
	 * @return boolean
	 */
	public boolean isWarning()
	{
		return warning;
	}

	/**
	 * Method setWarning
	 * @param warning boolean
	 */
	public void setWarning(boolean warning)
	{
		this.warning = warning;
	}
}
