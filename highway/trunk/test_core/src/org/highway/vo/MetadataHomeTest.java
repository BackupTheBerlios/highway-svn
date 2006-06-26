package org.highway.vo;

import junit.framework.TestCase;

/**
 * @author David Attias
 */
public class MetadataHomeTest extends TestCase
{
	// TODO should work with definition interface ?
	public void test()
	{
//		assertEquals("gif",
//				MetadataHome.getPropertyMetaValue(
//						BuildingDef.class, Building.FIRME_LOGOS, "myapp.image.type"));
		
		assertEquals("gif",
				MetadataHome.getPropertyMetaValue(
						Building.class, Building.FIRME_LOGOS, "myapp.image.type"));
		
		assertEquals(3,
				MetadataHome.getPropertySizeMin(
						Building.class, Building.NAME).intValue());
		
//		assertEquals(30,
//				MetadataHome.getPropertySizeMax(
//						BuildingDef.class, Building.NAME).intValue());
		
		assertEquals(true,
				MetadataHome.isPropertyMandatory(
						Building.class, Building.FLOOR_NUMBER));
		
		assertEquals(true,
				MetadataHome.isPropertyMandatory(
						Castle.class, Castle.TOWER_NUMBER));
		
		assertEquals(1,
				MetadataHome.getPropertySizeMin(
						Castle.class, Castle.KEEP_HEIGHT).intValue());
		
//		assertEquals(3,
//				MetadataHome.getPropertySizeMax(
//						CastleDef.class, Castle.KEEP_HEIGHT).intValue());
	}
}
