package org.highway.servicetest.presentation.eai;

import org.highway.servicetest.application.eai.GererCandidat;
import org.highway.servicetest.presentation.PresentationServices;

/**
 * Unit tests for {@link org.highway.eai.EAIHome}.
 * 
 * @author Jérôme RAULINE
 * @since 1.4.1
 */
public class EAITest extends TestCase
{

	/**
	 * Tests the nominal behaviour.
	 */
	public void testNominal()
	{

		// Get service
		GererCandidat service = (GererCandidat) PresentationServices
				.getApplicationService(GererCandidat.class);
		assertNotNull(service);

		// Create candidat
		Candidat cand = new Candidat();
		cand.setId(new Long(1));
		cand.setName("My Name");

		service.engagerCandidat(cand);
		service.virerCandidat(cand);
	}

}
