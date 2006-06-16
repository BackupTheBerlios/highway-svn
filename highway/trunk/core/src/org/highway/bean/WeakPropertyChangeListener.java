/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.ref.WeakReference;

/**
 * @author David Attias
 */
class WeakPropertyChangeListener extends WeakReference
	implements PropertyChangeListener
{
	/**
	 * Constructor for WeakPropertyChangeListener
	 * @param listener PropertyChangeListener
	 */
	WeakPropertyChangeListener(PropertyChangeListener listener)
	{
		super(listener);
	}

	/**
	 * Method isFree
	 * @return boolean
	 */
	boolean isFree()
	{
		return get() == null;
	}

	/**
	 * Method propertyChange
	 * @param evt PropertyChangeEvent
	 * @see java.beans.PropertyChangeListener#propertyChange(PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt)
	{
		PropertyChangeListener listener = (PropertyChangeListener) get();

		if (listener != null)
		{
			listener.propertyChange(evt);
		}
	}

	public boolean equals(Object obj)
	{
		if (obj instanceof WeakPropertyChangeListener)
		{
			WeakPropertyChangeListener wl = (WeakPropertyChangeListener) obj;

			return get().equals(wl.get());
		}
		else
		{
			return false;
		}
	}

	/**
	 * Method hashCode
	 * @return int
	 */
	public int hashCode()
	{
		return (get() == null) ? super.hashCode() : get().hashCode();
	}

	/**
	 * Method toString
	 * @return String
	 */
	public String toString()
	{
		return (get() == null) ? "null" : get().toString();
	}
}
