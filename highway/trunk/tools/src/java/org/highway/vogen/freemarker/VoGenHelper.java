package org.highway.vogen.freemarker;

import java.util.Collection;

import org.highway.JavaHelper;
import org.highway.annotation.VoAbstract;
import org.highway.annotation.VoBaseOnly;
import org.highway.annotation.VoSerialVersionUID;
import org.highway.annotation.VoSuperClass;
import org.highway.vogen.VoGenConstantsHibernate2;
import org.highway.vogen.VoGenConstantsHibernate3;

import com.sun.mirror.declaration.InterfaceDeclaration;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.type.InterfaceType;

public class VoGenHelper implements VoGenConstantsHibernate3
{
//	private static final String HIBERNATE_TYPE_SUFFIX = "HibernateType";
//	private static final String ENUM_CLASS_NAME = "org.highway.vo.Enum";
//	private static final String DECIMAL_CLASS_NAME =
//		"org.highway.vo.Decimal";
//	private static final String DECIMAL_HIBERNATE_TYPE_CLASS_NAME =
//		"org.highway.database.hibernate.DecimalHibernateType";
	private InterfaceDeclaration declaration;
	
	public VoGenHelper(InterfaceDeclaration anInterfaceDeclaration) {
		declaration = anInterfaceDeclaration;
	}
	public boolean getHasSerialVersionUID(){
		return declaration.getAnnotation(VoSerialVersionUID.class)!=null;
	}
	public long getSerialVersionUID(){
		if (getHasSerialVersionUID())
			return declaration.getAnnotation(VoSerialVersionUID.class).value();
		return 1L;
	}
	public Collection getMethods(){
		return declaration.getMethods();
	}
	public String getPropertyName(MethodDeclaration method){
		if (method != null)
		{
			return JavaHelper.firstCharToLowerCase(method.getSimpleName().substring(3));
		}
		else
			return "";
	}
	public static String getEntityClassName(InterfaceDeclaration decl)
	{
		String className = decl.getQualifiedName();

		if (isDefInterface(className))
		{
			className = removeDefSuffix(className);
		}

		return className;
	}
	public String getGeneratedShortClassName()
		
	{
		return JavaHelper.getShortClassName(getGeneratedFullClassName());
	}
	public String constantName(String propertyName)
	{
		if (propertyName != null)
		{
			return JavaHelper.getConstantNameFromPropertyName(propertyName);
		}
		return "";
	}
	public String getPackageName(){
		return declaration.getPackage().getQualifiedName();
	}
	
	public String getGeneratedFullClassName()
		
	{
		return getGeneratedClassName( declaration);
	}

	public String entityShortClassName()
		
	{
		return JavaHelper.getShortClassName(entityFullClassName());
	}

	public String entityFullClassName()
		
	{
		return getEntityClassName(declaration);
	}

	public String getAbstract() 
	{
		return isAbstract(declaration) ? " abstract" : "";
	}

	public String getSuperClassName()
		
	{
		boolean hasSuperClass = declaration.getAnnotation(VoSuperClass.class)!=null;
		

		if (!hasSuperClass)
		{
			Collection<InterfaceType> interfaces =
				declaration.getSuperinterfaces();

//			for (Iterator iter = interfaces.iterator(); iter.hasNext();)
			for (InterfaceType type : interfaces) {
					String interfaceFullName =
					type.getDeclaration().getQualifiedName();

				if (isDefInterface(interfaceFullName))
				{
					return removeDefSuffix(interfaceFullName);
				}
			}

			return VoGenConstantsHibernate2.VALUE_OBJECT_ABSTRACT_CLASS_NAME;
		}
		else
		{
			return declaration.getAnnotation(VoSuperClass.class).value();
		}
	}

	public InterfaceDeclaration getDeclaration() {
		return declaration;
	}

	public void setDeclaration(InterfaceDeclaration declaration) {
		this.declaration = declaration;
	}
	public String setMethodName(String propertyName)
{
	return JavaHelper.getSetterName(propertyName);
}
	public static boolean isAbstract(InterfaceDeclaration decl)
	{
		return decl.getAnnotation(VoAbstract.class)!=null || isBaseOnly(decl);
	}

	public static boolean isBaseOnly(InterfaceDeclaration decl)
	{
		return decl.getAnnotation(VoBaseOnly.class)!=null;
	}

	public static boolean isDefInterface(InterfaceDeclaration decl)
	{
		return isDefInterface(decl.getSimpleName());
	}

	public static boolean isDefInterface(String className)
	{
		return className.endsWith("Def");
	}

	public static String removeDefSuffix(String className)
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
}