package org.highway.service;

import java.util.List;

import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;
import org.highway.service.ejb.GenerateEjb;

@GenerateEjb
public interface ManageCorporation extends EjbService, DynamicService
{
	void calculateTotalIncomeOfAllCorporations();

	void calculateIncome(Corporation corporation)
			throws RuningOutOfBusinessException;

	List<Employee> getEmployees(Corporation corporation, EmployeeType type)
			throws MergingException, RuningOutOfBusinessException;
}
