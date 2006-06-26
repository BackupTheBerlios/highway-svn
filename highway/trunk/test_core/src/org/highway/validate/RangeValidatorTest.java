package org.highway.validate;

import java.util.Date;

import junit.framework.TestCase;

import org.highway.validate.RangeValidator;
import org.highway.validate.ValidateContext;
import org.highway.vo.Decimal;

/**
 * @author attias
 */
public class RangeValidatorTest extends TestCase
{
    public void testAllValidator()
    {
        RangeValidator validator = new RangeValidator(new Decimal("0.1"), true, new Decimal("0.2"), true);
        // 0.1 < 0.12 < 0.2 is true
        ValidateContext context = validator.validate(new Decimal("0.12"), new ValidateContext());
        System.out.println("0.1 < 0.12 < 0.2 is true, " + context);
        assertNull(context.getRootProblems());
        // 0.1 < 0.21 < 0.2 is false
        context = validator.validate(new Decimal("0.21"), new ValidateContext());
        System.out.println("0.1 < 0.21 < 0.2 is false, " + context);
        assertNotNull(context.getRootProblems());
        assertTrue(context.getRootProblems().size() == 1);
        // 0.1 < 0.01 < 0.2 is false
        context = validator.validate(new Decimal("0.01"), new ValidateContext());
        System.out.println("0.1 < 0.01 < 0.2 is false, " + context);
        assertNotNull(context.getRootProblems());
        assertTrue(context.getRootProblems().size() == 1);
        // 0.1 < 0.1 < 0.2 is false
        context = validator.validate(new Decimal("0.1"), new ValidateContext());
        System.out.println("0.1 < 0.1 < 0.2 is false, " + context);
        assertNotNull(context.getRootProblems());
        assertTrue(context.getRootProblems().size() == 1);
        // 0.1 < 0.2 < 0.2 is false
        context = validator.validate(new Decimal("0.2"), new ValidateContext());
        System.out.println("0.1 < 0.2 < 0.2 is false, " + context);
        assertNotNull(context.getRootProblems());
        assertTrue(context.getRootProblems().size() == 1);

        validator = new RangeValidator(new Decimal("0.1"), false, new Decimal("0.2"), false);
        // 0.1 <= 0.12 <= 0.2 is true
        context = validator.validate(new Decimal("0.12"), new ValidateContext());
        System.out.println("0.1 <= 0.12 <= 0.2 is true, " + context);
        assertNull(context.getRootProblems());
        // 0.1 <= 0.21 <= 0.2 is false
        context = validator.validate(new Decimal("0.21"), new ValidateContext());
        System.out.println("0.1 <= 0.21 <= 0.2 is false, " + context);
        assertNotNull(context.getRootProblems());
        assertTrue(context.getRootProblems().size() == 1);
        // 0.1 <= 0.01 <= 0.2 is false
        context = validator.validate(new Decimal("0.01"), new ValidateContext());
        System.out.println("0.1 <= 0.01 <= 0.2 is false, " + context);
        assertNotNull(context.getRootProblems());
        assertTrue(context.getRootProblems().size() == 1);
        // 0.1 <= 0.1 <= 0.2 is true
        context = validator.validate(new Decimal("0.1"), new ValidateContext());
        System.out.println("0.1 <= 0.1 <= 0.2 is true, " + context);
        assertNull(context.getRootProblems());
        // 0.1 <= 0.2 <= 0.2 is true
        context = validator.validate(new Decimal("0.2"), new ValidateContext());
        System.out.println("0.1 <= 0.2 <= 0.2 is true, " + context);
        assertNull(context.getRootProblems());

        validator = new RangeValidator(new Decimal("0.1"), false, null, false);
        // 0.1 <= 0.12 is true
        context = validator.validate(new Decimal("0.12"), new ValidateContext());
        System.out.println("0.1 <= 0.12 is true, " + context);
        assertNull(context.getRootProblems());
        // 0.1 <= 0.01 is false
        context = validator.validate(new Decimal("0.01"), new ValidateContext());
        System.out.println("0.1 <= 0.01 is false, " + context);
        assertNotNull(context.getRootProblems());
        assertTrue(context.getRootProblems().size() == 1);
        // 0.1 <= 0.1 is true
        context = validator.validate(new Decimal("0.1"), new ValidateContext());
        System.out.println("0.1 <= 0.1 is true, " + context);
        assertNull(context.getRootProblems());

        validator = new RangeValidator(null, false, new Date(10000), true);
        context = validator.validate(new Date(0), new ValidateContext());
        System.out.println(new Date(0) + " < " + new Date(10000) + " is true, " + context);
        assertNull(context.getRootProblems());
    }
}
