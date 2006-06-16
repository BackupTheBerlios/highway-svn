/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.metagen;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Location;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import xdoclet.DocletTask;
import java.io.File;

/**
 * @author attias
 */
public class MetaGenTask extends Task
{
	private File inputDir;
	private File outputDir;
	private DocletTask docletTask;

	public MetaGenTask()
	{
		docletTask = new DocletTask();
	}

	public void setSrcDir(File srcDir)
	{
	}

	public void setInputDir(File dir)
	{
		inputDir = dir;
	}

	public void setOutputDir(File dir)
	{
		outputDir = dir;
	}

	public void setLocation(Location location)
	{
		super.setLocation(location);
		docletTask.setLocation(location);
	}

	public void setOwningTarget(Target target)
	{
		super.setOwningTarget(target);
		docletTask.setOwningTarget(target);
	}

	public void setProject(Project project)
	{
		super.setProject(project);
		docletTask.setProject(project);
	}

	public void init() throws BuildException
	{
		docletTask.init();
	}

	public void execute() throws BuildException
	{
		if (inputDir == null)
		{
			throw new BuildException(
				"metagen attribute inputDir is mandatory", getLocation());
		}

		if (outputDir == null)
		{
			throw new BuildException(
				"metagen attribute outputDir is mandatory", getLocation());
		}

		// set input java files
		FileSet fileSet = new FileSet();
		fileSet.setDir(inputDir);
		fileSet.setIncludes("**/*.java");
		docletTask.addFileset(fileSet);

		// set doclet output dir
		docletTask.setDestDir(outputDir);

		// set doclet subtasks
		docletTask.addSubTask(new MetaGenSubTask());

		// execute doclet
		docletTask.execute();
	}
}
