package org.highway.servicetest.presentation.gereremploye;

import java.util.Date;

import org.highway.servicetest.access.employe.EmployeSexe;
import org.highway.servicetest.application.gereemploye.GererEmploye;
import org.highway.servicetest.application.gereemploye.GererEmployeCompress;
import org.highway.servicetest.application.gereemploye.TestEmploye;
import org.highway.servicetest.presentation.PresentationServices;

public class EmployePageTest extends TestCase
{
	public void testCreer()
	{
		try
		{
			TestEmploye employe = new TestEmploye("toto", 32);

			Projet projet = new Projet();
			projet.setDebut(new Date());
			projet.setNew(true);
			projet.setNom("project 1");

			Telephone telephone = new Telephone();
			telephone.setValue("05 23 15 48 12");
			Long firmeId = new Long(5);

			employe.setTelephone(telephone);
			employe.setProjet(projet);
			employe.setPaye(true);
			employe.setSexe(EmployeSexe.MASCULIN);
			employe.setFirmeId(firmeId);
			employe.setArrayByte("array".getBytes());
			GererEmploye gererEmploye = (GererEmploye) PresentationServices
				.getApplicationService(GererEmploye.class);
			TestEmploye employeEngage = gererEmploye.engagerEmploye(employe);
			assertTrue(32 == employeEngage.getAge());
			assertTrue("array".equals(new String(employe.getArrayByte())));
			assertNotNull(employeEngage.getFirmeTest());
			assertTrue(employeEngage.getFirmeTest().getId().longValue() == firmeId
				.longValue());

		}
		catch (RuntimeException e)
		{
			System.out.println("exception est " + e);
			e.printStackTrace();
			assertTrue(false);
			e.printStackTrace();
		}
	}

	public void testCreerCompress()
	{
		try
		{
			TestEmploye employe = new TestEmploye("toto", 32);

			Projet projet = new Projet();
			projet.setDebut(new Date());
			projet.setNew(true);
			projet.setNom("project 1");

			Telephone telephone = new Telephone();
			telephone.setValue("05 23 15 48 12");
			Long firmeId = new Long(5);

			employe.setTelephone(telephone);
			employe.setProjet(projet);
			employe.setPaye(true);
			employe.setSexe(EmployeSexe.MASCULIN);
			employe.setFirmeId(firmeId);
			employe.setArrayByte("array".getBytes());
			GererEmployeCompress gererEmploye = (GererEmployeCompress) PresentationServices
				.getApplicationService(GererEmployeCompress.class);
			TestEmploye employeEngage = gererEmploye.engagerEmploye(employe);
			assertTrue(32 == employeEngage.getAge());
			assertTrue("array".equals(new String(employe.getArrayByte())));
			assertNotNull(employeEngage.getFirmeTest());
			assertTrue(employeEngage.getFirmeTest().getId().longValue() == firmeId
				.longValue());
		}
		catch (RuntimeException e)
		{
			System.out.println("exception est " + e);
			e.printStackTrace();
			assertTrue(false);
			e.printStackTrace();
		}
	}

	public void testRetrunPrimitive()
	{
		TestEmploye employe = new TestEmploye("toto", 32);

		Projet projet = new Projet();
		projet.setDebut(new Date());
		projet.setNew(true);
		projet.setNom("project 1");

		Telephone telephone = new Telephone();
		telephone.setValue("05 23 15 48 12");
		Long firmeId = new Long(5);

		employe.setTelephone(telephone);
		employe.setProjet(projet);
		employe.setPaye(true);
		employe.setSexe(EmployeSexe.MASCULIN);
		employe.setFirmeId(firmeId);
		employe.setArrayByte("array".getBytes());

		GererEmploye gererEmploye = (GererEmploye) PresentationServices
			.getApplicationService(GererEmploye.class);
		System.out.println("ici 1");
		int age = gererEmploye.test1(employe, " test1", 30);
		assertTrue(30 == age);
	}

	public void testRetrunPrimitiveCompressed()
	{
		TestEmploye employe = new TestEmploye("toto", 32);

		Projet projet = new Projet();
		projet.setDebut(new Date());
		projet.setNew(true);
		projet.setNom("project 1");

		Telephone telephone = new Telephone();
		telephone.setValue("05 23 15 48 12");
		Long firmeId = new Long(5);

		employe.setTelephone(telephone);
		employe.setProjet(projet);
		employe.setPaye(true);
		employe.setSexe(EmployeSexe.MASCULIN);
		employe.setFirmeId(firmeId);
		employe.setArrayByte("array".getBytes());

		GererEmployeCompress gererEmploye = (GererEmployeCompress) PresentationServices
			.getApplicationService(GererEmployeCompress.class);
		System.out.println("ici");
		int age = gererEmploye.test1(employe, " test1", 30);
		assertTrue(30 == age);
	}

	
	public void testNoParameterCompressed()
	{

		GererEmployeCompress gererEmploye = (GererEmployeCompress) PresentationServices
			.getApplicationService(GererEmployeCompress.class);
		System.out.println("ici");
		int age = gererEmploye.testWithoutParameter();
		assertTrue(20 == age);
	}

	
	public void testNoParameter()
	{

		GererEmploye gererEmploye = (GererEmploye) PresentationServices
			.getApplicationService(GererEmploye.class);
		System.out.println("ici 1");
		int age = gererEmploye.testWithoutParameter();
		assertTrue(18 == age);
	}

	public void testVoidParameterCompressed()
	{

		GererEmployeCompress gererEmploye = (GererEmployeCompress) PresentationServices
			.getApplicationService(GererEmployeCompress.class);
		System.out.println("ici");
		gererEmploye.testVoid();
		assertTrue(true);
	}

	
	public void testVoidParameter()
	{

		GererEmploye gererEmploye = (GererEmploye) PresentationServices
			.getApplicationService(GererEmploye.class);
		System.out.println("ici 1");
		gererEmploye.testVoid();
		assertTrue(true);
	}

}
