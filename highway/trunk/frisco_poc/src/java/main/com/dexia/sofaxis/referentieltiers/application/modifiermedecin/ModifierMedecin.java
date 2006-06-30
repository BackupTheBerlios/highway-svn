package com.dexia.sofaxis.referentieltiers.application.modifiermedecin;

import org.highway.service.Service;
import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;
import org.highway.service.ejb.GenerateEjb;

import com.dexia.sofaxis.referentieltiers.access.adresse.Adresse;
import com.dexia.sofaxis.referentieltiers.access.medecin.Medecin;
import com.dexia.sofaxis.referentieltiers.access.rib.Rib;
/**
 * @highway.service.generate.ejb
 * @socle.service.interceptors org.highway.transaction.TransactionInterceptor 
 */
@GenerateEjb
public interface ModifierMedecin extends Service, DynamicService, EjbService {
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
