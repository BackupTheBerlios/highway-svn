package org.highway.servicetest.access.employee;

import org.highway.service.ServiceInterceptors;
import org.highway.servicetest.access.AccessService;
import org.highway.transaction.TransactionInterceptor;
import org.highway.transaction.TransactionOption;
import org.highway.transaction.TransactionOption.TransactionOptions;
@ServiceInterceptors(TransactionInterceptor.class)
public interface EmployeeAccess extends AccessService
{
	@TransactionOption(TransactionOptions.REQUIRED)
	void create(Employee employee);
	@TransactionOption(TransactionOptions.SUPPORTS)
	void delete(String firstname);
	@TransactionOption(TransactionOptions.SUPPORTS)
	int count(String firstname);
}
