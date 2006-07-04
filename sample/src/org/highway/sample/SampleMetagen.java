package org.highway.sample;

import org.highway.debug.DebugHome;
import org.highway.debug.Log4jDebugLog;
import org.highway.sample.application.gererfacture.GererFacture;


public class SampleMetagen
{
	public static void main(String[] args) throws Exception
	{
		try
		{
			DebugHome.setDebugLog(new Log4jDebugLog());
			
			JavadocHome.setJavadocCache(new MetagenJavadocCache());
			
			String tagValue = JavadocHome.getClassTag(GererFacture.class,
				"socle.service.interceptors");

			System.out.println(tagValue);
		}
		catch (Exception e)
		{
			DebugHome.getDebugLog().error(e);
		}
	}
}
