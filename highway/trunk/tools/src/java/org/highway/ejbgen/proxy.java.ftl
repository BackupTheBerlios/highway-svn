<@forAllTypes var="type"annotation="org.highway.service.ejb.GenerateEjb"  includeInterfaces="true">
<@javaSource name="${type.package.qualifiedName}.ejb.${type.generatedShortClassName}EjbProxy">
/*
 * File generated by ejbgen. Do not modify manually.
 */

package ${type.package.qualifiedName}.ejb;

import org.highway.exception.TechnicalException;
import org.highway.service.ejb.EjbRequest;
import org.highway.service.ejb.EjbProxy;
import org.highway.helper.ValueHelper;

/**
 * File generated by ejbgen. Do not modify manually.
 */
public class ${type.generatedShortClassName}EjbProxy extends EjbProxy
	implements ${type.generatedClassName}
{
	protected Class getServiceClass()
	{
		return ${type.generatedClassName}.class;
	}

<@forAllMethods var="method">
	public ${method.returnType} ${method.simpleName}(${method.parametersDeclaration})
		${method.exceptionsDeclaration}
	{
		try
		{
			EjbRequest request = createEjbRequest("${method.simpleName}");
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
<#else>
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
	}
</@forAllMethods>
}
</@javaSource>
</@forAllTypes> 