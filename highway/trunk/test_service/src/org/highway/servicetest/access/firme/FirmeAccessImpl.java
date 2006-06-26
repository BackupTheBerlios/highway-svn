package org.highway.servicetest.access.firme;

import java.util.List;

import org.highway.database.DatabaseSession;
import org.highway.database.SelectQuery;
import org.highway.domain.AccessImplAbstract;
import org.highway.idgen.IdGenHome;

public class FirmeAccessImpl extends AccessImplAbstract implements FirmeAccess {
	
	public Firme charger(Long firmeId) {
	    DatabaseSession session = getSession();
		return (Firme) session.select(Firme.class, firmeId);
	}
	
	public void creer(Firme firme) {
		
		// Exception lancée si le nom de la firme est null, pour test du rollback sur 
		// générateur d'identifiants.
		if (firme.getId() == null)
		{
			Long id = new Long(IdGenHome.getNextSimpleId());
			firme.setId(id);
		}
		if (firme.getNom() == null || firme.getNom().equals(""))
		{
			throw new IllegalArgumentException("Le nom de la firme " + firme.getId() + " est null");
		}
		
	    DatabaseSession session = getSession();
		session.insert(firme);
	}
	
	public void modifier(Firme firme) {
	    DatabaseSession session = getSession();
		session.update(firme);
	}
	
	public List rechercherTous() {
	    DatabaseSession session = getSession();
		SelectQuery query = session.createSelectQuery();
		query.addQueryText("from Firme firme order by firme.id desc");
		query.setFetchMax(10);
	    return query.list();
	}
	
	public List rechercherTous(int first, int max) {
	    DatabaseSession session = getSession();
		SelectQuery query = session.createSelectQuery();
		query.addQueryText("from Firme firme order by firme.id");
		query.setFetchFirst(first);
		query.setFetchMax(max);
	    return query.list();
	}
	
	public void supprimer(Long firmeId) {
	    DatabaseSession session = getSession();
	    session.delete(charger(firmeId));
	}
	
	public void supprimerTous() {
	    DatabaseSession session = getSession();
		session.delete("from Firme");
	}

}
