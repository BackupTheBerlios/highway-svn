package org.highway.io;

/**
 * <p>this interface provides serialisation services. The implementation of the interface 
 * decides of the type of support to store the data.</p>
 * 
 * 
 * @author David Attias
 * @since 1.4.2
 */
public interface Deserializer
{
	/**
	 * Read the underlying output and return a byte 
	 * @return
	 */
	byte readByte();

	/**
	 * Read the underlying output and return a short 
	 * @return
	 */
	short readShort();

	/**
	 * Read the underlying output and return a int 
	 * @return
	 */
	int readInt();

	/**
	 * Read the underlying output and return a long 
	 * @return
	 */
	long readLong();

	/**
	 * Read the underlying output and return a boolean 
	 * @return
	 */
	boolean readBoolean();

	/**
	 * Read the underlying output and return a float 
	 * @return
	 */
	float readFloat();

	/**
	 * Read the underlying output and return a double 
	 * @return
	 */
	double readDouble();

	/**
	 * Read the underlying output and return a char 
	 * @return
	 */
	char readChar();

	/**
	 * Read the underlying output and return a Object 
	 * @return
	 */
	Object readObject();
}
