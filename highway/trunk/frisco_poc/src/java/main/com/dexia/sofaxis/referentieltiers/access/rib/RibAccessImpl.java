/*
 * Created on 9 nov. 2004
 */
package com.dexia.sofaxis.referentieltiers.access.rib;

import com.dexia.sofaxis.referentieltiers.access.common.AccessServiceSessionImpl;
import com.dexia.sofaxis.tools.common.UUIDHelper;

public class RibAccessImpl extends AccessServiceSessionImpl implements
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
