package org.highway.validate;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.highway.validate");
		//$JUnit-BEGIN$
		suite.addTestSuite(RangeValidatorTest.class);
		suite.addTestSuite(SizeValidatorTest.class);
		suite.addTestSuite(ValidateContextTest.class);
		suite.addTestSuite(ValidateHomeTest.class);
		//$JUnit-END$
		return suite;
	}

}
