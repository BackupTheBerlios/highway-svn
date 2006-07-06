package com.dexia.sofaxis.referentieltiers.application.creerentreprise;

import org.highway.service.Service;
import org.highway.service.ServiceInterceptors;
import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;
import org.highway.transaction.TransactionInterceptor;
import org.highway.transaction.TransactionOption;
import org.highway.transaction.TransactionOption.TransactionOptions;

import com.dexia.sofaxis.referentieltiers.access.entreprise.Entreprise;
/**
 * 
 * @author frilaine
 * @highway.service.generate.ejb
 * @socle.service.interceptors org.highway.transaction.TransactionInterceptor 
 */
@org.highway.service.ejb.GenerateEjb
@ServiceInterceptors(TransactionInterceptor.class)
public interface CreerEntreprise extends Service, DynamicService, EjbService {
	
	/**
	 * @socle.service.transaction Required
	 */		
	@TransactionOption(TransactionOptions.MANDATORY)
	public CreationEntrepriseInfo dedoublonnerEntreprise(Entreprise entreprise, boolean forcer);
}
