package com.manpower.servicetest.application.gereemploye;

import org.highway.debug.DebugHome;

public class GererEmployeCompressImpl implements GererEmployeCompress {


	public TestEmploye engagerEmploye(TestEmploye employe) {
		DebugHome.debugEnter();
		employe.setStatus("engaged");
		TestFirme firme = new TestFirme(" mapower", "rue touzet" );
		firme.setId(employe.getFirmeId());
		employe.setFirmeTest(firme);
		
		return employe;
	}	

	/**
     * @socle.service.transaction Supports
     */
	public int test1(TestEmploye testEmploye, String nomTest, int age ){
		return age;
		
	}
	
	/**
     * @socle.service.transaction Supports
     */
	public int testWithoutParameter(){
		return 20;
	}

	/**
     * @socle.service.transaction Supports
     */
	public void testVoid(){
		
	}

}
