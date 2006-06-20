package org.highway.ejbgen;

import java.util.ArrayList;
import java.util.Collection;

import org.highway.annotation.GenerateEjb;

import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.InterfaceDeclaration;
import com.sun.mirror.util.SimpleDeclarationVisitor;

public class EjbDefVisitor extends SimpleDeclarationVisitor {
	protected final AnnotationProcessorEnvironment env;
	EjbGenerator generatorEjb = new EjbGenerator();
	private Collection<InterfaceDeclaration> sessions = new ArrayList<InterfaceDeclaration>();
	
	public EjbDefVisitor(AnnotationProcessorEnvironment env) {
		this.env = env;
	}

	public void visitInterfaceDeclaration(InterfaceDeclaration declaration) {
		if (declaration.getAnnotation(GenerateEjb.class)!=null){
			generatorEjb.generate(declaration);
			sessions.add(declaration);
		}
	}

	public Collection<InterfaceDeclaration> getSessions() {
		return sessions;
	}

}
