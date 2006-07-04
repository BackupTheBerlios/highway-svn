/*
 * Created on 29 sept. 2004
 */
package org.highway.sample.domain.virement;

import org.highway.sample.domain.common.AccessService;

/**
 * @author attias
 * @socle.service.interceptors 
 */
public interface VirementAccess extends AccessService
{
	public void creer(Virement virement);
}