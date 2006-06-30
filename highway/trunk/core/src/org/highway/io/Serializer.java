package org.highway.io;

import java.io.OutputStream;

/**
 * <p> this interface is defined to use specific serialization. The implementation 
 * of the interface decides of the type of support to store the data  </p>
 * 
 * 
 */
public interface Serializer
{
	/**
	 * Write the byte value to the underlying output  
	 * @param value: byte to write
	 */
	void write(byte value);

	/**
	 * Write the short value to the underlying output  
	 * @param value: byte to write
	 */
	void write(short value);

	/**
	 * Write the int value to the underlying output  
	 * @param value: byte to write
	 */
	void write(int value);

	/**
	 * Write the long value to the underlying output  
	 * @param value: byte to write
	 */
	void write(long value);

	/**
	 * Write the boolean value to the underlying output  
	 * @param value: byte to write
	 */
	void write(boolean value);

	/**
	 * Write the float value to the underlying output  
	 * @param value: byte to write
	 */
	void write(float value);

	/**
	 * Write the double value to the underlying output  
	 * @param value: byte to write
	 */
	void write(double value);

	/**
	 * Write the char value to the underlying output  
	 * @param value: byte to write
	 */
	void write(char value);

	/**
	 * Write the object value to the underlying output  
	 * @param value: byte to write
	 */
	void write(Object value);

	/**
	 * Serializes the object to the type defined by the class. Normally it used to
	 * serialize the object to a primitive type 
	 * @param value: Object to serialize
	 * @param klass: Class to convert the object (normally primitive type)
	 */
	void write(Object value, Class klass);

	/**
	 * Returns the underlying OutputStream.
	 */
	OutputStream getOutputStream();

	/**
	 * If the underlying OutputStream is ByteArrayOutputStream, returns its byte
	 * array. Returns null otherwise.
	 */
	byte[] toByteArray();
}
