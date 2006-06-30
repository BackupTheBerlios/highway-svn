package com.dexia.sofaxis.referentieltiers.application.creerentreprise;

import java.util.Collection;

import com.dexia.sofaxis.referentieltiers.access.entreprise.Entreprise;

public interface CreationEntrepriseInfoDef extends org.highway.bean.ValueObject {
	
	/** Cas d'un tiers cr�� correctement */
	public static final int STATUS_CREE = 1;

	/** Cas d'un tiers non cr�� car il existe d�j� en base */
	public static final int STATUS_DOUBLON = 2;
	
	public int getStatus();
	
	public Entreprise getEntrepriseCree();
	
	public Collection getDoublons();
}
