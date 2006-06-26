package org.highway.validate;

import junit.framework.TestCase;

import org.highway.validate.SizeValidator;
import org.highway.validate.ValidateContext;

/**
 * @author attias
 */
public class SizeValidatorTest extends TestCase
{
    public void testAllValidator()
    {
        SizeValidator validator = new SizeValidator(2, 5);
        ValidateContext context = new ValidateContext();
        context = validator.validate("123456", context);
        System.out.println("2 <= size(\"123456\") <= 5 is false, " + context);
        assertNotNull(context.getRootProblems());
        assertTrue(context.getRootProblems().size() == 1);

        context = new ValidateContext();
        context = validator.validate("12345", context);
        System.out.println("2 <= size(\"12345\") <= 5 is true, " + context);
        assertNull(context.getRootProblems());

        context = new ValidateContext();
        context = validator.validate("1234", context);
        System.out.println("2 <= size(\"1234\") <= 5 is true, " + context);
        assertNull(context.getRootProblems());

        context = new ValidateContext();
        context = validator.validate("12", context);
        System.out.println("2 <= size(\"12\") <= 5 is true, " + context);
        assertNull(context.getRootProblems());

        context = new ValidateContext();
        context = validator.validate("1", context);
        System.out.println("2 <= size(\"1\") <= 5 is false, " + context);
        assertNotNull(context.getRootProblems());
        assertTrue(context.getRootProblems().size() == 1);
    }
}
