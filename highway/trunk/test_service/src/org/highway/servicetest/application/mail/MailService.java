package org.highway.servicetest.application.mail;

import org.highway.servicetest.access.employee.Employee;
import org.highway.servicetest.application.ApplicationService;

/**
 * @socle.service.generate.ejb
 */
public interface MailService extends ApplicationService
{
    /**
     * @socle.service.transaction Required
     */
	void createEmployees(Employee[] employees) throws MailException;
	
	/**
	 * @socle.service.transaction Required
	 */
	void createEmployee(Employee employee) throws MailException;
	
	/**
	 * @socle.service.transaction Supports
	 */
	void deleteEmployees(String firstname);
	
	/**
	 * @socle.service.transaction Supports
	 */
	int countEmployees(String firstname);
}
