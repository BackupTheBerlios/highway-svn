/*
 * Created on 9 nov. 2004
 */
package com.dexia.sofaxis.referentieltiers.access.rib;

import org.highway.database.DatabaseAccessBase;

import com.dexia.sofaxis.tools.common.UUIDHelper;

public class RibAccessImpl extends DatabaseAccessBase implements
		RibAccess {


	public void creerOuMettreAJour(Rib aRib) {
		if (aRib.isNew()) {
			aRib.setRibId(UUIDHelper.newUUID());
		}
		
		getSession().insertOrUpdate(aRib);
	}

	public Rib charger(String primaryKey) {
		return (Rib) getSession().select(Rib.class, primaryKey);
	}

}
