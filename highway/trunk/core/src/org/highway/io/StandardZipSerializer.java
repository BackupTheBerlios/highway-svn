package org.highway.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;

import org.highway.exception.TechnicalException;
import org.highway.lifecycle.Closeable;

/**
 * <p>Standard implementation of Serializer.  it compres and serializes int an outputStream</p>
 * 
 * @since 1.4.2
 */
public class StandardZipSerializer implements Serializer, Closeable
{
	/**
	 * 
	 */
	private ObjectOutputStream objectOutputStream;

	/**
	 * 
	 */
	private OutputStream underlyingOutputStream;

	/**
	 * Construct a StandardZipSerializer
	 */
	public StandardZipSerializer()
	{
		this(new ByteArrayOutputStream());
	}

	/**
	 * Construct a StandardZipSerializer, Used an DeflaterInputStream without 
	 *  option to zip the inputStream
	 * @param outputStream
	 */
	public StandardZipSerializer(OutputStream outputStream)
	{
		try
		{
			underlyingOutputStream = outputStream;
			objectOutputStream = new ObjectOutputStream(
					new DeflaterOutputStream(outputStream));
		}
		catch (IOException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	public void write(byte value)
	{
		try
		{
			objectOutputStream.writeByte(value);
		}
		catch (IOException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	public void write(short value)
	{
		try
		{
			objectOutputStream.writeShort(value);
		}
		catch (IOException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	public void write(int value)
	{
		try
		{
			objectOutputStream.writeInt(value);
		}
		catch (IOException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	public void write(long value)
	{
		try
		{
			objectOutputStream.writeLong(value);
		}
		catch (IOException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	public void write(boolean value)
	{
		try
		{
			objectOutputStream.writeBoolean(value);
		}
		catch (IOException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	public void write(float value)
	{
		try
		{
			objectOutputStream.writeFloat(value);
		}
		catch (IOException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	public void write(double value)
	{
		try
		{
			objectOutputStream.writeDouble(value);
		}
		catch (IOException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	public void write(char value)
	{
		try
		{
			objectOutputStream.writeChar(value);
		}
		catch (IOException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	public void write(Object value)
	{
		try
		{
			objectOutputStream.writeObject(value);
		}
		catch (IOException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	public void write(Object value, Class klass)
	{
		try
		{
			if (klass.isPrimitive())
			{
				unwrapAndWrite(value, klass);
			}
			else
			{
				objectOutputStream.writeObject(value);
			}
		}
		catch (IOException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	private void unwrapAndWrite(Object value, Class klass)
	{
		if (klass == int.class) write(((Number) value).intValue());
		else if (klass == boolean.class) write(((Boolean) value).booleanValue());
		else if (klass == char.class) write(((Character) value).charValue());
		else if (klass == long.class) write(((Number) value).longValue());
		else if (klass == byte.class) write(((Number) value).byteValue());
		else if (klass == short.class) write(((Number) value).shortValue());
		else if (klass == double.class) write(((Number) value).doubleValue());
		else if (klass == float.class) write(((Number) value).floatValue());
	}

	public OutputStream getOutputStream()
	{
		return underlyingOutputStream;
	}

	/**
	 * return the content of the stream 
	 */
	public byte[] toByteArray()
	{
		if (!(underlyingOutputStream instanceof ByteArrayOutputStream)) return null;
		
			close();
			return ((ByteArrayOutputStream) underlyingOutputStream).toByteArray();
		
	}

	/**
	 * Flush and close the stream 
	 */
	public void close()
	{
		if (objectOutputStream != null)
		{
			try
			{
				objectOutputStream.flush();
				objectOutputStream.close();
			}
			catch (IOException exc)
			{
				throw new TechnicalException(exc);
			}
		}
	}
}
