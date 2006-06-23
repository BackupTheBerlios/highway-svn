/*
 * Created on 9 nov. 2004
 */
package com.dexia.sofaxis.referentieltiers.access.medecin;


import java.sql.SQLException;

import org.highway.service.dynamic.DynamicService;

import com.dexia.sofaxis.tools.common.SearchResult;
import com.dexia.sofaxis.tools.services.AccessService;



public interface MedecinAccess extends AccessService, DynamicService
{
	SearchResult<Medecin> chargerMedecin(RechercheMedecinCritere critere, int maxResultat) throws SQLException;
	
	SearchResult<Medecin> chercherDoublon(RechercheMedecinCritere critere) throws SQLException;
	
	void creerOuMettreAJour(Medecin medecin);
	
	Medecin charger(String primaryKey);
}
