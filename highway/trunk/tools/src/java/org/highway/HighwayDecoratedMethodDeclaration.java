package org.highway;

import net.sf.jelly.apt.decorations.declaration.DecoratedMethodDeclaration;

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

	public String getExceptionsDeclarationWithRemoteException()
	{
		return EjbGenHelper.getExceptionsDeclaration(
				(MethodDeclaration) this.delegate, "java.rmi.RemoteException");
	}

	public String getPropertyHibernateType()
	{
		return HibernateHelper
				.getPropertyHibernateType((MethodDeclaration) this.delegate);
	}

	public String getReturnTypeQualifiedName()
	{
		return getReturnType().toString();
	}

}