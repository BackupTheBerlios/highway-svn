package com.dexia.sofaxis.referentieltiers.application.modifierentreprise;

import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;

import com.dexia.sofaxis.referentieltiers.access.entreprise.Entreprise;
import com.dexia.sofaxis.tools.services.ApplicationService;
/**
 * 
 * @author frilaine
 * @highway.service.generate.ejb
 * @socle.service.interceptors org.highway.transaction.TransactionInterceptor
 * 
 */
public interface ModifierEntreprise extends ApplicationService, DynamicService, EjbService {

	/**
	 * @socle.service.transaction Required
	 */
	void modifierEntreprise(Entreprise entreprise);
}
