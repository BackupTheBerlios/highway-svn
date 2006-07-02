package org.highway.bean;

import org.highway.service.ejb.EjbService;
import org.highway.service.ejb.GenerateEjb;

@GenerateEjb
public interface MyService extends EjbService
{
	Object[] load(Object[] ids); 
}
