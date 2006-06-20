package org.highway.ejbgen;

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

public class EjbGenerator {
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
			Template tempBean = cfg.getTemplate("bean.java.ftl");
			Template tempHome = cfg.getTemplate("home.java.ftl");
			Template tempRemote= cfg.getTemplate("remote.java.ftl");
			Template tempBeanZip = cfg.getTemplate("beanZip.java.ftl");
			Template tempProxy = cfg.getTemplate("proxy.java.ftl");
			Template tempProxyZip = cfg.getTemplate("proxyZip.java.ftl");
			Template tempRemoteZip = cfg.getTemplate("remoteZip.java.ftl");
			
			
			/* Create a data model */
			Map<String, EjbGenHelper> rootBean = new HashMap<String, EjbGenHelper>();
			EjbGenHelper helper = new EjbGenHelper(declaration);
			rootBean.put("declaration", helper);
	        /* Merge data model with template */

	        Writer out = new OutputStreamWriter(System.out);
	        tempBean.process(rootBean, out);
	        tempBeanZip.process(rootBean, out);
	        tempHome.process(rootBean, out);
	        tempProxy.process(rootBean, out);
	        tempProxyZip.process(rootBean, out);
	        tempRemote.process(rootBean, out);
	        tempRemoteZip.process(rootBean, out);
	        
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
