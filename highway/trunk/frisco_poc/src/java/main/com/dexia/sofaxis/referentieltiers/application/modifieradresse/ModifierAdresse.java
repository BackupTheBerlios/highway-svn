package com.dexia.sofaxis.referentieltiers.application.modifieradresse;

import org.highway.service.Service;
import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;
import org.highway.service.ejb.GenerateEjb;

import com.dexia.sofaxis.referentieltiers.access.adresse.Adresse;
/**
 * @highway.service.generate.ejb
 * @socle.service.interceptors org.highway.transaction.TransactionInterceptor 
 */
@GenerateEjb
public interface ModifierAdresse extends Service, DynamicService, EjbService {
	/**
	 * @socle.service.transaction Required
	 */
	void modifierAdresse(Adresse adresse);

}
