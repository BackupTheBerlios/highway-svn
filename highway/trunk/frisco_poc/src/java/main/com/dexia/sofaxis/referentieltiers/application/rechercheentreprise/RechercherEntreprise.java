/*
 * Created on 26 oct. 2004
 */
package com.dexia.sofaxis.referentieltiers.application.rechercheentreprise;



import org.highway.service.Service;
import org.highway.service.ServiceInterceptors;
import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;
import org.highway.transaction.TransactionInterceptor;
import org.highway.transaction.TransactionOption;
import org.highway.transaction.TransactionOption.TransactionOptions;

import com.dexia.sofaxis.referentieltiers.access.entreprise.Entreprise;
import com.dexia.sofaxis.referentieltiers.access.entreprise.RechercheEntrepriseCritere;
import com.dexia.sofaxis.tools.common.SearchResult;

@ServiceInterceptors(TransactionInterceptor.class)
public interface RechercherEntreprise extends Service, DynamicService, EjbService
{
	@TransactionOption(TransactionOptions.REQUIRED)
	public SearchResult<Entreprise> rechercherEntreprise(RechercheEntrepriseCritere criteres);

}
