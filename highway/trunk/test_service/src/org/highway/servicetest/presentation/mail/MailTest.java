package org.highway.servicetest.presentation.mail;

import org.highway.idgen.IdGenHome;

import org.highway.servicetest.application.mail.MailException;
import org.highway.servicetest.application.mail.MailService;
import org.highway.servicetest.presentation.PresentationInit;
import org.highway.servicetest.presentation.PresentationServices;

/**
 * Unit tests for {@link org.highway.mail.MailHome}.
 * 
 * @author Christian de Bevotte
 * @since 1.4.5
 */
public class MailTest extends TestCase
{
	private static final String FIRSTNAME = "Test";
	
	/**
	 * The recipient address is specified through a system property.
	 */
	private static final String EMAIL = System.getProperty("socle.servicetest.mail");
	
	protected void setUp() throws Exception
	{
		PresentationInit.init();
	}
	
	/**
	 * Tests the nominal behaviour. We should get two employees creation in
	 * database and two emails sending.
	 */
	public void testNominal()
	{
		MailService service = (MailService) 
			PresentationServices.getApplicationService(MailService.class);
		
		// Initialization
		service.deleteEmployees(FIRSTNAME);

		try
		{
			// Creation of two employees
			service.createEmployees(
				new Employee[] { 
					newEmployee(FIRSTNAME, "1", EMAIL), 
					newEmployee(FIRSTNAME, "2", EMAIL)
				}
			);
		}
		catch(MailException exc)
		{
			fail();
		}
		
		// Both employees should exist
		assertEquals(service.countEmployees(FIRSTNAME), 2);
	}
	
	/**
	 * Tests in case of functional exception. The transaction does not rollback
	 * by default. We should get one employee creation in database and one email
	 * sending.
	 */
	public void testFunctionalException()
	{
		MailService service = (MailService) 
			PresentationServices.getApplicationService(MailService.class);

		// Initialization
		service.deleteEmployees(FIRSTNAME);

		try
		{
			// A mail exception should be thrown since the second employee has a
			// null email address
			service.createEmployees(
				new Employee[] { 
					newEmployee(FIRSTNAME, "3", EMAIL), 
					newEmployee(FIRSTNAME, "4", null)
				}
			);
			
			fail();
		}
		catch (MailException exc)
		{
			// A mail exception should be catched
		}
		catch (Exception exc)
		{
			fail();
		}

		// The first employee should exist since by default a functional
		// exception does not rollback the transaction
		assertEquals(service.countEmployees(FIRSTNAME), 1);
	}
	
	/**
	 * Tests in case of technical exception. The transaction rollbacks by
	 * default. We should get no employee creation in database and no email
	 * sending.
	 */
	public void testTechnicalException()
	{
		MailService service = (MailService) 
			PresentationServices.getApplicationService(MailService.class);

		// Initialization
		service.deleteEmployees(FIRSTNAME);
		
		try
		{
			// A technical exception should be thrown since the second employee
			// is null
			service.createEmployees(
				new Employee[] { 
					newEmployee(FIRSTNAME, "5", EMAIL), 
					null
				}
			);
			
			fail();
		}
		catch(MailException exc)
		{
			fail();
		}
		catch(Exception exc)
		{
			// A technical exception should be catched
		}
		
		// No employee should exist since by default a technical exception 
		// rollbacks the transaction
		assertEquals(service.countEmployees(FIRSTNAME), 0);
	}

	private Employee newEmployee(String firstname, String lastname, String email)
	{
		Employee result = new Employee();
		result.setId(IdGenHome.getNextSimpleId());
		result.setFirstname(firstname);
		result.setLastname(lastname);
		result.setEmail(email);
		return result;
	}
}
