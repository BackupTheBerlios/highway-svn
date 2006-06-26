package org.highway.servicetest.application.ctg;

import org.highway.servicetest.ServiceTestServices;
import org.highway.servicetest.access.interimaire.InterimaireAccess1;

public class CtgServiceImpl implements CtgService
{
	public Output1 lectureInterimaire(Input1 input)
	{
		InterimaireAccess1 service = (InterimaireAccess1) ServiceTestServices.getAccessService(InterimaireAccess1.class);
		return service.lectureInterimaire(input);
	}
}
