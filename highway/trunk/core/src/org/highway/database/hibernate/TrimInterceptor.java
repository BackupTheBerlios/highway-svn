package org.highway.database.hibernate;

import java.io.Serializable;
import java.util.Iterator;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.Interceptor;
import net.sf.hibernate.type.Type;

import org.highway.bean.BeanMetadataHome;
import org.highway.helper.StringHelper;
import org.highway.helper.StringHelper.TrimPolicy;

/**
 * This Hibernate interceptor trims string properties when loading or saving a
 * value object, according to the trim policy specified through the
 * {@link org.highway.vo.MetadataHome#TRIMPOLICY} annotation.
 * 
 * 
 * 
 * @see net.sf.hibernate.cfg.Configuration#setInterceptor(net.sf.hibernate.Interceptor)
 */
public class TrimInterceptor implements Interceptor, Serializable
{
	/**
	 * <tt>true</tt> if trimming should occur when loading, <tt>false</tt>
	 * otherwise.
	 */
	private boolean trimOnLoad;
	
	/**
	 * <tt>true</tt> if trimming should occur when saving, <tt>false</tt>
	 * otherwise.
	 */
	private boolean trimOnSave;
	
	/**
	 * Constructs a <tt>TrimInterceptor</tt> instance with both
	 * <tt>trimOnLoad</tt> and <tt>trimOnSave</tt> parameters set to
	 * <tt>true</tt>.
	 * 
	 * @see #TrimInterceptor(boolean, boolean)
	 */
	public TrimInterceptor()
	{
		this(true, true);
	}
	
	/**
	 * Constructs a <tt>TrimInterceptor</tt> instance with the provided
	 * <tt>trimOnLoad</tt> and <tt>trimOnSave</tt> parameters.
	 * 
	 * @param trimOnLoad <tt>true</tt> if trimming should occur on load,
	 *            <tt>false</tt> otherwise
	 * @param trimOnSave <tt>true</tt> if trimming should occur on save,
	 *            <tt>false</tt> otherwise
	 */
	public TrimInterceptor(boolean trimOnLoad, boolean trimOnSave)
	{
		this.trimOnLoad = trimOnLoad;
		this.trimOnSave = trimOnSave;
	}

	/**
	 * Trims string properties when loading an object - if <tt>trimOnLoad</tt>
	 * is <tt>true</tt> - according to the trim policy specified through the
	 * {@link org.highway.vo.MetadataHome#TRIMPOLICY} annotation.
	 * 
	 * @return <tt>true</tt> if the <tt>state</tt> was modified.
	 */
	public boolean onLoad(Object entity, Serializable id, Object[] state,
		String[] propertyNames, Type[] types)
	{
		return trimOnLoad ? trim(entity, state, propertyNames, types) : false;
	}

	/**
	 * Trims string properties when saving an object - if <tt>trimOnSave</tt>
	 * is <tt>true</tt> - according to the trim policy specified through the
	 * {@link org.highway.vo.MetadataHome#TRIMPOLICY} annotation.
	 * 
	 * @return <tt>true</tt> if the <tt>state</tt> was modified.
	 */
	public boolean onSave(Object entity, Serializable id, Object[] state,
		String[] propertyNames, Type[] types)
	{
		return trimOnSave ? trim(entity, state, propertyNames, types) : false;
	}
	
	/**
	 * Returns <tt>null</tt>.
	 */
	public int[] findDirty(Object entity, Serializable id,
		Object[] currentState, Object[] previousState, String[] propertyNames,
		Type[] types)
	{
		return null;
	}

	/**
	 * Returns <tt>null</tt>.
	 */
	public Object instantiate(Class clazz, Serializable id)
	{
		return null;
	}

	/**
	 * Returns <tt>null</tt>.
	 */
	public Boolean isUnsaved(Object entity)
	{
		return null;
	}

	/**
	 * Does nothing.
	 */
	public void onDelete(Object entity, Serializable id, Object[] state,
		String[] propertyNames, Type[] types)
	{
	}

	/**
	 * Returns <tt>false</tt>.
	 */
	public boolean onFlushDirty(Object entity, Serializable id,
		Object[] currentState, Object[] previousState, String[] propertyNames,
		Type[] types)
	{
		return false;
	}

	/**
	 * Does nothing.
	 */
	public void postFlush(Iterator entities)
	{
	}

	/**
	 * Does nothing.
	 */
	public void preFlush(Iterator entities)
	{
	}
	
	/**
	 * Trims string properties according to the trim policy specified through
	 * the {@link org.highway.vo.MetadataHome#TRIMPOLICY} annotation.
	 * 
	 * @return <tt>true</tt> if the <tt>state</tt> was modified.
	 */
	private boolean trim(Object entity, Object[] state, String[] propertyNames,
		Type[] types)
	{
		boolean result = false;
		TrimPolicy policy = null;
		String trimmed = null;
		
		for (int i = 0; i < propertyNames.length; i++)
		{
			if (Hibernate.STRING.equals(types[i])
				&& state[i] instanceof String
				&& (policy = BeanMetadataHome.getPropertyTrimPolicy(entity.getClass(), propertyNames[i])) != null
				&& !state[i].equals(trimmed = StringHelper.trim(policy, (String) state[i])))
			{
				state[i] = trimmed;
				result = true;
			}
		}
		
		return result;
	}
}
