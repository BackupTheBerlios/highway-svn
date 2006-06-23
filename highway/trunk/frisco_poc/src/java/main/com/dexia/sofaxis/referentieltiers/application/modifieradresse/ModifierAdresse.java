package com.dexia.sofaxis.referentieltiers.application.modifieradresse;

import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;

import com.dexia.sofaxis.referentieltiers.access.adresse.Adresse;
import com.dexia.sofaxis.tools.services.ApplicationService;
/**
 * @highway.service.generate.ejb
 * @socle.service.interceptors org.highway.transaction.TransactionInterceptor 
 */
public interface ModifierAdresse extends ApplicationService, DynamicService, EjbService {
	/**
	 * @socle.service.transaction Required
	 */
	void modifierAdresse(Adresse adresse);

}
