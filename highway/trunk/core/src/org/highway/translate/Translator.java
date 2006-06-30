/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.translate;

import java.util.Locale;

/**
 * Translates messages.<br><br>
 *
 * A message is sometimes associated to a context object.
 * It usually means that the message is defined in this context class.
 * The context object is used to get a qualified class name by using
 * <code>context.getClass().getName()</code> or <code>context.getName()</code>
 * if the context is directly a Class. This class name is then used to find
 * the translation depending on the implementation.<br><br>
 *
 * 
 * 
 */
public interface Translator
{
	/**
	 * Translates the specified message in the default context and locale.
	 */
	String translate(String message);

	/**
	 * Translates the specified message in the specified context and the default
	 * locale.
	 * The context is the object asking for the translation of the specified
	 * message.
	 * 
	 */
	String translate(String message, Object context);

	/**
	 * Translates the specified message in the specified locale.
	 * 
	 */
	String translate(String message, Locale locale);

	/**
	 * Translates the specified message in the specified context and locale.
	 * The context is the object asking for the translation of the specified
	 * message.
	 * 
	 */
	String translate(String message, Object context, Locale locale);

	/**
	 * Returns this Translator default Locale.
	 * The default Locale is usually the JVM default Locale.
	 * In this case, use <code>Locale.setDefault(Locale)</code> to set this
	 * Translator default Locale.
	 * 
	 */
	Locale getDefaultLocale();
}
