package org.highway.vogen;

import com.sun.mirror.declaration.MethodDeclaration;

import net.sf.jelly.apt.decorations.declaration.DecoratedMethodDeclaration;

public class ValueObjectDecoratedMethodDeclaration extends
		DecoratedMethodDeclaration {

	public ValueObjectDecoratedMethodDeclaration(MethodDeclaration delegate) {
		super(delegate);
	}
	
	public String getPropertyName(){
		return VoGenHelper.getPropertyName((MethodDeclaration) this.delegate);
	}
	public String getConstantName(){
		return VoGenHelper.getConstantName((MethodDeclaration) this.delegate);
	}
	public String getSetMethodName(){
		return VoGenHelper.getSetMethodName((MethodDeclaration) this.delegate);
	}

}
