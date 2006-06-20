package org.highway.validate;

import java.util.Collections;

import org.highway.helper.Serializer;


import junit.framework.TestCase;

public class ValidateContextTest extends TestCase
{
	public void testSerializable()
	{
		ValidateContext context = new ValidateContext();
		context.setPropertiesToAvoid(Collections.singleton("name"));
		context.addMissingProperty("age");
		context.addProblem(SizeValidator.class, "telephone", false);
		ValidateProblem problem = (ValidateProblem)
			context.getPropertyProblems("telephone").get(0);
		ValidateContext clone = (ValidateContext) Serializer.clone(context);
		assertNotSame(context, clone);
		assertTrue(clone.isMissing("age"));
		assertNotSame(problem, clone.getPropertyProblems("telephone").get(0));
	}
}
