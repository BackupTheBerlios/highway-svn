package org.highway.servicetest.access.telephone;

import org.highway.servicetest.access.AccessService;

public interface TelephoneAccess extends AccessService {
	
    /**
     * @socle.service.transaction Required
     */
	void creer(Telephone telephone);
	
    /**
     * @socle.service.transaction Required
     */
	void supprimerTous();

}
