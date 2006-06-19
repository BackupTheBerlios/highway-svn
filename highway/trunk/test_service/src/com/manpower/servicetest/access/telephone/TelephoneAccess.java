package com.manpower.servicetest.access.telephone;

import com.manpower.servicetest.access.AccessService;

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
