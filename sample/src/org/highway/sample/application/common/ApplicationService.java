/*
 * Created on 29 sept. 2004
 */
package org.highway.sample.application.common;

import org.highway.debug.DebugInterceptor;
import org.highway.service.ProfilingInterceptor;
import org.highway.service.ServiceInterceptors;
import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;
import org.highway.transaction.TransactionInterceptor;

@ServiceInterceptors({ProfilingInterceptor.class, DebugInterceptor.class,
		TransactionInterceptor.class})
public interface ApplicationService extends DynamicService, EjbService
{
}
