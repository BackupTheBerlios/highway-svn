package org.highway.ejbgen;


import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.Declaration;
import com.sun.mirror.util.DeclarationVisitors;

public class EjbProcessor implements AnnotationProcessor {
	protected final AnnotationProcessorEnvironment env;
	
	public EjbProcessor (AnnotationProcessorEnvironment env) {
		this.env = env;
	}
	public void process() {
		EjbDefVisitor ejbVisitor = new EjbDefVisitor(env);
		EjbJarGenerator generatorEjbJar = new EjbJarGenerator();
		for ( Declaration d : env.getTypeDeclarations()) {
			d.accept(  DeclarationVisitors.getSourceOrderDeclarationScanner(
					ejbVisitor, DeclarationVisitors.NO_OP) );
		}
		generatorEjbJar.generate(ejbVisitor.getSessions());
	}
}
