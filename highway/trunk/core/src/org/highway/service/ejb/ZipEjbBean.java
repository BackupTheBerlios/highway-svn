package org.highway.service.ejb;

import org.highway.io.Deserializer;
import org.highway.io.Serializer;
import org.highway.io.StandardZipDeserializer;
import org.highway.io.StandardZipSerializer;

/**
 * Abstract bean implementation class used for compressed EJB based service access.<br>
 * Developers should not use this class.
 *
 * 
 * 
 */
public abstract class ZipEjbBean extends EjbBean
{
	protected Serializer getSerializer()
	{
		return new StandardZipSerializer();
	}
	
	protected Deserializer getDeserializer(byte[] bytes)
	{
		return new StandardZipDeserializer(bytes);
	}
}
