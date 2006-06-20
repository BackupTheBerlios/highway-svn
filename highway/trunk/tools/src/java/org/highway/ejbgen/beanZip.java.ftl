package ${declaration.packageName}.ejb;

import org.highway.exception.TechnicalException;
import org.highway.service.ejb.EjbRequest;
import org.highway.service.ejb.ZipEjbBean;
import org.highway.helper.ValueHelper;
import org.highway.io.Deserializer;
import org.highway.io.Serializer;
import java.util.List;
import java.util.ArrayList;

public class ${declaration.generatedShortClassName}>EjbBean extends ZipEjbBean
{
	protected Class getServiceClass()
	{
		return ${declaration.generatedFullClassName}.class;
	}

<#list declaration.methods as method>
	public <#if declaration.ifReturnsVoid(method)> void <#else> byte[] </#if> ${method.simpleName}${declaration.incrementIndex}( <#if declaration.ifHasParameters(method)>byte[] entry</#if>)
		${declaration.getExceptionsDeclaration(method)}
	{
		try
		{
			EjbRequest request = init("${method.simpleName}");
			// decompression et deserialisation
<#if declaration.ifHasParameters(method)>
			Deserializer deserializer =  getDeserializer(entry);
</#if>
			
<#list declaration.parameters as parameter>
<#if declaration.isOfTypePrimitive(parameter)>
<#if declaration.ifOfType(parameter, "int")>
			${parameter.type} ${parameter.simpleName} = deserializer.readInt();
<#elseif declaration.ifOfType(parameter, "byte")>
			${parameter.type} ${parameter.simpleName} = deserializer.readByte();
<#elseif declaration.ifOfType(parameter, "short")>
			${parameter.type} ${parameter.simpleName} = deserializer.readShort();
<#elseif declaration.ifOfType(parameter, "long")>
			${parameter.type} ${parameter.simpleName} = deserializer.readLong();
<#elseif declaration.ifOfType(parameter, "boolean")>
			${parameter.type} ${parameter.simpleName} = deserializer.readBoolean();
<#elseif declaration.ifOfType(parameter, "char")>
			${parameter.type} ${parameter.simpleName} = deserializer.readChar();
<#elseif declaration.ifOfType(parameter, "double")>
			${parameter.type} ${parameter.simpleName} = deserializer.readDouble();
<#elseif declaration.ifOfType(parameter, "float")>
			${parameter.type} ${parameter.simpleName} = deserializer.readFloat();
</#if>
<#else>
			${parameter.type} ${parameter.simpleName} = (${parameter.type})  deserializer.readObject();
</#if>
			request.addParameter("${parameter.simpleName}", ${parameter.type}.class, ValueHelper.wrap(${parameter.simpleName}));
</#list>


			Object o1 =request.invoke();

<#if declaration.ifReturnsVoid(method)=false>
			// compression serialisation
			Serializer serializer = getSerializer();
			serializer.write(o1);
			byte[] response =  serializer.toByteArray();;
			return response;
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
