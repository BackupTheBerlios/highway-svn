/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.legacy;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.highway.helper.ReflectHelper;
import org.highway.init.InitException;
import org.highway.vo.MetadataHome;

/**
 * Read values.
 * 
 * @author Olivier Mallassi
 * @since 1.3
 * 
 */
public class ResponseData
{
    /**
     * 
     */
    private InputStream inStream;

    /**
     * 
     */
    private ConverterMap converters;

    /**
     * string representation of <code>this</code>
     */
    private String toString;

    /**
     * Constructs a response data with the specified implementation of
     * <code>ConverterMap</code> and a byte array.
     */
    public ResponseData(byte[] b, ConverterMap converterBase)
    {
        // the attribute toString is calculated once in order to read several
        // times the encapsulated stream
        this.toString = new String(b);
        //
        this.inStream = new ByteArrayInputStream(b);
        this.converters = converterBase;
    }

    /**
     * Reads some numbers of byte (in fact <code>b.length()</code>) and
     * stores them into the specified byte array.
     * 
     * @see java.io.InputStream
     */
    public int read(byte[] b)
    {
        try
        {
            return this.inStream.read(b);
        }
        catch (Exception e)
        {
            throw new UnknownException("error while reading", e);
        }
    }

    /**
     * Reads datas and sets the java bean property value. The default behaviour
     * is the same as the <code>RequestData.writeParameter </code> method
     * 
     * @param bean
     *            the java bean object
     * @param propertyName
     *            the java bean property name
     */
    public void read(Object bean, String propertyName)
    {
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
                    Object propertyValue = null;
                    BasicConverter converter = this.converters.getBasicConverter(converterName);

                    if (converter != null)
                    {
                        propertyValue = converter.read(this.inStream);
                    }

                    AnnotationConverter annBasedConverter = this.converters.getAnnotatedConverter(converterName);

                    if (annBasedConverter != null)
                    {
                        propertyValue = annBasedConverter.read(this.inStream, bean, propertyName);
                    }

                    //
                    ReflectHelper.setProperty(bean, propertyName, propertyValue);
                }
                catch (InitException e)
                {
                    throw new ConvertException("converter for name <" + converterName + "> has not been found");
                }
            }
            else
            {
                Class type = null;

                try
                {
                    type = ReflectHelper.getPropertyType(bean.getClass(), propertyName);

                    AnnotationConverter converter = this.converters.getTypedAnnotationConverter(type);
                    Object propertyValue = converter.read(this.inStream, bean, propertyName);
                    ReflectHelper.setProperty(bean, propertyName, propertyValue);
                }
                catch (InitException e)
                {
                    throw new ConvertException("converter for type <" + type + "> has not been found");
                }
            }
        }
        catch (ConvertException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new UnknownException("error while reading response for bean <" + bean.getClass() + "> and property <"
                    + propertyName + ">", e);
        }
    }

    /**
     * Reads datas and return an object based on <code>converter</code>
     * 
     */
    public Object read(BasicConverter converter)
    {
        try
        {
            return converter.read(this.inStream);
        }
        catch (ConvertException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new UnknownException("error while reading", e);
        }
    }

    /**
     * Returns the number of bytes that can be read
     * 
     * @see java.io.InputStream
     */
    public int available()
    {
        try
        {
            return this.inStream.available();
        }
        catch (Exception e)
        {
            throw new UnknownException(e.getMessage(), e);
        }
    }

    /**
     * 
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return toString;
    }
}
