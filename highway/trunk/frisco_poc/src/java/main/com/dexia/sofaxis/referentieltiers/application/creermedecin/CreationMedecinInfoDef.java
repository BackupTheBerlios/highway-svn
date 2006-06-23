package com.dexia.sofaxis.referentieltiers.application.creermedecin;

import java.util.Collection;

import org.highway.vo.ValueObject;

import com.dexia.sofaxis.referentieltiers.access.medecin.Medecin;

public interface CreationMedecinInfoDef extends ValueObject {

	/** Cas d'un tiers cr�� correctement */
	public static final int STATUS_CREE = 1;

	/** Cas d'un tiers non cr�� car il existe d�j� en base */
	public static final int STATUS_DOUBLON = 2;
	
	public int getStatus();
	
	public Medecin getMedecinCree();
	
	public Collection getDoublons();

}
