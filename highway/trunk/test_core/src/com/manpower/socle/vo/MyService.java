package org.highway.vo;

import org.highway.service.ejb.EjbService;

/**
 * @socle.service.generate.ejbZip
 * @author David Attias
 * @since 1.5
 */
public interface MyService extends EjbService
{
	Object[] load(Object[] ids); 
}
