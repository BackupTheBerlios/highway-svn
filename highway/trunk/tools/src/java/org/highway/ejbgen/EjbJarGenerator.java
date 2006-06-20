package org.highway.ejbgen;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.sun.mirror.declaration.InterfaceDeclaration;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class EjbJarGenerator {
	public void generate(Collection<InterfaceDeclaration> sessions){
        try {
			/* Create and adjust the configuration */
			Configuration cfg = new Configuration();
			cfg.setClassForTemplateLoading(getClass(), "");
			cfg.setObjectWrapper(new DefaultObjectWrapper());

			/* ------------------------------------------------------------------- */    
			/* You usually do these for many times in the application life-cycle:  */    
			/* Get or create a template */
			Template tempEjbJar = cfg.getTemplate("ejb-jar.java.ftl");
			
			/* Create a data model */
			Map<String, Collection> rootBean = new HashMap<String, Collection>();

			rootBean.put("declaration", sessions);
	        /* Merge data model with template */

	        Writer out = new OutputStreamWriter(System.out);
	        tempEjbJar.process(rootBean, out);	        
        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
