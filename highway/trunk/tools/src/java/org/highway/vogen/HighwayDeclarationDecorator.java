package org.highway.vogen;

import net.sf.jelly.apt.decorations.DeclarationDecorator;

import com.sun.mirror.declaration.InterfaceDeclaration;
import com.sun.mirror.declaration.MethodDeclaration;

public class HighwayDeclarationDecorator extends DeclarationDecorator {

	@Override
	public void visitInterfaceDeclaration(InterfaceDeclaration declaration) {
		super.visitInterfaceDeclaration(new HighwayDecoratedInterfaceDeclaration(declaration));
	}

	@Override
	public void visitMethodDeclaration(MethodDeclaration declaration) {
		super.visitMethodDeclaration(new HighwayDecoratedMethodDeclaration(declaration));
	}

}
