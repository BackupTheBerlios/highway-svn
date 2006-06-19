package com.manpower.socle.legacy;

import java.io.InputStream;
import java.io.OutputStream;

import com.manpower.socle.legacy.AnnotationConverter;
import com.manpower.socle.legacy.ConvertException;

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
