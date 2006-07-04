/*
 * Created on 26 oct. 2004
 */
package org.highway.sample.application.gererfacture;

import org.highway.sample.application.common.ApplicationService;
import org.highway.service.ejb.GenerateEjb;
import org.highway.transaction.TransactionOption;
import org.highway.transaction.TransactionTimeout;
import org.highway.transaction.TransactionOption.TransactionOptions;

@GenerateEjb
public interface GererFacture extends ApplicationService
{
	@TransactionOption(TransactionOptions.REQUIRED)
	@TransactionTimeout(3000)
	public org.highway.sample.application.gererfacture.PayerFactureInfo payerFacture(
			org.highway.sample.access.facture.Facture facture)
			throws MauvaisFournisseurException;
}
