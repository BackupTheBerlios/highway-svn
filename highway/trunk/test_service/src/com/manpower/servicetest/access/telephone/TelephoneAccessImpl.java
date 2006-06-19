package com.manpower.servicetest.access.telephone;

import org.highway.database.DatabaseSession;
import org.highway.domain.AccessImplAbstract;

public class TelephoneAccessImpl extends AccessImplAbstract implements TelephoneAccess {
	
	public void creer(Telephone telephone) {
	    DatabaseSession session = getSession();
		session.insert(telephone);
	}
	
	public void supprimerTous() {
	    DatabaseSession session = getSession();
		session.delete("from Telephone");
	}

}
