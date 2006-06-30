/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.translate;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.highway.debug.DebugHome;

/**
 * Implementation of <tt>Translator</tt> that relies on <tt>ResourceBundle</tt>
 * to find translations.
 *
 * 
 * 
 * @since 1.3
 */
public class StandardTranslator implements Translator
{
	private Object defaultContext;
	private Locale defaultLocale;

	/**
	 * Constructs a <tt>StandardTranslator</tt> with <tt>null</tt> as
	 * default context and the JVM default locale as default locale.
	 * @deprecated you must pass a non null default context
	 * @see #StandardTranslator(Object, Locale)
	 */
	public StandardTranslator()
	{
		this(null, null);
	}

	/**
	 * Constructs a <tt>StandardTranslator</tt> with <tt>null</tt> as
	 * default context and the JVM default locale as default locale.
	 * @see #StandardTranslator(Object, Locale)
	 */
	public StandardTranslator(Object defaultContext)
	{
		this(defaultContext, null);
	}

	/**
	 * Constructs a <tt>StandardTranslator</tt> with a default context and
	 * a default locale provided as arguments.
	 */
	public StandardTranslator(Object defaultContext, Locale defaultLocale)
	{
		if (defaultContext == null)
			throw new IllegalArgumentException("default context is null");
		
		this.defaultContext = defaultContext;
		this.defaultLocale = defaultLocale == null ? Locale.getDefault() : defaultLocale;
	}

	/*
	 * Implements or overrides an already defined method.
	 */
	public Locale getDefaultLocale()
	{
		return defaultLocale;
	}

	/*
	 * Implements or overrides an already defined method.
	 */
	public String translate(String message)
	{
		return translate(message, defaultContext, defaultLocale);
	}

	/*
	 * Implements or overrides an already defined method.
	 */
	public String translate(String message, Object context)
	{
		return translate(message, context, defaultLocale);
	}

	/*
	 * Implements or overrides an already defined method.
	 */
	public String translate(String message, Locale locale)
	{
		return translate(message, defaultContext, locale);
	}

	/**
	 * Returns the message argument if no translation was found.
	 */
	public String translate(String message, Object context, Locale locale)
	{
		if (context == null) context = defaultContext;
		if (locale == null) locale = defaultLocale;
		
		String translation = translate0(message, context, locale);

		if (translation == null)
		{
			return message;
		}
		
		DebugHome.getDebugLog().debug("'", message,
				"' is translated into '", translation, "'");

		return translation;
	}

	/**
	 * Returns <tt>null</tt> if no translation was found.
	 */
	private String translate0(String message, Object context, Locale locale)
	{
		if (message != null && context != null)
		{
			Class contextClass =
				(context instanceof Class) ? (Class) context : context.getClass();

			try
			{
				if (locale == null) Locale.getDefault();
				
				ResourceBundle resourceBundle =
					ResourceBundle.getBundle(contextClass.getName(), locale);
				
				return resourceBundle.getString(message);
			}
			catch (MissingResourceException e)
			{
				// nothing to do
			}
		}

		return null;
	}
}
