package com.dexia.sofaxis.referentieltiers.application.creermedecin;

import org.highway.service.Service;
import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;
import org.highway.service.ejb.GenerateEjb;

import com.dexia.sofaxis.referentieltiers.access.medecin.Medecin;

/**
 * @highway.service.generate.ejb
 * @socle.service.interceptors org.highway.debug.DebugInterceptor
 * org.highway.transaction.TransactionInterceptor
 */
@GenerateEjb(useCompression=true)
public interface CreerMedecin extends Service, DynamicService, EjbService {
	/**
	 * @socle.service.transaction Required
	 */
	public CreationMedecinInfo dedoublonnerMedecin(Medecin medecin, boolean forcer) ;
}
