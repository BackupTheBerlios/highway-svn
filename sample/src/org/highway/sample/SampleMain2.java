package org.highway.sample;

import org.highway.debug.DebugHome;
import org.highway.debug.Log4jDebugLog;
import org.highway.sample.application.gererfacture.GererFacture;
import org.highway.sample.application.gererfacture.PayerFactureInfo;
import org.highway.sample.domain.facture.Facture;
import org.highway.service.ServiceLocator;
import org.highway.service.ejb.EjbLocator;


public class SampleMain2
{
	public static void main(String[] args) throws Exception
	{
		try
		{
			DebugHome.setDebugLog(new Log4jDebugLog());			
			Facture facture = new Facture();

			ServiceLocator locator = new EjbLocator();
			
			GererFacture gererFacture =
				(GererFacture) locator.getService(GererFacture.class);
				
			PayerFactureInfo info = gererFacture.payerFacture(facture);
			
			DebugHome.getDebugLog().debugValue("info", info);
			
		}
		catch (Exception e)
		{
			DebugHome.getDebugLog().error(e);
		}
	}
}
