/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.service.context;

import java.security.Principal;

/**
 * Simple principal implementation.<br>
 * Developers should not use this class.
 *
 * @author attias
 */
public class SimplePrincipal implements Principal
{
	/**
	 * Field userLogin
	 */
	private String userLogin;

	/**
	 * Constructs a SimplePrincipal.
	 *
	 * @param userLogin the user login
	 */
	public SimplePrincipal(String userLogin)
	{
		this.userLogin = userLogin;
	}

	public String getName()
	{
		return userLogin;
	}

	/**
	 * 2 SimplePrincipal objects are equal if their user login are equals.
	 */
	public boolean equals(Object another)
	{
		if (another == null)
		{
			return false;
		}

		if (another instanceof Principal)
		{
			return userLogin.equals(((Principal) another).getName());
		}
		else
		{
			return false;
		}
	}

	public String toString()
	{
		return userLogin;
	}

	/**
	 * Returns the user login hashcode.
	 */
	public int hashCode()
	{
		return userLogin.hashCode();
	}
}
