package org.highway.servicetest.application;

import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;

/**
 * @socle.service.interceptors
 * 		org.highway.debug.DebugInterceptor
 * 		org.highway.transaction.TransactionInterceptor
 */
public interface ApplicationService extends DynamicService, EjbService
{

}