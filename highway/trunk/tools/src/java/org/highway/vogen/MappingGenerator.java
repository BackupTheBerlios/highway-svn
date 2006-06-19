package org.highway.vogen;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;


import com.sun.mirror.declaration.InterfaceDeclaration;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class MappingGenerator {
	public void generate(InterfaceDeclaration declaration){
        try {
			/* Create and adjust the configuration */
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(
			        new File("./cfg/templates"));
			cfg.setObjectWrapper(new DefaultObjectWrapper());

			/* ------------------------------------------------------------------- */    
			/* You usually do these for many times in the application life-cycle:  */    
			        
			/* Get or create a template */
			Template tempHibernate = cfg.getTemplate("hibernate.xml.ftl");
		              
			/* Create a data model */
			Map<String,HibernateHelper> rootHibernate = new HashMap<String, HibernateHelper>();
			HibernateHelper helperHibernate = new HibernateHelper(declaration);
			rootHibernate.put("helper", helperHibernate);
	        /* Merge data model with template */
	        Writer outHbm = new OutputStreamWriter(System.out);
	        tempHibernate.process(rootHibernate, outHbm);
	        outHbm.flush();
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
