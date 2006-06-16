package org.highway.legacy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.highway.helper.ReflectHelper;
import org.highway.vo.Decimal;
import org.highway.vo.MetadataHome;

/**
 * Converter for org.highway.vo.Decimal object. The padding will be done
 * by the following way:
 * 
 * <pre>
 *                                                                                                                                                -1234,56 will be padded to -1234,56000 with 0 as padding value.
 * </pre>
 * 
 * @author Olivier Mallassi
 * @since 1.3
 * 
 */
public class DecimalConverter implements AnnotationConverter, BasicConverter
{
    /**
     * Javadoc annotation name for setting the padding value.
     */
    public static final String SOCLE_LEGACY_PADDING_VALUE = "highway.legacy.padding.value";

    /**
     * Javadoc annotation name for setting the length of the integer part.
     */
    public static final String SOCLE_LEGACY_INT_PART_LENGTH = "highway.legacy.int.part.length";

    /**
     * Javadoc annotation name for setting the length of the decimal part
     */
    public static final String SOCLE_LEGACY_DEC_PART_LENGTH = "highway.legacy.dec.part.length";

    /**
     * Javadoc annotation name for setting the separator. If no separator must
     * be used, you can either not write the tag or write the tag with the value
     * none.
     */
    public static final String SOCLE_LEGACY_SEPARATOR = "highway.legacy.separator";

    /**
     * If no separator must be used, the tag <code>highway.legacy.separator</code>
     * can be used with the value none: <code>@highway.legacy.separator none<code>
     * </pre>
     */
    public static final String SOCLE_LEGACY_NO_SEPARATOR = "none";

    /**
     * 
     */
    private int intPartLength;

    /**
     * 
     */
    private int decimalPartLength;

    /**
     * 
     */
    private char paddingValue;

    /**
     * 
     */
    private Character separator;

    /**
     * Constructs a DecimalConverter with no default value.
     * 
     */
    public DecimalConverter()
    {
        super();
    }

    /**
     * Constructs a DecimalConverter with the specified value. For instance
     * 
     * <pre>
     *                                                                                                                                                                                              1234,566 will be converter to 001324,566000 where 0 is the padding value.
     * </pre>
     * 
     * @param integerPartLength
     *            Length of the integer part
     * @param intPartPaddingValue
     *            Padding value for the integer part
     * @param decimalPartLength
     *            Length of the decimal part
     * @param decimalPartPaddingValue
     *            Padding value for the decimal part
     * @param separator
     *            The separator to use
     */
    public DecimalConverter(int integerPartLength, int decimalPartLength, char paddingValue, char separator)
    {
        this(integerPartLength, decimalPartLength, paddingValue);
        this.separator = new Character(separator);
    }

    /**
     * Constructs a DecimalConverter with the specified value but with no
     * separator. For instance
     * 
     * <pre>
     *                                                                                                                                                                                              1234,566 will be converter to 001324566000 where 0 is the padding value.
     * </pre>
     * 
     * @param integerPartLength
     *            Length of the integer part
     * @param intPartPaddingValue
     *            Padding value for the integer part
     * @param decimalPartLength
     *            Length of the decimal part
     * @param decimalPartPaddingValue
     *            Padding value for the decimal part
     */
    public DecimalConverter(int integerPartLength, int decimalPartLength, char paddingValue)
    {
        this.intPartLength = integerPartLength;
        this.decimalPartLength = decimalPartLength;
        this.paddingValue = paddingValue;
    }

    /**
     * Writes a <code>org.highway.vo.Decimal</code>
     * 
     */
    public int write(OutputStream outputStream, Object bean, String propertyName) throws ConvertException
    {
        Object propertyValue = ReflectHelper.getProperty(bean, propertyName);

        // get the expected size in meta model
        String integerPartSize = this.getIntegerPartSizeTag(bean, propertyName);
        Integer integerPartSizeAsInteger = new Integer(integerPartSize);
        //
        String decimalPartSize = this.getDecimalPartSizeTag(bean, propertyName);
        Integer decimalPartSizeAsInteger = new Integer(decimalPartSize);
        //
        Character separator = this.getSeparatorTag(bean, propertyName);
        //
        char padValue = getPaddingValueTag(bean, propertyName);

        return this.writeObject(outputStream, propertyValue, integerPartSizeAsInteger.intValue(),
                decimalPartSizeAsInteger.intValue(), padValue, separator);
    }

    /**
     * 
     * @param bean
     * @param propertyName
     * @return
     */
    private String getIntegerPartSizeTag(Object bean, String propertyName)
    {
        String size = MetadataHome.getPropertyMetaValue(bean.getClass(), propertyName,
                DecimalConverter.SOCLE_LEGACY_INT_PART_LENGTH);

        if ((size == null) || "".equals(size))
        {
            throw new ConvertException("xdoclet tag <" + DecimalConverter.SOCLE_LEGACY_INT_PART_LENGTH
                    + "> missing for property: " + bean.getClass() + "." + propertyName);
        }

        return size;
    }

    /**
     * 
     * @param bean
     * @param propertyName
     * @return
     */
    private Character getSeparatorTag(Object bean, String propertyName)
    {
        String value = MetadataHome.getPropertyMetaValue(bean.getClass(), propertyName,
                DecimalConverter.SOCLE_LEGACY_SEPARATOR);
        if (value != null && !"".equals(value) && !DecimalConverter.SOCLE_LEGACY_NO_SEPARATOR.equals(value))
        {
            if (value.length() == 1)
            {
                return new Character(value.charAt(0));
            }

            throw new ConvertException("value of tag <" + DecimalConverter.SOCLE_LEGACY_SEPARATOR
                    + "> must be a character");
        }
        else if (DecimalConverter.SOCLE_LEGACY_NO_SEPARATOR.equals(value))
        {
            // this case is not really necessary but i guess the code is easier
            // to read.
            return null;
        }

        return null;
    }

    /**
     * 
     * @param bean
     * @param propertyName
     * @return
     */
    private String getDecimalPartSizeTag(Object bean, String propertyName)
    {
        String size = MetadataHome.getPropertyMetaValue(bean.getClass(), propertyName,
                DecimalConverter.SOCLE_LEGACY_DEC_PART_LENGTH);

        if ((size == null) || "".equals(size))
        {
            throw new ConvertException("xdoclet tag <" + DecimalConverter.SOCLE_LEGACY_DEC_PART_LENGTH
                    + "> missing for property: " + bean.getClass() + "." + propertyName);
        }

        return size;
    }

    /**
     * 
     * @param bean
     * @param propertyName
     * @return
     */
    private char getPaddingValueTag(Object bean, String propertyName)
    {
        String padValue = MetadataHome.getPropertyMetaValue(bean.getClass(), propertyName,
                DecimalConverter.SOCLE_LEGACY_PADDING_VALUE);

        if ((padValue == null) || "".equals(padValue))
        {
            throw new ConvertException("xdoclet tag <" + DecimalConverter.SOCLE_LEGACY_PADDING_VALUE
                    + "> missing for property: " + bean.getClass() + "." + propertyName);
        }
        if (padValue.length() != 1)
        {
            throw new ConvertException("value of tag <" + DecimalConverter.SOCLE_LEGACY_PADDING_VALUE
                    + "> must be a char");
        }

        return padValue.charAt(0);
    }

    /**
     * Writes a <code>org.highway.vo.Decimal</code>.
     */
    public int write(OutputStream outputStream, Object value) throws ConvertException
    {
        return this.writeObject(outputStream, value, this.intPartLength, this.decimalPartLength, this.paddingValue,
                this.separator);
    }

    /**
     * 
     * @param outputStream
     * @param value
     * @param length
     * @param padValue
     * @return
     */
    private int writeObject(OutputStream outputStream, Object value, int integerPartLength, int decimalPartLength,
            char padValue, Character separator)
    {
        try
        {
            // the downcast can be done...propertyValue should be a String
            String paddedValue = this.padNumber(integerPartLength, decimalPartLength, padValue, separator,
                    (Decimal) value);
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
     * pad the number
     * 
     * @param propertyValueAsNumber
     * @return
     */
    private String padNumber(int integerPartLength, int decimalPartLength, char padValue, Character separator,
            Decimal propertyValueAsNumber)
    {
        StringBuffer returnedString = new StringBuffer();
        // pad the integer Part
        long integerPart = propertyValueAsNumber.getIntegerPart();
        // if the propertySize and the expected size, are different,
        // then we pad..
        String integerPartAsString = Long.toString(integerPart);
        int paddedValue = integerPartLength - integerPartAsString.length();
        if (paddedValue >= 0)
        {
            //
            boolean isNegative = integerPartAsString.startsWith("-");
            if (isNegative)
            {
                returnedString.append(integerPartAsString);
                // if this is negative, there is one character less.
                for (int i = 0; i < paddedValue; i++)
                {
                    returnedString.insert(1, padValue);
                }
            }
            else
            {
                for (int i = 0; i < paddedValue; i++)
                {
                    returnedString.append(padValue);
                }
                returnedString.append(integerPartAsString);
            }
        }
        else
        {
            throw new ConvertException("values length are too long compare to padding length");
        }
        // add the separator
        if (separator != null)
        {
            returnedString.append(separator);
        }
        // pad the decimal part.
        long decimalPart = propertyValueAsNumber.getDecimalPart();
        String decimalPartAsString = Long.toString(decimalPart);
        paddedValue = decimalPartLength - decimalPartAsString.length();
        returnedString.append(decimalPartAsString);
        if (paddedValue > 0)
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

        return returnedString.toString();
    }

    /**
     * Returns a <code>org.highway.vo.Decimal</code>.
     * 
     */
    public Object read(InputStream inputStream) throws ConvertException
    {
        return this.readObject(inputStream, this.intPartLength, this.decimalPartLength, this.paddingValue,
                this.separator);
    }

    /**
     * Returns a <code>org.highway.vo.Decimal</code>.
     * 
     */
    public Object read(InputStream stream, Object bean, String propertyName) throws ConvertException
    {
        // extract the substring from the message
        String integerPartSize = this.getIntegerPartSizeTag(bean, propertyName);
        Integer integerPartSizeAsInteger = new Integer(integerPartSize);
        //
        String decimalPartSize = this.getDecimalPartSizeTag(bean, propertyName);
        Integer decimalPartSizeAsInteger = new Integer(decimalPartSize);
        //
        Character separator = this.getSeparatorTag(bean, propertyName);

        char paddingValue = this.getPaddingValueTag(bean, propertyName);

        Object propertyValue = readObject(stream, integerPartSizeAsInteger.intValue(), decimalPartSizeAsInteger
                .intValue(), paddingValue, separator);

        ReflectHelper.setProperty(bean, propertyName, propertyValue);

        return propertyValue;
    }

    /**
     * 
     * @param stream
     * @param sizeAsInteger
     * @return
     */
    private Object readObject(InputStream stream, int intPartLength, int decPartLength, char paddingValue,
            Character separator)
    {
        try
        {
            int sizeAsInteger = intPartLength + decPartLength;
            if (separator != null)
            {
                sizeAsInteger += 1;
            }

            byte[] object = new byte[sizeAsInteger];
            stream.read(object, 0, object.length);
            // the 0 will be trimed by constructor
            String decimalValue = unpadNumber(new String(object), intPartLength, decPartLength, paddingValue, separator);
            return new Decimal(decimalValue);
        }
        catch (IOException e)
        {
            throw new ConvertException(e);
        }
    }

    /**
     * 
     * @param value
     * @param intPartLength
     * @param decPartLength
     * @param paddingValue
     * @return
     */
    private String unpadNumber(String value, int intPartLength, int decPartLength, char paddingValue,
            Character separator)
    {
        StringBuffer returnedValue = new StringBuffer();
        // integer Part
        String integerPart = value.substring(0, intPartLength);
        boolean isNegative = integerPart.startsWith("-");
        int index = isNegative ? 1 : 0;
        StringBuffer integerPartAsBuffer = new StringBuffer(integerPart);
        while (true)
        {
            if (integerPartAsBuffer.length() > 0)
            {
                char firstChar = integerPartAsBuffer.charAt(index);
                if (firstChar == paddingValue)
                {
                    integerPartAsBuffer.deleteCharAt(index);
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
        returnedValue.append(integerPartAsBuffer);
        //
        if (separator != null)
        {
            returnedValue.append(separator);
        }
        else
        {
            // if no separator were used, just add one that is used by Decimal
            returnedValue.append(".");
        }
        //
        String decimalPart = value.substring(value.length() - decPartLength);
        StringBuffer decimalPartAsBuffer = new StringBuffer(decimalPart);
        while (true)
        {
            if (decimalPartAsBuffer.length() > 0)
            {
                char lastChar = decimalPartAsBuffer.charAt(decimalPartAsBuffer.length() - 1);
                if (lastChar == paddingValue)
                {
                    decimalPartAsBuffer.deleteCharAt(decimalPartAsBuffer.length() - 1);
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
        //
        returnedValue.append(decimalPartAsBuffer);

        return returnedValue.toString();
    }
}
