package com.manpower.socle.legacy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import junit.framework.TestCase;

/**
 * 
 * @author Olivier Mallassi
 * @since 1.3
 * 
 */
public class StringConverterTest extends TestCase
{
    /**
     * 
     * 
     */
    public void testConvertOutput()
    {
        StringConverter converter = new StringConverter("essai".length() + 2, " ".charAt(0),
                StringConverter.LEGACY_PADDING_RIGHT);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        converter.write(outputStream, "essai  ");
        assertEquals("essai  ", outputStream.toString());
        //
        converter = new StringConverter();
        StringMock stringMock = new StringMock();
        stringMock.setAttribute("value");
        outputStream = new ByteArrayOutputStream();
        converter.write(outputStream, stringMock, StringMock.ATTRIBUTE);
        assertEquals("xxxxxvalue", outputStream.toString());
    }

    /**
     * 
     * 
     */
    public void testConvertInput()
    {
        StringConverter converter = new StringConverter("essai".length() + 2, " ".charAt(0),
                StringConverter.LEGACY_PADDING_RIGHT);
        ByteArrayInputStream inputStream = new ByteArrayInputStream("essai  ".getBytes());
        Object object = converter.read(inputStream);
        assertEquals("essai", object);
        //
        converter = new StringConverter();
        StringMock stringMock = new StringMock();
        inputStream = new ByteArrayInputStream("xxxxxvalue".getBytes());
        object = converter.read(inputStream, stringMock, StringMock.ATTRIBUTE);
        assertNotNull(stringMock.getAttribute());
        assertEquals("value", object);
    }

    /**
     * 
     * 
     */
    public void testPaddingStrategy()
    {
        StringConverter converter = new StringConverter(10, "x".charAt(0), StringConverter.LEGACY_PADDING_RIGHT);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        converter.write(outputStream, "essai");
        assertEquals("essaixxxxx", outputStream.toString());
        //
        converter = new StringConverter(10, "x".charAt(0), StringConverter.LEGACY_PADDING_LEFT);
        outputStream = new ByteArrayOutputStream();
        converter.write(outputStream, "essai");
        assertEquals("xxxxxessai", outputStream.toString());
        //
        converter = new StringConverter(10, "x".charAt(0), StringConverter.LEGACY_PADDING_NONE);
        outputStream = new ByteArrayOutputStream();
        converter.write(outputStream, "essai");
        assertEquals("essai", outputStream.toString());
    }
}
