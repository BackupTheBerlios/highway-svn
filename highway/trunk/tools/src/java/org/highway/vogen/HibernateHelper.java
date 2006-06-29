package org.highway.vogen;

import org.highway.database.DiscriminatorColumn;
import org.highway.database.Identity;
import org.highway.database.Mapped;

import com.sun.mirror.declaration.InterfaceDeclaration;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.type.InterfaceType;
import com.sun.mirror.type.TypeMirror;

public class HibernateHelper {
	private static final String DECIMAL_HIBERNATE_TYPE_CLASS_NAME = "org.highway.database.hibernate.DecimalHibernateType";

	public static boolean hasPrimitiveId(InterfaceDeclaration aDeclaration) {
		return countId(aDeclaration) == 1;
	}

	private static int countId(InterfaceDeclaration aDeclaration) {
		int count = 0;

		for (MethodDeclaration method : aDeclaration.getMethods()) {
			if (method.getAnnotation(Identity.class) != null) {
				count++;
			}
		}

		return count;
	}

	public static String getPropertyHibernateType(MethodDeclaration aMethod) {
		TypeMirror propertyType = aMethod.getReturnType();

		if (propertyType.getClass().isAssignableFrom(org.highway.bean.Decimal.class))
			return DECIMAL_HIBERNATE_TYPE_CLASS_NAME;

		return propertyType.getClass().getName();
	}

	public static boolean hasCompositeId(InterfaceDeclaration aDeclaration) {
		return countId(aDeclaration) > 1;
	}
	public static boolean isClass(InterfaceDeclaration aDeclaration) {
		return superEntity(aDeclaration) == null;
	}
	private static InterfaceType superEntity(InterfaceDeclaration aDeclaration) {
		for (InterfaceType superEntityDefInterface : aDeclaration.getSuperinterfaces())
		{
			if (superEntityDefInterface.getDeclaration().getAnnotation(Mapped.class)!=null){
				return superEntityDefInterface;
			}
		}
		return null;
	}
	public static boolean isSubclass(InterfaceDeclaration aDeclaration)
	{
		return superEntity(aDeclaration) != null
				&& aDeclaration.getAnnotation(DiscriminatorColumn.class)!=null;
	}
	public static boolean isJoinedSubclass(InterfaceDeclaration aDeclaration){
		return superEntity(aDeclaration) != null
				&& aDeclaration.getAnnotation(DiscriminatorColumn.class)==null;
	}

	public static String keyColumn(InterfaceDeclaration aDeclaration) throws VoGenException 
	{
		MethodDeclaration methodId = null;
		for (MethodDeclaration method : aDeclaration.getMethods())
		{
			if (method.getAnnotation(Identity.class)!=null)
				methodId = method;
				break;
		} 

		if (methodId == null)
		{
			throw new VoGenException(
				"no id mapping found in hierarchy of class "
				+ aDeclaration.getQualifiedName());
		}

		return VoGenHelper.getPropertyName(methodId);
	}

}
