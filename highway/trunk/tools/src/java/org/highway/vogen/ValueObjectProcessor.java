package org.highway.vogen;


import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.Declaration;
import com.sun.mirror.util.DeclarationVisitors;

public class ValueObjectProcessor implements AnnotationProcessor {
	protected final AnnotationProcessorEnvironment env;
	
	public ValueObjectProcessor (AnnotationProcessorEnvironment env) {
		this.env = env;
	}
	public void process() {
		ValueObjectDefVisitor voVisitor = new ValueObjectDefVisitor(env);
		for ( Declaration d : env.getTypeDeclarations()) {
			d.accept(  DeclarationVisitors.getSourceOrderDeclarationScanner(
					voVisitor, DeclarationVisitors.NO_OP) );
		}
		HibernateCfgGenerator generatorHibernateCfg = new HibernateCfgGenerator();
		generatorHibernateCfg.generate(voVisitor.getResourcesNameForHibernate());
	}
}
