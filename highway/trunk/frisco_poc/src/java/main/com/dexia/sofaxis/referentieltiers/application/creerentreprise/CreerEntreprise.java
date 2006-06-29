package com.dexia.sofaxis.referentieltiers.application.creerentreprise;

import org.highway.annotation.GenerateEjb;
import org.highway.annotation.Interceptors;
import org.highway.annotation.TransactionOption;
import org.highway.annotation.TransactionOption.TransactionOptions;
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
@GenerateEjb
@Interceptors("org.highway.transaction.TransactionInterceptor")
public interface CreerEntreprise extends ApplicationService, DynamicService, EjbService {
	
	/**
	 * @socle.service.transaction Required
	 */		
	@TransactionOption(TransactionOptions.MANDATORY)
	public CreationEntrepriseInfo dedoublonnerEntreprise(Entreprise entreprise, boolean forcer);
}
