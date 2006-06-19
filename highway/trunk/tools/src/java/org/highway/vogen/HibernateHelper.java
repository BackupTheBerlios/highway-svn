package org.highway.vogen;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import org.highway.JavaHelper;
import org.highway.annotation.VoMapping;
import org.highway.annotation.VoMappingDiscriminator;
import org.highway.annotation.VoMappingDiscriminatorValue;
import org.highway.annotation.VoMappingGeneratorParam;
import org.highway.annotation.VoMappingId;
import org.highway.annotation.VoMappingProperty;
import org.highway.vogen.freemarker.VoGenHelper;

import com.sun.mirror.declaration.InterfaceDeclaration;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.type.TypeMirror;

public class HibernateHelper implements  VoGenConstantsHibernate3{
	private static final String HIBERNATE_TYPE_SUFFIX = "HibernateType";
	private static final String DECIMAL_HIBERNATE_TYPE_CLASS_NAME =
		"org.highway.database.hibernate.DecimalHibernateType";
	
	private InterfaceDeclaration declaration;
	
	public HibernateHelper(InterfaceDeclaration anInterfaceDeclaration) {
		declaration = anInterfaceDeclaration;
	}
	public String getHibernateMappingSystemId(){
		return HIBERNATE_CONFIGURATION_SYSTEM_ID;
	}
	public String getHibernateMappingPublicId(){
		return HIBERNATE_CONFIGURATION_PUBLIC_ID;
	}

	public String getEntityFullClassName()
	{
		return VoGenHelper.getEntityClassName(declaration);
	}
	public String getSuperClassName()
	{
		return VoGenHelper.getEntityClassName(declaration);
	}
	public String getMappingNomTable(){
		return declaration.getAnnotation(VoMapping.class).table();
	}
	public String getDiscriminatorValue(){
		return declaration.getAnnotation(VoMappingDiscriminatorValue.class).value();
	}
	public boolean getHasMapping(){
		return declaration.getAnnotation(VoMapping.class)!=null;
	}
	public boolean getHasPrimitiveId(){
		return countId()==1;
	}
	private int countId()
	{
		int count = 0;

		
		for (MethodDeclaration method : declaration.getMethods()) {
			if (method.getAnnotation(VoMappingId.class)!=null)
			{
				count++;
			}			
		}

		return count;
	}
	public String getPropertyName(MethodDeclaration method){
		if (method != null)
		{
			return JavaHelper.firstCharToLowerCase(method.getSimpleName().substring(3));
		}
		else
			return "";
	}
	public Collection<MethodDeclaration> getMethodsAnnotatedWithMappingId(){
		Collection<MethodDeclaration> methodsAnnotated = new ArrayList<MethodDeclaration>();

		for (MethodDeclaration method : declaration.getMethods()) {
			if (method.getAnnotation(VoMappingId.class)!=null)
				methodsAnnotated.add(method);
		}
		return methodsAnnotated;
	}
	public Collection<MethodDeclaration> getMethodsAnnotatedWithMappingProperty(){
		Collection<MethodDeclaration> methodsAnnotated = new ArrayList<MethodDeclaration>();

		for (MethodDeclaration method : declaration.getMethods()) {
			if (method.getAnnotation(VoMappingProperty.class)!=null)
				methodsAnnotated.add(method);
		}
		return methodsAnnotated;
	}
	public String getColumnName(MethodDeclaration aMethod){
		VoMappingId id = aMethod.getAnnotation(VoMappingId.class);
		VoMappingProperty prop = aMethod.getAnnotation(VoMappingProperty.class);
//		if (id!=null && prop!=null)
//			// TODO : gestion des exceptions pour freemarker
//			return "";

		if (id !=null && id.column() != null)
			return id.column();
		if (prop!=null)
			return prop.column();
		return "";
		
	}
	public String getPropertyHibernateType(MethodDeclaration aMethod)
{
//	if (aMethod == null)
//	{
//		throw new Exception("no current method set");
//	}
	
	// case where the hibernate type is explicitly specified
	String converter = aMethod.getAnnotation(VoMappingProperty.class).type();
	
	if (converter != null)
	{
		return converter;
	}
	TypeMirror propertyType = aMethod.getReturnType();

	// case where the property type extends Enum and is qualified 
	// the hibernate type is the property type + suffix
	
	if (propertyType.getClass().isEnum())
		return propertyType + HIBERNATE_TYPE_SUFFIX;

	// case where the property type extends Decimal
	if (propertyType.getClass().isAssignableFrom(org.highway.vo.Decimal.class))
		return DECIMAL_HIBERNATE_TYPE_CLASS_NAME;

	// case where the property type is not an enum:
	// the hibernate type is the property type
	return propertyType.getClass().getName();
	}
	
	public String getGeneratorClass(MethodDeclaration aMethod){
		VoMappingId id = aMethod.getAnnotation(VoMappingId.class);
		String generatorClass = id.generatorClass();
		return generatorClass;
	}

	public Collection getMethodsAnnotatedWithGeneratorParam(){
		Collection<MethodDeclaration> methodsAnnotated = new ArrayList<MethodDeclaration>();

		for (MethodDeclaration method : declaration.getMethods()) {
			if (method.getAnnotation(VoMappingGeneratorParam.class)!=null)
				methodsAnnotated.add(method);
		}
		return methodsAnnotated;
	}
	public String getGeneratorParamName(MethodDeclaration aMethod){
		return aMethod.getAnnotation(VoMappingGeneratorParam.class).name();
	}
	public String getGeneratorParamValue(MethodDeclaration aMethod){
		return aMethod.getAnnotation(VoMappingGeneratorParam.class).value();
	}
	public boolean getHasCompositeId(String template, Properties attributes) {
		return countId() > 1;
	}
	public boolean getHasDiscriminator(){
		return declaration.getAnnotation(VoMappingDiscriminator.class)!=null;
	}
	public boolean getHasDiscriminatorValue(){
		return declaration.getAnnotation(VoMappingDiscriminatorValue.class)!=null;
	}
	public String getDiscriminatorColumn(){
		return declaration.getAnnotation(VoMappingDiscriminator.class).column();
	}
	public String getDiscriminatorType(){
		return declaration.getAnnotation(VoMappingDiscriminator.class).type();
	}
	public String getDiscriminatorInsert(){
		if ( declaration.getAnnotation(VoMappingDiscriminator.class).insert() )
			return "true";
		return	"false";
	}
	public String getDiscriminatorForce(){
		if ( declaration.getAnnotation(VoMappingDiscriminator.class).force() )
			return "true";
		return	"false";
	}
	public String getKeyColumn()
	{
		MethodDeclaration method = getMethodsAnnotatedWithMappingId().iterator().next();

		String columnName = getColumnName(method);


		return columnName;
	}
	public String getInsertValue(MethodDeclaration aMethod){
		if (aMethod.getAnnotation(VoMappingProperty.class).insert())
			return "true";
		return	"false";
	}
	public String getUpdateValue(MethodDeclaration aMethod){
		if (aMethod.getAnnotation(VoMappingProperty.class).update())
			return "true";
		return	"false";
	}
	public boolean hasInsertValue(MethodDeclaration aMethod){
		return aMethod.getAnnotation(VoMappingProperty.class).insert();
	}
	public boolean hasUpdateValue(MethodDeclaration aMethod){
		return aMethod.getAnnotation(VoMappingProperty.class).update();

	}
	public boolean getIsSubClass(){
		//return declaration.getAnnotation(VoMapping.class) != null && declaration.getAnnotation(VoMappingDiscriminator.class) != null;
		return declaration.getAnnotation(VoMappingDiscriminatorValue.class)!=null;
	}
	public boolean getIsJoinedSubClass(){
		return declaration.getAnnotation(VoMapping.class) != null && declaration.getAnnotation(VoMappingDiscriminator.class) == null;
	}
	public boolean hasMappingTableValue(MethodDeclaration aMethod){
		return false;
	}
	public String getMappingTableValue(MethodDeclaration aMethod){
		return "";
	}
	public static String getResourceNameForHibernate(InterfaceDeclaration declaration)
	{
		String result = VoGenHelper.getEntityClassName(declaration);
		result = result.replace('.', '/');
		result =
			MessageFormat.format(
				HIBERNATE_MAPPING_OUTPUT_FILE_NAME, new Object[] { result });

		return result;
	}
}
