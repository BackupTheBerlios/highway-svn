package com.dexia.sofaxis.referentieltiers.application.modifiermedecin;

import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;

import com.dexia.sofaxis.referentieltiers.access.adresse.Adresse;
import com.dexia.sofaxis.referentieltiers.access.medecin.Medecin;
import com.dexia.sofaxis.referentieltiers.access.rib.Rib;
import com.dexia.sofaxis.tools.services.ApplicationService;
/**
 * @highway.service.generate.ejb
 * @socle.service.interceptors org.highway.transaction.TransactionInterceptor 
 */
public interface ModifierMedecin extends ApplicationService, DynamicService, EjbService {
	/**
	 * @socle.service.transaction Required
	 */
	void modifierMedecin(Medecin medecin);
	/**
	 * @socle.service.transaction Required
	 */
	void modifierAdresseMedecin(String medecinPk, Adresse adresse);
	/**
	 * @socle.service.transaction Required
	 */
	void modifierRibMedecin(String medecinPk, Rib rib);
}
