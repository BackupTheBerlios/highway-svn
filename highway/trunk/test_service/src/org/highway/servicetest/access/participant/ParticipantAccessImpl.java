package org.highway.servicetest.access.participant;

import org.highway.database.DatabaseAccessBase;
import org.highway.database.DatabaseSession;

public class ParticipantAccessImpl extends DatabaseAccessBase implements ParticipantAccess {
	
	public Participant charger(Participant participant) {
		DatabaseSession session = getSession();
		return (Participant) session.select(participant);
	}
	
	public Participant charger(Long projetId, Long employeId) {
	    Participant participant = new Participant();
	    participant.setProjetId(projetId);
	    participant.setEmployeId(employeId);
	    DatabaseSession session = getSession();
		return (Participant) session.select(Participant.class, participant);
	}
	
	public void creer(Participant participant) {
	    DatabaseSession session = getSession();
		session.insert(participant);
	}
	

	public void supprimer(Long projetId, Long employeId) {
	    DatabaseSession session = getSession();
	    session.delete(charger(projetId, employeId));
	}
	
	public void supprimer(Participant participant) {
	    DatabaseSession session = getSession();
	    session.delete(participant);
	}
	
	public void supprimerPourProjet(Long projetId) {
	    DatabaseSession session = getSession();
	    session.delete(
			"from Participant participant where participant.projetId = ?",
			projetId
		);
	}
	
	public void supprimerTous() {
	    DatabaseSession session = getSession();
	    session.delete("from Participant");
	}

}
