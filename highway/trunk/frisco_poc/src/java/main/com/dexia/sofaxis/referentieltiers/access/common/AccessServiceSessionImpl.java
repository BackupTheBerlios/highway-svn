package com.dexia.sofaxis.referentieltiers.access.common;

import java.util.List;

import javax.naming.directory.SearchResult;

import org.highway.database.SelectQuery;
import org.highway.service.Service;

public class AccessServiceSessionImpl extends AccessImplAbstract implements Service {
	
	
	/**
	 * Requ�te de recherche
	 * @param queryHql Requ�te HQL de recherche
	 * @param parameters Liste des param�tres de la requ�tes
	 * @param maxResult Nombre maximum d'enregistrements retourn�
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
