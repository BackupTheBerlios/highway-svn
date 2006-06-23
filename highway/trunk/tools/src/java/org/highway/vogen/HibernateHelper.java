package org.highway.vogen;

import org.highway.annotation.VoMappingId;
import org.highway.annotation.VoMappingProperty;

import com.sun.mirror.declaration.InterfaceDeclaration;
import com.sun.mirror.declaration.MethodDeclaration;
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

	public String getPropertyHibernateType(MethodDeclaration aMethod) {
		// case where the hibernate type is explicitly specified
		String converter = aMethod.getAnnotation(VoMappingProperty.class)
				.type();

		if (converter != null) {
			return converter;
		}
		TypeMirror propertyType = aMethod.getReturnType();

		// case where the property type extends Enum and is qualified
		// the hibernate type is the property type + suffix

		if (propertyType.getClass().isEnum())
			return propertyType + HIBERNATE_TYPE_SUFFIX;

		// case where the property type extends Decimal
		// TODO : temporaire
		// if
		// (propertyType.getClass().isAssignableFrom(org.highway.vo.Decimal.class))
		if (propertyType.getClass().isAssignableFrom(Number.class))
			return DECIMAL_HIBERNATE_TYPE_CLASS_NAME;

		// case where the property type is not an enum:
		// the hibernate type is the property type
		return propertyType.getClass().getName();
	}

	public static boolean hasCompositeId(InterfaceDeclaration aDeclaration) {
		return countId(aDeclaration) > 1;
	}
}
