package org.highway.vogen;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import net.sf.jelly.apt.ProcessorFactory;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;

public class HighwayProcessorFactory extends ProcessorFactory {

	  public static final String FM_LIBRARY_NS_OPTION = "-AAPTJellyFreemarkerLibraryNS";

	  public HighwayProcessorFactory() {
	  }

	  public HighwayProcessorFactory(URL script) {
	    super(script);
	  }

	  @Override
	  public Collection<String> supportedOptions() {
	    ArrayList<String> options = new ArrayList<String>(super.supportedOptions());
	    options.add(FM_LIBRARY_NS_OPTION);
	    return Collections.unmodifiableCollection(options);
	  }

	  
	  
	  @Override
	  protected AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> annotations) {
		URL url = null;
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
	    return new FreemarkerVoGenProcessor();
	  }
}
