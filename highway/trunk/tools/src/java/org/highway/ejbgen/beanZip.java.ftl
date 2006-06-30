<@forAllTypes var="type" annotation="org.highway.service.ejb.GenerateEjb" annotationVar="generateEjb" includeInterfaces="true">
<#if generateEjb.useCompression>
<@javaSource name="${type.package.qualifiedName}.ejb.${type.generatedShortClassName}EjbBean">
/*
 * File generated by ejbgen. Do not modify manually.
 */
 
package ${type.package.qualifiedName}.ejb;

import org.highway.exception.TechnicalException;
import org.highway.service.ejb.EjbRequest;
import org.highway.service.ejb.ZipEjbBean;
import org.highway.helper.ValueHelper;
import org.highway.io.Deserializer;
import org.highway.io.Serializer;
import java.util.List;
import java.util.ArrayList;

public class ${type.generatedShortClassName}EjbBean extends ZipEjbBean
{
	protected Class getServiceClass()
	{
		return ${type.generatedClassName}.class;
	}

<@forAllMethods var="method" indexVar="inc">
	public <#if method.returnType.isVoid()> void <#else> byte[] </#if> ${method.simpleName}${inc}( <#if method.parameter.empty=false>byte[] entry</#if>)
		${method.exceptionsDeclaration}
	{
		try
		{
			EjbRequest request = init("${method.simpleName}");
			// decompression et deserialisation
<#if method.parameter.empty=false>
			Deserializer deserializer =  getDeserializer(entry);
</#if>
			
<@forAllParameters var="parameter">
<#if parameter.isPrimitive()>
<#if parameter.keyword="int">
			${parameter.type} ${parameter.simpleName} = deserializer.readInt();
<#elseif parameter.keyword="byte">
			${parameter.type} ${parameter.simpleName} = deserializer.readByte();
<#elseif parameter.keyword="short">
			${parameter.type} ${parameter.simpleName} = deserializer.readShort();
<#elseif parameter.keyword="long">
			${parameter.type} ${parameter.simpleName} = deserializer.readLong();
<#elseif parameter.keyword="boolean">
			${parameter.type} ${parameter.simpleName} = deserializer.readBoolean();
<#elseif parameter.keyword="char">
			${parameter.type} ${parameter.simpleName} = deserializer.readChar();
<#elseif parameter.keyword="double">
			${parameter.type} ${parameter.simpleName} = deserializer.readDouble();
<#elseif parameter.keyword="float">
			${parameter.type} ${parameter.simpleName} = deserializer.readFloat();
</#if>
<#else>
			${parameter.type} ${parameter.simpleName} = (${parameter.type})  deserializer.readObject();
</#if>
			request.addParameter("${parameter.simpleName}", ${parameter.type}.class, ValueHelper.wrap(${parameter.simpleName}));
</@forAllParameters>


			Object o1 =request.invoke();

<#if method.returnType.isVoid()=false>
			// compression serialisation
			Serializer serializer = getSerializer();
			serializer.write(o1);
			byte[] response =  serializer.toByteArray();;
			return response;
</#if>


		}
<@forAllThrownTypes var="exception">
		catch (${exception} e)
		{
			throw e;
		}
</@forAllThrownTypes>
		catch (TechnicalException e)
		{
			throw e;
		}
		catch (Error e)
		{
			throw e;
		}
		catch (Throwable e)
		{
			throw new TechnicalException("Service ${method.simpleName} unexpectedly failed", e);
		}
		finally
		{
			reset();
		}
	}
</@forAllMethods>
}
</@javaSource>
</#if>
</@forAllTypes> 