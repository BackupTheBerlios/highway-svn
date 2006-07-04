package org.highway.sample.application.gererfacture;

import org.highway.bean.ValueObject;
import org.highway.sample.domain.facture.Facture;
import org.highway.sample.domain.fournisseur.Fournisseur;
import org.highway.sample.domain.virement.Virement;


public interface PayerFactureInfoDef extends ValueObject
{
	Virement getVirement();
	
	Fournisseur getFournisseur();
	
	Facture getFacture();
}
