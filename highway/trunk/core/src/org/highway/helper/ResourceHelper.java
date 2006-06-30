/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.helper;

import org.highway.debug.DebugHome;
import org.highway.exception.DoNotInstantiateException;
import org.highway.exception.TechnicalException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Provides static help methods to manage resources.<br><br>
 *
 * A resource is a file located in the classpath.
 * In the following, a resource is defined by a resource path or by
 * a resource name and a context object defined in the same package than
 * the resource. The context can be a Class object<br><br>
 *
 * Resource path example:
 *     <tt>java/lang/Object.class</tt><br>
 * Resource URL example:
 *     <tt>file:/D:/Dev/jdk14/jre/lib/rt.jar!/java/lang/Object.class</tt><br>
 *
 * 
 */
public final class ResourceHelper
{
	private ResourceHelper()
	{
		throw new DoNotInstantiateException();
	}

	/**
	 * @deprecated use <code>getResourcePath</code> method
	 */
	public static String getLocalResourcePath(
		Class context, String resourceName)
	{
		return getResourcePath(context, resourceName);
	}

	/**
	 * Returns the path of the resource defined by the specified relative
	 * path and the specified context.
	 * 
	 * If the context is a path, it must be absolute and of the form
	 * eg. com/mycompanie/myapp
	 * If the context is a Class, its package name is used as a context path
	 * eg. com.mycompanie.myapp.Main.class -> com/mycompanie/myapp
	 * If the context is a Package object, its name is used as a context path
	 * If the context is a regular object, its class package name is
	 * used as context path.
	 *
	 * @param context the context path or package
	 * @param resourcePath the resource path relative to the context
	 * eg. image/hello.gif
	 * @return the resource path
	 * eg. com/mycompanie/myapp/image/hello.gif
	 * @since 1.3
	 */
	public static String getResourcePath(Object context, String resourcePath)
	{
		if (context == null)
		{
			return resourcePath;
		}
				
		if (context instanceof String)
		{
			return context.toString() + '/' + resourcePath;
		}
		
		Package contextPackage = null;
		
		if (context instanceof Package)
		{
			contextPackage = (Package)context;
		}
		else if (context instanceof Class)
		{
			contextPackage = ((Class)context).getPackage();
			
		}
		else
		{
			contextPackage = context.getClass().getPackage();
		}
		
		return contextPackage.getName().replace('.', '/') + '/' + resourcePath;
	}

	/**
	 * Returns an InputStream to load the resource or null if not found.
	 *
	 * @param resourcePath the resource path
	 * @return an InputStream to load the resource
	 */
	public static InputStream getResourceAsStream(String resourcePath)
	{
		// FIXME forbid null resourcePath (exception or return null
		
		DebugHome.getDebugLog().debug("Loading ", resourcePath, " ...");

		ClassLoader classLoader =
			Thread.currentThread().getContextClassLoader();

		return classLoader.getResourceAsStream(resourcePath);
	}

	/**
	 * Returns an InputStream to load the resource defined by the
	 * specified name and context or null if not found.
	 *
	 * @param context the context path or package
	 * @param resourceName the resource name
	 * @return an InputStream to load the resource
	 * @see ResourceHelper#getResourcePath(Object, String)
	 */
	public static InputStream getResourceAsStream(
		Object context, String resourceName)
	{
		return getResourceAsStream(getResourcePath(context, resourceName));
	}

	/**
	 * Returns the specified resource URL or null if not found.
	 *
	 * @param resourcePath the resource path
	 * @return the resource URL
	 */
	public static URL getResourceUrl(String resourcePath)
	{
		ClassLoader classLoader =
			Thread.currentThread().getContextClassLoader();

		return classLoader.getResource(resourcePath);
	}

	/**
	 * Returns the specified resource URL or null if not found.
	 *
	 * @param context the context path or package
	 * @param resourceName the resource name
	 * @return the resource URL
	 * @see ResourceHelper#getResourcePath(Object, String)
	 */
	public static URL getResourceUrl(Object context, String resourceName)
	{
		ClassLoader classLoader =
			Thread.currentThread().getContextClassLoader();

		return classLoader.getResource(getResourcePath(context, resourceName));
	}

	/**
	 * Checks if the specified resource exists.
	 * @param resourcePath the resource class path
	 * @return true if the resource exists
	 * @since 1.3
	 */
	public static boolean checkResource(String resourcePath)
	{
		return getResourceUrl(resourcePath) != null;
	}

	/**
	 * Checks if the specified resource exists.
	 * @param context the context path or package
	 * @param resourceName the resource name
	 * @return true if the resource exists
	 * @see ResourceHelper#getResourcePath(Object, String)
	 * @since 1.3
	 */
	public static boolean checkResource(Object context, String resourceName)
	{
		return getResourceUrl(context, resourceName) != null;
	}

	/**
	 * Returns all the root paths to the classpath directories.
	 * No classpath jar path is returned.
	 */
	public static List getClasspathRootPathList()
	{
		try
		{
			List list = new ArrayList();
			Enumeration enumeration = ClassLoader.getSystemResources("");

			while (enumeration.hasMoreElements())
			{
				URL url = (URL) enumeration.nextElement();
				list.add(url.getPath());
			}

			return list;
		}
		catch (IOException e)
		{
			throw new TechnicalException(e);
		}
	}
}
