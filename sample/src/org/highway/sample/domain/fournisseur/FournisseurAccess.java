/*
 * Created on 10 nov. 2004
 */
package org.highway.sample.domain.fournisseur;

import org.highway.sample.domain.common.AccessService;


/**
 * @author attias
 */
public interface FournisseurAccess extends AccessService
{

	public Fournisseur charger(long fournisseurId);

	public void mettreAJour(Fournisseur fournisseur);

}
