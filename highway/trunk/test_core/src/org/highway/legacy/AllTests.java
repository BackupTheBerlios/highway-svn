package org.highway.legacy;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.highway.legacy");
		//$JUnit-BEGIN$
		suite.addTestSuite(IntegerConverterTest.class);
		suite.addTestSuite(DecimalConverterTest.class);
		suite.addTestSuite(DateConverterTest.class);
		suite.addTestSuite(StringConverterTest.class);
		suite.addTestSuite(ConverterMapTest.class);
		//$JUnit-END$
		return suite;
	}

}
