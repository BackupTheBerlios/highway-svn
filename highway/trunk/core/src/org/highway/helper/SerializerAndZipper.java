package org.highway.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

import org.highway.exception.TechnicalException;

public class SerializerAndZipper {

	
	public static byte[] zippAndSerialize(Object o) throws TechnicalException{	
		try {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			DeflaterOutputStream stream = new DeflaterOutputStream(buffer, new Deflater(
			Deflater.BEST_COMPRESSION));
			ObjectOutputStream objectStream = new ObjectOutputStream(stream);
			objectStream.writeObject(o);
			objectStream.flush();
			objectStream.close();
			stream.close();
			buffer.close();
			byte[] data = buffer.toByteArray();
			return data ;
		} catch (IOException e) {
			throw new TechnicalException(
					"failed to serialize and zip object ", e);		}
	}
	
	
	public static Object unzipAndUnserialize(byte[] entry ) throws TechnicalException {
		
		try {
			ByteArrayInputStream buffer = new ByteArrayInputStream(entry);
			InflaterInputStream stream = new InflaterInputStream(buffer);
			ObjectInputStream objectStream = new ObjectInputStream(stream);
			Object obj = objectStream.readObject();
			objectStream.close();
			stream.close();
			buffer.close();
			return obj;
		} catch (IOException e) {
			throw new TechnicalException(
					"failed to serialize and zip object ", e);		
		} catch (ClassNotFoundException e) {
			throw new TechnicalException(
					"failed to serialize and zip object ", e);		
		}
		
		
	}
}
