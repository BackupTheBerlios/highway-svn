/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.idgen;


/**
 * Simple identifier generator implementation based on the system time.<br>
 * This class uses the system time (<code>System.currentTimeMillis()</code>) to
 * initialize its counter. The counter is then incremented every time
 * an identifier is requested.<br>
 * <br>
 * This class garantie identifier uniqueness if only one instance is
 * used through out the appication and if the application is not an intensive
 * identifier consumer (max instant identifier consumption should be less than
 * 100 per second).
 *
 * @author David Attias
 */
public class TimeBasedSimpleIdGenerator implements SimpleIdGenerator
{
	/**
	 * Field nextId
	 */
	private long nextId;

	/**
	 * Constructor for TimeBasedSimpleIdGenerator
	 */
	public TimeBasedSimpleIdGenerator()
	{
		this((long)0);
	}

	/**
	 * Constructor for TimeBasedSimpleIdGenerator.
	 * @param referenceDate the date from witch the generator
	 * must calculate the id counter first value.
	 * @since 1.4.3
	 */
	public TimeBasedSimpleIdGenerator(long referenceDate)
	{
		nextId = System.currentTimeMillis() - referenceDate;

		if (nextId < 0)
			throw new IllegalArgumentException(
				"the start date must be in the past");
	}

	public synchronized long getNextId()
	{
		return nextId++;
	}

	public long getNextId(Object argument)
	{
		return getNextId();
	}
}
