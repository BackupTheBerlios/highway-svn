package org.highway.vo;

import junit.framework.TestCase;

public class AnnotationMetadataHomeTest extends TestCase {

	/*
	 * Test method for 'org.highway.vo.AnnotationMetadataHome.getClassMetaValue(Class, Class<T>) <T>'
	 */
	public void testGetClassMetaValue() {

	}

	/*
	 * Test method for 'org.highway.vo.AnnotationMetadataHome.getPropertyMetaValue(Class, String, Class<T>) <T>'
	 */
	public void testGetPropertyMetaValue() {

	}

	/*
	 * Test method for 'org.highway.vo.AnnotationMetadataHome.getPropertySizeMin(Class, String)'
	 */
	public void testGetPropertySizeMin() {
		assertEquals(3,
				AnnotationMetadataHome.getPropertySizeMin(Building.class, Building.NAME).intValue());
	}

	/*
	 * Test method for 'org.highway.vo.AnnotationMetadataHome.getPropertySizeMax(Class, String)'
	 */
	public void testGetPropertySizeMax() {
		assertEquals(30,
				AnnotationMetadataHome.getPropertySizeMax(Building.class, Building.NAME).intValue());
	}

	/*
	 * Test method for 'org.highway.vo.AnnotationMetadataHome.getPropertyPattern(Class, String)'
	 */
	public void testGetPropertyPattern() {
		assertEquals("jj/mm/aaaa",
				AnnotationMetadataHome.getPropertyPattern(Building.class, Building.NAME));
	}

	/*
	 * Test method for 'org.highway.vo.AnnotationMetadataHome.isPropertyMandatory(Class, String)'
	 */
	public void testIsPropertyMandatory() {
		assertEquals(true,
				AnnotationMetadataHome.isPropertyMandatory(Building.class, Building.NAME));
	}

	/*
	 * Test method for 'org.highway.vo.AnnotationMetadataHome.isPropertyUppercase(Class, String)'
	 */
	public void testIsPropertyUppercase() {
		assertEquals(true,
				AnnotationMetadataHome.isPropertyUppercase(Building.class, Building.NAME));
	}

	/*
	 * Test method for 'org.highway.vo.AnnotationMetadataHome.isPropertyReadOnly(Class, String)'
	 */
	public void testIsPropertyReadOnly() {
		assertEquals(true,
				AnnotationMetadataHome.isPropertyReadOnly(Building.class, Building.NAME));
	}

	/*
	 * Test method for 'org.highway.vo.AnnotationMetadataHome.getPropertyShortDescription(Class, String)'
	 */
	public void testGetPropertyShortDescription() {
		assertEquals("ceci est une description courte",
				AnnotationMetadataHome.getPropertyShortDescription(Building.class, Building.NAME));
	}

	/*
	 * Test method for 'org.highway.vo.AnnotationMetadataHome.getPropertyLongDescription(Class, String)'
	 */
	public void testGetPropertyLongDescription() {
		assertEquals("ceci est une description longue",
				AnnotationMetadataHome.getPropertyLongDescription(Building.class, Building.NAME));
	}

	/*
	 * Test method for 'org.highway.vo.AnnotationMetadataHome.getPropertyDecimalScale(Class, String)'
	 */
	public void testGetPropertyDecimalScale() {
		assertEquals(100,
				AnnotationMetadataHome.getPropertyDecimalScale(Building.class, Building.NAME).intValue());
	}

}
