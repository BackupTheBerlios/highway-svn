/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.translate;

import java.util.Locale;

/**
 * The silent translator class do not translate,
 * it returns the message as received.
 * This class is used as the standard global Translator.
 *
 * 
 * @since 1.2
 */
public class SilentTranslator implements Translator
{
	/**
	 * Returns the specified message unmodified.
	 */
	public String translate(String message)
	{
		return message;
	}

	/**
	 * Returns the specified message unmodified.
	 */
	public String translate(String message, Object context)
	{
		return message;
	}

	/**
	 * Returns the specified message unmodified.
	 */
	public String translate(String message, Locale locale)
	{
		return message;
	}

	/**
	 * Returns the specified message unmodified.
	 */
	public String translate(String message, Object context, Locale locale)
	{
		return message;
	}

	/**
	 * Returns the JVM default Locale.
	 */
	public Locale getDefaultLocale()
	{
		return Locale.getDefault();
	}
}
