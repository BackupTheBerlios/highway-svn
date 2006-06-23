package com.dexia.sofaxis.referentieltiers.access.medecin;

import org.highway.vo.ValueObject;
@org.highway.annotation.ValueObject
public interface RechercheMedecinCritereDef extends ValueObject {

	String getNom();
	
	String getPrenom();
	
	String getCodePostal();
	
	String getIdFonctionnel();
	
	String getVille();
	
	String getNumeroAdeli();
	
	
}
