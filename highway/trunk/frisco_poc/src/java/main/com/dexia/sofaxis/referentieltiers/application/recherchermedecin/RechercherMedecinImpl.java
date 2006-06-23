package com.dexia.sofaxis.referentieltiers.application.recherchermedecin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.highway.helper.StringHelper;

import com.dexia.sofaxis.referentieltiers.access.adresse.AdresseAccess;
import com.dexia.sofaxis.referentieltiers.access.medecin.Medecin;
import com.dexia.sofaxis.referentieltiers.access.medecin.MedecinAccess;
import com.dexia.sofaxis.referentieltiers.access.medecin.RechercheMedecinCritere;
import com.dexia.sofaxis.referentieltiers.access.rib.RibAccess;
import com.dexia.sofaxis.tools.common.SearchResult;
import com.dexia.sofaxis.tools.locator.Locator;



public class RechercherMedecinImpl implements RechercherMedecin
{
	private static final int MAX_MEDECIN = 30;

	/**
	 * Recherche une entreprise à partir de critères
	 * 
	 * @param criteria liste des crières pour la recherche
	 * @return un objet recherche entreprise info
	 * @throws MultiFieldException
	 */
	public SearchResult<Medecin> rechercherMedecin(RechercheMedecinCritere criteres) {
		SearchResult<Medecin> resultatRecherche = null;
		
		MedecinAccess medecinAccess = Locator.getAccessService(MedecinAccess.class);
		try {
			resultatRecherche = medecinAccess.chargerMedecin(criteres, MAX_MEDECIN);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultatRecherche;
	}

	public SearchResult<RechercherMedecinInfo> rechercherMedecinDetaille(RechercheMedecinCritere criteres) {

		SearchResult<Medecin> resultatRecherche = rechercherMedecin(criteres);

		List<RechercherMedecinInfo> listMInfo = new ArrayList<RechercherMedecinInfo>(MAX_MEDECIN); 
		
		AdresseAccess adresseAccess = Locator.getAccessService(AdresseAccess.class);		
		RibAccess ribAccess = (RibAccess) Locator.getAccessService(RibAccess.class);
		
		for (Medecin medecin : resultatRecherche.getResult()) {

			RechercherMedecinInfo info = new RechercherMedecinInfo();
			
			
			info.setMedecin(medecin);
			
			if (!StringHelper.isNullOrEmpty(medecin.getAdresseId())) {

				info.setAdresse(adresseAccess.charger(medecin.getAdresseId()));
			}
			
			if (!StringHelper.isNullOrEmpty(medecin.getRibId())) {

				info.setRib(ribAccess.charger(medecin.getRibId()));
			}
			listMInfo.add(info);
			
		}
		
		return new SearchResult<RechercherMedecinInfo>(listMInfo, resultatRecherche.hasMoreResult());
	}
	
	
	
}