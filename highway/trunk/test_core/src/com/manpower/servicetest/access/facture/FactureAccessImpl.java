package com.manpower.servicetest.access.facture;

import java.util.List;

import org.highway.database.DatabaseSession;
import org.highway.domain.AccessImplAbstract;

public class FactureAccessImpl extends AccessImplAbstract implements FactureAccess 
{

	public Facture charger(Long factureId) {
	    DatabaseSession session = getSession();
		return (Facture) session.select(Facture.class, factureId);
	}
	
	public void creer(Facture facture) {
	    DatabaseSession session = getSession();
		session.insert(facture);
	}
	
	public void modifier(Facture facture) {
	    DatabaseSession session = getSession();
	    session.update(facture);
	}
	
	public List rechercherFacturesPourProjet(Long projetId) {
	    DatabaseSession session = getSession();
		return session.select(
	        "from Facture facture where facture.projetId = ?",
			projetId
		);
	}
	
	public void supprimer(Long factureId) {
	    DatabaseSession session = getSession();
		session.delete(charger(factureId));
	}
	
	public void supprimerTous() {
	    DatabaseSession session = getSession();
		session.delete("from Facture");
	}

}
