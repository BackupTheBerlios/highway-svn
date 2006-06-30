/*
 * Copyright (c) 2005. All rights reserved.
 */

/*
 * Created on 29 sept. 2004
 */
package org.highway.service;


/**
 * Defines a service request interceptor.<br>
 * An interceptor is used to intercept calls to services.<br>
 * An interceptor can do some processing before and after the service
 * is really processed.<br>
 * Interceptors should contain mostly technical code used for
 * all the services or large group of them.<br>
 * The interceptors used by a service are defined in the
 * javadoc tags of the component service interface. <br>
 * Interceptors must be stateless since a single instance is used for any
 * intercepted services.
 *
 * 
 */
public interface ServiceInterceptor
{
	/**
	 * Called by the socle when a request is sent to a service
	 * this interceptor intercepts.<br>
	 * This method must contain the interception before and after logic.<br>
	 * To continue the request process, this method must call <code>invoke</code>
	 * on the specified request.<br>
	 * It will call the next interceptor or the real implementation if all
	 * the interceptors have been already called.<br>
	 * <br>
	 * Example :<br>
	 * <pre>
	 *  public Object invoke(ServiceRequest request) throws Throwable
	 *  {
	 *      // before interception logic
	 *      ...
	 *
	 *      // continue request processing
	 *      Object result = request.invoke();
	 *
	 *      // after interceptor logic
	 *      ...
	 *
	 *      // end interception
	 *      return result;
	 *  }
	 * </pre>
	 *
	 * @param request contains all the information about the intercepted service
	 * @return the value returned by the service
	 * @throws Throwable the throwable thrown by the service
	 * or a TechnicalException if an error has occured in the interception process
	 */
	public Object invoke(ServiceRequest request) throws Throwable;
}
