package org.highway.service;

import java.util.List;

import org.highway.service.dynamic.DynamicLocator;

import junit.framework.TestCase;

public class DynamicServiceTest extends TestCase
{
	private ServiceLocator locator = new DynamicLocator();

	@Override
	protected void setUp() throws Exception
	{
		super.setUp();

	}

	public void test()
	{
		ManageCorporation manageCorporation = (ManageCorporation) locator
				.getService(ManageCorporation.class);

		try
		{
			manageCorporation.calculateIncome(new Corporation());
			fail();
		}
		catch (RuningOutOfBusinessException e)
		{
		}

		manageCorporation.calculateTotalIncomeOfAllCorporations();

		try
		{
			List<Employee> list = manageCorporation.getEmployees(
					new Corporation(), EmployeeType.WORKER);
			System.out.println(list);
		}
		catch (MergingException e)
		{
			fail();
		}
		catch (RuningOutOfBusinessException e)
		{
			fail();
		}
	}
}
