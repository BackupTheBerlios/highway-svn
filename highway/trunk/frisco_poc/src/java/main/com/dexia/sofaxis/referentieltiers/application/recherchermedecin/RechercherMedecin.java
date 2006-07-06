package com.dexia.sofaxis.referentieltiers.application.recherchermedecin;

import org.highway.service.Service;
import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;
import org.highway.service.ejb.GenerateEjb;

import com.dexia.sofaxis.referentieltiers.access.medecin.Medecin;
import com.dexia.sofaxis.referentieltiers.access.medecin.RechercheMedecinCritere;
import com.dexia.sofaxis.tools.common.SearchResult;


/**
 * @highway.service.generate.ejb
 * @socle.service.interceptors org.highway.debug.DebugInterceptor
 * org.highway.transaction.TransactionInterceptor
 */
@GenerateEjb
public interface RechercherMedecin extends Service, DynamicService, EjbService
{
	/**
	 * @socle.service.transaction Supports
	 */
	public SearchResult<Medecin> rechercherMedecin(RechercheMedecinCritere criteres);
	/**
	 * @socle.service.transaction Supports
	 */
	public SearchResult<RechercherMedecinInfo> rechercherMedecinDetaille(RechercheMedecinCritere criteres);

}
