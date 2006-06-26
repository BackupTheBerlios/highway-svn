package org.highway.legacy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import junit.framework.TestCase;

/**
 * 
 * @author Olivier Mallassi
 * @since 1.3
 * 
 */
public class IntegerConverterTest extends TestCase
{
    /**
     * 
     * 
     */
    public void testConvertOutput()
    {
        IntegerConverter converter = new IntegerConverter("545".length(), '0');
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        converter.write(outputStream, new Integer(545));
        assertEquals("545", outputStream.toString());
        //
        IntegerMock integerMock = new IntegerMock();
        integerMock.setAttribute(new Integer(12));
        converter = new IntegerConverter();
        outputStream = new ByteArrayOutputStream();
        converter.write(outputStream, integerMock, IntegerMock.ATTRIBUTE);
        assertEquals("00012", outputStream.toString());
    }

    /**
     * 
     * 
     */
    public void testConvertInput()
    {
        IntegerConverter converter = new IntegerConverter("545".length(), '0');
        ByteArrayInputStream inputStream = new ByteArrayInputStream("545".getBytes());
        Object object = converter.read(inputStream);
        assertEquals(new Integer(545), object);
        //
        converter = new IntegerConverter("xx545".length(), 'x');
        inputStream = new ByteArrayInputStream("xx545".getBytes());
        object = converter.read(inputStream);
        assertEquals(new Integer(545), object);
        //
        converter = new IntegerConverter();
        inputStream = new ByteArrayInputStream("00012".getBytes());
        IntegerMock integerMock = new IntegerMock();
        object = converter.read(inputStream, integerMock, IntegerMock.ATTRIBUTE);
        assertNotNull(integerMock.getAttribute());
        assertEquals(object, new Integer(12));
    }
}
