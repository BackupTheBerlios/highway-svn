package org.highway.servicetest.application.gereemploye;

import org.highway.servicetest.application.ApplicationService;

/**
 * @socle.service.generate.ejbzip
 */
public interface GererEmployeCompress extends ApplicationService {
	

    /**
     * @socle.service.transaction Supports
     */
	public TestEmploye engagerEmploye (TestEmploye employe) ;

	/**
     * @socle.service.transaction Supports
     */
	public int test1(TestEmploye testEmploye, String nomTest, int age );

	
	/**
     * @socle.service.transaction Supports
     */
	public int testWithoutParameter();

	/**
     * @socle.service.transaction Supports
     */
	public void testVoid();

}

