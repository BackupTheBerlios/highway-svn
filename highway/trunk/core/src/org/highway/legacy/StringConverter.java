/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.legacy;

import org.highway.helper.ReflectHelper;
import org.highway.vo.MetadataHome;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Write an <code>java.lang.Integer</code> on a stream or read a stream to
 * create a <code>java.lang.Integer</code>. This converter can be used as a
 * <code>BasicConverter</code> or as an <code>AnnotationConverter</code>.
 * In the second case, refers to <code>SOCLE_LEGACY_PADDING_VALUE</code> and
 * <code>SOCLE_LEGACY_PADDING_STRATEGY</code> for tag name to use.
 * 
 * @author Olivier Mallassi
 * @since 1.3
 * 
 */
public class StringConverter implements BasicConverter, AnnotationConverter
{
    /**
     * If the padding value is ' ' (understand space) specify <code>space</code>
     * in the javadoc annotation.
     */
    public static final String LEGACY_PADDING_VALUE_SPACE = "space";

    /**
     * If the padding is done by the left. For instance: myValue could be padded
     * to ______myValue where '_' is the padding value.
     */
    public static final String LEGACY_PADDING_LEFT = "left";

    /**
     * If the padding is done by the right. For instance: AZERTY could be padde
     * to AZERTYxxxxx where 'x' is the padding value.
     */
    public static final String LEGACY_PADDING_RIGHT = "right";

    /**
     * If there is no padding.
     */
    public static final String LEGACY_PADDING_NONE = "none";

    /**
     * Sets the padding value to use.
     */
    public static final String SOCLE_LEGACY_PADDING_VALUE = "highway.legacy.padding.value";

    /**
     * Javadoc annotation name for setting the expected length.
     */
    public static final String SOCLE_LEGACY_LENGTH = "highway.legacy.length";

    /**
     * Sets the padding strategy. can have the values none/right/left
     * 
     * @see LEGACY_PADDING_LEFT
     * @see LEGACY_PADDING_RIGHT
     * @see LEGACY_PADDING_NONE
     */
    public static final String SOCLE_LEGACY_PADDING_STRATEGY = "highway.legacy.padding.strategy";

    /**
     * 
     */
    private int expectedLength;

    /**
     * 
     */
    private char paddingValue;

    /**
     * 
     */
    private String paddingStrategy;

    /**
     * Constructs a StringConverter with no specified values.
     * 
     */
    public StringConverter()
    {
    }

    /**
     * Constructs a StingConverter with the specified <code>length</code>,
     * <code>paddingValue</code> and <code>paddingStrategy</code>.
     * 
     * @param length
     * @param paddingValue
     * @param paddingStrategy
     */
    public StringConverter(int length, char paddingValue, String paddingStrategy)
    {
        expectedLength = length;
        this.paddingValue = paddingValue;
        this.paddingStrategy = paddingStrategy;
    }

    /**
     * Writes a property that must be a <code>java.lang.String</code>.
     * 
     */
    public int write(OutputStream outputStream, Object bean, String propertyName) throws ConvertException
    {
        Object propertyValue = ReflectHelper.getProperty(bean, propertyName);

        // get the expected size in meta model
        String size = this.getSizeTag(bean, propertyName);
        String padStrategy = getPaddingStrategyTag(bean, propertyName);
        String padValue = getPaddingValueTag(bean, propertyName);

        return this.writeObject(outputStream, propertyValue, (new Integer(size)).intValue(), padValue.charAt(0),
                padStrategy);
    }

    /**
     * Writes a <code>java.lang.String</code>.
     * 
     */
    public int write(OutputStream outputStream, Object value) throws ConvertException
    {
        return writeObject(outputStream, value, this.expectedLength, this.paddingValue, this.paddingStrategy);
    }

    /**
     * 
     * @param outputStream
     * @param value
     * @param length
     * @param padValue
     * @param padStrat
     * @return
     */
    private int writeObject(OutputStream outputStream, Object value, int length, char padValue, String padStrat)
    {
        try
        {
            String paddedValue = padString(length, (String) value, padStrat, padValue);
            byte[] bytes = paddedValue.getBytes();
            outputStream.write(bytes);

            return bytes.length;
        }
        catch (IOException e)
        {
            throw new ConvertException(e);
        }
    }

    /**
     * 
     * @param bean
     * @param propertyName
     * @return
     */
    private String getPaddingValueTag(Object bean, String propertyName)
    {
        String padValue = MetadataHome.getPropertyMetaValue(bean.getClass(), propertyName,
                StringConverter.SOCLE_LEGACY_PADDING_VALUE);

        if ((padValue == null) || "".equals(padValue))
        {
            throw new ConvertException("xdoclet tag <" + StringConverter.SOCLE_LEGACY_PADDING_VALUE
                    + "> missing for property: " + bean.getClass() + "." + propertyName);
        }

        if (StringConverter.LEGACY_PADDING_VALUE_SPACE.equals(padValue))
        {
            padValue = " ";
        }

        return padValue;
    }

    /**
     * 
     * @param bean
     * @param propertyName
     * @return
     */
    private String getPaddingStrategyTag(Object bean, String propertyName)
    {
        String padStrategy = MetadataHome.getPropertyMetaValue(bean.getClass(), propertyName,
                StringConverter.SOCLE_LEGACY_PADDING_STRATEGY);

        if ((padStrategy == null) || "".equals(padStrategy))
        {
            throw new ConvertException("xdoclet tag <" + StringConverter.SOCLE_LEGACY_PADDING_STRATEGY
                    + "> missing for property: " + bean.getClass() + "." + propertyName);
        }

        return padStrategy;
    }

    /**
     * 
     * @param bean
     * @param propertyName
     * @return
     */
    private String getSizeTag(Object bean, String propertyName)
    {
        String size = MetadataHome.getPropertyMetaValue(bean.getClass(), propertyName,
                StringConverter.SOCLE_LEGACY_LENGTH);

        if ((size == null) || "".equals(size))
        {
            throw new ConvertException("xdoclet tag <" + StringConverter.SOCLE_LEGACY_LENGTH
                    + "> missing for property: " + bean.getClass() + "." + propertyName);
        }

        return size;
    }

    /**
     * enable to unpad a String
     * 
     * @param propertyValue
     * @return
     */
    private Object unpadString(String propertyValue, String paddStrat, char padValue)
    {
        StringBuffer returnedValue = new StringBuffer(propertyValue);

        // if padding options is left, then reverse the string to work with a
        // right padding
        if (StringConverter.LEGACY_PADDING_LEFT.equals(paddStrat))
        {
            returnedValue.reverse();
        }

        // delete the space at the right
        while (true)
        {
            int stringLength = returnedValue.toString().length() - 1;

            if (stringLength != -1)
            {
                // if length == -1, that means there are no longer char in the
                // string
                char lastChar = returnedValue.charAt(stringLength);

                if (lastChar == padValue)
                {
                    returnedValue.deleteCharAt(stringLength);
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

        if (paddStrat == StringConverter.LEGACY_PADDING_LEFT)
        {
            returnedValue = returnedValue.reverse();
        }

        return returnedValue.toString();
    }

    /**
     * enable to pad a String
     * 
     * @param expectedLength
     * @param propertyValueAsString
     * @return
     */
    private String padString(int length, String propertyValueAsString, String padStrat, char padValue)
    {
        // if the propertySize and the expected size, are different,
        // then we pad..
        int paddedValue = length - propertyValueAsString.length();

        // anyway, we add the value
        StringBuffer returnedString = new StringBuffer();
        returnedString.append(propertyValueAsString);

        if (paddedValue >= 0)
        {
            if (StringConverter.LEGACY_PADDING_RIGHT.equals(padStrat))
            {
                for (int i = 0; i < paddedValue; i++)
                {
                    returnedString.append(padValue);
                }
            }
            else if (StringConverter.LEGACY_PADDING_LEFT.equals(padStrat))
            {
                for (int i = 0; i < paddedValue; i++)
                {
                    returnedString.insert(0, padValue);
                }
            }
        }
        else
        {
            throw new ConvertException("values length are too long compare to padding length");
        }

        return returnedString.toString();
    }

    /**
     * Returns a <code>java.lang.String</code>.
     * 
     */
    public Object read(InputStream stream, Object bean, String propertyName) throws ConvertException
    {
        //
        String size = this.getSizeTag(bean, propertyName);
        String padStrategy = getPaddingStrategyTag(bean, propertyName);
        String padValue = getPaddingValueTag(bean, propertyName);
        Integer sizeAsInteger = new Integer(size);

        Object propertyValue = readObject(stream, sizeAsInteger.intValue(), padStrategy, padValue.charAt(0));
        ReflectHelper.setProperty(bean, propertyName, propertyValue);

        return propertyValue;
    }

    /**
     * Returns a <code>java.lang.String</code>.
     * 
     */
    public Object read(InputStream inputStream) throws ConvertException
    {
        return readObject(inputStream, this.expectedLength, this.paddingStrategy, this.paddingValue);
    }

    /**
     * 
     * @param inputStream
     * @param length
     * @param padStrat
     * @param chara
     * @return
     */
    private Object readObject(InputStream inputStream, int length, String padStrat, char chara)
    {
        try
        {
            byte[] object = new byte[length];
            inputStream.read(object, 0, object.length);

            return unpadString(new String(object), padStrat, chara);
        }
        catch (IOException e)
        {
            throw new ConvertException(e);
        }
    }
}
