package com.dexia.sofaxis.referentieltiers.access.entreprise;

import org.highway.validate.JavaBeanValidator;
import org.highway.validate.ValidateContext;


public class RechercheEntrepriseCritereValidator extends  JavaBeanValidator {

	protected void validateObjectRules(Object valueObject, ValidateContext context) {
		// regle a respecter pour les criteres de recherche : au moins un des criteres
		// doit etre saisi.
		RechercheEntrepriseCritere criteres = (RechercheEntrepriseCritere) valueObject;
		if( criteres.getCodePostal() != null)
			return;
		if(criteres.getRaisonSociale() != null)
			return;
		
		if(criteres.getVille()!= null)
			return;
		
		if(criteres.getCodePostal()!= null)
			return;
		
		if(criteres.getIdFonctionnel()!= null)
			return;
		
		if(criteres.getNumSiret() != null)
			return;
		
		context.addProblem(this.getClass(), false, "Au moins un des critères de recherche doit être renseigné");
	}
}
