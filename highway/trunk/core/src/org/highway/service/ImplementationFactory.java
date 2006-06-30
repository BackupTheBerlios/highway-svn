/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.service;


/**
 * Factory of Service component implementations.
 * ImplementationFactory default implementation is StandardImplementationFactory.
 *
 * @since 1.2
 * 
 * @see org.highway.service.StandardImplementationFactory
 */
public interface ImplementationFactory
{
	/**
	 * Returns a new instance of the implementation class of the specified
	 * Service component.
	 *
	 * @param serviceClass the Service component interface
	 * @return a new implementation instance of the service component
	 * @throws IllegalArgumentException if serviceClass do not extend Service
	 */
	Service newImplementation(Class serviceClass);
}
