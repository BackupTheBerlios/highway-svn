/*
 * Created on 9 nov. 2004
 */
package org.highway.sample.access.facture;

import org.highway.sample.access.common.AccessService;

/**
 * @author attias
 */
public interface FactureAccess extends AccessService
{
	void creer(Facture facture);

	void mettreAJour(Facture facture);
}
