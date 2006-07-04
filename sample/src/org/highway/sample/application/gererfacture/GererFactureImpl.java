/*
 * Created on 26 oct. 2004
 */
package org.highway.sample.application.gererfacture;

import org.highway.sample.access.facture.Facture;
import org.highway.sample.access.facture.FactureAccess;
import org.highway.sample.access.facture.FactureStatut;
import org.highway.sample.access.fournisseur.Fournisseur;
import org.highway.sample.access.fournisseur.FournisseurAccess;
import org.highway.sample.access.fournisseur.FournisseurStatut;
import org.highway.sample.access.virement.Virement;
import org.highway.sample.access.virement.VirementAccess;
import org.highway.sample.application.common.AccessLocator;


public class GererFactureImpl implements GererFacture
{
	public PayerFactureInfo payerFacture(Facture facture)
		throws MauvaisFournisseurException
	{
		FournisseurAccess fournisseurAccess = (FournisseurAccess)
			AccessLocator.getAccessService(FournisseurAccess.class);
		
		FactureAccess factureAccess = (FactureAccess)
			AccessLocator.getAccessService(FactureAccess.class);
		
		VirementAccess virementAccess = (VirementAccess)
			AccessLocator.getAccessService(VirementAccess.class);

		Fournisseur fournisseur = fournisseurAccess.charger(facture.getFournisseurId());
		
		if (fournisseur.getStatut() == FournisseurStatut.MAUVAIS)
			throw new MauvaisFournisseurException(fournisseur.getMontantFactureTotal(),
				fournisseur.getStatut().toString());
		
		fournisseur.setMontantFactureTotal(
			fournisseur.getMontantFactureTotal().add(facture.getMontant()));
		
		fournisseurAccess.mettreAJour(fournisseur);
		
		Virement virement = new Virement();
		virement.setFactureId(facture.getId());
		virement.setFournisseurId(facture.getFournisseurId());
		virement.setMontant(facture.getMontant());
		virementAccess.creer(virement);	
		
		facture.setVirementId(virement.getId());
		facture.setStatut(FactureStatut.PAYE);
		factureAccess.mettreAJour(facture);
		
		PayerFactureInfo info = new PayerFactureInfo();
		info.setVirement(virement);
		info.setFournisseur(fournisseur);
		info.setFacture(facture);
		
		return info;
	}
}