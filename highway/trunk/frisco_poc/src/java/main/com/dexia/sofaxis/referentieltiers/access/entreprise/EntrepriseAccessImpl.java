/*
 * Created on 9 nov. 2004
 */
package com.dexia.sofaxis.referentieltiers.access.entreprise;

import java.util.ArrayList;

import org.highway.helper.StringHelper;

import com.dexia.sofaxis.referentieltiers.access.common.AccessServiceSessionImpl;
import com.dexia.sofaxis.tools.common.SearchResult;
import com.dexia.sofaxis.tools.common.UUIDHelper;

/**
 * @author attias
 */
public class EntrepriseAccessImpl extends AccessServiceSessionImpl implements EntrepriseAccess {
	private static final int MAX_DOUBLON = 10;

	public SearchResult<Entreprise> chargerEntreprise(RechercheEntrepriseCritere critere, int maxResultat)
	{
		ArrayList<Object> listParam = new ArrayList<Object>();
		StringBuilder selectQuery = new StringBuilder(256);
		
		selectQuery.append("from entreprise in class ");
		selectQuery.append("com.dexia.sofaxis.referentieltiers.access.entreprise.Entreprise ");
		selectQuery.append("where (1=1) ");
		if (!StringHelper.isNullOrEmpty(critere.getRaisonSociale()))
		{
			selectQuery.append(" and entreprise.nom like ? ");
			listParam.add(critere.getRaisonSociale() + "%");			
		}
		
		//Recherche sur l'id fonctionnel
		if (!(StringHelper.isNullOrEmpty(critere.getIdFonctionnel())))
		{
			selectQuery.append(" and entreprise.identifiantFonctionnel = ? ");
			listParam.add(critere.getIdFonctionnel());
		}
		
		//Recherche sur le numéro siret
		if (!(StringHelper.isNullOrEmpty(critere.getNumSiret())))
		{
			selectQuery.append(" and entreprise.numeroSiret = ? ");
			listParam.add(critere.getNumSiret() + "%");
		}
		// criteres sur l adresse
		if (!StringHelper.isNullOrEmpty(critere.getCodePostal())
				|| !StringHelper.isNullOrEmpty(critere.getVille())){
			
			selectQuery.append(" and exists ( "
					+ "from Adresse adresse " 
					+ "where medecin.adresseId = adresse.adresseId ");
			
			//Recherche sur la ville
			if (!(StringHelper.isNullOrEmpty(critere.getVille())))
			{
				selectQuery.append(" and adresse.libelleLocalite like ? ");
				listParam.add(critere.getVille() + "%");
			}
		
	        //	Recherche sur le code postal
			if (!(StringHelper.isNullOrEmpty(critere.getCodePostal())))
			{
				selectQuery.append(" and adresse.codePostal like ? ");
				listParam.add(critere.getCodePostal() + "%");
			}
			selectQuery.append(") ");
		}		 
		
		return search(selectQuery.toString(),listParam,maxResultat);
	}	

	public SearchResult<Entreprise> chercherDoublon(RechercheEntrepriseCritere critere) {
		return chargerEntreprise(critere, MAX_DOUBLON);
	}

	public void creerOuMettreAJour(Entreprise entreprise) {
		if (entreprise.isNew()) {
			entreprise.setTiersId(UUIDHelper.newUUID());
		}
		getSession().insertOrUpdate(entreprise);
	}

}