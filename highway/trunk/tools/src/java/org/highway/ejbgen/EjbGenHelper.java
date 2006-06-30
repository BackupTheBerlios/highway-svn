package org.highway.ejbgen;

import com.sun.mirror.declaration.InterfaceDeclaration;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.ParameterDeclaration;
import com.sun.mirror.type.ReferenceType;

public class EjbGenHelper implements EjbGenConstants
{
//	private InterfaceDeclaration declaration;
//	private int index= 0;
	
//	public EjbGenHelper(InterfaceDeclaration anInterfaceDeclaration) {
//		declaration = anInterfaceDeclaration;
//	}
//	public Collection getMethods(){
//		return declaration.getMethods();
//	}
//	public String getGeneratedFullClassName()
//	
//	{
//		return getGeneratedClassName( declaration);
//	}
//	public String getGeneratedShortClassName()
//		
//	{
//		return JavaHelper.getShortClassName(getGeneratedFullClassName());
//	}

	public static String getParametersDeclaration(MethodDeclaration method){
		if (method != null)
		{
			StringBuilder builder = new StringBuilder("");
			for (ParameterDeclaration parameter : method.getParameters()) {
				if (builder.length()>0)
					builder.append(", ");
				builder.append(parameter.getType().toString() + " " + parameter.getSimpleName());
			}
			return builder.toString();
		}
		else
			return "";
	}
//	public Collection getParameters(MethodDeclaration method){
//		if (method != null)
//		{
//			return method.getParameters();
//		}
//		else
//			return Collections.EMPTY_LIST;
//	}
	public static String getExceptionsDeclaration(MethodDeclaration method){
		if (method != null)
		{
			StringBuilder builder = new StringBuilder();
			if (!method.getThrownTypes().isEmpty())
				builder.append("throws ");
			for (ReferenceType exception : method.getThrownTypes()) {
				if (builder.length()>7)
					builder.append(", ");
				builder.append(exception.toString());
			}
			return builder.toString();
		}
		else
			return "";
	}
	public static String getExceptionsDeclaration(MethodDeclaration method, String append){
		String decl = getExceptionsDeclaration(method);
		if (decl!=null && decl.length()>0)
			return decl+", "+append;
		return "throws " + append;
	}
//	public boolean ifReturnsVoid(MethodDeclaration method){
//		if (method != null)
//		{
//			return method.getReturnType().getClass().isAssignableFrom(Void.class);
//		}
//		else
//			return false;
//	}
//	public boolean ifReturnsObject(MethodDeclaration method){
//		if (method != null)
//		{
//			return method.getReturnType().getClass().isPrimitive();
//		}
//		else
//			return false;
//	}
//	public boolean ifReturns(MethodDeclaration method, String returnsType){
//		if (method != null && returnsType!=null)
//		{
//			if (returnsType.equals("int"))
//				return method.getReturnType().getClass().isAssignableFrom(Integer.class);
//			if (returnsType.equals("byte"))
//				return method.getReturnType().getClass().isAssignableFrom(Byte.class);
//			if (returnsType.equals("short"))
//				return method.getReturnType().getClass().isAssignableFrom(Short.class);
//			if (returnsType.equals("long"))
//				return method.getReturnType().getClass().isAssignableFrom(Long.class);
//			if (returnsType.equals("boolean"))
//				return method.getReturnType().getClass().isAssignableFrom(Boolean.class);
//			if (returnsType.equals("char"))
//				return method.getReturnType().getClass().isAssignableFrom(Character.class);
//			if (returnsType.equals("double"))
//				return method.getReturnType().getClass().isAssignableFrom(Double.class);
//			if (returnsType.equals("float"))
//				return method.getReturnType().getClass().isAssignableFrom(Float.class);			
//			return false;
//		}
//		return false;
//	}
//	public boolean ifOfTypePrimitive(ParameterDeclaration parameter){
//		if (parameter != null)
//		{
//			return parameter.getType().getClass().isPrimitive();
//		}
//		return false;
//	}
//	public boolean ifOfType(ParameterDeclaration parameter, String returnsType){
//		if (parameter != null && returnsType!=null)
//		{
//			if (returnsType.equals("int"))
//				return parameter.getType().getClass().isAssignableFrom(Integer.class);
//			if (returnsType.equals("byte"))
//				return parameter.getType().getClass().isAssignableFrom(Byte.class);
//			if (returnsType.equals("short"))
//				return parameter.getType().getClass().isAssignableFrom(Short.class);
//			if (returnsType.equals("long"))
//				return parameter.getType().getClass().isAssignableFrom(Long.class);
//			if (returnsType.equals("boolean"))
//				return parameter.getType().getClass().isAssignableFrom(Boolean.class);
//			if (returnsType.equals("char"))
//				return parameter.getType().getClass().isAssignableFrom(Character.class);
//			if (returnsType.equals("double"))
//				return parameter.getType().getClass().isAssignableFrom(Double.class);
//			if (returnsType.equals("float"))
//				return parameter.getType().getClass().isAssignableFrom(Float.class);			
//			return false;
//		}
//		return false;
//	}
//	public Collection getExceptions(MethodDeclaration method){
//		return method.getThrownTypes();
//	}
//	public String getIncrementIndex () {
//		index++;
//		return Integer.toString(index) ;
//	}	
//	public boolean ifHasParameters(MethodDeclaration method){
//		if (method != null)
//		{
//			return !method.getParameters().isEmpty();
//		}
//		return false;
//	}	

//	public static String getGeneratedClassName(InterfaceDeclaration decl)
//	{
//		String className = getEntityClassName(decl);
//
//		if (isBaseOnly(decl))
//		{
//			className = className + "Base";
//		}
//
//		return className;
//	}
//	public static String getEntityClassName(InterfaceDeclaration decl)
//	{
//		String className = decl.getQualifiedName();
//
//		if (isDefInterface(decl))
//		{
//			className = removeDefSuffix(className);
//		}
//
//		return className;
//	}
//	public static boolean isBaseOnly(InterfaceDeclaration decl)
//	{
//		return decl.getAnnotation(GenerateBaseOnly.class)!=null;
//	}
//	public static boolean isDefInterface(InterfaceDeclaration decl)
//	{
//		return decl.getSimpleName().endsWith("Def");
//	}
//
//
//	private static String removeDefSuffix(String className)
//	{
//		return className.substring(0, className.length() - 3);
//	}
}