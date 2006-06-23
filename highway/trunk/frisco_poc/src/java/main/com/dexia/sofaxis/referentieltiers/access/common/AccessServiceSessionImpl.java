package com.dexia.sofaxis.referentieltiers.access.common;

import java.util.List;

import org.highway.database.SelectQuery;
import org.highway.domain.AccessImplAbstract;

import com.dexia.sofaxis.tools.common.SearchResult;
import com.dexia.sofaxis.tools.services.AccessService;

public class AccessServiceSessionImpl extends AccessImplAbstract implements AccessService {
	
	
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
