package org.highway.servicetest.access.projet;

import java.util.List;

import org.highway.database.DatabaseAccessBase;
import org.highway.database.DatabaseSession;

public class ProjetAccessImpl extends DatabaseAccessBase implements ProjetAccess {
	
	public Projet charger(Long projetId) {
	    DatabaseSession session = getSession();
		return (Projet) session.select(Projet.class, projetId);
	}
	
	public void creer(Projet projet) {
	    DatabaseSession session = getSession();
		session.insert(projet);
	}
	
	public void modifier(Projet projet) {
	    DatabaseSession session = getSession();
	    session.update(projet);
	}
	
	public List rechercherPourFirme(Long firmeId) {
	    DatabaseSession session = getSession();
	    return session.select(
	        "from Projet projet where projet.firmeId = ?",
	        firmeId
	    );
	}
	
	public void supprimer(Long projetId) {
	    DatabaseSession session = getSession();
		session.delete(charger(projetId));
	}
	
	public void supprimerTous() {
	    DatabaseSession session = getSession();
		session.delete("from Projet");
	}

}
