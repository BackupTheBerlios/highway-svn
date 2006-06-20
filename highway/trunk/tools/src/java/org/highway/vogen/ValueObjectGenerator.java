package org.highway.vogen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.highway.annotation.ValueObject;


import com.sun.mirror.declaration.InterfaceDeclaration;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ValueObjectGenerator {
	public void generate(InterfaceDeclaration declaration){
        try {
			/* Create and adjust the configuration */
			Configuration cfg = new Configuration();
//			cfg.setDirectoryForTemplateLoading(new File("org.highway.vogen"));
			cfg.setClassForTemplateLoading(getClass(), "");
			cfg.setObjectWrapper(new DefaultObjectWrapper());

			/* ------------------------------------------------------------------- */    
			/* You usually do these for many times in the application life-cycle:  */    
			        
			/* Get or create a template */
			Template tempVO = cfg.getTemplate("valueoject.java.ftl");
	      
			/* Create a data model */
			Map<String, VoGenHelper> rootVO = new HashMap<String, VoGenHelper>();
			VoGenHelper helperVO = new VoGenHelper(declaration);
			rootVO.put("declaration", helperVO);
	        /* Merge data model with template */
			
//			Writer out = new FileWriter("/bin/gensrc/"+declaration.getPackage().getQualifiedName().replace('.', '\\')+"\\"+declaration.getSimpleName()+".java"	);
			
	        Writer out = new OutputStreamWriter(System.out);
	        tempVO.process(rootVO, out);
	        out.flush();
	        
        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
