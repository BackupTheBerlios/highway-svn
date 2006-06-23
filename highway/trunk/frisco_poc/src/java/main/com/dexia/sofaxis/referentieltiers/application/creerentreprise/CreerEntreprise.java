package com.dexia.sofaxis.referentieltiers.application.creerentreprise;

import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;

import com.dexia.sofaxis.referentieltiers.access.entreprise.Entreprise;
import com.dexia.sofaxis.tools.services.ApplicationService;
/**
 * 
 * @author frilaine
 * @highway.service.generate.ejb
 * @socle.service.interceptors org.highway.transaction.TransactionInterceptor 
 */
public interface CreerEntreprise extends ApplicationService, DynamicService, EjbService {
	
	/**
	 * @socle.service.transaction Required
	 */		
	public CreationEntrepriseInfo dedoublonnerEntreprise(Entreprise entreprise, boolean forcer);
}
