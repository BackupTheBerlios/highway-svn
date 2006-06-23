package com.dexia.sofaxis.referentieltiers.access.entreprise;

import org.highway.vo.ValueObject;
@org.highway.annotation.ValueObject
public interface RechercheEntrepriseCritereDef extends ValueObject {

	String getRaisonSociale();
	
	String getVille();
	
	String getCodePostal();
	
	String getIdFonctionnel();
	
	String getNumSiret();
	
	
}
