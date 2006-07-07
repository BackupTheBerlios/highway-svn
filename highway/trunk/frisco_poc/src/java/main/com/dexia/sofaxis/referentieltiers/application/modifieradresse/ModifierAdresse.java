package com.dexia.sofaxis.referentieltiers.application.modifieradresse;

import org.highway.service.Service;
import org.highway.service.ServiceInterceptors;
import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;
import org.highway.service.ejb.GenerateEjb;
import org.highway.transaction.TransactionInterceptor;
import org.highway.transaction.TransactionOption;
import org.highway.transaction.TransactionOption.TransactionOptions;

import com.dexia.sofaxis.referentieltiers.access.adresse.Adresse;

@GenerateEjb
@ServiceInterceptors(TransactionInterceptor.class)
public interface ModifierAdresse extends Service, DynamicService, EjbService {
	@TransactionOption(TransactionOptions.REQUIRED)
	void modifierAdresse(Adresse adresse);

}
