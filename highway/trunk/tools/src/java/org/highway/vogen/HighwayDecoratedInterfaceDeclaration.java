package org.highway.vogen;



import net.sf.jelly.apt.decorations.declaration.DecoratedInterfaceDeclaration;

import org.highway.ejbgen.EjbGenSessionHelper;

import com.sun.mirror.declaration.InterfaceDeclaration;

public class HighwayDecoratedInterfaceDeclaration extends
		DecoratedInterfaceDeclaration {

	public HighwayDecoratedInterfaceDeclaration(InterfaceDeclaration delegate) {
		super(delegate);
	}
	public String getGeneratedClassName(){
		return VoGenHelper.getGeneratedClassName((InterfaceDeclaration) this.delegate);
	}
	public String getGeneratedShortClassName(){
		return VoGenHelper.getGeneratedShortClassName((InterfaceDeclaration) this.delegate);
	}
	public String getSuperClassName(){
		return VoGenHelper.getSuperClassName((InterfaceDeclaration) this.delegate);
	}
	public boolean hasCompositeId(){
		return HibernateHelper.hasCompositeId((InterfaceDeclaration) this.delegate);
	}
	public boolean hasPrimitiveId(){
		return HibernateHelper.hasPrimitiveId((InterfaceDeclaration) this.delegate);
	}
	public String getEntityClassName(){
		return VoGenHelper.getEntityClassName((InterfaceDeclaration) this.delegate);
	}
	public String getResourceHibernateFileName(){
		return VoGenHelper.getResource((InterfaceDeclaration) this.delegate);
	}
	public String getHomeJndiName(){
		return EjbGenSessionHelper.homeJNDIName((InterfaceDeclaration) this.delegate);
	}
	public String beanFullClassName(){
		return EjbGenSessionHelper.beanFullClassName((InterfaceDeclaration) this.delegate);
	}
	public String homeFullClassName(){
		return EjbGenSessionHelper.homeFullClassName((InterfaceDeclaration) this.delegate);
	}
	public String remoteFullClassName(){
		return EjbGenSessionHelper.remoteFullClassName((InterfaceDeclaration) this.delegate);
	}
}
