package org.highway;

import net.sf.jelly.apt.decorations.declaration.DecoratedMethodDeclaration;
import net.sf.jelly.apt.decorations.type.DecoratedClassType;
import net.sf.jelly.apt.decorations.type.DecoratedDeclaredType;
import net.sf.jelly.apt.decorations.type.DecoratedTypeMirror;

import org.highway.ejbgen.EjbGenHelper;
import org.highway.vogen.HibernateHelper;
import org.highway.vogen.VoGenHelper;

import com.sun.mirror.declaration.MethodDeclaration;

public class HighwayDecoratedMethodDeclaration extends
		DecoratedMethodDeclaration
{

	public HighwayDecoratedMethodDeclaration(MethodDeclaration delegate)
	{
		super(delegate);
	}

	public String getPropertyName()
	{
		return VoGenHelper.getPropertyName((MethodDeclaration) this.delegate);
	}

	public String getConstantName()
	{
		return VoGenHelper.getConstantName((MethodDeclaration) this.delegate);
	}

	public String getSetMethodName()
	{
		return VoGenHelper.getSetMethodName((MethodDeclaration) this.delegate);
	}

	public String getParametersDeclaration()
	{
		return EjbGenHelper
				.getParametersDeclaration((MethodDeclaration) this.delegate);
	}

	public String getExceptionsDeclaration()
	{
		return EjbGenHelper
				.getExceptionsDeclaration((MethodDeclaration) this.delegate);
	}

	public String getExceptionsDeclaration(String append)
	{
		return EjbGenHelper.getExceptionsDeclaration(
				(MethodDeclaration) this.delegate, append);
	}

	public String getPropertyHibernateType()
	{
		return HibernateHelper
				.getPropertyHibernateType((MethodDeclaration) this.delegate);
	}

	public String getReturnTypeQualifiedName()
	{
//		System.out.println("return type = " + getReturnType().toString() + ", primitive = " + ((DecoratedTypeMirror) getReturnType()).isPrimitive() + ", declared = " + ((DecoratedTypeMirror) getReturnType()).isDeclared());

		if (!((DecoratedTypeMirror) getReturnType()).isPrimitive()
				&& ((DecoratedTypeMirror) getReturnType()).isDeclared()
				&& ((DecoratedDeclaredType) getReturnType()).getDeclaration()!=null)
		{
			return ((DecoratedDeclaredType) getReturnType()).getDeclaration().getQualifiedName();
		}
		return getReturnType().toString();
	}
}