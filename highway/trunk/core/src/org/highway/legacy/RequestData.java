/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.legacy;

import java.io.ByteArrayOutputStream;

import org.highway.helper.ReflectHelper;
import org.highway.init.InitException;
import org.highway.vo.MetadataHome;

/**
 * Write values.
 * 
 * @author Olivier Mallassi
 * @since 1.3
 * 
 */
public class RequestData
{
    /**
     * 
     */
    private ByteArrayOutputStream outStream;

    /**
     * 
     */
    private ConverterMap converters;

    /**
     * Sets the converter name. That's the name of the constant attribute
     * declared in the specific implementation of ConverterMap.
     */
    public static final String SOCLE_LEGACY_CONVERTER_NAME = "highway.legacy.converter.name";

    /**
     * Constructs a request data with the specified implementation of
     * <code>ConverterMap</code>.
     */
    public RequestData(ConverterMap converterBase)
    {
        this.outStream = new ByteArrayOutputStream();
        this.converters = converterBase;
    }

    /**
     * Adds an input param to the message. The default behaviour is: <br> -
     * check the tag <code>highway.cics.converter.name</code> on the
     * propertyName <br> - if exists, use it <br> - if not, try to find to
     * <code>AnnotationConverter</code> for the property type. and use it.
     * <br> - if none exists, try to use a <code>BasicConverter</code> for the
     * property java type.
     * 
     * @param bean
     *            the javabean object
     * @param propertyName
     *            the java bean property name
     * @return the lenght of the written element
     * @throws ConvertException
     *             if the converter name specified on <code>propertyName</code>
     *             is unknown in <code>ConverterMap</code> or if no converters
     *             have been setted for the property java type
     * @throws UnknownException
     *             for all other pbs
     */
    public int write(Object bean, String propertyName) throws ConvertException
    {
        int length = 0;

        try
        {
            // retrieve the converter name
            String converterName = MetadataHome.getPropertyMetaValue(bean.getClass(), propertyName,
                    RequestData.SOCLE_LEGACY_CONVERTER_NAME);

            // no check can be done on the existence (or not) or the tag
            // converterName.
            // Indeed, if it does not exists, we go to the else...
            if (converterName != null)
            {
                try
                {
                    BasicConverter converter = this.converters.getBasicConverter(converterName);

                    if (converter != null)
                    {
                        // retrieve the propertyValue
                        Object propertyValue = ReflectHelper.getProperty(bean, propertyName);
                        length = converter.write(this.outStream, propertyValue);
                    }

                    AnnotationConverter annBasedConverter = this.converters.getAnnotatedConverter(converterName);

                    if (annBasedConverter != null)
                    {
                        length = annBasedConverter.write(this.outStream, bean, propertyName);
                    }
                }
                catch (InitException e)
                {
                    throw new ConvertException("converter for name <" + converterName + "> has not been found");
                }
            }

            // try to find the converter for the type
            else
            {
                Class type = null;

                try
                {
                    type = ReflectHelper.getPropertyType(bean.getClass(), propertyName);

                    // look for an AnnotationConverter for the type
                    AnnotationConverter converter = this.converters.getTypedAnnotationConverter(type);

                    if (converter != null)
                    {
                        length = converter.write(this.outStream, bean, propertyName);
                    }
                    else
                    {
                        // if you are here, that means there are no
                        // AnnotationConverter
                        // for the type...so we look for a BasicConverter
                        BasicConverter basicConverter = this.converters.getTypedBasicConverter(type);

                        if (basicConverter != null)
                        {
                            // retrieve the propertyValue
                            Object propertyValue = ReflectHelper.getProperty(bean, propertyName);
                            length = basicConverter.write(this.outStream, propertyValue);
                        }
                    }
                }
                catch (InitException e)
                {
                    throw new ConvertException("converter for type <" + type + "> has not been found");
                }
            }

            return length;
        }
        catch (ConvertException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new UnknownException("error during writing datas", e);
        }
    }

    /**
     * Writes the value based on a specified <code>converter</code>.
     * 
     * @param value
     *            the value to write
     * @param converter
     *            the converter to use
     * @return the lenght of the written element
     */
    public int write(Object value, BasicConverter converter)
    {
        try
        {
            return converter.write(this.outStream, value);
        }
        catch (ConvertException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new UnknownException("error during writing datas", e);
        }
    }

    /**
     * Resets the encapsulated stream.
     * 
     */
    public void reset()
    {
    	this.outStream.reset();
    }

    /**
     * 
     * 
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        // do not catch anything. i guess there is nothing to catch
        return this.outStream.toString();
    }

    /**
     * Returns a byte array representation of <code>this</code> object.
     */
    public byte[] toByteArray()
    {
        try
        {
            return this.outStream.toByteArray();
        }
        catch (Exception e)
        {
            throw new UnknownException("error while calling toByteArray()", e);
        }
    }

    /**
     * Writes a byte array.
     * 
     */
    public void write(byte[] b)
    {
        try
        {
            this.outStream.write(b);
        }
        catch (Exception e)
        {
            throw new UnknownException("error while calling toByteArray()", e);
        }
    }

    /**
     * Returns the current size of the buffer.
     * 
     */
    public int size()
    {
        // do not catch anything. i guess there is nothing to catch
        return this.outStream.size();
    }
}
