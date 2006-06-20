package ${declaration.packageName}.ejb;

import javax.ejb.EJBObject;

public interface ${declaration.generatedShortClassName}EjbRemote extends EJBObject
{
<#list declaration.methods as method>
	public <#if declaration.ifReturnsVoid(method)> void <#else> byte[] </#if> ${method.simpleName}${declaration.incrementIndex}( <#if declaration.ifHasParameters(method)>byte[] entry</#if>)
		${declaration.getExceptionsDeclaration(method, "java.rmi.RemoteException")};
</#list>
}