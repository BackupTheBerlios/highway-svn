package com.manpower.socle.helper;

import java.util.Properties;

import org.highway.helper.PropertiesHelper;

import junit.framework.TestCase;

/**
 * @author David Attias
 */
public class LoadAsResourceTest extends TestCase
{
    public void testLoadAsResource()
    {
        Properties props = PropertiesHelper.loadAsResource(LoadAsResourceTest.class, "test.properties");
        assertEquals("my value", props.get("my.property"));
    }
}
