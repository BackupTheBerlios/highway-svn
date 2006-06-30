package org.highway.bean;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.highway.vo");
		//$JUnit-BEGIN$
		suite.addTestSuite(EnumTest.class);
		suite.addTestSuite(VoGenTest.class);
		suite.addTestSuite(MetadataHomeTest.class);
		suite.addTestSuite(DecimalTest.class);
		//$JUnit-END$
		return suite;
	}

}
