package org.highway.legacy;

import java.io.InputStream;
import java.io.OutputStream;

public class BasicConverterMockImpl implements BasicConverter
{
    public int write(OutputStream outputStream, Object value) throws ConvertException
    {
        return 0;
    }

    public Object read(InputStream inputStream) throws ConvertException
    {
        return null;
    }
}
