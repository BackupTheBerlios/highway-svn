/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.bean;

import org.highway.helper.ValueHelper;

/**
 * Abstract implementation of ValueObject.<br>
 * Manages the new and dirty properties.<br>
 * Manages the property change events.
 *
 * @author dattias
 * @todo review the code when moving towards jdk 1.4,
 * use new PropertyChangeSupport methods, remove the
 * WeakPropertyChangeSupport attribute.
 */
public abstract class ValueObjectAbstract extends JavaBeanAbstract
	implements ValueObject
{
	//////////////////////////
	///// ctor methods ///////
	//////////////////////////

	/**
	 * Constructs a ValueObjectAbstract.
	 */
	public ValueObjectAbstract()
	{
	}

	//////////////////////////////////////
	///// state management methods ///////
	//////////////////////////////////////

	/**
	 * dirty flag.
	 */
	private boolean dirty = false;

	/**
	 * isNew flag.
	 */
	private boolean isNew = true;

	public boolean isDirty()
	{
		return dirty;
	}

	public void setDirty(boolean value)
	{
		setDirty_0(value);
	}

	protected final void setDirty_0(boolean newValue)
	{
		boolean oldValue = dirty;

		if (! ValueHelper.equals(oldValue, newValue))
		{
			dirty = newValue;

			// we need to use the superclass implemention of firePropertyChange
			// instead of this class implementation because this class
			// firePropertyChange sets the dirty flag to true
			super.firePropertyChange(DIRTY, oldValue, newValue);
		}
	}

	public boolean isNew()
	{
		return isNew;
	}

	public void setNew(boolean value)
	{
		setNew_0(value);
	}

	protected final void setNew_0(boolean newValue)
	{
		boolean oldValue = isNew;

		if (! ValueHelper.equals(oldValue, newValue))
		{
			isNew = newValue;

			// we need to use the superclass implemention of firePropertyChange
			// instead of this class implementation because this class
			// firePropertyChange sets the dirty flag to true
			super.firePropertyChange(NEW, oldValue, newValue);
		}
	}

	public void setSaved()
	{
		setNew(false);
		setDirty(false);
	}

	////////////////////////////
	///// equals methods ///////
	////////////////////////////

	/**
	 * Makes a few easy equality tests and then delegates to the abstract equals2
	 * method. The first tests are :<br>
	 * <br>
	 * <ul>
	 * <li>Returns false if the specified object is null.</li>
	 * <li>Returns true is this object is the same (in memory) than the specified one.</li>
	 * <li>Returns false if the specified object is not of the same class than this object.</li>
	 * </ul>
	 * <br>
	 * If the specified object passes the first tests, this method delegates to
	 * the abstract equals2 method which must contain the real business code.
	 *
	 * @param obj the object to compare with this object
	 * @return true is they are equal
	 * @see equals2()
	 */
	public final boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		else if (this == obj)
		{
			return true;
		}
		else if (this.getClass().equals(obj.getClass()))
		{
			return equals2((ValueObjectAbstract) obj);
		}
		else
		{
			return false;
		}
	}

	/**
	 * Determines if this object is equal to the specified object.<br>
	 * This method is only called by the regular <code>equals</code>
	 * method after it has verified that the specified object is<br>
	 * <ul>
	 * <li>not null</li>
	 * <li>of the the type of this object</li>
	 * <li>not the same object than this object</li>
	 * </ul>
	 * <br>
	 * Developers should override this method to provide the application
	 * or business specific equality logic of this object. This default
	 * implementation verifies that the dirty and new properties are equal.<br>
	 * <br>
	 * Developers should not call directly this method but use normally the
	 * <code>equals</code> method.
	 *
	 * @param obj the object to compare with this object
	 * @return true is they are equal
	 * @see equals()
	 */
	protected boolean equals2(ValueObjectAbstract obj)
	{
		return (this.dirty == obj.dirty) && (this.isNew == obj.isNew);
	}

	/**
	 * Returns the Object default hashcode but this method is required
	 * by Hibernate in case of composite id.
	 */
	public int hashCode()
	{
		return ValueHelper.hashCode(dirty) + ValueHelper.hashCode(isNew);
	}

	//////////////////////////////////////////
	///// fire property change methods ///////
	//////////////////////////////////////////

	/**
	 * Sets this object state to dirty and calls the superclass
	 * implementation.
	 */
	protected void firePropertyChange(
		String propertyName, boolean oldValue, boolean newValue)
	{
		setDirty(true);
		super.firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * Sets this object state to dirty and calls the superclass
	 * implementation.
	 */
	protected void firePropertyChange(
		String propertyName, byte oldValue, byte newValue)
	{
		setDirty(true);
		super.firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * Sets this object state to dirty and calls the superclass
	 * implementation.
	 */
	protected void firePropertyChange(
		String propertyName, char oldValue, char newValue)
	{
		setDirty(true);
		super.firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * Sets this object state to dirty and calls the superclass
	 * implementation.
	 */
	protected void firePropertyChange(
		String propertyName, double oldValue, double newValue)
	{
		setDirty(true);
		super.firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * Sets this object state to dirty and calls the superclass
	 * implementation.
	 */
	protected void firePropertyChange(
		String propertyName, float oldValue, float newValue)
	{
		setDirty(true);
		super.firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * Sets this object state to dirty and calls the superclass
	 * implementation.
	 */
	protected void firePropertyChange(
		String propertyName, int oldValue, int newValue)
	{
		setDirty(true);
		super.firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * Sets this object state to dirty and calls the superclass
	 * implementation.
	 */
	protected void firePropertyChange(
		String propertyName, long oldValue, long newValue)
	{
		setDirty(true);
		super.firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * Sets this object state to dirty and calls the superclass
	 * implementation.
	 */
	protected void firePropertyChange(
		String propertyName, Object oldValue, Object newValue)
	{
		setDirty(true);
		super.firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * Sets this object state to dirty and calls the superclass
	 * implementation.
	 */
	protected void firePropertyChange(
		String propertyName, short oldValue, short newValue)
	{
		setDirty(true);
		super.firePropertyChange(propertyName, oldValue, newValue);
	}
}
