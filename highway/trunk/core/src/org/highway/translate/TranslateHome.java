/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.translate;

import org.highway.debug.DebugHome;
import org.highway.lifecycle.InitException;

import java.util.Locale;

/**
 * Home class of the translate package.
 * It provides static methods to set the default gloabl Translator
 * and access its methods.<br><br>
 *
 * Applications should create, configure and set the global Translator
 * at init time. Use the <code>setGlobalTranslator</code> method to set it.
 * By default, a SilentTranslator is set as standard global Translator.
 *
 * 
 * @since 1.2
 * @see org.highway.translate.SilentTranslator
 */
public class TranslateHome
{
	private static Translator globalTranslator = new SilentTranslator();

	/**
	 * Sets the global Translator.
	 */
	public static synchronized void setGlobalTranslator(Translator translator)
	{
		globalTranslator = translator;
		DebugHome.getDebugLog().info(
			"Global Translator is set to " + translator);
	}

	/**
	 * Returns the global Translator.
	 * Returns null if no global Translator is set.
	 */
	public static Translator getGlobalTranslator()
	{
		return globalTranslator;
	}

	/**
	 * Returns the global Translator.
	 * @throws InitException if no global Translator is set
	 */
	private static Translator getSafeTranslator()
	{
		if (globalTranslator == null)
		{
			throw new InitException("No global Translator set");
		}

		return globalTranslator;
	}

	/**
	 * Delegates to the global Translator.
	 * @throws InitException if no global Translator is set
	 * @see Translator#translate(String)
	 */
	public static String translate(String message)
	{
		return getSafeTranslator().translate(message);
	}

	/**
	 * Delegates to the global Translator.
	 * @throws InitException if no global Translator is set
	 * @see Translator#translate(String, Locale)
	 * @since 1.3
	 */
	public static String translate(String message, Locale locale)
	{
		return getSafeTranslator().translate(message, locale);
	}

	/**
	 * Delegates to the global Translator.
	 * @throws InitException if no global Translator is set
	 * @see Translator#translate(String, Object)
	 * @since 1.3
	 */
	public static String translate(String message, Object context)
	{
		return getSafeTranslator().translate(message, context);
	}

	/**
	 * Delegates to the global Translator.
	 * @throws InitException if no global Translator is set
	 * @see Translator#translate(String, Object, Locale)
	 * @since 1.3
	 */
	public static String translate(
		String message, Object context, Locale locale)
	{
		return getSafeTranslator().translate(message, context, locale);
	}
}
