package org.highway;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.FileSet;

public class VogenTask extends AptTask {

	public VogenTask() {
		super();
	}

	@Override
	public void execute() throws BuildException {
		// set factory
		setFactory("org.highway.vogen.HighwayProcessorFactory");
		// no compile
		setNocompile(true);
		// set input java files
		FileSet fileSet = new FileSet();
		fileSet.setDir(new File(getSourcepath().toString()));
		fileSet.setIncludes("**/*Def.java");
		addConfiguredSource(fileSet);
		super.execute();
	}
}
