package com.manpower.servicetest.application;

import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;

/**
 * @socle.service.interceptors
 * 		com.manpower.socle.debug.DebugInterceptor
 * 		com.manpower.socle.transaction.TransactionInterceptor
 */
public interface ApplicationService extends DynamicService, EjbService
{

}