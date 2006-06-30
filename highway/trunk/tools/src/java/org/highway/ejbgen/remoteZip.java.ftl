<@forAllTypes var="type" annotation="org.highway.service.ejb.GenerateEjb" annotationVar="generateEjb" includeInterfaces="true">
<#if generateEjb.useCompression>
<@javaSource name="${type.package.qualifiedName}.ejb.${type.generatedShortClassName}EjbRemote">

package ${type.package.qualifiedName}.ejb;

import javax.ejb.EJBObject;

public interface ${type.generatedShortClassName}EjbRemote extends EJBObject
{
<@forAllMethods var="method" indexVar="inc">
	public <#if method.returnType.isVoid()> void <#else> byte[] </#if> ${method.simpleName}${inc}( <#if method.parameter.empty=false>byte[] entry</#if>)
		throws ${method.exceptionsDeclarationWithRemoteException};
</@forAllMethods>
}
</@javaSource>
</#if>
</@forAllTypes>