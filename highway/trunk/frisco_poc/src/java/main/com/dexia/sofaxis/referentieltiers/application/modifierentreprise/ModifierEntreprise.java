package com.dexia.sofaxis.referentieltiers.application.modifierentreprise;

import org.highway.service.Service;
import org.highway.service.ServiceInterceptors;
import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;
import org.highway.service.ejb.GenerateEjb;
import org.highway.transaction.TransactionInterceptor;
import org.highway.transaction.TransactionOption;
import org.highway.transaction.TransactionOption.TransactionOptions;

import com.dexia.sofaxis.referentieltiers.access.entreprise.Entreprise;

@GenerateEjb
@ServiceInterceptors(TransactionInterceptor.class)
public interface ModifierEntreprise extends Service, DynamicService, EjbService {

	@TransactionOption(TransactionOptions.REQUIRED)
	void modifierEntreprise(Entreprise entreprise);
}
