/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.highway.helper.ValueHelper;

/**
 * This is a utility class that can be used by beans that support bound
 * properties. You can use an instance of this class as a member field of your
 * bean and delegate various work to it.<br>
 * <br>
 * This class is serializable. When it is serialized it will save (and restore)
 * any listeners that are themselves serializable. Any non-serializable
 * listeners will be skipped during serialization.
 * 
 * 
 */
class WeakPropertyChangeSupport
{
	/**
	 * Constructs a <code>PropertyChangeSupport</code> object.
	 *
	 * @param sourceBean
	 *            The bean to be given as the source for any events.
	 */
	WeakPropertyChangeSupport(Object sourceBean)
	{
		if (sourceBean == null)
		{
			throw new NullPointerException();
		}

		source = sourceBean;
	}

	/**
	 * Method firePropertyChange
	 * @param propertyName String
	 * @param oldValue byte
	 * @param newValue byte
	 */
	void firePropertyChange(String propertyName, byte oldValue, byte newValue)
	{
		if ((listeners != null) && (oldValue != newValue))
		{
			firePropertyChange0(
				propertyName, ValueHelper.wrap(oldValue),
				ValueHelper.wrap(newValue));
		}
	}

	/**
	 * Method firePropertyChange
	 * @param propertyName String
	 * @param oldValue short
	 * @param newValue short
	 */
	void firePropertyChange(
		String propertyName, short oldValue, short newValue)
	{
		if ((listeners != null) && (oldValue != newValue))
		{
			firePropertyChange0(
				propertyName, ValueHelper.wrap(oldValue),
				ValueHelper.wrap(newValue));
		}
	}

	/**
	 * Method firePropertyChange
	 * @param propertyName String
	 * @param oldValue int
	 * @param newValue int
	 */
	void firePropertyChange(String propertyName, int oldValue, int newValue)
	{
		if ((listeners != null) && (oldValue != newValue))
		{
			firePropertyChange0(
				propertyName, ValueHelper.wrap(oldValue),
				ValueHelper.wrap(newValue));
		}
	}

	/**
	 * Method firePropertyChange
	 * @param propertyName String
	 * @param oldValue long
	 * @param newValue long
	 */
	void firePropertyChange(String propertyName, long oldValue, long newValue)
	{
		if ((listeners != null) && (oldValue != newValue))
		{
			firePropertyChange0(
				propertyName, ValueHelper.wrap(oldValue),
				ValueHelper.wrap(newValue));
		}
	}

	/**
	 * Method firePropertyChange
	 * @param propertyName String
	 * @param oldValue float
	 * @param newValue float
	 */
	void firePropertyChange(
		String propertyName, float oldValue, float newValue)
	{
		if ((listeners != null) && (oldValue != newValue))
		{
			firePropertyChange0(
				propertyName, ValueHelper.wrap(oldValue),
				ValueHelper.wrap(newValue));
		}
	}

	/**
	 * Method firePropertyChange
	 * @param propertyName String
	 * @param oldValue double
	 * @param newValue double
	 */
	void firePropertyChange(
		String propertyName, double oldValue, double newValue)
	{
		if ((listeners != null) && (oldValue != newValue))
		{
			firePropertyChange0(
				propertyName, ValueHelper.wrap(oldValue),
				ValueHelper.wrap(newValue));
		}
	}

	/**
	 * Method firePropertyChange
	 * @param propertyName String
	 * @param oldValue boolean
	 * @param newValue boolean
	 */
	void firePropertyChange(
		String propertyName, boolean oldValue, boolean newValue)
	{
		if ((listeners != null) && (oldValue != newValue))
		{
			firePropertyChange0(
				propertyName, ValueHelper.wrap(oldValue),
				ValueHelper.wrap(newValue));
		}
	}

	/**
	 * Method firePropertyChange
	 * @param propertyName String
	 * @param oldValue char
	 * @param newValue char
	 */
	void firePropertyChange(String propertyName, char oldValue, char newValue)
	{
		if ((listeners != null) && (oldValue != newValue))
		{
			firePropertyChange0(
				propertyName, ValueHelper.wrap(oldValue),
				ValueHelper.wrap(newValue));
		}
	}

	/**
	 * Method firePropertyChange
	 * @param propertyName String
	 * @param oldValue Object
	 * @param newValue Object
	 */
	void firePropertyChange(
		String propertyName, Object oldValue, Object newValue)
	{
		if (listeners == null)
		{
			return;
		}

		if (oldValue == newValue)
		{
			return;
		}

		if (
			(oldValue != null) && (newValue != null)
				&& oldValue.equals(newValue))
		{
			return;
		}

		firePropertyChange0(propertyName, oldValue, newValue);
	}

	/**
	 * Report a bound property update to any registered listeners. No event is
	 * fired if old and new are equal and non-null.
	 *
	 * @param propertyName
	 *            The programmatic name of the property that was changed.
	 * @param oldValue
	 *            The old value of the property.
	 * @param newValue
	 *            The new value of the property.
	 */
	private void firePropertyChange0(
		String propertyName, Object oldValue, Object newValue)
	{
		List targets = null;

		synchronized (this)
		{
			targets = new ArrayList(listeners.size());

			for (Iterator iter = listeners.iterator(); iter.hasNext();)
			{
				WeakPropertyChangeListener weakListener =
					(WeakPropertyChangeListener) iter.next();

				if (weakListener.isFree())
				{
					// remove empty references from main listener list
					iter.remove();
				}
				else
				{
					// add to this event target list
					targets.add(weakListener);
				}
			}
		}

		PropertyChangeEvent evt =
			new PropertyChangeEvent(source, propertyName, oldValue, newValue);

		for (Iterator iter = targets.iterator(); iter.hasNext();)
		{
			PropertyChangeListener listener =
				(PropertyChangeListener) iter.next();
			listener.propertyChange(evt);
		}
	}

	/**
	 * Add a PropertyChangeListener to the listener list. The listener is
	 * registered for all properties.
	 *
	 * @param listener
	 *            The PropertyChangeListener to be added
	 */
	synchronized void addPropertyChangeListener(
		PropertyChangeListener listener)
	{
		if (listeners == null)
		{
			listeners = new ArrayList(3);
		}

		listeners.add(new WeakPropertyChangeListener(listener));
	}

	/**
	 * Remove a PropertyChangeListener from the listener list. This removes a
	 * PropertyChangeListener that was registered for all properties.
	 *
	 * @param listener
	 *            The PropertyChangeListener to be removed
	 */
	void removePropertyChangeListener(PropertyChangeListener listener)
	{
		if (listeners != null)
		{
			for (Iterator iter = listeners.iterator(); iter.hasNext();)
			{
				WeakPropertyChangeListener weakListener =
					(WeakPropertyChangeListener) iter.next();

				if (listener.equals(weakListener.get()))
				{
					weakListener.clear();
					iter.remove();
				}
			}
		}
	}

	/**
	 * Returns an array of all the listeners that were added to the
	 * PropertyChangeSupport object with addPropertyChangeListener().<br>
	 * <br>
	 * If some listeners have been added with a named property, then the
	 * returned array will be a mixture of PropertyChangeListeners and
	 * <code>PropertyChangeListenerProxy</code>s. If the calling method is
	 * interested in distinguishing the listeners then it must test each element
	 * to see if it's a <code>PropertyChangeListenerProxy</code>, perform the
	 * cast, and examine the parameter.<br>
	 *
	 * <pre>
	 * PropertyChangeListener[] listeners = bean.getPropertyChangeListeners();
	 * for (int i = 0; i &lt; listeners.length; i++)
	 * {
	 *     if (listeners[i] instanceof PropertyChangeListenerProxy)
	 *     {
	 *         PropertyChangeListenerProxy proxy = (PropertyChangeListenerProxy) listeners[i];
	 *         if (proxy.getPropertyName().equals(&quot;foo&quot;))
	 *         {
	 *             // proxy is a PropertyChangeListener which was associated
	 *             // with the property named &quot;foo&quot;
	 *         }
	 *     }
	 * }
	 * </pre>
	 *
	 * @return all of the <code>PropertyChangeListeners</code> added or an
	 *         empty array if no listeners have been added
	 * @see PropertyChangeListenerProxy
	 * @since 1.4
	 */
	synchronized PropertyChangeListener[] getPropertyChangeListeners()
	{
		//		List returnList = new ArrayList();
		//
		//		// Add all the PropertyChangeListeners
		//		if (listeners != null)
		//		{
		//			returnList.addAll(listeners);
		//		}
		//
		//		// Add all the PropertyChangeListenerProxys
		//		if (children != null)
		//		{
		//			Iterator iterator = children.keySet().iterator();
		//			while (iterator.hasNext())
		//			{
		//				String key = (String) iterator.next();
		//				PropertyChangeSupport child = (PropertyChangeSupport) children
		//						.get(key);
		//				PropertyChangeListener[] childListeners = child
		//						.getPropertyChangeListeners();
		//				for (int index = childListeners.length - 1; index >= 0; index--)
		//				{
		//					returnList.add(new PropertyChangeListenerProxy(key,
		//							childListeners[index]));
		//				}
		//			}
		//		}
		//		return (PropertyChangeListener[]) (returnList
		//				.toArray(new PropertyChangeListener[0]));
		throw new UnsupportedOperationException();
	}

	/**
	 * Add a PropertyChangeListener for a specific property. The listener will
	 * be invoked only when a call on firePropertyChange names that specific
	 * property.
	 *
	 * @param propertyName
	 *            The name of the property to listen on.
	 * @param listener
	 *            The PropertyChangeListener to be added
	 */
	synchronized void addPropertyChangeListener(
		String propertyName, PropertyChangeListener listener)
	{
		//		if (children == null)
		//		{
		//			children = new java.util.Hashtable();
		//		}
		//		PropertyChangeSupport child = (PropertyChangeSupport) children
		//				.get(propertyName);
		//		if (child == null)
		//		{
		//			child = new PropertyChangeSupport(source);
		//			children.put(propertyName, child);
		//		}
		//		child.addPropertyChangeListener(listener);
		throw new UnsupportedOperationException();
	}

	/**
	 * Remove a PropertyChangeListener for a specific property.
	 *
	 * @param propertyName
	 *            The name of the property that was listened on.
	 * @param listener
	 *            The PropertyChangeListener to be removed
	 */
	synchronized void removePropertyChangeListener(
		String propertyName, PropertyChangeListener listener)
	{
		//		if (children == null)
		//		{
		//			return;
		//		}
		//		PropertyChangeSupport child = (PropertyChangeSupport) children
		//				.get(propertyName);
		//		if (child == null)
		//		{
		//			return;
		//		}
		//		child.removePropertyChangeListener(listener);
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns an array of all the listeners which have been associated with the
	 * named property.
	 *
	 * @param propertyName String
	 * @return all of the <code>PropertyChangeListeners</code> associated with
	 *         the named property or an empty array if no listeners have been
	 *         added
	 */
	synchronized PropertyChangeListener[] getPropertyChangeListeners(
		String propertyName)
	{
		//		ArrayList returnList = new ArrayList();
		//
		//		if (children != null)
		//		{
		//			PropertyChangeSupport support = (PropertyChangeSupport) children
		//					.get(propertyName);
		//			if (support != null)
		//			{
		//				returnList.addAll(Arrays.asList(support
		//						.getPropertyChangeListeners()));
		//			}
		//		}
		//		return (PropertyChangeListener[]) (returnList
		//				.toArray(new PropertyChangeListener[0]));
		throw new UnsupportedOperationException();
	}

	/**
	 * Report an int bound property update to any registered listeners. No event
	 * is fired if old and new are equal and non-null.<br>
	 * <br>
	 * This is merely a convenience wrapper around the more general
	 * firePropertyChange method that takes Object values.
	 *
	 * @param propertyName
	 *            The programmatic name of the property that was changed.
	 * @return boolean
	 */
	/**
	 * Report a boolean bound property update to any registered listeners. No
	 * event is fired if old and new are equal and non-null.<br>
	 * <br>
	 * This is merely a convenience wrapper around the more general
	 * firePropertyChange method that takes Object values.
	 *
	 * @param propertyName
	 *            The programmatic name of the property that was changed.
	 * @param oldValue
	 *            The old value of the property.
	 * @param newValue
	 *            The new value of the property.
	 */
	/**
	 * Check if there are any listeners for a specific property.
	 *
	 * @param propertyName
	 *            the property name.
	 * @return true if there are ore or more listeners for the given property
	 */
	synchronized boolean hasListeners(String propertyName)
	{
		throw new UnsupportedOperationException();

		//		if (listeners != null && !listeners.isEmpty())
		//		{
		//			// there is a generic listener
		//			return true;
		//		}
		//		if (children != null)
		//		{
		//			PropertyChangeSupport child = (PropertyChangeSupport) children
		//					.get(propertyName);
		//			if (child != null && child.listeners != null)
		//			{
		//				return !child.listeners.isEmpty();
		//			}
		//		}
		//		return false;
	}

	/**
	 * "listeners" lists all the generic listeners.<br>
	 * <br>
	 * This is transient - its state is written in the writeObject method.
	 */
	private List listeners;

	/**
	 * The object to be provided as the "source" for any generated events.
	 *
	 * @serial
	 */
	private Object source;
}
