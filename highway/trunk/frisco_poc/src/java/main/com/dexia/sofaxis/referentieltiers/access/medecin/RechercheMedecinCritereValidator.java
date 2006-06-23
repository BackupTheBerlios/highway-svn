package com.dexia.sofaxis.referentieltiers.access.medecin;

import org.highway.validate.JavaBeanValidator;
import org.highway.validate.ValidateContext;


public class RechercheMedecinCritereValidator extends  JavaBeanValidator {

	protected void validateObjectRules(Object valueObject, ValidateContext context) {
		// regle a respecter pour les criteres de recherche : au moins un des criteres
		// doit etre saisi.
		RechercheMedecinCritere criteres = (RechercheMedecinCritere) valueObject;
		if(criteres.getCodePostal() != null)
			return;
		if(criteres.getNom() != null)
			return;
		if(criteres.getNumeroAdeli() != null)
			return;
		if(criteres.getPrenom() != null)
			return;
		if(criteres.getNom() != null)
			return;
		if(criteres.getCodePostal() != null)
			return;
		if(criteres.getIdFonctionnel() != null)
			return;
		if(criteres.getVille() != null)
			return;
		
		context.addProblem(this.getClass(), false, "Au moins un des critères de recherche doit être renseigné");
	}
}
