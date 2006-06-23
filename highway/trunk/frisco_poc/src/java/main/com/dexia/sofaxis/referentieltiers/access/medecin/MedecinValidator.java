package com.dexia.sofaxis.referentieltiers.access.medecin;

import org.highway.validate.JavaBeanValidator;
import org.highway.validate.ValidateContext;

public class MedecinValidator extends JavaBeanValidator {


	@Override
	protected void validateObjectRules(Object valueObject, ValidateContext context) {
		Medecin medecin = (Medecin) valueObject;
		if(medecin.getNom()!=null && medecin.getNom().startsWith(" ")) {
			context.addProblem(this.getClass(), false, "La propiété 'nom' ne doit pas commencer par un espace");
		}

	}

}
