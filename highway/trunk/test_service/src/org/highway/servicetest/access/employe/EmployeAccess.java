package org.highway.servicetest.access.employe;

import org.highway.servicetest.access.AccessService;

public interface EmployeAccess extends AccessService {
	
    /**
     * @socle.service.transaction Supports
     */
	Employe charger(Long employeId);
	
    /**
     * @socle.service.transaction Required
     */
	void creer(Employe employe);
	
    /**
     * @socle.service.transaction Required
     */
	void modifier(Employe employe);
	
    /**
     * @socle.service.transaction Required
     */
	void supprimer(Employe employe);
	
    /**
     * @socle.service.transaction Required
     */
	void supprimer(Long employeId);
	
	
}

