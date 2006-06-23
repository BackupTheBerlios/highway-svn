package com.dexia.sofaxis.referentieltiers.application.creermedecin;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import org.highway.helper.StringHelper;

import com.dexia.sofaxis.referentieltiers.access.medecin.Medecin;
import com.dexia.sofaxis.referentieltiers.access.medecin.MedecinAccess;
import com.dexia.sofaxis.referentieltiers.access.medecin.RechercheMedecinCritere;
import com.dexia.sofaxis.referentieltiers.access.medecin.TypeEtatMedecin;
import com.dexia.sofaxis.referentieltiers.business.compteur.CompteurBusiness;
import com.dexia.sofaxis.tools.common.SearchResult;
import com.dexia.sofaxis.tools.locator.Locator;

public class CreerMedecinImpl implements CreerMedecin {

	private static final int MAX_MEDECIN = 30;

	/**
	 * Traitement de d�doublonnage du m�decin
	 */
	public CreationMedecinInfo dedoublonnerMedecin(Medecin medecin,
			boolean forcer) {
		CreationMedecinInfo infoCreation = new CreationMedecinInfo();
		MedecinAccess medecinAccess = Locator
				.getAccessService(MedecinAccess.class);
		infoCreation.setStatus(CreationMedecinInfo.STATUS_CREE);

		// r�gle stricte : n�ADELI diff�rent (mais le num�ro ADELI est
		// facultatif...)
		Collection listeMedecins = Collections.EMPTY_LIST;
		if (medecin.getIdentifiantFonctionnel() != null) {
			// On cherche un m�decin qui aurait d�j� ce num�ro
			RechercheMedecinCritere critere = new RechercheMedecinCritere();
			critere.setNumeroAdeli(medecin.getNumeroAdeli());
			SearchResult infoRecherche = null;
			try {
				infoRecherche = medecinAccess.chercherDoublon(critere);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (infoRecherche != null) {
				listeMedecins = infoRecherche.getResult();
			}
		}

		// Si la liste n'est pas vide, l'identifiant adeli est d�j� attribu�
		if (!listeMedecins.isEmpty()) {
			infoCreation.setStatus(CreationMedecinInfo.STATUS_DOUBLON);
			infoCreation.setDoublons(listeMedecins);
			return infoCreation;
		}
		// Il n'y a pas violation de la r�gle stricte du num�ro ADELI.

		// r�gle non stricte : nom/pr�nom diff�rent
		if (!forcer) {
			// On ne rel�ve cette r�gle que si le flag 'forcer' est � false
			SearchResult infoForcer = null;
			RechercheMedecinCritere critereForcer = new RechercheMedecinCritere();
			critereForcer.setNom(medecin.getNom());
			critereForcer.setPrenom(medecin.getPrenom());
			try {
				infoForcer = medecinAccess.chargerMedecin(critereForcer,
						MAX_MEDECIN);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			listeMedecins = infoForcer.getResult();
			if (!listeMedecins.isEmpty()) {
				// Il y a des doublons et le flag 'forcer' vaut false
				infoCreation.setStatus(CreationMedecinInfo.STATUS_DOUBLON);
				infoCreation.setDoublons(listeMedecins);
				return infoCreation;
			}
		}

		// Soit le flag forcer vaut true, soit il n'y a pas de doublons : On
		// peut cr�er le m�decin
		CompteurBusiness compteurBusiness = (CompteurBusiness) Locator
				.getBusinessService(CompteurBusiness.class);
		String identifiantFonctionnel = compteurBusiness.getNextMedecinNumber();
		medecin.setIdentifiantFonctionnel(identifiantFonctionnel);
		medecin.setTypeEtat(TypeEtatMedecin.NONAGREE.getCode());
		medecin.setDateInscription(new Date());
		medecin.setNomCourt(medecin.getNom());
		medecin.setNomPostalLigne1(StringHelper.toNoAccent(medecin.getNom())
				.toUpperCase());

		infoCreation.setMedecinCree(medecin);

		return infoCreation;
	}

}
