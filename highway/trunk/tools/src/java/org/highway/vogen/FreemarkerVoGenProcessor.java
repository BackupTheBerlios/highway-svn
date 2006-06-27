package org.highway.vogen;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jelly.apt.Context;
import net.sf.jelly.apt.freemarker.APTJellyObjectWrapper;
import net.sf.jelly.apt.freemarker.FreemarkerModel;
import net.sf.jelly.apt.freemarker.FreemarkerProcessorFactory;
import net.sf.jelly.apt.freemarker.FreemarkerTransform;
import net.sf.jelly.apt.freemarker.FreemarkerVariable;
import net.sf.jelly.apt.freemarker.transforms.AnnotationValueTransform;
import net.sf.jelly.apt.freemarker.transforms.FileTransform;
import net.sf.jelly.apt.freemarker.transforms.ForAllConstructorsTransform;
import net.sf.jelly.apt.freemarker.transforms.ForAllFieldsTransform;
import net.sf.jelly.apt.freemarker.transforms.ForAllImportedTypesTransform;
import net.sf.jelly.apt.freemarker.transforms.ForAllMethodsTransform;
import net.sf.jelly.apt.freemarker.transforms.ForAllNestedTypesTransform;
import net.sf.jelly.apt.freemarker.transforms.ForAllPackagesTransform;
import net.sf.jelly.apt.freemarker.transforms.ForAllParametersTransform;
import net.sf.jelly.apt.freemarker.transforms.ForAllThrownTypesTransform;
import net.sf.jelly.apt.freemarker.transforms.ForAllTypesTransform;
import net.sf.jelly.apt.freemarker.transforms.IfHasAnnotationTransform;
import net.sf.jelly.apt.freemarker.transforms.IfHasDeclarationTransform;
import net.sf.jelly.apt.freemarker.transforms.JavaSourceTransform;

import com.sun.mirror.apt.AnnotationProcessor;

import freemarker.cache.URLTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerVoGenProcessor implements AnnotationProcessor {
	

	  public FreemarkerVoGenProcessor() {
	  }

	  public void process() {
	    Configuration configuration = getConfiguration();
	    
	    URL urlTemplateValueObject = this.getClass().getClassLoader().getResource("org/highway/vogen/valueoject.java.ftl");
	    URL urlTemplateHibernate = this.getClass().getClassLoader().getResource("org/highway/vogen/hibernate.xml.ftl");
	    URL urlTemplateHibernateCfg = this.getClass().getClassLoader().getResource("org/highway/vogen/hibernate.cfg.xml.ftl");
	    try {
	      Template templateVo = configuration.getTemplate(urlTemplateValueObject.toString());
	      Template templateHbm = configuration.getTemplate(urlTemplateHibernate.toString());
	      Template templateHbmCfg = configuration.getTemplate(urlTemplateHibernateCfg.toString());
	      templateHbm.process(getRootModel(), new OutputStreamWriter(System.out));
	      templateVo.process(getRootModel(), new OutputStreamWriter(System.out));
	      templateHbmCfg.process(getRootModel(), new OutputStreamWriter(System.out));
	    }
	    catch (IOException e) {
	      throw new RuntimeException(e);
	    }
	    catch (TemplateException e) {
	      throw new RuntimeException(e);
	    }
	  }

	  /**
	   * Get the object wrapper for the main model.
	   *
	   * @return the object wrapper for the main model.
	   */
	  protected APTJellyObjectWrapper getObjectWrapper() {
	    return new APTJellyObjectWrapper();
	  }

	  /**
	   * The root data model for the template.
	   *
	   * @return The root data model for the template.
	   */
	  protected FreemarkerModel getRootModel() {
	    HashMap<String, Map<String, Object>> sourceMap = new HashMap<String, Map<String, Object>>();

	    //set up the variables....
	    for (FreemarkerVariable var : getVariables()) {
	      String namespace = var.getNamespace();
	      if (!sourceMap.containsKey(namespace)) {
	        sourceMap.put(namespace, new HashMap<String, Object>());
	      }
	      sourceMap.get(namespace).put(var.getName(), var.getValue());
	    }

	    //set up the transforms....
	    for (FreemarkerTransform transform : getTransforms()) {
	      String namespace = transform.getTransformNamespace();
	      if (!sourceMap.containsKey(namespace)) {
	        sourceMap.put(namespace, new HashMap<String, Object>());
	      }
	      sourceMap.get(namespace).put(transform.getTransformName(), transform);
	    }

	    FreemarkerModel model = newRootModel();
	    FreemarkerModel.set(model);
	    model.setObjectWrapper(getObjectWrapper());
	    if (sourceMap.containsKey(null)) {
	      model.putAll(sourceMap.remove(null));
	    }
	    model.putAll(sourceMap);

	    return model;
	  }

	  /**
	   * Instantiate a new root model.
	   *
	   * @return The new root model.
	   */
	  protected FreemarkerModel newRootModel() {
	    return new FreemarkerModel();
	  }

	  /**
	   * Get the freemarker configuration.
	   *
	   * @return the freemarker configuration.
	   */
	  protected Configuration getConfiguration() {
	    Configuration configuration = new Configuration();
	    configuration.setTemplateLoader(getTemplateLoader());
	    configuration.setLocalizedLookup(false);
	    return configuration;
	  }

	  /**
	   * The collection of transforms to establish in the model before processing.
	   *
	   * @return The collection of transforms to establish in the model before processing.
	   */
	  protected Collection<FreemarkerTransform> getTransforms() {
	    String namespace = Context.getCurrentEnvironment().getOptions().get(FreemarkerProcessorFactory.FM_LIBRARY_NS_OPTION);
	    Collection<FreemarkerTransform> transforms = new ArrayList<FreemarkerTransform>();
	    transforms.add(new AnnotationValueTransform(namespace));
	    transforms.add(new FileTransform(namespace));
	    transforms.add(new ForAllConstructorsTransform(namespace));
	    transforms.add(new ForAllFieldsTransform(namespace));
	    transforms.add(new ForAllImportedTypesTransform(namespace));
	    transforms.add(new ForAllMethodsTransform(namespace));
	    transforms.add(new ForAllNestedTypesTransform(namespace));
	    transforms.add(new ForAllPackagesTransform(namespace));
	    transforms.add(new ForAllParametersTransform(namespace));
	    transforms.add(new ForAllThrownTypesTransform(namespace));
	    transforms.add(new ForAllTypesTransform(namespace));
	    transforms.add(new IfHasAnnotationTransform(namespace));
	    transforms.add(new IfHasDeclarationTransform(namespace));
	    transforms.add(new JavaSourceTransform(namespace));
	    return transforms;
	  }

	  /**
	   * The collection of variables to establish in the model before processing.
	   *
	   * @return The collection of variables to establish in the model before processing.
	   */
	  protected Collection<FreemarkerVariable> getVariables() {
	    Collection<FreemarkerVariable> variables = new ArrayList<FreemarkerVariable>();
	    Map<String, String> options = Context.getCurrentEnvironment().getOptions();
	    String namespace = options.get(FreemarkerProcessorFactory.FM_LIBRARY_NS_OPTION);
	    variables.add(new FreemarkerVariable(namespace, "aptOptions", options));
	    return variables;
	  }

	  /**
	   * Get the template loader for the freemarker configuration.
	   *
	   * @return the template loader for the freemarker configuration.
	   */
	  protected URLTemplateLoader getTemplateLoader() {
	    return new URLTemplateLoader() {
	      protected URL getURL(String name) {
	        try {
	          return new URL(name);
	        }
	        catch (MalformedURLException e) {
	          return null;
	        }
	      }
	    };
	  }


}
