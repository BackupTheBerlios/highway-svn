/*
 * Created on 29 sept. 2004
 */
package org.highway.sample.access.common;

import org.highway.debug.DebugInterceptor;
import org.highway.service.ProfilingInterceptor;
import org.highway.service.ServiceInterceptors;
import org.highway.service.dynamic.DynamicService;

@ServiceInterceptors({ProfilingInterceptor.class, DebugInterceptor.class})
public interface AccessService extends DynamicService
{
}
