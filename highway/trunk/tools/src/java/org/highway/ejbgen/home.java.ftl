<@forAllTypes var="type" annotation="org.highway.service.ejb.GenerateEjb" includeInterfaces="true">
<@javaSource name="${type.package.qualifiedName}.ejb.${type.generatedShortClassName}EjbHome">
/*
 * File generated by ejbgen. Do not modify manually.
 */
 
package ${type.package.qualifiedName}.ejb;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * File generated by ejbgen. Do not modify manually.
 */
public interface ${type.generatedShortClassName}EjbHome extends EJBHome
{
	public ${type.generatedShortClassName}EjbRemote create() throws CreateException, RemoteException;
}
</@javaSource>
</@forAllTypes> 