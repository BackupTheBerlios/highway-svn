package org.highway.servicetest.access.candidat;

import org.highway.database.DatabaseAccessBase;
import org.highway.database.DatabaseSession;
import org.highway.eai.EAIHome;

public class CandidatAccessImpl extends DatabaseAccessBase implements
		CandidatAccess
{

	public void create(Candidat candidat)
	{
		DatabaseSession session = getSession();
		session.insert(candidat);
		EAIHome.integrate(candidat, candidat.getId(),
				WBIConstants.DEFAULT_VERB_CREATE);

	}

	public void delete(Candidat candidat)
	{
		DatabaseSession session = getSession();
		session.delete(candidat);
		EAIHome.integrate(candidat, candidat.getId(),
				WBIConstants.DEFAULT_VERB_DELETE);
	}

	public void update(Candidat candidat)
	{
		DatabaseSession session = getSession();
		session.update(candidat);
		EAIHome.integrate(candidat, candidat.getId(),
				WBIConstants.DEFAULT_VERB_UPDATE);
	}
}
