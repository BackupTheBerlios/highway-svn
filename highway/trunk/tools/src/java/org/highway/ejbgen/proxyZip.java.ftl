package ${declaration.packageName}.ejb;

import org.highway.exception.TechnicalException;
import org.highway.service.ejb.ZipEjbProxyRequest;
import org.highway.service.ejb.ZipEjbProxy;
import org.highway.helper.ValueHelper;
import java.util.List;
import java.util.ArrayList;

public class  ${declaration.generatedShortClassName}EjbProxy extends ZipEjbProxy
	implements ${declaration.generatedFullClassName}
{
	protected Class getServiceClass()
	{
		return ${declaration.generatedFullClassName}.class;
	}

<#list declaration.methods as method>
	public ${method.returnType} ${method.simpleName}(${declaration.getParametersDeclaration(method)})
		${declaration.getExceptionsDeclaration(method)}
	{
		try
		{
			ZipEjbProxyRequest request = init("${method.simpleName}","${method.simpleName}${declaration.incrementIndex}");

<#list declaration.parameters as parameter>
			request.addParameter("${parameter.simpleName}", ${parameter.type}.class, ValueHelper.wrap(${parameter.simpleName}));
</#list>
<#if declaration.ifReturnsVoid(method)>
			request.invoke();
<#elseif declaration.ifReturnsObject(method)>
			return (<XDtMethod:methodType/>) request.invoke();
<#elseif declaration.ifReturns(method, "int")>
			return ((Integer) request.invoke()).intValue();
<#elseif declaration.ifReturns(method, "byte")>
			return ((Byte) request.invoke()).byteValue();
<#elseif declaration.ifReturns(method, "short")>
			return ((Short) request.invoke()).shortValue();
<#elseif declaration.ifReturns(method, "long")>
			return ((Long) request.invoke()).longValue();
<#elseif declaration.ifReturns(method, "boolean")>
			return ((Boolean) request.invoke()).booleanValue();
<#elseif declaration.ifReturns(method, "char")>
			return ((Character) request.invoke()).charValue();
<#elseif declaration.ifReturns(method, "double")>
			return ((Double) request.invoke()).doubleValue();
<#elseif declaration.ifReturns(method, "float")>
			return ((Float) request.invoke()).floatValue();
</#if>
		}
<#list declaration.exceptions as exception>
		catch (${exception.toString()} e)
		{
			throw e;
		}
</#list>
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
</#list>
}
