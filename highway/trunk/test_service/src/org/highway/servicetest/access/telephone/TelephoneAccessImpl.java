package org.highway.servicetest.access.telephone;

import org.highway.database.DatabaseAccessBase;
import org.highway.database.DatabaseSession;

public class TelephoneAccessImpl extends DatabaseAccessBase implements TelephoneAccess {
	
	public void creer(Telephone telephone) {
	    DatabaseSession session = getSession();
		session.insert(telephone);
	}
	
	public void supprimerTous() {
	    DatabaseSession session = getSession();
		session.delete("from Telephone");
	}

}
