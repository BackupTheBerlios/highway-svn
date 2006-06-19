package org.highway.vogen;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HibernateCfgGenerator {
	public void generate(List<String> resourcesName){
        try {
			/* Create and adjust the configuration */
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(
			        new File("./cfg/templates"));
			cfg.setObjectWrapper(new DefaultObjectWrapper());

			/* ------------------------------------------------------------------- */    
			/* You usually do these for many times in the application life-cycle:  */    
			        
			/* Get or create a template */
			Template tempHibernate = cfg.getTemplate("hibernate.cfg.xml.ftl");
		              
			/* Create a data model */
			Map<String,List> rootHibernate = new HashMap<String, List>();
			
			rootHibernate.put("resources", resourcesName);
	        /* Merge data model with template */
	        Writer outHbCfg = new OutputStreamWriter(System.out);
	        tempHibernate.process(rootHibernate, outHbCfg);
	        outHbCfg.flush();
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
