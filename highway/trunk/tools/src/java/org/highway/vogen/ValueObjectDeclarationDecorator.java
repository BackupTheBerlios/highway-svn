package org.highway.vogen;

import net.sf.jelly.apt.decorations.DeclarationDecorator;

import com.sun.mirror.declaration.InterfaceDeclaration;
import com.sun.mirror.declaration.MethodDeclaration;

public class ValueObjectDeclarationDecorator extends DeclarationDecorator {

	@Override
	public void visitInterfaceDeclaration(InterfaceDeclaration declaration) {
		super.visitInterfaceDeclaration(new ValueObjectDecoratedInterfaceDeclaration(declaration));
	}

	@Override
	public void visitMethodDeclaration(MethodDeclaration declaration) {
		super.visitMethodDeclaration(new ValueObjectDecoratedMethodDeclaration(declaration));
	}

}
