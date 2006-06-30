package org.highway.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.util.zip.InflaterInputStream;

import org.highway.exception.TechnicalException;
import org.highway.lifecycle.Closeable;

/**
 * <p>Standard implementation of Deserializer. it deserializes and undercompress in an inputStream.</p> 
 * 
 * 
 */
public class StandardZipDeserializer implements Deserializer, Closeable
{
	private ObjectInputStream objectInputStream;

	/**
	 * Constructs the StandardZipDeserializer and used the following contructor 
	 * with a ByteArrayInputStream 
	 * @param bytes: containing the zipped and serialized datas 
	 */
	public StandardZipDeserializer(byte[] bytes)
	{
		this(new ByteArrayInputStream(bytes));
	}

	/**
	 *  Construct a StandardZipDeserializer, Used an InflaterInputStream without 
	 *  option to unzip the inputStream
	 * @param inputStream: containing the zipped and serialized datas
	 */
	public StandardZipDeserializer(InputStream inputStream)
	{
		try
		{
			objectInputStream = new ObjectInputStream(new InflaterInputStream(
					inputStream));
		}
		catch (StreamCorruptedException exc)
		{
			throw new TechnicalException(exc);
		}
		catch (IOException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	public byte readByte()
	{
		try
		{
			return objectInputStream.readByte();
		}
		catch (IOException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	public short readShort()
	{
		try
		{
			return objectInputStream.readShort();
		}
		catch (IOException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	public int readInt()
	{
		try
		{
			return objectInputStream.readInt();
		}
		catch (IOException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	public long readLong()
	{
		try
		{
			return objectInputStream.readLong();
		}
		catch (IOException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	public boolean readBoolean()
	{
		try
		{
			return objectInputStream.readBoolean();
		}
		catch (IOException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	public float readFloat()
	{
		try
		{
			return objectInputStream.readFloat();
		}
		catch (IOException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	public double readDouble()
	{
		try
		{
			return objectInputStream.readDouble();
		}
		catch (IOException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	public char readChar()
	{
		try
		{
			return objectInputStream.readChar();
		}
		catch (IOException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	public Object readObject()
	{
		try
		{
			return objectInputStream.readObject();
		}
		catch (ClassNotFoundException exc)
		{
			throw new TechnicalException(exc);
		}
		catch (IOException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	public void close()
	{
		if (objectInputStream != null) 
		{
			try
			{
				objectInputStream.close();
			}
			catch (IOException exc)
			{
				throw new TechnicalException(exc);
			}
		}
	}

}
