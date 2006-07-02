package org.highway.bean;

import junit.framework.TestCase;

/**
 * @author David Attias
 */
public class BeanMetadataHomeTest extends TestCase {
	public void test() {
		assertEquals(3, BeanMetadataHome.getPropertySizeMin(Building.class,
				Building.NAME).intValue());

		assertEquals(30, BeanMetadataHome.getPropertySizeMax(BuildingDef.class,
				Building.NAME).intValue());

		assertTrue(BeanMetadataHome.isPropertyMandatory(Building.class,
				Building.NAME));

		assertTrue(BeanMetadataHome.isPropertyMandatory(Building.class,
				Building.NUMBER_OF_FLOORS));

		assertTrue(BeanMetadataHome.isPropertyMandatory(Castle.class,
				Castle.KEEP_HEIGHT));
	}
}
