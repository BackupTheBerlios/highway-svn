package org.highway.ejbgen;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.highway.annotation.GenerateEjb;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.apt.AnnotationProcessors;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;

public class EjbProcessorFactory implements AnnotationProcessorFactory {
	
	/** Option de la ligne de commande qui active le mode "release" */
	static final String RELEASE = "-Arelease";
	
	/** Collection contenant le nom des Annotations support�es. */
	protected Collection<String> supportedAnnotationTypes = 
		Arrays.asList(GenerateEjb.class.getName());

	
	/** Collection des options support�es. */
	protected Collection<String> supportedOptions =
		Arrays.asList(RELEASE);

	/**
	 * Retourne la liste des annotations support�es par cette Factory.
	 */
	public Collection<String> supportedAnnotationTypes() {
		return supportedAnnotationTypes;
	}

	/**
	 * Retourne la liste des options support�es par cette Factory.
	 */
	public Collection<String> supportedOptions() {
		return Collections.emptyList();
	}
	
	/**
	 * Retourne l'AnnotationProcessor associ� avec cette Factory...
	 */
	public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds,
			AnnotationProcessorEnvironment env) {
//		 Si aucune annotation n'est pr�sente on retourne un processeur "vide"
		if (atds.isEmpty())
			return AnnotationProcessors.NO_OP;
		else {
			return new EjbProcessor(env);
		}
	}
}