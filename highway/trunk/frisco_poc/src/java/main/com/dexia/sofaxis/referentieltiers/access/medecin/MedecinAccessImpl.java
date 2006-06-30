/*
 * Created on 9 nov. 2004
 */
package com.dexia.sofaxis.referentieltiers.access.medecin;


import java.sql.SQLException;
import java.util.ArrayList;

import org.highway.helper.StringHelper;

import com.dexia.sofaxis.referentieltiers.access.common.AccessServiceSessionImpl;
import com.dexia.sofaxis.tools.common.SearchResult;
import com.dexia.sofaxis.tools.common.UUIDHelper;


/**
 * 
 */
public class MedecinAccessImpl extends AccessServiceSessionImpl implements
		MedecinAccess
{
	
	private static final String FROM_RECHERCHE_CLAUSE = "select medecin from " +
    "Medecin medecin ";

	private static final int MAX_DOUBLON = 10;

	public SearchResult<Medecin> chargerMedecin(RechercheMedecinCritere critere, int maxResultat) throws SQLException
	{
		ArrayList<Object> listParam = new ArrayList<Object>();
		
		StringBuilder selectQuery = new StringBuilder(256);

		StringBuilder queryFrom = new StringBuilder(128);
		StringBuilder queryWhere = new StringBuilder(128);
		
		queryFrom.append(FROM_RECHERCHE_CLAUSE);
		queryWhere.append(" where (1=1) ");
		//queryWhere.append(" where medecin.adresseId = adresse.adresseId");
		if (!(StringHelper.isNullOrEmpty(critere.getNom())))
		{
			queryWhere.append(" and medecin.nom like ? ");
			listParam.add(critere.getNom() + "%");
		}
	
					
		// Recherche sur le prénom
		if (!(StringHelper.isNullOrEmpty(critere.getPrenom())))
		{
			queryWhere.append(" and medecin.prenom like ? ");
			listParam.add(critere.getPrenom() + "%");
		}
		//Recherche sur l'id fonctionnel
		if (!(StringHelper.isNullOrEmpty(critere.getIdFonctionnel())))
		{
			queryWhere.append(" and medecin.identifiantFonctionnel = ? ");
			listParam.add(critere.getIdFonctionnel());
		}
		
        // Recherche sur le numéro adeli
		if (!(StringHelper.isNullOrEmpty(critere.getNumeroAdeli())))
		{
			queryWhere.append(" and medecin.numeroAdeli = ? ");
			listParam.add(critere.getNumeroAdeli());
		}
		
		// criteres sur l adresse
		if (!StringHelper.isNullOrEmpty(critere.getCodePostal())
				|| !StringHelper.isNullOrEmpty(critere.getVille())){
			
			queryWhere.append(" and exists ( "
					+ "from Adresse adresse " 
					+ "where medecin.adresseId = adresse.adresseId ");
			
			//Recherche sur la ville
			if (!(StringHelper.isNullOrEmpty(critere.getVille())))
			{
				queryWhere.append(" and adresse.libelleLocalite like ? ");
				listParam.add(critere.getVille() + "%");
			}
		
	        //	Recherche sur le code postal
			if (!(StringHelper.isNullOrEmpty(critere.getCodePostal())))
			{
				queryWhere.append(" and adresse.codePostal like ? ");
				listParam.add(critere.getCodePostal() + "%");
			}
			queryWhere.append(") ");
		}

		
		// Construction de la requête globale
		selectQuery.append(queryFrom.toString());
		selectQuery.append(queryWhere.toString());
		
		return search(selectQuery.toString(),listParam,maxResultat);
	}
	
	
	public SearchResult<Medecin> chercherDoublon(RechercheMedecinCritere critere) throws SQLException
	{
		return chargerMedecin(critere,MAX_DOUBLON);
	}


	public void creerOuMettreAJour(Medecin medecin) {
		if (medecin.isNew())
		{
			medecin.setTiersId(UUIDHelper.newUUID());
		}
		getSession().insertOrUpdate(medecin);
	}


	public Medecin charger(String primaryKey) {
		return (Medecin)getSession().select(Medecin.class,primaryKey);
	}
	


}
