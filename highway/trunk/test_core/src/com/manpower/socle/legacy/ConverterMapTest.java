package org.highway.legacy;

import org.highway.init.InitException;

import junit.framework.TestCase;


/**
 * 
 * @author Olivier Mallassi
 * @since 1.3
 * 
 */
public class ConverterMapTest extends TestCase
{
    /**
     * Tests a classic initialization.
     * 
     */
    public void testInit()
    {
        ConverterMap converters = new ConverterMap2Mock();
        AnnotationConverter ac = converters.getAnnotatedConverter("STRING_AC");
        assertNotNull(ac);
        BasicConverter bc = converters.getBasicConverter("STRING_BC");
        assertNotNull(bc);
    }

    /**
     * Tests the setters.
     * 
     */
    public void testSetters()
    {
        ConverterMap converters = new ConverterMapMock();
        converters.setNamedConverter("name1", new AnnotationConverterMockImpl());
        converters.setNamedConverter("name2", new BasicConverterMockImpl());
        try
        {
            converters.setNamedConverter("name2", new BasicConverterMockImpl());
            fail();
        }
        catch (InitException e)
        {
        }
        //
        try
        {
            converters.setNamedConverter("name2", new AnnotationConverterMockImpl());
            fail();
        }
        catch (InitException e)
        {
        }
        //
        try
        {
            converters.setNamedConverter("name1", new BasicConverterMockImpl());
            fail();
        }
        catch (InitException e)
        {
        }
        //
        try
        {
            converters.setDefaultBasicConverter(DateConverterTest.class, new BasicConverterMockImpl());
        }
        catch (InitException e)
        {
            fail();
        }
        //
        try
        {
            converters
                    .setDefaultAnnotationBasedConverter(IntegerConverterTest.class, new AnnotationConverterMockImpl());
            converters.setDefaultAnnotationBasedConverter(DateConverterTest.class, new AnnotationConverterMockImpl());
            fail();
        }
        catch (InitException e)
        {
        }
        //
        try
        {
            converters.setDefaultBasicConverter(IntegerConverterTest.class, new BasicConverterMockImpl());
            fail();
        }
        catch (InitException e)
        {
        }
    }

    /**
     * 
     * 
     */
    public void testSettersHierarchy()
    {
        ConverterMap converters = new ConverterMapMock();
        converters.setDefaultBasicConverter(Object.class, new BasicConverterMockImpl());
        try
        {
            converters.getTypedBasicConverter(Exception.class);
        }
        catch (InitException e)
        {
            fail();
        }
    }

    /**
     * Tests the getters.
     * 
     */
    public void testGetters()
    {
        ConverterMap converters = new ConverterMapMock();
        converters.setDefaultAnnotationBasedConverter(IntegerConverterTest.class, new AnnotationConverterMockImpl());
        //
        Object object = converters.getTypedAnnotationConverter(IntegerConverterTest.class);
        assertNotNull(object);
        assertTrue(object instanceof AnnotationConverterMockImpl);
    }

    /**
     * Tests the failure cases during initialization phase.
     * 
     */
    public void testInitFailure()
    {
        // Two basic converters setted with the same name
        ConverterMap2Mock converters = new ConverterMap2Mock();
        try
        {
            converters.setNamedConverter("STRING_BC", ConverterMap2Mock.STRING_BC);
            fail();
        }
        catch (InitException e)
        {
            assertTrue(true);
        }
        // Two AnnotationConverter with the same name.
        converters = new ConverterMap2Mock();
        try
        {
            converters.setNamedConverter("STRING_AC", ConverterMap2Mock.STRING_AC);
            fail();
        }
        catch (InitException e)
        {
            assertTrue(true);
        }
        // Two BasicConverter for the same java type
        converters = new ConverterMap2Mock();
        converters.setDefaultBasicConverter(IntegerConverterTest.class, new BasicConverterMockImpl());
        try
        {
            converters.setDefaultBasicConverter(IntegerConverterTest.class, new BasicConverterMockImpl());
            fail();
        }
        catch (InitException e)
        {
            assertTrue(true);
        }
        // Two annotationConverters for the same java type
        converters = new ConverterMap2Mock();
        converters.setDefaultAnnotationBasedConverter(IntegerConverterTest.class, new AnnotationConverterMockImpl());
        try
        {
            converters
                    .setDefaultAnnotationBasedConverter(IntegerConverterTest.class, new AnnotationConverterMockImpl());
            fail();
        }
        catch (InitException e)
        {
            assertTrue(true);
        }
        // No converters setted for a java type
        converters = new ConverterMap2Mock();
        try
        {
            converters.getTypedAnnotationConverter(String.class);
            fail();
        }
        catch (InitException e)
        {
            assertTrue(true);
            try
            {
                converters.getTypedBasicConverter(String.class);
                fail();
            }
            catch (InitException e1)
            {
                assertTrue(true);
            }
        }
    }

}
