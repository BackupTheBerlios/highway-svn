package com.manpower.socle.legacy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import junit.framework.TestCase;

import com.manpower.socle.vo.Decimal;

public class DecimalConverterTest extends TestCase
{
    /**
     * 
     * 
     */
    public void testWrite()
    {
        Decimal decimal = new Decimal(123456, 2);
        DecimalConverter converter = new DecimalConverter(6, 5, '0', '.');
        //
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        converter.write(outputStream, decimal);
        assertEquals(outputStream.toString(), "001234.56000");
        //
        decimal = new Decimal(-123456, 2);
        outputStream = new ByteArrayOutputStream();
        converter.write(outputStream, decimal);
        assertEquals(outputStream.toString(), "-01234.56000");
        //
        DecimalMock mockJavaBean = new DecimalMock();
        decimal = new Decimal(123456, 2);
        mockJavaBean.setAttribute(decimal);
        outputStream.reset();
        converter.write(outputStream, mockJavaBean, DecimalMock.ATTRIBUTE);
        assertEquals(outputStream.toString(), "001234.56000");
        // test no separator
        converter = new DecimalConverter(6, 5, '0');
        outputStream = new ByteArrayOutputStream();
        converter.write(outputStream, decimal);
        assertEquals(outputStream.toString(), "00123456000");
        // test on a bean
        // test with no tag separator
        mockJavaBean = new DecimalMock();
        decimal = new Decimal(123456, 2);
        mockJavaBean.setAttributeWithNoSepValue(decimal);
        outputStream.reset();
        converter.write(outputStream, mockJavaBean, DecimalMock.ATTRIBUTE_WITH_NO_SEP_VALUE);
        assertEquals(outputStream.toString(), "00123456000");
        // test with tag separator and 'none' as value
        mockJavaBean = new DecimalMock();
        decimal = new Decimal(123456, 2);
        mockJavaBean.setAttributeWithSepValue(decimal);
        outputStream.reset();
        converter.write(outputStream, mockJavaBean, DecimalMock.ATTRIBUTE_WITH_SEP_VALUE);
        assertEquals(outputStream.toString(), "00123456000");
    }

    /**
     * 
     * 
     */
    public void testRead()
    {
        DecimalConverter converter = new DecimalConverter(6, 5, '0', '.');
        ByteArrayInputStream inputStream = new ByteArrayInputStream("001234.56000".getBytes());
        Object object = converter.read(inputStream);
        assertEquals(new Decimal(123456, 2), object);
        //
        inputStream = new ByteArrayInputStream("-01234.56000".getBytes());
        object = converter.read(inputStream);
        assertEquals(new Decimal(-123456, 2), object);
        //
        DecimalMock mockJavaBean = new DecimalMock();
        inputStream = new ByteArrayInputStream("001234.56000".getBytes());
        object = converter.read(inputStream, mockJavaBean, DecimalMock.ATTRIBUTE);
        assertEquals(object, new Decimal(123456, 2));
        //
        converter = new DecimalConverter(6, 5, '0');
        inputStream = new ByteArrayInputStream("00123456000".getBytes());
        object = converter.read(inputStream);
        assertEquals(new Decimal(123456, 2), object);
        // test the javadoc tags with no separator..
        mockJavaBean = new DecimalMock();
        inputStream = new ByteArrayInputStream("00123456000".getBytes());
        object = converter.read(inputStream, mockJavaBean, DecimalMock.ATTRIBUTE_WITH_NO_SEP_VALUE);
        assertEquals(object, new Decimal(123456, 2));
        //
        mockJavaBean = new DecimalMock();
        inputStream = new ByteArrayInputStream("00123456000".getBytes());
        object = converter.read(inputStream, mockJavaBean, DecimalMock.ATTRIBUTE_WITH_SEP_VALUE);
        assertEquals(object, new Decimal(123456, 2));
    }

}