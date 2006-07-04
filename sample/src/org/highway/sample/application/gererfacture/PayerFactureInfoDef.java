package org.highway.sample.application.gererfacture;

import org.highway.bean.ValueObject;


public interface PayerFactureInfoDef extends ValueObject
{
	org.highway.sample.access.virement.Virement getVirement();
	
	org.highway.sample.access.fournisseur.Fournisseur getFournisseur();
	
	org.highway.sample.access.facture.Facture getFacture();
}
