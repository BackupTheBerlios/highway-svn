package com.dexia.sofaxis.referentieltiers.application.creerentreprise;

import org.highway.service.Service;
import org.highway.service.ServiceInterceptors;
import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;
import org.highway.transaction.TransactionInterceptor;
import org.highway.transaction.TransactionOption;
import org.highway.transaction.TransactionOption.TransactionOptions;

import com.dexia.sofaxis.referentieltiers.access.entreprise.Entreprise;

@org.highway.service.ejb.GenerateEjb
@ServiceInterceptors(TransactionInterceptor.class)
public interface CreerEntreprise extends Service, DynamicService, EjbService {
	

	@TransactionOption(TransactionOptions.REQUIRED)
	public CreationEntrepriseInfo dedoublonnerEntreprise(Entreprise entreprise, boolean forcer);
}
