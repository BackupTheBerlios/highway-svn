/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.supervise;

import org.highway.vo.Enum;
import org.apache.log4j.Level;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Defines the set of severities recognized by supervision components, that is
 * INFO, WARN, ERROR, SEVERE and FATAL.
 *
 * @see SupervisionMessage
 * @author Christian de Bevotte
 * @since 1.1
 */
public final class Severity extends Enum
{
	/**
	 * The associated Log4j {@link Level}.
	 */
	private Level level;
	public static final Severity INFO = new Severity('I', "INFO", Level.INFO);
	public static final Severity WARN = new Severity('W', "WARN", Level.WARN);
	public static final Severity ERROR =
		new Severity('E', "ERROR", Level.ERROR);
	public static final Severity SEVERE =
		new Severity('S', "SEVERE", Level.ERROR);
	public static final Severity FATAL =
		new Severity('F', "FATAL", Level.FATAL);

	/**
	 * Default constructor.
	 */
	private Severity(char code, String description, Level level)
	{
		super(code, description);
		this.level = level;
	}

	/**
	 * Returns the Log4j {@link Level} instance associated with this
	 * <tt>Severity</tt>.
	 *
	 * @return a Log4j {@link Level} instance.
	 * @see Log4jSupervisor
	 */
	public Level toLevel()
	{
		return level;
	}

	/**
	 * Returns the {@link Collection} of defined <tt>Severity</tt> instances.
	 *
	 * @return a {@link Collection} of <tt>Severity</tt> instances.
	 */
	public static Collection getAll()
	{
		return new ArrayList(getAll(Severity.class));
	}
}
