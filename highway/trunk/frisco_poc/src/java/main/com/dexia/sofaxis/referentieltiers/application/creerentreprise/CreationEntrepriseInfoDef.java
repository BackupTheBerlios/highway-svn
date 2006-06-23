package com.dexia.sofaxis.referentieltiers.application.creerentreprise;

import java.util.Collection;

import org.highway.vo.ValueObject;

import com.dexia.sofaxis.referentieltiers.access.entreprise.Entreprise;

public interface CreationEntrepriseInfoDef extends ValueObject {
	
	/** Cas d'un tiers créé correctement */
	public static final int STATUS_CREE = 1;

	/** Cas d'un tiers non créé car il existe déjà en base */
	public static final int STATUS_DOUBLON = 2;
	
	public int getStatus();
	
	public Entreprise getEntrepriseCree();
	
	public Collection getDoublons();
}
