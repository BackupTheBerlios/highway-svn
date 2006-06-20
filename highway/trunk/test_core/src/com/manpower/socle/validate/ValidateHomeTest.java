package org.highway.validate;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.highway.validate.ValidateContext;
import org.highway.validate.ValidateHome;

/**
 * @author David Attias
 * @author Christian de Bevotte
 */
public class ValidateHomeTest extends TestCase
{
	public void testSimpleValidation()
	{
		ValidateContext context;
		Human human = new Human();
		context = ValidateHome.validate(human, null);
		// 3 properties are missing: firstname, birthdate and lastname
		assertTrue(context.getMissingProperties().size() == 3);
		System.out.println(context);

		//
		human.setFirstName("Eric");
		human.setLastName("Lef");
		context = ValidateHome.validate(human, null);
		assertTrue(context.getMissingProperties().size() == 1);
		System.out.println(context);
		//
		human.setFirstName("Eric");
		human.setLastName("Eric");
		context = ValidateHome.validate(human, null);
		assertTrue(context.getMissingProperties().size() == 1);
		System.out.println(context);
		//
		human.setFirstName("Eric");
		human.setLastName("Lefevre");
		human.setBirthDate(new Date(2007, 0, 1));
		context = ValidateHome.validate(human, null);
		assertNull(context.getMissingProperties());
		System.out.println(context);

		human.setEmailAddress("kjhkh@kljh");
		context = ValidateHome.validate(human, null);
		assertNull(context.getMissingProperties());
		System.out.println(context);
	}

	public void testValidateMultipleObjects()
	{
		Human human1 = new Human();
		Human human2 = new Human();

		ValidateContext validateContext = new ValidateContext();
		Set propertiesToValidate = new HashSet();
		propertiesToValidate.add("human1." + Human.FIRST_NAME);
		propertiesToValidate.add("human1." + Human.LAST_NAME);
		propertiesToValidate.add("human2." + Human.FIRST_NAME);
		propertiesToValidate.add("human2." + Human.LAST_NAME);
		validateContext.setPropertiesToValidate(propertiesToValidate);

		validateContext.enterProperty("human1");
		ValidateHome.validate(human1, validateContext);
		validateContext.leaveProperty();

		validateContext.enterProperty("human2");
		ValidateHome.validate(human2, validateContext);
		validateContext.leaveProperty();

		// First name and last name are mandatory
		assertTrue(validateContext.isInvalid());
		assertTrue(validateContext.isMissing("human1." + Human.FIRST_NAME));
		assertTrue(validateContext.isMissing("human1." + Human.LAST_NAME));
		assertTrue(validateContext.isMissing("human2." + Human.FIRST_NAME));
		assertTrue(validateContext.isMissing("human2." + Human.LAST_NAME));
		assertTrue(validateContext.getMissingProperties().size() == 4);
		assertFalse(validateContext.isProblematic());

		System.out.println(validateContext);
	}

	public void test2()
	{
		// test de base
		Employe emp = new Employe();
		Firme firme = new Firme();
		emp.setFirme(firme);
		emp.setNom("davidss");
		System.out.println(ValidateHome.validate(emp, null));

		// test deep = false
		ValidateContext context = new ValidateContext();
		context.setDeep(false);
		System.out.println(ValidateHome.validate(emp, context));

		// avoid property nom
		context = new ValidateContext();
		HashSet set = new HashSet();
		set.add(Employe.NOM);
		context.setPropertiesToAvoid(set);
		System.out.println(ValidateHome.validate(emp, context));

		// only validate nom
		context = new ValidateContext();
		set = new HashSet();
		set.add(Employe.NOM);
		context.setPropertiesToValidate(set);
		System.out.println(ValidateHome.validate(emp, context));

		// test graphe circulaire
		emp = new Employe();
		firme = new Firme();
		emp.setFirme(firme);
		firme.setDirecteur(emp);
		System.out.println(ValidateHome.validate(emp, null));

		System.out.println("test non graphe circulaire par contraste");
		emp = new Employe();
		firme = new Firme();
		emp.setFirme(firme);
		firme.setDirecteur(new Employe());
		System.out.println(ValidateHome.validate(emp, null));

		System.out.println("test héritage");
		Cadre cadre = new Cadre();
		cadre.setNomService("ASI");
		System.out.println(ValidateHome.validate(cadre, null));
	}
}
