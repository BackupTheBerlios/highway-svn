package org.highway.vogen;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.highway.AptTask;

public class VogenTask extends AptTask {

	public VogenTask() {
		super();
	}

	@Override
	public void execute() throws BuildException {
		// set factory
		setFactory("org.highway.vogen.HighwayProcessorFactory");
		// no compile
		setNocompile(false);
		// set input java files
		FileSet fileSet = new FileSet();
		fileSet.setDir(new File(getSourcepath().toString()));
		fileSet.setIncludes("**/*Def.java");
		addConfiguredSource(fileSet);
		this.setNowarn(true);
		super.execute();
	}
    /******************** -inputdir option **********************/
    public Path getInputDir() { return getSourcepath(); }
    public void setInputDir(Path inputDir) { setSourcepath(inputDir); }
    /******************** -outputdir option **********************/
    public File getOutputDir() { return getSourcedestdir(); }
    public void setOutputDir(File outputDir) { setSourcedestdir(outputDir); }
}
