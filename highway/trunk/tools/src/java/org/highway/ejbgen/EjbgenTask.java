package org.highway.ejbgen;

import java.io.File;
import java.net.MalformedURLException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.highway.AptTask;

import com.sun.org.apache.xerces.internal.util.URI.MalformedURIException;

public class EjbgenTask extends AptTask {
	String outputCfgDir;
	
	public EjbgenTask() {
		super();
	}

	@Override
	public void execute() throws BuildException {
		// set factory
		setFactory("org.highway.ejbgen.HighwayProcessorFactory");
		// no compile
		setNocompile(true);
		// set input java files
		FileSet fileSet = new FileSet();
		fileSet.setDir(new File(getSourcepath().toString()));
		fileSet.setIncludes("**/*.java");
		addConfiguredSource(fileSet);
		this.setNowarn(true);
		Option option = this.createOption();
		option.setKey("OutputCfgDir");
		option.setValue(getOutputCfgDir());
			System.out.println("outputCfgDir = " + getOutputCfgDir());
	
		

		super.execute();
	}
    /******************** -inputdir option **********************/
    public Path getInputDir() { return getSourcepath(); }
    public void setInputDir(Path inputDir) { setSourcepath(inputDir); }
    /******************** -outputdir option **********************/
    public File getOutputDir() { return getSourcedestdir(); }
    public void setOutputDir(File outputDir) { setSourcedestdir(outputDir); }
    /******************** -outputCfgdir option **********************/
    public String getOutputCfgDir() { return outputCfgDir; }
    public void setOutputCfgDir(String outputCfgDir) { this.outputCfgDir = outputCfgDir; }
}
