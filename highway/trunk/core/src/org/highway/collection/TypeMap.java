package org.highway.collection;

import java.util.HashMap;

public class TypeMap extends HashMap {

	public TypeMap()
	{
	}
	
	public TypeMap(int initialCapacity)
	{
		super(initialCapacity);
	}
	
	public TypeMap(int initialCapacity, float loadFactor)
	{
		super(initialCapacity, loadFactor);
	}
	
	public TypeMap(TypeMap typeMap)
	{
		super(typeMap);
	}
	
	public Object put(Object key, Object value)
	{
		return super.put(getClass(key), value);
	}

	public boolean containsKey(Object arg0) {
		return get(arg0) != null;
	}

	public Object get(Object key) {
		Class type = getClass(key);
		Object value = super.get(type);
		while (value == null)
		{
			Class[] interfaces = type.getInterfaces();
			for (int i = 0; i < interfaces.length; i++) {
				//FIXME
			}
			if (type.equals(Object.class)) break;
			type = type.getSuperclass();
			value = super.get(type);
		}
		return value;
	}
	
	private Class getClass(Object key) {
		return (key instanceof Class) ? (Class) key : key.getClass();
	}
}
