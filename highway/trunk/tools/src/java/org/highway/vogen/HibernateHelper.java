package org.highway.vogen;

import java.util.Iterator;

import org.highway.annotation.VoMapping;
import org.highway.annotation.VoMappingDiscriminator;
import org.highway.annotation.VoMappingId;

import com.sun.mirror.declaration.InterfaceDeclaration;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.type.InterfaceType;
import com.sun.mirror.type.TypeMirror;

public class HibernateHelper {
	private static final String HIBERNATE_TYPE_SUFFIX = "HibernateType";

	private static final String DECIMAL_HIBERNATE_TYPE_CLASS_NAME = "org.highway.database.hibernate.DecimalHibernateType";

	public static boolean hasPrimitiveId(InterfaceDeclaration aDeclaration) {
		return countId(aDeclaration) == 1;
	}

	private static int countId(InterfaceDeclaration aDeclaration) {
		int count = 0;

		for (MethodDeclaration method : aDeclaration.getMethods()) {
			if (method.getAnnotation(VoMappingId.class) != null) {
				count++;
			}
		}

		return count;
	}

	public static String getPropertyHibernateType(MethodDeclaration aMethod) {
		// case where the hibernate type is explicitly specified
//		String converter = aMethod.getAnnotation(VoMappingProperty.class).type();
//
//		if (converter != null) {
//			return converter;
//		}
		TypeMirror propertyType = aMethod.getReturnType();

		// case where the property type extends Enum and is qualified
		// the hibernate type is the property type + suffix
//
//		if (propertyType.getClass().isAssignableFrom(org.highway.vo.Enum.class))
//			return propertyType + HIBERNATE_TYPE_SUFFIX;
//
//		if (propertyType.getClass().isAssignableFrom(org.highway.vo.Decimal.class))
//			return DECIMAL_HIBERNATE_TYPE_CLASS_NAME;

		// case where the property type is not an enum:
		// the hibernate type is the property type
		return propertyType.getClass().getName();
	}

	public static boolean hasCompositeId(InterfaceDeclaration aDeclaration) {
		return countId(aDeclaration) > 1;
	}
	public static boolean isClass(InterfaceDeclaration aDeclaration) {
		return superEntity(aDeclaration) == null;
	}
	private static InterfaceType superEntity(InterfaceDeclaration aDeclaration) {
		Iterator<InterfaceType> interfaces = aDeclaration.getSuperinterfaces().iterator();

		while (interfaces.hasNext()){
			InterfaceType superEntityDefInterface = interfaces.next();
			if (superEntityDefInterface.getDeclaration().getAnnotation(VoMapping.class)!=null){
				return superEntityDefInterface;
			}
		}
		return null;
	}
	public static boolean isSubclass(InterfaceDeclaration aDeclaration)
	{
		return superEntity(aDeclaration) != null
				&& aDeclaration.getAnnotation(VoMappingDiscriminator.class)!=null;
	}
	public static boolean isJoinedSubclass(InterfaceDeclaration aDeclaration){
		return superEntity(aDeclaration) != null
				&& aDeclaration.getAnnotation(VoMappingDiscriminator.class)==null;
	}
}
