package com.dexia.sofaxis.referentieltiers.application.modifiermedecin;

import org.highway.service.Service;
import org.highway.service.ServiceInterceptors;
import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;
import org.highway.service.ejb.GenerateEjb;
import org.highway.transaction.TransactionInterceptor;
import org.highway.transaction.TransactionOption;
import org.highway.transaction.TransactionOption.TransactionOptions;

import com.dexia.sofaxis.referentieltiers.access.adresse.Adresse;
import com.dexia.sofaxis.referentieltiers.access.medecin.Medecin;
import com.dexia.sofaxis.referentieltiers.access.rib.Rib;

@GenerateEjb
@ServiceInterceptors(TransactionInterceptor.class)
public interface ModifierMedecin extends Service, DynamicService, EjbService {
	@TransactionOption(TransactionOptions.REQUIRED)
	void modifierMedecin(Medecin medecin);
	@TransactionOption(TransactionOptions.REQUIRED)
	void modifierAdresseMedecin(String medecinPk, Adresse adresse);
	@TransactionOption(TransactionOptions.REQUIRED)
	void modifierRibMedecin(String medecinPk, Rib rib);
}
