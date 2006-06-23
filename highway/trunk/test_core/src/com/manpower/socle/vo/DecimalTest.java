package org.highway.vo;

import java.math.BigDecimal;

import org.highway.helper.Serializer;


import junit.framework.TestCase;

/**
 * 
 * @author Olivier Mallassi
 * @since 1.3
 * 
 */
@org.highway.annotation.ValueObject
public class DecimalTest extends TestCase
{
    /**
     * 
     * 
     */
    public void testGetIntegerPart()
    {
        Decimal decimalObj = new Decimal(123456, 2);
        assertEquals(decimalObj.getIntegerPart(), 1234);
        decimalObj = new Decimal(-123456, 2);
        assertEquals(decimalObj.getIntegerPart(), -1234);
    }

    /**
     * 
     * 
     */
    public void testGetDecimalPart()
    {
        Decimal decimalObj = new Decimal(123456, 2);
        assertEquals(decimalObj.getDecimalPart(), 56);
    }
    
    /**
     * 
     * 
     */
    public void testSerializationWeight()
    {
        Object[] array = new Object[10];
        String value = "123456789.123456789";
        
        for (int i = 0; i < array.length; i++)
        	array[i] = new BigDecimal(value);
        
        int bigWeight = Serializer.toByte(array).length;
        
        System.out.println("10 BigDecimal objects equal to "
        	+ value + " weight " + bigWeight + " bytes");

        for (int i = 0; i < array.length; i++)
        	array[i] = new Decimal(value);
        
        int smallWeight = Serializer.toByte(array).length;
        
        System.out.println("10 Decimal objects equal to "
        	+ value + " weight " + smallWeight + " bytes");
        
        System.out.println("Ratio = " + (float) bigWeight/smallWeight);
    }

}
