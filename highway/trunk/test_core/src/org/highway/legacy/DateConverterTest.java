package org.highway.legacy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import junit.framework.TestCase;

/**
 * 
 * @author Olivier Mallassi
 * @since 1.3
 * 
 */
public class DateConverterTest extends TestCase
{

    /**
     * 
     * 
     */
    public void testConvertOutput()
    {
        DateConverter converter = new DateConverter("yyyyMMdd");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2005, Calendar.OCTOBER, 13);
        converter.write(outputStream, calendar.getTime());
        assertEquals("20051013", outputStream.toString());
        //
        converter = new DateConverter();
        outputStream = new ByteArrayOutputStream();
        DateMock dateMock = new DateMock();
        dateMock.setAttribute(calendar.getTime());
        converter.write(outputStream, dateMock, DateMock.ATTRIBUTE);
        assertEquals("13102005", outputStream.toString());
    }

    /**
     * 
     * 
     */
    public void testConvertInput()
    {
        DateConverter converter = new DateConverter("yyyyMMdd");
        ByteArrayInputStream inputStream = new ByteArrayInputStream("20051013".getBytes());
        Object returnedDate = converter.read(inputStream);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime((java.util.Date) returnedDate);
        assertEquals(calendar.get(Calendar.YEAR), 2005);
        assertEquals(calendar.get(Calendar.MONTH), Calendar.OCTOBER);
        assertEquals(calendar.get(Calendar.DAY_OF_MONTH), 13);
        //
        converter = new DateConverter();
        DateMock dateMock = new DateMock();
        inputStream = new ByteArrayInputStream("13102005".getBytes());
        returnedDate = converter.read(inputStream, dateMock, DateMock.ATTRIBUTE);
        assertNotNull(dateMock.getAttribute());
        calendar.setTime((java.util.Date) returnedDate);
        assertEquals(calendar.get(Calendar.YEAR), 2005);
        assertEquals(calendar.get(Calendar.MONTH), Calendar.OCTOBER);
        assertEquals(calendar.get(Calendar.DAY_OF_MONTH), 13);
    }
    
}
