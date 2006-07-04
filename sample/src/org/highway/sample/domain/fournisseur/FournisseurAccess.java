/*
 * Created on 10 nov. 2004
 */
package org.highway.sample.access.fournisseur;

import org.highway.sample.access.common.AccessService;


/**
 * @author attias
 */
public interface FournisseurAccess extends AccessService
{

	public Fournisseur charger(long fournisseurId);

	public void mettreAJour(Fournisseur fournisseur);

}
