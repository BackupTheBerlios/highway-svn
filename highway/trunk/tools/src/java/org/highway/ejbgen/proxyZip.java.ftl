<@forAllTypes var="type" includeInterfaces="true">
<@javaSource name="${type.package.qualifiedName}.ejb.${type.generatedShortClassName}EjbProxy">
package ${type.package.qualifiedName}.ejb;

import org.highway.exception.TechnicalException;
import org.highway.service.ejb.ZipEjbProxyRequest;
import org.highway.service.ejb.ZipEjbProxy;
import org.highway.helper.ValueHelper;
import java.util.List;
import java.util.ArrayList;

public class  ${type.generatedShortClassName}EjbProxy extends ZipEjbProxy
	implements ${declaration.generatedClassName}
{
	protected Class getServiceClass()
	{
		return ${declaration.generatedClassName}.class;
	}

<@forAllMethods var="method" indexVar="inc">
	public ${method.returnType} ${method.simpleName}(${method.parametersDeclaration})
	  ${method.exceptionsDeclaration}
	{
		try
		{
			ZipEjbProxyRequest request = init("${method.simpleName}","${method.simpleName}${inc}");

<@forAllParameters var="parameter">
			request.addParameter("${parameter.simpleName}", ${parameter.type}.class, ValueHelper.wrap(${parameter.simpleName}));
</@forAllParameters>
<#if method.returnType.void>
			request.invoke();
<#elseif method.returnType.primitive && method.returnType.keyword="int">
			return ((Integer) request.invoke()).intValue();
<#elseif method.returnType.primitive &&  method.returnType.keyword="byte">
			return ((Byte) request.invoke()).byteValue();
<#elseif method.returnType.primitive &&  method.returnType.keyword="short">
			return ((Short) request.invoke()).shortValue();
<#elseif method.returnType.primitive &&  method.returnType.keyword="long">
			return ((Long) request.invoke()).longValue();
<#elseif method.returnType.primitive &&  method.returnType.keyword="boolean">
			return ((Boolean) request.invoke()).booleanValue();
<#elseif method.returnType.primitive &&  method.returnType.keyword="char">
			return ((Character) request.invoke()).charValue();
<#elseif method.returnType.primitive &&  method.returnType.keyword="double">
			return ((Double) request.invoke()).doubleValue();
<#elseif method.returnType.primitive &&  method.returnType.keyword="float">
			return ((Float) request.invoke()).floatValue();
<#elseif method.returnType.class>
			return (${method.returnType}) request.invoke();
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
</@forAllTypes>
