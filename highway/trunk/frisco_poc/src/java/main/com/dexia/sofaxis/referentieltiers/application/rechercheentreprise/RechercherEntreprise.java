/*
 * Created on 26 oct. 2004
 */
package com.dexia.sofaxis.referentieltiers.application.rechercheentreprise;



import org.highway.service.Service;
import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;

import com.dexia.sofaxis.referentieltiers.access.entreprise.Entreprise;
import com.dexia.sofaxis.referentieltiers.access.entreprise.RechercheEntrepriseCritere;
import com.dexia.sofaxis.tools.common.SearchResult;

/**
 * @highway.service.generate.ejb
 * @socle.service.interceptors org.highway.debug.DebugInterceptor
 * org.highway.transaction.TransactionInterceptor
 */
public interface RechercherEntreprise extends Service, DynamicService, EjbService
{
	/**
	 * @socle.service.transaction Supports
	 */
	public SearchResult<Entreprise> rechercherEntreprise(RechercheEntrepriseCritere criteres);

}
