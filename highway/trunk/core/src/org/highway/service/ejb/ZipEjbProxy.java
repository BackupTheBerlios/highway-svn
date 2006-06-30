package org.highway.service.ejb;



/**
 * Abstract proxy class used for compressed EJB based service access.<br>
 * Developers should not use this class.
 *
 * 
 * 
 */
public abstract class ZipEjbProxy extends EjbProxy
{
	protected ZipEjbProxyRequest init(String methodName, String zipMethodName)
	{
		return new ZipEjbProxyRequest(getServiceClass(), this, getRemote(),
				methodName, getInterceptors(), zipMethodName);
	}
}
