package com.dexia.sofaxis.referentieltiers.business.compteur;

import org.highway.service.Service;
import org.highway.service.dynamic.DynamicService;

public interface CompteurBusiness extends Service, DynamicService {

	String getNextCollectiviteNumber();
	
	String getNextEntrepriseNumber();
	
	String getNextMedecinNumber();
}
