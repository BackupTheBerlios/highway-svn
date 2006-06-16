/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.supervise;

import org.highway.exception.DoNotInstantiateException;
import org.highway.exception.TechnicalException;
import org.highway.helper.ResourceHelper;
import org.highway.vo.Enum;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * This helper class provides static methods to load a supervision messages
 * catalog from an XML file.
 * </p>
 *
 * <p>
 * Example of XML file :
 *
 * <pre>
 *
 *  &lt;messages&gt;
 *      &lt;message&gt;
 *          &lt;messageId&gt;4&lt;/messageId&gt;
 *          &lt;severity&gt;W&lt;/severity&gt;
 *          &lt;key.4&gt;E&lt;/key.4&gt;
 *          &lt;info&gt;ERREUR APPLICATIVE&lt;/info&gt;
 *      &lt;/message&gt;
 *      &lt;message&gt;
 *          &lt;messageId&gt;5&lt;/messageId&gt;
 *          &lt;severity&gt;W&lt;/severity&gt;
 *          &lt;key.4&gt;M&lt;/key.4&gt;
 *          &lt;info&gt;ERREUR DE CONNEXION MAINFRAME&lt;/info&gt;
 *      &lt;/message&gt;
 *  &lt;/messages&gt;
 *
 * </pre>
 *
 * </p>
 *
 * @see SupervisionMessage
 * @author Christian de Bevotte
 * @since 1.1
 */
public abstract class XmlCatalogHelper
{
	/**
	 * Constant for the <tt>messageId</tt> XML tag (value is
	 * {@value #MESSAGE_ID}).
	 */
	public static final String MESSAGE_ID = "messageId";

	/**
	 * Constant for the <tt>severity</tt> XML tag (value is {@value #SEVERITY}).
	 */
	public static final String SEVERITY = "severity";

	/**
	 * Constant for the <tt>info</tt> XML tag (value is {@value #INFO}).
	 */
	public static final String INFO = "info";

	/**
	 * Constant for the <tt>key</tt> XML tag (value is {@value #KEY}).
	 */
	public static final String KEY = "key";

	/**
	 * Loads a supervision messages catalog from an XML file.
	 *
	 * @param in an {@link InputStream} instance attached to the XML file.
	 * @return a {@link List} of {@link SupervisionMessage} instances.
	 */
	public static List load(InputStream in)
	{
		List result = new ArrayList();

		try
		{
			SAXReader reader = new SAXReader();
			Document document = reader.read(in);
			Element catalogElement = document.getRootElement();

			Iterator messageElements = catalogElement.elementIterator();

			while (messageElements.hasNext())
			{
				Element messageElement = (Element) messageElements.next();
				String messageId = messageElement.elementText(MESSAGE_ID);
				Severity severity =
					(Severity) Enum.getEnumFromString(
						Severity.class, messageElement.elementText(SEVERITY));
				String info = messageElement.elementText(INFO);
				String[] keys = null;

				for (int i = 10; i > 0; i--)
				{
					String key = messageElement.elementText(KEY + '.' + i);

					if (key != null)
					{
						if (keys == null)
						{
							keys = new String[i];
						}

						keys[i - 1] = key;
					}
				}

				SupervisionMessage message =
					new SimpleSupervisionMessage(
						messageId, severity, keys, info);
				result.add(message);
			}
		}
		catch (DocumentException exc)
		{
			throw new TechnicalException(exc);
		}

		return result;
	}

	/**
	 * Loads a supervision messages catalog from an XML file.
	 *
	 * @param resourcePath the resource path
	 * @return a {@link List} of {@link SupervisionMessage} instances.
	 */
	public static List load(String path)
	{
		InputStream inputStream = ResourceHelper.getResourceAsStream(path);

		if (inputStream == null)
		{
			throw new TechnicalException("Resource not found: " + path);
		}

		return load(inputStream);
	}

	/**
	 * Loads a supervision messages catalog from an XML file.
	 *
	 * @param context used to get the resource package
	 * @param resourceName the resource name
	 * @return a {@link List} of {@link SupervisionMessage} instances.
	 * @since 1.3
	 */
	public static List load(Object context, String resourceName)
	{
		return load(ResourceHelper.getResourcePath(context, resourceName));
	}

	/**
	 * Do not instantiate this class.
	 */
	private XmlCatalogHelper()
	{
		throw new DoNotInstantiateException();
	}
}
