package org.highway.bean;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class PropertySizeHome {

	private static Map typeMap = new HashMap();
	
	static 
	{
		setSizeCalculator(String.class, new StringSizeCalculator());
		setSizeCalculator(Collection.class, new CollectionSizeCalculator());
		setSizeCalculator(Map.class, new MapSizeCalculator());
	}
	
	public static void setSizeCalculator(Class type, SizeCalculator sizeCalculator)
	{
		typeMap.put(type, sizeCalculator);
	}
	
	private static SizeCalculator getSizeCalculator(Class type)
	{
		Iterator keys = typeMap.keySet().iterator();
		while (keys.hasNext()) {
			Class key = (Class) keys.next();
			if (key.isAssignableFrom(type))
				return (SizeCalculator) typeMap.get(key);
		}
		return null;
	}
	
	public static int size(Object object)
	{
		if (object.getClass().isArray())
		{
			return Array.getLength(object);
		}
		
		SizeCalculator calculator = getSizeCalculator(object.getClass());
		if (calculator == null)
		{
			throw new IllegalArgumentException(
					"no SizeCalculator for class " + object.getClass());
		}
		return calculator.size(object);
	}
}

class StringSizeCalculator implements SizeCalculator
{
	public int size(Object object) {
		return ((String)object).length();
	}	
}

class CollectionSizeCalculator implements SizeCalculator
{
	public int size(Object object) {
		return ((Collection)object).size();
	}	
}

class MapSizeCalculator implements SizeCalculator
{
	public int size(Object object) {
		return ((Map)object).size();
	}	
}
