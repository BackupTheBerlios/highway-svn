package com.dexia.sofaxis.referentieltiers.application.recherchermedecin;

import org.highway.service.Service;
import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;
import org.highway.service.ejb.GenerateEjb;
import org.highway.transaction.TransactionOption;
import org.highway.transaction.TransactionOption.TransactionOptions;

import com.dexia.sofaxis.referentieltiers.access.medecin.Medecin;
import com.dexia.sofaxis.referentieltiers.access.medecin.RechercheMedecinCritere;
import com.dexia.sofaxis.tools.common.SearchResult;


@GenerateEjb
@TransactionOption(TransactionOptions.REQUIRED)
public interface RechercherMedecin extends Service, DynamicService, EjbService
{
	@TransactionOption(TransactionOptions.REQUIRED)
	public SearchResult<Medecin> rechercherMedecin(RechercheMedecinCritere criteres);
	@TransactionOption(TransactionOptions.REQUIRED)
	public SearchResult<RechercherMedecinInfo> rechercherMedecinDetaille(RechercheMedecinCritere criteres);

}
