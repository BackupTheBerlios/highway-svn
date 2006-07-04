/*
 * Created on 10 nov. 2004
 */
package org.highway.sample.domain.fournisseur;

import org.highway.database.DatabaseAccessBase;

/**
 * @author attias
 */
public class FournisseurAccessImpl extends DatabaseAccessBase implements
		FournisseurAccess
{
	public Fournisseur charger(long fournisseurId)
	{
		return (Fournisseur) getSession().select(Fournisseur.class,
				fournisseurId);
	}

	public void mettreAJour(Fournisseur fournisseur)
	{
		getSession().update(fournisseur);
	}
}
