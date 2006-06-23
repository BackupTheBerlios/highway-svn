package com.dexia.sofaxis.referentieltiers.business.compteur;

import org.highway.service.dynamic.DynamicService;

import com.dexia.sofaxis.tools.services.BusinessService;

public interface CompteurBusiness extends BusinessService, DynamicService {

	String getNextCollectiviteNumber();
	
	String getNextEntrepriseNumber();
	
	String getNextMedecinNumber();
}
