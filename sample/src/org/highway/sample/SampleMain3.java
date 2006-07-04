package org.highway.sample;

import java.util.HashMap;
import java.util.Map;

import org.highway.debug.DebugHome;
import org.highway.debug.Log4jDebugLog;
import org.highway.sample.application.gererfacture.PayerFactureInfo;
import org.highway.sample.domain.fournisseur.Fournisseur;
import org.highway.sample.domain.virement.Virement;

public class SampleMain3
{
	public static void main(String[] args) throws Exception
	{
		try
		{
			
			DebugHome.setDebugLog(new Log4jDebugLog());
			
			PayerFactureInfo info = new PayerFactureInfo();
			info.setVirement(new Virement());
			info.setFournisseur(new Fournisseur());
			
			Map map = new HashMap();
			map.put("info", info);
			map.put("virement1", info.getVirement());
			map.put("virement2", new Virement());
			
			DebugHome.getDebugLog().debugValue("map", map);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			DebugHome.getDebugLog().error(e);
		}
	}
}
