package org.highway.service;

import java.util.ArrayList;
import java.util.List;

public class ManageCorporationImpl implements ManageCorporation
{

	public void calculateTotalIncomeOfAllCorporations()
	{
	}

	public void calculateIncome(Corporation corporation) throws RuningOutOfBusinessException
	{
		throw new RuningOutOfBusinessException();
	}

	public List<Employee> getEmployees(Corporation corporation, EmployeeType type) throws MergingException, RuningOutOfBusinessException
	{
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee());
		return employees;
	}
}
