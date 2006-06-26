package org.highway.servicetest.presentation.ctg;

import java.util.Calendar;

import junit.framework.TestCase;

import org.highway.debug.DebugHome;
import org.highway.servicetest.access.interimaire.Input1;
import org.highway.servicetest.access.interimaire.Output1;
import org.highway.servicetest.application.ctg.CtgService;
import org.highway.servicetest.presentation.PresentationServices;

public class UltrascoreTest extends TestCase
{
	public void test()
	{
		Input1 input = new Input1();
		input.setVersion(209);
		input.setModule("INGINT");
		input.setCodeFonction("G0");
		input.setParamLength(10);
		input.setParam(1510);
		
		CtgService service = (CtgService) PresentationServices.getApplicationService(CtgService.class);
		Output1 output = service.lectureInterimaire(input);

		DebugHome.getDebugLog().debugValue("Output bean", output);
		
		// assertEquals("I000200813", output.getHeader());
		assertEquals("O", output.getCOD_DEPL_ETR());
		assertEquals("N", output.getCOD_GXP());
		assertEquals("N", output.getCOD_N_SS_INT());
		assertEquals("202", output.getCOD_NAL_INT());
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(output.getDTS_CRE_PRENR());
		assertEquals(calendar.get(Calendar.YEAR), 2004);
		assertEquals(calendar.get(Calendar.MONTH), Calendar.DECEMBER);
		assertEquals(calendar.get(Calendar.DAY_OF_MONTH), 14);
	}
}
