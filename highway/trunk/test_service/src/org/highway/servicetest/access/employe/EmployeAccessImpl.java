package org.highway.servicetest.access.employe;

import org.highway.database.DatabaseAccessBase;
import org.highway.database.DatabaseSession;

public class EmployeAccessImpl extends DatabaseAccessBase implements
		EmployeAccess {

	public Employe charger(Long employeId) {
	    DatabaseSession session = getSession();
		return (Employe) session.select(Employe.class, employeId);
	}
	
	public void creer(Employe employe) {
	    DatabaseSession session = getSession();
		session.insert(employe);
	}
	
	public void modifier(Employe employe) {
	    DatabaseSession session = getSession();
	    session.update(employe);
	}
	
	public void supprimer(Employe employe) {
	    DatabaseSession session = getSession();
	    session.delete(employe);
	}
	
	public void supprimer(Long employeId) {
	    DatabaseSession session = getSession();
	    session.delete(charger(employeId));
	}
	
}
