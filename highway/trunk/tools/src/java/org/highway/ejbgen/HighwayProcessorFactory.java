package org.highway.ejbgen;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import net.sf.jelly.apt.Context;
import net.sf.jelly.apt.ProcessorFactory;
import net.sf.jelly.apt.decorations.DeclarationDecorator;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;

public class HighwayProcessorFactory extends ProcessorFactory {

	  public static final String FM_LIBRARY_NS_OPTION = "-AAPTJellyFreemarkerLibraryNS";

	  /**
	   * Option for specifying the outputCfgDir.
	   */
	  public static final String OUTPUT_CFG_DIR = "-AOutputCfgDir";

	  public HighwayProcessorFactory() {
	  }

	  public HighwayProcessorFactory(URL script) {
	    super(script);
	  }

	  @Override
	  public Collection<String> supportedOptions() {
	    ArrayList<String> options = new ArrayList<String>(super.supportedOptions());
	    options.add(FM_LIBRARY_NS_OPTION);
	    options.add(TEMPLATE_FILE_OPTION);
	    options.add(TEMPLATE_URL_OPTION);
	    options.add(DECLARATION_DECORATOR_OPTION);
	    options.add(TYPE_DECORATOR_OPTION);
	    options.add(OUTPUT_CFG_DIR);
	    return Collections.unmodifiableCollection(options);
	  }

	  
 	  @Override
	  protected AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> annotations) {
		URL url = null;
	    System.setProperty(DeclarationDecorator.class.getName(), "org.highway.HighwayDeclarationDecorator");
	    return newProcessor(url);
	  }

	/**
	   * Instantiate a new freemarker processor.
	   *
	   * @param url The URL to the template.
	   * @return The processor.
	   */
	  @Override
	  protected AnnotationProcessor newProcessor(URL url) {
	    AnnotationProcessorEnvironment env = Context.getCurrentEnvironment();
	    Map<String, String> options = env.getOptions();
	    String outputCfgDirvalue = options.get(OUTPUT_CFG_DIR);
	    return new FreemarkerEjbGenProcessor(outputCfgDirvalue);
	  }
}
