package com.manpower.socle.helper;

import org.highway.helper.StringHelper;

import junit.framework.TestCase;

public class StringHelperTest extends TestCase
{
	public void testSafeToString()
	{
		assertEquals(StringHelper.safeToString(null), null);
		assertEquals(StringHelper.safeToString(new StringHolder(null)), null);
		assertEquals(StringHelper.safeToString(""), "");
		assertEquals(StringHelper.safeToString(new StringHolder("")), "");
		assertEquals(StringHelper.safeToString("toto"), "toto");
		assertEquals(StringHelper.safeToString(new StringHolder("toto")), "toto");
	}
	
	public void testSafeToStringOrEmpty()
	{
		assertEquals(StringHelper.safeToStringOrEmpty(null), "");
		assertEquals(StringHelper.safeToStringOrEmpty(new StringHolder(null)), "");
		assertEquals(StringHelper.safeToStringOrEmpty(""), "");
		assertEquals(StringHelper.safeToStringOrEmpty(new StringHolder("")), "");
		assertEquals(StringHelper.safeToStringOrEmpty("toto"), "toto");
		assertEquals(StringHelper.safeToStringOrEmpty(new StringHolder("toto")), "toto");
	}
	
	public void testSafeToStringOrNull()
	{
		assertEquals(StringHelper.safeToStringOrNull(null), null);
		assertEquals(StringHelper.safeToStringOrNull(new StringHolder(null)), null);
		assertEquals(StringHelper.safeToStringOrNull(""), null);
		assertEquals(StringHelper.safeToStringOrNull(new StringHolder("")), null);
		assertEquals(StringHelper.safeToStringOrNull("toto"), "toto");
		assertEquals(StringHelper.safeToStringOrNull(new StringHolder("toto")), "toto");
	}
	
	public void testSafeEquals()
	{
		assertTrue(StringHelper.safeEquals(null, null));
		assertFalse(StringHelper.safeEquals(null, ""));
		assertFalse(StringHelper.safeEquals("", null));
		assertTrue(StringHelper.safeEquals("", ""));
		assertFalse(StringHelper.safeEquals("toto", null));
		assertFalse(StringHelper.safeEquals(null, "toto"));
		assertFalse(StringHelper.safeEquals("toto", ""));
		assertFalse(StringHelper.safeEquals("", "toto"));
		assertTrue(StringHelper.safeEquals("toto", "toto"));
		assertFalse(StringHelper.safeEquals("toto", "TOTO"));
		assertFalse(StringHelper.safeEquals("toto", "titi"));
	}

	public void testSafeEqualsIgnoreCase()
	{
		assertTrue(StringHelper.safeEqualsIgnoreCase(null, null));
		assertFalse(StringHelper.safeEqualsIgnoreCase(null, ""));
		assertFalse(StringHelper.safeEqualsIgnoreCase("", null));
		assertTrue(StringHelper.safeEqualsIgnoreCase("", ""));
		assertFalse(StringHelper.safeEqualsIgnoreCase("toto", null));
		assertFalse(StringHelper.safeEqualsIgnoreCase(null, "toto"));
		assertFalse(StringHelper.safeEqualsIgnoreCase("toto", ""));
		assertFalse(StringHelper.safeEqualsIgnoreCase("", "toto"));
		assertTrue(StringHelper.safeEqualsIgnoreCase("toto", "toto"));
		assertTrue(StringHelper.safeEqualsIgnoreCase("toto", "TOTO"));
		assertFalse(StringHelper.safeEqualsIgnoreCase("toto", "titi"));
	}

	public void testSafeEqualsIgnoreEmpty()
	{
		assertTrue(StringHelper.safeEqualsIgnoreEmpty(null, null));
		assertTrue(StringHelper.safeEqualsIgnoreEmpty(null, ""));
		assertTrue(StringHelper.safeEqualsIgnoreEmpty("", null));
		assertTrue(StringHelper.safeEqualsIgnoreEmpty("", ""));
		assertFalse(StringHelper.safeEqualsIgnoreEmpty("toto", null));
		assertFalse(StringHelper.safeEqualsIgnoreEmpty(null, "toto"));
		assertFalse(StringHelper.safeEqualsIgnoreEmpty("toto", ""));
		assertFalse(StringHelper.safeEqualsIgnoreEmpty("", "toto"));
		assertTrue(StringHelper.safeEqualsIgnoreEmpty("toto", "toto"));
		assertFalse(StringHelper.safeEqualsIgnoreEmpty("toto", "TOTO"));
		assertFalse(StringHelper.safeEqualsIgnoreEmpty("toto", "titi"));
	}
	
	public void testSafeEqualsIgnoreCaseAndEmpty()
	{
		assertTrue(StringHelper.safeEqualsIgnoreCaseAndEmpty(null, null));
		assertTrue(StringHelper.safeEqualsIgnoreCaseAndEmpty(null, ""));
		assertTrue(StringHelper.safeEqualsIgnoreCaseAndEmpty("", null));
		assertTrue(StringHelper.safeEqualsIgnoreCaseAndEmpty("", ""));
		assertFalse(StringHelper.safeEqualsIgnoreCaseAndEmpty("toto", null));
		assertFalse(StringHelper.safeEqualsIgnoreCaseAndEmpty(null, "toto"));
		assertFalse(StringHelper.safeEqualsIgnoreCaseAndEmpty("toto", ""));
		assertFalse(StringHelper.safeEqualsIgnoreCaseAndEmpty("", "toto"));
		assertTrue(StringHelper.safeEqualsIgnoreCaseAndEmpty("toto", "toto"));
		assertTrue(StringHelper.safeEqualsIgnoreCaseAndEmpty("toto", "TOTO"));
		assertFalse(StringHelper.safeEqualsIgnoreCaseAndEmpty("toto", "titi"));
	}
	
	public void testTrim()
	{
		assertNull(StringHelper.trim(null, null));
		assertEquals(StringHelper.trim(null, ""), "");
		assertEquals(StringHelper.trim(null, "   toto  titi   "), "   toto  titi   ");
		
		assertNull(StringHelper.trim("UNKNOWN", null));
		assertEquals(StringHelper.trim("UNKNOWN", ""), "");
		assertEquals(StringHelper.trim("UNKNOWN", "   toto  titi   "), "   toto  titi   ");

		assertNull(StringHelper.trim(StringHelper.TRIM_SPACE_LEFT, null));
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_LEFT, ""), "");
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_LEFT, "   "), "");
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_LEFT, "toto  titi"), "toto  titi");
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_LEFT, "toto  titi   "), "toto  titi   ");
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_LEFT, "   toto  titi"), "toto  titi");
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_LEFT, "   toto  titi   "), "toto  titi   ");
		
		assertNull(StringHelper.trim(StringHelper.TRIM_SPACE_RIGHT, null));
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_RIGHT, ""), "");
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_RIGHT, "   "), "");
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_RIGHT, "toto  titi"), "toto  titi");
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_RIGHT, "toto  titi   "), "toto  titi");
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_RIGHT, "   toto  titi"), "   toto  titi");
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_RIGHT, "   toto  titi   "), "   toto  titi");
		
		assertNull(StringHelper.trim(StringHelper.TRIM_SPACE_BOTH, null));
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_BOTH, ""), "");
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_BOTH, "   "), "");
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_BOTH, "toto  titi"), "toto  titi");
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_BOTH, "toto  titi   "), "toto  titi");
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_BOTH, "   toto  titi"), "toto  titi");
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_BOTH, "   toto  titi   "), "toto  titi");
		
		assertNull(StringHelper.trim(StringHelper.TRIM_SPACE_ALL, null));
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_ALL, ""), "");
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_ALL, "   "), "");
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_ALL, "toto  titi"), "toto titi");
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_ALL, "toto  titi   "), "toto titi");
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_ALL, "   toto  titi"), "toto titi");
		assertEquals(StringHelper.trim(StringHelper.TRIM_SPACE_ALL, "   toto  titi   "), "toto titi");
	}
}

class StringHolder
{
	private String value;

	StringHolder(String value)
	{
		this.value = value;
	}

	public String toString()
	{
		return value;
	}	
}