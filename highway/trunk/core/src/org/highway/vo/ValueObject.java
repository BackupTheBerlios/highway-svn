/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.vo;

import org.highway.bean.JavaBean;
import org.highway.debug.Dumpable;

/**
 * A value object must implement this interface.<br>
 * A value object must be JavaBeans compliant.<br>
 * A value object is serializable, cloneable, dumpable<br>
 * <br>
 * The new and dirty properties are defined here because they are not only
 * associated with persistent value objects. These properties have real meanings
 * even for regular non persistent value objects.
 *
 * @author David Attias
 */
public interface ValueObject extends JavaBean, Cloneable, Dumpable
{
	/**
	 * Name of the <code>new</code> property.
	 */
	public String NEW = "new";

	/**
	 * Name of the <code>dirty</code> property.
	 */
	public String DIRTY = "dirty";

	/**
	 * Determines if this object has ever been saved.
	 */
	public boolean isNew();

	/**
	 * Sets the new flag with the specified boolean value.
	 */
	public void setNew(boolean value);

	/**
	 * Determines if the state of this object has been modified since
	 * the last save operation.
	 */
	public boolean isDirty();

	/**
	 * Sets the dirty flag of this object to the specified boolean value.
	 */
	public void setDirty(boolean value);

	/**
	 * Sets the new and dirty flags of this object to false.
	 */
	public void setSaved();
}
