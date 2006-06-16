/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.legacy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.highway.helper.ReflectHelper;
import org.highway.vo.MetadataHome;

/**
 * Write an <code>java.lang.Integer</code> on a stream or read a stream to
 * create a <code>java.lang.Integer</code>. This converter can be used as a
 * <code>BasicConverter</code> or as an <code>AnnotationConverter</code>.
 * In the second case, refers to <code>SOCLE_LEGACY_PADDING_VALUE</code> for
 * tag name to use.
 * 
 * @author Olivier Mallassi
 * @since 1.3
 * 
 */
public class IntegerConverter implements BasicConverter, AnnotationConverter
{
    /**
     * Javadoc annotation name for setting the expected length.
     */
    public static final String SOCLE_LEGACY_LENGTH = "highway.legacy.length";

    /**
     * Javadoc annotation name for setting the padding value. The padding will
     * automatically be done by the left with this value. so 134 will be padded
     * to 000134 where 0 is the padding value.
     */
    public static final String SOCLE_LEGACY_PADDING_VALUE = "highway.legacy.padding.value";

    /**
     * 
     */
    private int expectedLength;

    /**
     * 
     */
    private boolean useAnnotationForExpectedLength;
    
    /**
     * 
     */
    private char paddingValue;

    /**
     * 
     */
    private boolean useAnnotationForPaddingValue;
    
    /**
     * Constructs an IntegerConverter with no default length or padding character.
     * Annotation will be used to get length and padding character.
     */
    public IntegerConverter()
    {
    	this.useAnnotationForExpectedLength = true;
    	this.useAnnotationForPaddingValue = true;
    }

    
    /**
     * Constructs an IntegerConverter with the specified <code>length</code>.
     * Annotation will be used to get length.
     */
    public IntegerConverter(int length)
    {
        this.expectedLength = length;
    	this.useAnnotationForPaddingValue = true;
    }

    /**
     * Constructs an IntegerConverter with the specified <code>paddingValue</code>.
     * Annotation will be used to get the padding character.
     */
    public IntegerConverter(char paddingValue)
    {
        this.paddingValue = paddingValue;
    	this.useAnnotationForExpectedLength = true;
    }

    /**
     * Constructs an IntegerConverter with the specified <code>length</code>
     * and <code>paddingValue</code>.
     * 
     * @param length
     */
    public IntegerConverter(int length, char paddingValue)
    {
        this.expectedLength = length;
        this.paddingValue = paddingValue;
    }

    /**
     * Writes a property that must be a <code>java.lang.Integer</code>.
     */
    public int write(OutputStream outputStream, Object bean, String propertyName) throws ConvertException
    {
        Object propertyValue = ReflectHelper.getProperty(bean, propertyName);

    	if (!(propertyValue instanceof Integer))
    	{
    		throw new ConvertException("not an Integer");
    	}
    	
        return this.write(outputStream, propertyValue,
        		this.getExpectedLength(bean, propertyName),
        		getPaddingCharacter(bean, propertyName));
    }

    /**
     * Writes a <code>java.lang.Integer</code>.
     */
    public int write(OutputStream outputStream, Object value) throws ConvertException
    {
        return write(outputStream, value, this.expectedLength, this.paddingValue);
    }

    private int write(OutputStream outputStream, Object value, int length, char padValue)
    {
        try
        {
            // the downcast can be done...propertyValue should be a String
            String paddedValue = this.padNumber(length, (Number) value, padValue);
            byte[] array = paddedValue.getBytes();
            outputStream.write(array);

            return array.length;
        }
        catch (IOException e)
        {
            throw new ConvertException(e);
        }
    }

    /**
     * Returns a <code>java.lang.Integer</code>.
     */
    public Object read(InputStream stream, Object bean, String propertyName) throws ConvertException
    {
        Object propertyValue = readObject(stream,
        		this.getExpectedLength(bean, propertyName),
        		this.getPaddingCharacter(bean, propertyName));

        ReflectHelper.setProperty(bean, propertyName, propertyValue);

        return propertyValue;
    }

    /**
     * Returns a <code>java.lang.Integer</code>.
     */
    public Object read(InputStream inputStream) throws ConvertException
    {
        return this.readObject(inputStream, this.expectedLength, this.paddingValue);
    }

    private Object readObject(InputStream stream, int sizeAsInteger, char paddingValue)
    {
        try
        {
            byte[] object = new byte[sizeAsInteger];
            stream.read(object, 0, object.length);
            String unpadValue = this.unpadNumber(new String(object), paddingValue);
            if (unpadValue != null && !"".equals(unpadValue))
            {
                return new Integer(unpadValue);
            }
            return null;
        }
        catch (IOException e)
        {
            throw new ConvertException(e);
        }
    }

    private int getExpectedLength(Object bean, String propertyName)
    {
    	if (useAnnotationForExpectedLength)
    	{
	        try
	        {
        		return Integer.parseInt(
       				MetadataHome.getPropertyMetaValue(bean.getClass(), propertyName,
   						SOCLE_LEGACY_LENGTH));
			}
	        catch (Exception e)
	        {
	            throw new ConvertException("Annotation " + SOCLE_LEGACY_LENGTH
	                    + " is missing or incorrect for property "
	                    + bean.getClass().getName() + '.' + propertyName, e);
			}
    	}
    	else
    	{
    		return expectedLength;
    	}
    }


    private char getPaddingCharacter(Object bean, String propertyName)
    {
    	if (useAnnotationForPaddingValue)
    	{
	        String value = MetadataHome.getPropertyMetaValue(bean.getClass(),
	        		propertyName, SOCLE_LEGACY_PADDING_VALUE);
	        
	        if (value == null || value.length() != 1)
	        {
	            throw new ConvertException("Annotation " + SOCLE_LEGACY_PADDING_VALUE
	                    + " is missing or incorrect for property "
	                    + bean.getClass().getName() + '.' + propertyName);
	        }
	
	        return value.charAt(0);
    	}
    	else
    	{
    		return paddingValue;
    	}
    }

    private String padNumber(int length, Number propertyValueAsNumber, char padValue)
    {
        StringBuffer returnedString = new StringBuffer();

        // if the propertySize and the expected size, are different,
        // then we pad..
        int paddedValue = length - propertyValueAsNumber.toString().length();

        if (paddedValue >= 0)
        {
            for (int i = 0; i < paddedValue; i++)
            {
                returnedString.append(padValue);
            }
        }
        else
        {
            throw new ConvertException("values length are too long compare to padding length");
        }

        // anyway, we add the value
        returnedString.append(propertyValueAsNumber);

        return returnedString.toString();
    }

    private String unpadNumber(String propertyValue, char padValue)
    {
        StringBuffer returnedString = new StringBuffer(propertyValue);
        //
        while (true)
        {
            if (returnedString.length() > 0)
            {
                char firstChar = returnedString.charAt(0);
                if (firstChar == padValue)
                {
                    returnedString.deleteCharAt(0);
                }
                else
                {
                    break;
                }
            }
            else
            {
                break;
            }
        }

        return returnedString.toString();
    }
}
