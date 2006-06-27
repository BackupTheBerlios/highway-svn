/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.idgen;

import org.highway.debug.DebugHome;
import org.highway.exception.DoNotInstantiateException;
import org.highway.lifecycle.InitException;

/**
 * Home class of the idgen package.
 * Provides easy access to the default simple and complex id generators.
 * Do not instantiate this class.
 * The default generators must be instantiated at init time and set with
 * the <tt>setSimpleIdGenerator</tt> and <tt>setComplexGenrator</tt> methods
 * before used. Example:<br><br>
 * <code>IdgenHome.setSimpleIdGenerator(new TimeBasedSimpleIdGenerator());</code>
 *
 * @author attias
 */
public class IdGenHome
{
	/**
	 * Do not instantiate this class.
	 */
	private IdGenHome()
	{
		throw new DoNotInstantiateException();
	}

	///////
	/////// simple id generator
	///////

	/**
	 * Field simpleIdGenerator
	 */
	private static SimpleIdGenerator simpleIdGenerator;

	/**
	 * Gets and returns the next unique long identifier from the simple
	 * generator.
	 *
	 * @return an identifier
	 * @throws InitException if no default generator is set
	 * @see SimpleIdGenerator.getNextId()
	 */
	public static long getNextSimpleId()
	{
		if (simpleIdGenerator == null)
		{
			throw new InitException("no default SimpleIdGenerator set");
		}

		return simpleIdGenerator.getNextId();
	}

	/**
	 * Gets and returns the next unique long identifier associated with
	 * the specified argument from the simple generator.
	 *
	 * @param argument argument specific to the default simple generator
	 * @return an identifier of type long
	 * @throws InitException if no default generator is set
	 * @see SimpleIdGenerator.getNextId(Object)
	 */
	public static long getNextSimpleId(Object argument)
	{
		if (simpleIdGenerator == null)
		{
			throw new InitException("no default SimpleIdGenerator set");
		}

		return simpleIdGenerator.getNextId(argument);
	}

	/**
	 * Sets the simple generator to use as the default.<br>
	 * This method must be called at init stage before the getNextSimpleId
	 * methods can be called.
	 *
	 * @param generator a simple generator
	 */
	public static synchronized void setSimpleIdGenerator(
		SimpleIdGenerator generator)
	{
		simpleIdGenerator = generator;
		DebugHome.getDebugLog().info(
			"Default SimpleIdGenerator is set to " + generator);
	}

	/**
	 * Returns the default SimpleIdGenerator.
	 * Returns null if no default generator is set.
	 */
	public static SimpleIdGenerator getSimpleIdGenerator()
	{
		return simpleIdGenerator;
	}

	///////
	/////// complex id generator
	///////

	/**
	 * Field complexIdGenerator
	 */
	private static ComplexIdGenerator complexIdGenerator;

	/**
	 * Gets and returns the next unique identifier from the complex default
	 * generator.
	 *
	 * @return an identifier
	 * @throws InitException if no default generator is set
	 * @see ComplexIdGenerator.getNextId()
	 */
	public static Object getNextComplexId()
	{
		if (complexIdGenerator == null)
		{
			throw new InitException("no default ComplexIdGenerator set");
		}

		return complexIdGenerator.getNextId();
	}

	/**
	 * Gets and returns the next unique identifier associated with the
	 * specified argument from the complex default generator.
	 *
	 * @param argument argument specific to the default complex generator
	 * @return an identifier
	 * @throws InitException if no default generator is set
	 * @see ComplexIdGenerator.getNextId(Object)
	 */
	public static Object getNextComplexId(Object argument)
	{
		if (complexIdGenerator == null)
		{
			throw new InitException("no default ComplexIdGenerator set");
		}

		return complexIdGenerator.getNextId(argument);
	}

	/**
	 * Sets the ComplexIdGenerator to use as the default.<br>
	 * This method must be called at init stage before the
	 * <tt>getNextComplexId</tt> methods can be called.
	 *
	 * @param generator the default generator
	 */
	public static synchronized void setComplexIdGenerator(
		ComplexIdGenerator generator)
	{
		complexIdGenerator = generator;
		DebugHome.getDebugLog().info(
			"Default ComplexIdGenerator is set to " + generator);
	}

	/**
	 * Returns the default ComplexIdGenerator.
	 * Returns null if no default generator is set.
	 */
	public static ComplexIdGenerator getComplexIdGenerator()
	{
		return complexIdGenerator;
	}
}
