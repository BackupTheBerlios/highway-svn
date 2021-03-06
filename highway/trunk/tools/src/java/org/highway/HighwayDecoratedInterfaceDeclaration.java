package org.highway;



import net.sf.jelly.apt.decorations.declaration.DecoratedInterfaceDeclaration;

import org.highway.ejbgen.EjbGenSessionHelper;
import org.highway.vogen.HibernateHelper;
import org.highway.vogen.VoGenHelper;

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
	public boolean getHasCompositeId(){
		return HibernateHelper.hasCompositeId((InterfaceDeclaration) this.delegate);
	}
	public boolean getHasPrimitiveId(){
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
	public String getBeanFullClassName(){
		return EjbGenSessionHelper.beanFullClassName((InterfaceDeclaration) this.delegate);
	}
	public String getHomeFullClassName(){
		return EjbGenSessionHelper.homeFullClassName((InterfaceDeclaration) this.delegate);
	}
	public String getRemoteFullClassName(){
		return EjbGenSessionHelper.remoteFullClassName((InterfaceDeclaration) this.delegate);
	}
	public boolean getIfIsClass(){
		return HibernateHelper.isClass((InterfaceDeclaration) this.delegate);
	}
	public boolean getIfIsSubClass(){
		return HibernateHelper.isSubclass((InterfaceDeclaration) this.delegate);
	}
	public boolean getIfIsJoinedSubClass(){
		return HibernateHelper.isJoinedSubclass((InterfaceDeclaration) this.delegate);
	}
	public String getKeyColumn(){
		return HibernateHelper.keyColumn((InterfaceDeclaration)this.delegate);
	}
}
