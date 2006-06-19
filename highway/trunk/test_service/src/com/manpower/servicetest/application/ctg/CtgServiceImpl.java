package com.manpower.servicetest.application.ctg;

import com.manpower.servicetest.ServiceTestServices;
import com.manpower.servicetest.access.interimaire.InterimaireAccess1;

public class CtgServiceImpl implements CtgService
{
	public Output1 lectureInterimaire(Input1 input)
	{
		InterimaireAccess1 service = (InterimaireAccess1) ServiceTestServices.getAccessService(InterimaireAccess1.class);
		return service.lectureInterimaire(input);
	}
}
