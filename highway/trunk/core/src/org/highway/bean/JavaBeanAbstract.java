/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.bean;

import java.beans.PropertyChangeListener;

import org.highway.exception.TechnicalException;
import org.highway.helper.ReflectHelper;

/**
 * JavaBean abstract class.
 * Provides help methods for JavaBeans compliant classes.
 *
 * 
 */
public abstract class JavaBeanAbstract implements JavaBean
{
	private transient WeakPropertyChangeSupport listeners;

	/**
	 * Constructs a JavaBeanAbstract.
	 */
	public JavaBeanAbstract()
	{
	}

	/**
	 * Clones this object.<br>
	 * Throw a <code>TechnicalException</code> if subclass does not implement <code>Cloneable</code>.<br>
	 * The clone support for listeners is set to null -> the clone does not
	 * have any listeners.
	 *
	 * @return a clone of this object
	 * @throws TechnicalException if the class does not implement Cloneable
	 * @see java.lang.Cloneable
	 */
	public Object clone()
	{
		try
		{
			JavaBeanAbstract clone = (JavaBeanAbstract) super.clone();
			clone.listeners = null;

			return clone;
		}
		catch (CloneNotSupportedException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	/////////////////////////////////////
	///// property change methods ///////
	/////////////////////////////////////

	/**
	 * Attachs the specified propery change listener to this object.
	 *
	 * @param listener the listener to attach
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		if (listeners == null)
		{
			listeners = new WeakPropertyChangeSupport(this);
		}

		listeners.addPropertyChangeListener(listener);
	}

	/**
	 * Detachs the specified propery change listener from this object.
	 *
	 * @param listener the listener to detach
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener)
	{
		if (listeners == null)
		{
			return;
		}

		listeners.removePropertyChangeListener(listener);
	}

	/**
	 * Fires a property change event on the specified boolean property.
	 *
	 * @param propertyName the property name
	 * @param oldValue the old property value
	 * @param newValue the new property value
	 */
	protected void firePropertyChange(
		String propertyName, boolean oldValue, boolean newValue)
	{
		if (listeners == null)
		{
			return;
		}

		listeners.firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * Fires a property change event on the specified byte property.
	 *
	 * @param propertyName the property name
	 * @param oldValue the old property value
	 * @param newValue the new property value
	 */
	protected void firePropertyChange(
		String propertyName, byte oldValue, byte newValue)
	{
		if (listeners == null)
		{
			return;
		}

		listeners.firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * Fires a property change event on the specified char property.
	 *
	 * @param propertyName the property name
	 * @param oldValue the old property value
	 * @param newValue the new property value
	 */
	protected void firePropertyChange(
		String propertyName, char oldValue, char newValue)
	{
		if (listeners == null)
		{
			return;
		}

		listeners.firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * Fires a property change event on the specified double property.
	 *
	 * @param propertyName the property name
	 * @param oldValue the old property value
	 * @param newValue the new property value
	 */
	protected void firePropertyChange(
		String propertyName, double oldValue, double newValue)
	{
		if (listeners == null)
		{
			return;
		}

		listeners.firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * Fires a property change event on the specified float property.
	 *
	 * @param propertyName the property name
	 * @param oldValue the old property value
	 * @param newValue the new property value
	 */
	protected void firePropertyChange(
		String propertyName, float oldValue, float newValue)
	{
		if (listeners == null)
		{
			return;
		}

		listeners.firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * Fires a property change event on the specified int property.
	 *
	 * @param propertyName the property name
	 * @param oldValue the old property value
	 * @param newValue the new property value
	 */
	protected void firePropertyChange(
		String propertyName, int oldValue, int newValue)
	{
		if (listeners == null)
		{
			return;
		}

		listeners.firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * Fires a property change event on the specified long property.
	 *
	 * @param propertyName the property name
	 * @param oldValue the old property value
	 * @param newValue the new property value
	 */
	protected void firePropertyChange(
		String propertyName, long oldValue, long newValue)
	{
		if (listeners == null)
		{
			return;
		}

		listeners.firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * Fires a property change event on the specified Object property.
	 *
	 * @param propertyName the property name
	 * @param oldValue the old property value
	 * @param newValue the new property value
	 */
	protected void firePropertyChange(
		String propertyName, Object oldValue, Object newValue)
	{
		if (listeners == null)
		{
			return;
		}

		listeners.firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * Fires a property change event on the specified short property.
	 *
	 * @param propertyName the property name
	 * @param oldValue the old property value
	 * @param newValue the new property value
	 */
	protected void firePropertyChange(
		String propertyName, short oldValue, short newValue)
	{
		if (listeners == null)
		{
			return;
		}

		listeners.firePropertyChange(propertyName, oldValue, newValue);
	}

	//////////////////////////////
	///// property methods ///////
	//////////////////////////////

	/**
	 * Sets the specified property to the specified value.
	 *
	 * @param propertyName the name of the property to set
	 * @param value the new value of the property
	 */
	public final void setProperty(String propertyName, Object value)
	{
		ReflectHelper.setProperty(this, propertyName, value);
	}

	/**
	 * Returns the value of the specified property.
	 *
	 * @param propertyName the name of the property to return
	 * @return the value of the property
	 */
	public final Object getProperty(String propertyName)
	{
		return ReflectHelper.getProperty(this, propertyName);
	}

	/**
	 * Returns the type of the specified property.
	 *
	 * @param propertyName the name of the property
	 * @return the type of the property
	 */
	public final Class getPropertyType(String propertyName)
	{
		return ReflectHelper.getPropertyType(this.getClass(), propertyName);
	}
}
