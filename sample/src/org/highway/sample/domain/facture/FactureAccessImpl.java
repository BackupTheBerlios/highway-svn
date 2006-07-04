/*
 * Created on 9 nov. 2004
 */
package org.highway.sample.domain.facture;

import org.highway.database.DatabaseAccessBase;


/**
 * @author attias
 */
public class FactureAccessImpl extends DatabaseAccessBase implements FactureAccess
{
	public void mettreAJour(Facture facture)
	{
		getSession().update(facture);
	}

	public void creer(Facture facture)
	{
		getSession().insert(facture);
	}
}
