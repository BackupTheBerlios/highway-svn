/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.legacy;

import org.highway.helper.ReflectHelper;
import org.highway.vo.MetadataHome;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Write an <code>java.util.Date</code> on a stream or read a stream to create
 * a <code>java.util.Date</code>. This converter can be used as a
 * <code>BasicConverter</code> or as an <code>AnnotationConverter</code>.
 * In the second case, refers to <code>SOCLE_LEGACY_DATE_FORMAT</code> for tag
 * name to use.
 * 
 * @author Olivier Mallassi
 * @since 1.3
 * 
 */
public class DateConverter implements BasicConverter, AnnotationConverter
{
    /**
     * 
     */
    private String _format;

    /**
     * Name of the javadoc tag that must be used to specified the date format.
     * 
     * @see SimpleDateFormat
     */
    public static final String SOCLE_LEGACY_DATE_FORMAT = "highway.legacy.date.format";

    /**
     * Constructs with the date format <code>yyyyMMdd</code>.
     * 
     */
    public DateConverter()
    {
        this("yyyyMMdd");
    }

    /**
     * Contructs a DateConverter with the specified <code>format</code>.
     * 
     * @param format
     *            the format as specified for SimpleDateFormatter.
     * @see SimpleDateFormatter
     * 
     */
    public DateConverter(String format)
    {
        this._format = format;
    }

    /**
     * Writes a property that must be a <code>java.util.Date</code>.
     * 
     */
    public int write(OutputStream outputStream, Object bean, String propertyName) throws ConvertException
    {
        Object propertyValue = ReflectHelper.getProperty(bean, propertyName);

        try
        {
            String format = this.getPaddingValueTag(bean, propertyName);

            return writeObject(outputStream, propertyValue, format);
        }
        catch (IOException e)
        {
            throw new ConvertException(e);
        }
    }

    /**
     * 
     * @param outputStream
     * @param propertyValue
     * @param format
     * @return
     * @throws IOException
     */
    private int writeObject(OutputStream outputStream, Object propertyValue, String format) throws IOException
    {
        Date propertyAsDate = (Date) propertyValue;
        byte[] array;
        SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
        array = dateFormatter.format(propertyAsDate).getBytes();
        outputStream.write(array);

        return array.length;
    }

    /**
     * Returns a <code>java.util.Date</code>.
     * 
     * @see org.highway.legacy.BasicConverter#read(java.lang.Class,
     *      java.lang.String, org.highway.jca.cics.record.CicsMessage)
     */
    public Object read(InputStream stream, Object bean, String propertyName) throws ConvertException
    {
        try
        {
            String localFormat = this.getPaddingValueTag(bean, propertyName);

            Object propertyValue = readObject(stream, localFormat);
            ReflectHelper.setProperty(bean, propertyName, propertyValue);

            return propertyValue;
        }
        catch (IOException e)
        {
            throw new ConvertException(e);
        }
        catch (ParseException e)
        {
            throw new ConvertException(e);
        }
    }

    /**
     * 
     * @param stream
     * @param localFormat
     * @param dateFormatter
     * @return
     * @throws IOException
     * @throws ParseException
     */
    private Object readObject(InputStream stream, String localFormat) throws IOException, ParseException
    {
        byte[] object = new byte[localFormat.length()];
        stream.read(object, 0, object.length);

        Date date;
        SimpleDateFormat dateFormatter = new SimpleDateFormat(localFormat);
        date = dateFormatter.parse(new String(object));

        return date;
    }

    /**
     * Writes a <code>java.util.Date</code>.
     * 
     */
    public int write(OutputStream outputStream, Object value) throws ConvertException
    {
        try
        {
            return writeObject(outputStream, value, this._format);
        }
        catch (IOException e)
        {
            throw new ConvertException(e);
        }
    }

    /**
     * Returns a <code>java.util.Date</code>.
     * 
     */
    public Object read(InputStream inputStream) throws ConvertException
    {
        try
        {
            return readObject(inputStream, this._format);
        }
        catch (IOException e)
        {
            throw new ConvertException(e);
        }
        catch (ParseException e)
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
        String padValue = MetadataHome.getPropertyMetaValue(bean.getClass(), propertyName, SOCLE_LEGACY_DATE_FORMAT);

        if ((padValue == null) || "".equals(padValue))
        {
            throw new ConvertException("xdoclet tag <" + SOCLE_LEGACY_DATE_FORMAT + "> missing for property: "
                    + bean.getClass() + "." + propertyName);
        }

        return padValue;
    }
}
