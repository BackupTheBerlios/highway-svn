package com.manpower.servicetest.application.mail;

import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.highway.exception.Assert;
import org.highway.exception.TechnicalException;
import org.highway.mail.MailHome;

import com.manpower.servicetest.ServiceTestServices;
import com.manpower.servicetest.access.employee.EmployeeAccess;

public class MailServiceImpl implements MailService
{
	public void createEmployees(Employee[] employees) throws MailException
	{
		MailService service = (MailService) ServiceTestServices.getApplicationService(MailService.class);
		
		for (int i = 0; i < employees.length; i++)
		{
			service.createEmployee(employees[i]);
		}
	}
	
	public void createEmployee(Employee employee) throws MailException
	{
		Assert.checkNotNull(employee);
		
		if (employee.getEmail() == null)
		{
			throw new MailException();
		}
		
		EmployeeAccess employeeAccess = (EmployeeAccess) ServiceTestServices.getAccessService(EmployeeAccess.class);
		employeeAccess.create(employee);
		
		try
		{
			MimeMessage message = MailHome.createMimeMessage();
			message.setRecipient(
				Message.RecipientType.TO,
				new InternetAddress(employee.getEmail())
			);
			message.setSubject("Test " + new Date());
			message.setText("New employee : " + employee.getFirstname() + " " + employee.getLastname());
			MailHome.sendInTransaction(message);
		}
		catch (MessagingException e)
		{
			throw new TechnicalException("Could not send email to: " + employee.getEmail(), e);
		}
	}
	
	public int countEmployees(String firstname)
	{
		EmployeeAccess employeeAccess = (EmployeeAccess) ServiceTestServices.getAccessService(EmployeeAccess.class);
		return employeeAccess.count(firstname);
	}

	public void deleteEmployees(String firstname)
	{
		EmployeeAccess employeeAccess = (EmployeeAccess) ServiceTestServices.getAccessService(EmployeeAccess.class);
		employeeAccess.delete(firstname);
	}
}
