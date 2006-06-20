package org.highway.vo;

import java.util.List;

import org.highway.helper.Serializer;


import junit.framework.TestCase;

/**
 * @author David Attias
 */
public class EnumTest extends TestCase
{
    public void testGetCode()
    {
        assertEquals(new Short((short)27), EnumMock.bleu.getCode());
    }

    public void testGetDescription()
    {
        assertEquals("bleu", EnumMock.bleu.getDescription());
    }

    public void testGetEnum()
    {
        Enum enumValue = Enum.getEnum(EnumMock.class, new Short((short) 27));
        assertEquals(enumValue, EnumMock.bleu);
    }

    public void testGetEnumCodeClass()
    {
        Class codeType = Enum.getEnumCodeClass(EnumMock.class);
        assertEquals(codeType, Short.class);
    }

    public void testGetEnumFromString()
    {
        Enum enumValue = Enum.getEnumFromString(EnumMock.class, "27");
        assertEquals(EnumMock.bleu, enumValue);
    }

	public void testComparable()
	{
		assertTrue(EnumMock.rouge.compareTo(EnumMock.bleu) < 0);
		assertTrue(EnumMock.bleu.compareTo(EnumMock.vert) < 0);
	}
	
    public void testGetAll()
    {
        List list = EnumMock.getAll();
		check(list);
        list.clear();
        list = EnumMock.getAll();
		check(list);
    }
	
	private void check(List all)
	{
        assertNotNull(all);
        assertTrue(all.size() == 3);
        assertEquals(all.get(0), EnumMock.rouge);
        assertEquals(all.get(1), EnumMock.bleu);
        assertEquals(all.get(2), EnumMock.vert);
	}
	
	public void testSerialization()
	{
		assertTrue(Serializer.clone(EnumMock.bleu) == EnumMock.bleu);
	}
	
	public void testGetAllNotModifiable(){
        List list = EnumMock.getAll();
		
		list.add(EnumMock.bleu);
		assertFalse(list.size() ==3);
	}
	
}
