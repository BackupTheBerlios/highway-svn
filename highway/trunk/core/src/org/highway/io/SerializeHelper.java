/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.io;

import org.highway.exception.DoNotInstantiateException;
import org.highway.exception.TechnicalException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Provides static help methods to serialize objects.
 * 
 * 
 */
public final class SerializeHelper
{
	/**
	 * Do not instanciate this class.
	 */
	private SerializeHelper()
	{
		throw new DoNotInstantiateException();
	}

	/**
	 * Clones the specified object through serialization.
	 * @param object the object to clone
	 * @return the clone
	 */
	public static Object clone(Object object)
	{
		return toObject(toByte(object));
	}

	/**
	 * Converts the specified object into a byte array through serialization.
	 * @param object the object to serialize
	 * @return the byte array, serialized form of the specified object
	 */
	public static byte[] toByte(Object object)
	{
		try
		{
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			ObjectOutputStream stream = new ObjectOutputStream(buffer);
			stream.writeObject(object);
			stream.flush();

			byte[] bytes = buffer.toByteArray();

			// we should close the stream in a finally block
			// but it does not worth the pain since no resources
			// realy need to be closed
			stream.close();

			return bytes;
		}
		catch (Exception exc)
		{
			throw new TechnicalException(
				"failed to serialize object = " + object, exc);
		}
	}

	/**
	 * Converts the specified byte array into an object.
	 * @param bytes byte array representing a serialized object
	 * @return the deserialized object
	 */
	public static Object toObject(byte[] bytes)
	{
		try
		{
			ByteArrayInputStream buffer = new ByteArrayInputStream(bytes);
			ObjectInputStream stream = new ObjectInputStream(buffer);
			Object object = stream.readObject();

			// we should close the stream in a finally block
			// but it does not worth the pain since no resources
			// realy need to be closed
			stream.close();

			return object;
		}
		catch (Exception exc)
		{
			throw new TechnicalException(
				"failed to unserialize byte array", exc);
		}
	}
}
