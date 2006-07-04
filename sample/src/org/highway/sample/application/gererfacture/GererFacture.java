/*
 * Created on 26 oct. 2004
 */
package org.highway.sample.application.gererfacture;

import org.highway.sample.application.common.ApplicationService;
import org.highway.sample.domain.facture.Facture;
import org.highway.service.ejb.GenerateEjb;
import org.highway.transaction.TransactionOption;
import org.highway.transaction.TransactionOption.TransactionOptions;


@GenerateEjb
public interface GererFacture extends ApplicationService
{
	@TransactionOption(TransactionOptions.REQUIRED)
	public PayerFactureInfo payerFacture(Facture facture)
		throws MauvaisFournisseurException;
}

