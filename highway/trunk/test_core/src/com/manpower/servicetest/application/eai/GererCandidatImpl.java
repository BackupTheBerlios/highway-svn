package com.manpower.servicetest.application.eai;

import org.highway.debug.DebugHome;

import com.manpower.servicetest.ServiceTestServices;
import com.manpower.servicetest.access.candidat.CandidatAccess;

public class GererCandidatImpl implements GererCandidat
{

	public void engagerCandidat(Candidat candidat)
	{
		DebugHome.debugEnter();

		CandidatAccess candAccess = (CandidatAccess) ServiceTestServices
				.getAccessService(CandidatAccess.class);
		candAccess.create(candidat);

		DebugHome.debugExit();
	}

	public void virerCandidat(Candidat candidat)
	{
		DebugHome.debugEnter();

		CandidatAccess candAccess = (CandidatAccess) ServiceTestServices
				.getAccessService(CandidatAccess.class);
		candAccess.delete(candidat);

		DebugHome.debugExit();
	}

}
