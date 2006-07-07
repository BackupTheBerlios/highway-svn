package com.dexia.sofaxis.referentieltiers.application.creermedecin;

import org.highway.service.Service;
import org.highway.service.ServiceInterceptors;
import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;
import org.highway.service.ejb.GenerateEjb;
import org.highway.transaction.TransactionInterceptor;
import org.highway.transaction.TransactionOption;
import org.highway.transaction.TransactionOption.TransactionOptions;

import com.dexia.sofaxis.referentieltiers.access.medecin.Medecin;


@GenerateEjb(useCompression=true)
@ServiceInterceptors(TransactionInterceptor.class)
public interface CreerMedecin extends Service, DynamicService, EjbService {
	@TransactionOption(TransactionOptions.REQUIRED)
	public CreationMedecinInfo dedoublonnerMedecin(Medecin medecin, boolean forcer) ;
}
