package org.highway.sample;

import org.highway.debug.DebugHome;
import org.highway.debug.Log4jDebugLog;
import org.highway.idgen.IdGenHome;
import org.highway.idgen.TimeBasedSimpleIdGenerator;
import org.highway.sample.domain.facture.Facture;

public class SampleMain4
{
	public static void main(String[] args) throws Exception
	{
		try
		{
			DebugHome.setDebugLog(new Log4jDebugLog());
			IdGenHome.setSimpleIdGenerator(new TimeBasedSimpleIdGenerator());
			
			Facture facture = new Facture();
			
			facture.setId(new Long(IdGenHome.getNextSimpleId()));

			DebugHome.getDebugLog().debugValue("facture", facture);
			
		}
		catch (Exception e)
		{
			DebugHome.getDebugLog().error(e);
		}
	}
}
