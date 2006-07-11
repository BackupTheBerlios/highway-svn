package org.highway.vogen;

import java.text.MessageFormat;
import java.util.Collection;

import org.highway.JavaHelper;

import com.sun.mirror.declaration.InterfaceDeclaration;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;
import com.sun.mirror.type.InterfaceType;

public class VoGenHelper
{

	private static String VALUE_OBJECT_ABSTRACT_CLASS_NAME = "org.highway.bean.ValueObjectAbstract";

	private static String HIBERNATE_MAPPING_OUTPUT_FILE_NAME = "{0}.hbm.xml";

	public static String getPropertyName(MethodDeclaration method)
	{
		if (method != null)
		{
			return JavaHelper.firstCharToLowerCase(method.getSimpleName()
					.substring(3));
		}
		else return "";
	}

	public static String getEntityClassName(InterfaceDeclaration decl)
	{
		String className = decl.getQualifiedName();
		if (isDefInterface(decl))
		{
			className = removeDefSuffix(className);
		}

		return className;
	}

	public static String getGeneratedShortClassName(InterfaceDeclaration decl)

	{
		return JavaHelper.getShortClassName(getGeneratedFullClassName(decl));
	}

	public static String getConstantName(MethodDeclaration decl)
	{
		String propertyName = getPropertyName(decl);
		if (propertyName != null)
		{
			return JavaHelper.getConstantNameFromPropertyName(propertyName);
		}
		return "";
	}

	public static String getGeneratedFullClassName(InterfaceDeclaration decl)

	{
		return getGeneratedClassName(decl);
	}

	public static String getSuperClassName(InterfaceDeclaration decl)
	{
		boolean hasSuperClass = decl.getAnnotation(Superclass.class) != null;

		if (!hasSuperClass)
		{
			Collection<InterfaceType> interfaces = decl.getSuperinterfaces();

			for (InterfaceType type : interfaces)
			{
				if (isDefInterface(type.getDeclaration()))
				{
					return removeDefSuffix(type.getDeclaration()
							.getQualifiedName());
				}
			}

			return VALUE_OBJECT_ABSTRACT_CLASS_NAME;
		}
		else
		{
			return decl.getAnnotation(Superclass.class).value().getName();
		}
	}

	public static String getSetMethodName(MethodDeclaration aDeclaration)
	{
		return JavaHelper.getSetterName(getPropertyName(aDeclaration));
	}

	public static boolean isAbstract(InterfaceDeclaration decl)
	{
		return decl.getAnnotation(GenerateAbstract.class) != null
				|| isBaseOnly(decl);
	}

	public static boolean isBaseOnly(InterfaceDeclaration decl)
	{
		return decl.getAnnotation(GenerateBaseOnly.class) != null;
	}

	public static boolean isDefInterface(InterfaceDeclaration decl)
	{
		return decl.getSimpleName().endsWith("Def");
	}

	private static String removeDefSuffix(String className)
	{
		return className.substring(0, className.length() - 3);
	}

	public static String getGeneratedClassName(InterfaceDeclaration decl)
	{
		String className = getEntityClassName(decl);

		if (isBaseOnly(decl))
		{
			className = className + "Base";
		}

		return className;
	}

	public static String getResource(InterfaceDeclaration decl)
	{
		String result = VoGenHelper.getGeneratedClassName(decl);
		result = result.replace('.', '/');
		result = MessageFormat.format(HIBERNATE_MAPPING_OUTPUT_FILE_NAME,
				new Object[] {result});

		return result;
	}

	public static boolean isObject(TypeDeclaration type)
	{
		// TODO Auto-generated method stub
		return !type.getClass().isPrimitive();
	}
}