package org.highway.vogen;

import java.util.ArrayList;
import java.util.List;

import org.highway.annotation.VoMapping;

import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.InterfaceDeclaration;
import com.sun.mirror.util.SimpleDeclarationVisitor;

public class ValueObjectDefVisitor extends SimpleDeclarationVisitor {
	protected final AnnotationProcessorEnvironment env;
	ValueObjectGenerator generatorVo = new ValueObjectGenerator();
	MappingGenerator generatorMapping = new MappingGenerator();
	List<String> resourceNameForHibernate = new ArrayList<String>();
	public ValueObjectDefVisitor(AnnotationProcessorEnvironment env) {
		this.env = env;
	}

	public void visitInterfaceDeclaration(InterfaceDeclaration declaration) {
		if (declaration.getAnnotation(org.highway.annotation.ValueObject.class)!=null)
			generatorVo.generate(declaration);
		if (declaration.getAnnotation(VoMapping.class)!=null){
			generatorMapping.generate(declaration);
			resourceNameForHibernate.add(HibernateHelper.getResourceNameForHibernate(declaration));
		}
	}
	public List<String> getResourcesNameForHibernate(){
		return resourceNameForHibernate;
	}
}
