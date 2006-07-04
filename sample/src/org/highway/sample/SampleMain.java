package org.highway.sample;

import org.highway.debug.DebugHome;
import org.highway.debug.Log4jDebugLog;
import org.highway.sample.access.facture.Facture;
import org.highway.sample.application.gererfacture.GererFacture;
import org.highway.sample.application.gererfacture.PayerFactureInfo;

public class SampleMain
{
	public static void main(String[] args) throws Exception
	{
		try
		{
			DebugHome.setDebugLog(new Log4jDebugLog());

			Facture facture = new Facture();

			DebugHome.debugValue("facture", facture);

			GererFacture gererFacture = (GererFacture) ApplicationLocator
					.getApplicationService(GererFacture.class);

			PayerFactureInfo info = gererFacture.payerFacture(facture);

			DebugHome.debugValue("info", info);
		}
		catch (Exception e)
		{
			DebugHome.error(e);
		}
	}
}
