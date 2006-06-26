package org.highway.legacy;

import java.io.InputStream;
import java.io.OutputStream;

import org.highway.legacy.AnnotationConverter;
import org.highway.legacy.ConvertException;


public class AnnotationConverterMockImpl implements AnnotationConverter
{
    public Object read(InputStream stream, Object bean, String propertyName) throws ConvertException
    {
        return null;
    }

    public int write(OutputStream outputStream, Object bean, String propertyName) throws ConvertException
    {
        return 0;
    }
}
