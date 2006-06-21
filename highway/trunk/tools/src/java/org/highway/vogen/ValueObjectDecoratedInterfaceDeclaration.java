package org.highway.vogen;



import net.sf.jelly.apt.decorations.declaration.DecoratedInterfaceDeclaration;

import com.sun.mirror.declaration.InterfaceDeclaration;

public class ValueObjectDecoratedInterfaceDeclaration extends
		DecoratedInterfaceDeclaration {

	public ValueObjectDecoratedInterfaceDeclaration(InterfaceDeclaration delegate) {
		super(delegate);
	}
	public String getGeneratedClassName()
	{
		return VoGenHelper.getGeneratedClassName((InterfaceDeclaration) this.delegate);
	}
	public String getGeneratedShortClassName()
	{
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
}
