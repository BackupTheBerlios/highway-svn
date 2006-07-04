package org.highway.sample;

import org.highway.debug.DebugHome;
import org.highway.debug.Log4jDebugLog;
import org.highway.sample.application.gererfacture.GererFacture;
import org.highway.sample.application.gererfacture.PayerFactureInfo;
import org.highway.sample.domain.facture.Facture;


public class SampleMain1
{
	public static void main(String[] args) throws Exception
	{
		try
		{
			DebugHome.setDebugLog(new Log4jDebugLog());
			DebugHome.getDebugLog().debugEnter();
			
			Facture facture = new Facture();
			DebugHome.getDebugLog().debugValue("facture", facture);

			GererFacture gererFacture = (GererFacture)
				SampleServices.getApplicationService(GererFacture.class);
				
			PayerFactureInfo info = gererFacture.payerFacture(facture);
			
			DebugHome.getDebugLog().debugExit(info);
		}
		catch (Exception e)
		{
			DebugHome.getDebugLog().error(e);
		}
	}
}
