/*
 * Created on 29 sept. 2004
 */
package org.highway.sample.access.virement;

import org.highway.sample.access.common.AccessService;

/**
 * @author attias
 * @socle.service.interceptors 
 */
public interface VirementAccess extends AccessService
{
	public void creer(Virement virement);
}