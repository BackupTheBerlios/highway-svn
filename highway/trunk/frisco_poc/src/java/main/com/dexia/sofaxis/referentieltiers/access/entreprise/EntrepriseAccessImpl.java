/*
 * Created on 9 nov. 2004
 */
package com.dexia.sofaxis.referentieltiers.access.entreprise;

import java.util.ArrayList;
import java.util.List;

import org.highway.database.DatabaseAccessBase;
import org.highway.database.SelectQuery;
import org.highway.helper.StringHelper;

import com.dexia.sofaxis.tools.common.SearchResult;
import com.dexia.sofaxis.tools.common.UUIDHelper;

/**
 * 
 */
public class EntrepriseAccessImpl extends DatabaseAccessBase implements EntrepriseAccess {
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
	/**
	 * Requête de recherche
	 * @param queryHql Requête HQL de recherche
	 * @param parameters Liste des paramètres de la requêtes
	 * @param maxResult Nombre maximum d'enregistrements retourné
	 * @return
	 */
	protected SearchResult search(String queryHql, List parameters,int maxResult )
	{

		SelectQuery selectQuery = getSession().createSelectQuery();
		
		selectQuery.addQueryText(queryHql);
		selectQuery.setParameters(parameters);
		selectQuery.setFetchMax(maxResult+1);
		selectQuery.setCheckTooManyResults(false);
		
		List resultList = selectQuery.list();
		
		boolean hasMoreResult = false;
		if (resultList.size() > maxResult)
		{
			hasMoreResult = true;
			resultList.remove(maxResult);
		}	
		return new SearchResult(resultList, hasMoreResult);
	}
}