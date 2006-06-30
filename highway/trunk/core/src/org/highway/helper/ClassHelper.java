/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.helper;

import org.highway.exception.TechnicalException;
import java.lang.reflect.Constructor;
import java.util.StringTokenizer;

/**
 * Provides static help methods on classes.
 *
 * 
 */
public class ClassHelper
{
	/**
	 * Dumps the specified class name into the specifird buffer.
	 *
	 * @param buffer the buffer where to dump the class name
	 * @param clazz Class
	 * @param qualified boolean
	 * @return StringBuffer
	 */
	public static StringBuffer dumpClassName(
		StringBuffer buffer, Class clazz, boolean qualified)
	{
		if (clazz == null)
		{
			return buffer;
		}

		String className = clazz.getName();

		if (qualified)
		{
			return buffer.append(className);
		}
		else
		{
			return buffer.append(
				className.substring(className.lastIndexOf('.') + 1));
		}
	}

	/**
	 * Method getClassName
	 * @param clazz Class
	 * @param qualified boolean
	 * @return String
	 */
	public static String getClassName(Class clazz, boolean qualified)
	{
		return dumpClassName(new StringBuffer(25), clazz, qualified).toString();
	}

	/**
	 * Method loadClass
	 * @param qualifiedClassName String
	 * @return Class
	 * @throws ClassNotFoundException
	 */
	public static Class loadClass(String qualifiedClassName)
		throws ClassNotFoundException
	{
		Class primitiveClass =
			PrimitiveHelper.getPrimitiveClass(qualifiedClassName);

		if (primitiveClass != null)
		{
			return primitiveClass;
		}

		ClassLoader loader = Thread.currentThread().getContextClassLoader();

		return Class.forName(encodeTypeName(qualifiedClassName), true, loader);
	}
	
	public static String encodeTypeName(String type ){

		Class primitiveClass =
			PrimitiveHelper.getPrimitiveClass(type);
		if (primitiveClass != null){
			return type;
		}
		
		StringBuffer sb = new StringBuffer();
		String typeClass = type;
		int position = type.indexOf("[");
		if (position !=-1){
			String arrayDimension = type.substring(position);
			StringTokenizer st = new StringTokenizer(arrayDimension, "[");
			while (st.hasMoreElements()){
				st.nextElement();
				sb.append("[");
			}
			typeClass = type.substring(0,position);
			if (typeClass.equals("int")){
				sb.append("I");
			} else if(typeClass.equals("boolean")){
				sb.append("Z");
			}else if(typeClass.equals("short")){
				sb.append("S");
			}else if(typeClass.equals("byte")){
				sb.append("B");
			}else if(typeClass.equals("long")){
				sb.append("J");
			}else if(typeClass.equals("char")){
				sb.append("C");
			}else if(typeClass.equals("double")){
				sb.append("D");
			}else if(typeClass.equals("float")){
				sb.append("F");
			} else {
				sb.append("L");
				sb.append(typeClass);
				sb.append(";");
			}
		}else{
			sb.append(type);
		}
		return sb.toString();
	}

	public static String decodeTypeName (String type){
		
		Class primitiveClass =
			PrimitiveHelper.getPrimitiveClass(type);
		if (primitiveClass != null){
			return type;
		}
		StringBuffer sb = new StringBuffer();
		
		int position = type.lastIndexOf("[")+1;
		if ( position !=0){
			String typeDimension =type.substring(position);
			StringTokenizer st = new StringTokenizer(typeDimension, "[");
			while (st.hasMoreElements()){
				st.nextElement();
				sb.append("[]");
			}
			String typeObject = type.substring(position,position+1);
			if (typeObject.equals("I")){
				sb.insert(0,"int");
			} else if(typeObject.equals("Z")){
				sb.insert(0,"boolean");
			}else if(typeObject.equals("S")){
				sb.insert(0,"short");
			}else if(typeObject.equals("B")){
				sb.insert(0,"byte");
			}else if(typeObject.equals("J")){
				sb.insert(0,"long");
			}else if(typeObject.equals("C")){
				sb.insert(0,"char");
			}else if(typeObject.equals("D")){
				sb.insert(0,"double");
			}else if(typeObject.equals("F")){
				sb.insert(0,"float");
			} else {
				sb.insert(0,type.substring(position+1,type.length()-1));
			}
		
		}else{
			sb.append(type);
		}
		
		
		return sb.toString();
	}
	
	

	/**
	 * Method newInstance
	 * @param qualifiedClassName String
	 * @return Object
	 * @throws ClassNotFoundException
	 */
	public static Object newInstance(String qualifiedClassName)
		throws ClassNotFoundException
	{
		return ClassHelper.newInstance(loadClass(qualifiedClassName));
	}

	/**
	 * Method newInstance
	 * @param clazz Class
	 * @return Object
	 */
	public static Object newInstance(Class clazz)
	{
		try
		{
			Constructor defaultCtor = clazz.getDeclaredConstructor(null);
			defaultCtor.setAccessible(true);

			return defaultCtor.newInstance(null);
		}
		catch (Exception exc)
		{
			/**
			 * @todo attias: develop a better exception management of this
			 * method. The users of this method should be able to know that the
			 * class is not of the right type to be instantiated like this.
			 * Maybe create a new tech exc like NoDefaultConstructorException.
			 */
			throw new TechnicalException(
				"Can not instantiate class " + clazz.getName(), exc);
		}
	}

	/**
	 * Method initializeCheck
	 * @param clazz Class
	 */
	public static void initializeCheck(Class clazz)
	{
		try
		{
			Class.forName(clazz.getName(), true, clazz.getClassLoader());
		}
		catch (ClassNotFoundException cnfe)
		{
			throw new RuntimeException(
				"Impossible d'initialiser la classe " + clazz.getName());
		}
	}
}
