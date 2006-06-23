package com.dexia.sofaxis.referentieltiers.application.creermedecin;

import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;

import com.dexia.sofaxis.referentieltiers.access.medecin.Medecin;
import com.dexia.sofaxis.tools.services.ApplicationService;

/**
 * @highway.service.generate.ejb
 * @socle.service.interceptors org.highway.debug.DebugInterceptor
 * org.highway.transaction.TransactionInterceptor
 */
public interface CreerMedecin extends ApplicationService, DynamicService, EjbService {
	/**
	 * @socle.service.transaction Required
	 */
	public CreationMedecinInfo dedoublonnerMedecin(Medecin medecin, boolean forcer);
}
