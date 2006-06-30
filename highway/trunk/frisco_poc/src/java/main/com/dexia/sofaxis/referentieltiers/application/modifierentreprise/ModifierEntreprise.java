package com.dexia.sofaxis.referentieltiers.application.modifierentreprise;

import org.highway.service.Service;
import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;
import org.highway.service.ejb.GenerateEjb;

import com.dexia.sofaxis.referentieltiers.access.entreprise.Entreprise;
/**
 * 
 * @author frilaine
 * @highway.service.generate.ejb
 * @socle.service.interceptors org.highway.transaction.TransactionInterceptor
 * 
 */
@GenerateEjb
public interface ModifierEntreprise extends Service, DynamicService, EjbService {

	/**
	 * @socle.service.transaction Required
	 */
	void modifierEntreprise(Entreprise entreprise);
}
