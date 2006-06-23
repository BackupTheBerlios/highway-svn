package org.highway.service;

import org.highway.annotation.GenerateEjb;
import org.highway.service.dynamic.DynamicService;
import org.highway.service.ejb.EjbService;

@GenerateEjb
public interface TestService  extends Service, DynamicService, EjbService {
	public void rechercherTest(String aString, String anotherString) throws TestException;
	public int isConnectedTest(Object anObject, int anInt);
	public String getValeurDeQuelqueChose();
}
