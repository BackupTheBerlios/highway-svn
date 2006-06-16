/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.vogen;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Location;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import xdoclet.DocletTask;
import xdoclet.TemplateSubTask;
import java.io.File;

/**
 * @author GRIZARD
 */
public class VoGenTask extends Task
{
	private File inputDir;
	private File outputDir;
	private String hibernateCfgFile;
	private DocletTask docletTask;

	public VoGenTask()
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

	public void setHibernateCfgFile(String file)
	{
		hibernateCfgFile = file;
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
				"vogen attribute inputDir is mandatory", getLocation());
		}

		if (outputDir == null)
		{
			throw new BuildException(
				"vogen attribute outputDir is mandatory", getLocation());
		}

		if (hibernateCfgFile == null)
		{
			hibernateCfgFile = "hibernate.cfg.xml";
		}

		// set input java files
		FileSet fileSet = new FileSet();
		fileSet.setDir(inputDir);
		fileSet.setIncludes("**/*Def.java");
		docletTask.addFileset(fileSet);

		// set doclet output dir
		docletTask.setDestDir(outputDir);

		// value object generation subtask
		docletTask.addSubTask(new VoGenSubTaskValueObject());

		// Hibernate mapping generation subtask
		docletTask.addSubTask(new VoGenSubTaskHibernate());

		// Hibernate configuration generation subtask
		TemplateSubTask hibernateSubTask = new VoGenSubTaskHibernateCfg();
		hibernateSubTask.setDestinationFile(hibernateCfgFile);
		docletTask.addSubTask(hibernateSubTask);

		// execute doclet
		docletTask.execute();
	}
}
