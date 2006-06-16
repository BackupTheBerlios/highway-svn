/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.ejbgen;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Location;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import xdoclet.TemplateSubTask;
import xdoclet.modules.ejb.EjbDocletTask;
import xdoclet.modules.ejb.dd.EjbDotXmlSubTask;
import java.io.File;

/**
 * Specialization of the class processing the "xdoclet" ant task to
 * automatically load the tag "SocleMethod" and process the "socleTemplate"
 * subtask
 */
public class EjbGenTask extends Task implements EjbGenConstants
{
	private File javaInputDir;
	private File javaOutputDir;
	private File cfgOutputDir;
	private EjbDocletTask ejbDocletTask;

	public EjbGenTask()
	{
		ejbDocletTask = new EjbDocletTask();
	}

	public void setJavaInputDir(File dir)
	{
		javaInputDir = dir;
	}

	public void setJavaOutputDir(File dir)
	{
		javaOutputDir = dir;
	}

	public void setCfgOutputDir(File dir)
	{
		cfgOutputDir = dir;
	}

	public void setLocation(Location location)
	{
		super.setLocation(location);
		ejbDocletTask.setLocation(location);
	}

	public void setOwningTarget(Target target)
	{
		super.setOwningTarget(target);
		ejbDocletTask.setOwningTarget(target);
	}

	public void setProject(Project project)
	{
		super.setProject(project);
		ejbDocletTask.setProject(project);
	}

	public void init() throws BuildException
	{
		ejbDocletTask.init();
	}

	public void execute() throws BuildException
	{
		if (javaInputDir == null)
		{
			throw new BuildException(
				"ejbgen attribute javaInputDir is mandatory", getLocation());
		}

		if (javaOutputDir == null)
		{
			throw new BuildException(
				"ejbgen attribute javaOutputDir is mandatory", getLocation());
		}

		if (cfgOutputDir == null)
		{
			throw new BuildException(
				"ejbgen attribute ddOutputDir is mandatory", getLocation());
		}

		// set ejbdoclet java file set
		FileSet fileSet = new FileSet();
		fileSet.setDir(javaInputDir);
		fileSet.setIncludes("**/*.java");
		fileSet.setExcludes("**/*Impl.java");
		ejbDocletTask.addFileset(fileSet);

		// set ejbdoclet destination dir
		ejbDocletTask.setDestDir(javaOutputDir);

		// ejb home generation subtask
		generateEjb("GEN-HOME", HOME_TEMPLATE_FILE,HOME_FILE_NAME );

		generateEjb("GEN-HOME", ZIP_EJB_SERVICE_TAG, HOME_TEMPLATE_FILE,HOME_FILE_NAME );

		// ejb remote generation subtask
		generateEjb("GEN-REMOTE", REMOTE_TEMPLATE_FILE,REMOTE_FILE_NAME );
		// zip ejb remote generation subtask 
		generateEjb("GEN-REMOTE",ZIP_EJB_SERVICE_TAG, ZIP_REMOTE_TEMPLATE_FILE,REMOTE_FILE_NAME );

		// ejb bean generation subtask 
		generateEjb("GEN-BEAN", BEAN_TEMPLATE_FILE,BEAN_FILE_NAME );
		// zip ejb bean generation subtask 
		generateEjb("GEN-BEAN", ZIP_EJB_SERVICE_TAG, ZIP_BEAN_TEMPLATE_FILE, BEAN_FILE_NAME );

		// ejb proxy generation subtask
		generateEjb("GEN-PROXY", PROXY_TEMPLATE_FILE, PROXY_FILE_NAME );
		// zip ejb proxy generation subtask
		generateEjb("GEN-PROXY", ZIP_EJB_SERVICE_TAG,ZIP_PROXY_TEMPLATE_FILE, PROXY_FILE_NAME );

		// ejb descriptor generation subtask
		EjbDotXmlSubTask ddSubTask = new EjbDotXmlSubTask();
		ddSubTask.setSubTaskName("GEN-EJB-DEPLOYMENT-DESCRIPTOR");
		ddSubTask.setDestDir(cfgOutputDir);
		ddSubTask.setTemplateURL(getClass().getResource(EJB_DD_TEMPLATE_File));
		ejbDocletTask.addSubTask(ddSubTask);

		// websphere bnd descriptor generation subtask
		EjbDotXmlSubTask bndSubTask = new EjbDotXmlSubTask();
		bndSubTask.setSubTaskName("GEN-WEBSPHERE-BND-DESCRIPTOR");
		bndSubTask.setDestDir(cfgOutputDir);
		bndSubTask.setTemplateURL(
			getClass().getResource(WEBSPHERE_BND_TEMPLATE_FILE));
		bndSubTask.setDestinationFile(WEBSPHERE_BND_FILE_NAME);
		ejbDocletTask.addSubTask(bndSubTask);

		// websphere ext descriptor generation subtask
		EjbDotXmlSubTask extSubTask = new EjbDotXmlSubTask();
		extSubTask.setSubTaskName("GEN-WEBSPHERE-EXT-DESCRIPTOR");
		extSubTask.setDestDir(cfgOutputDir);
		extSubTask.setTemplateURL(
			getClass().getResource(WEBSPHERE_EXT_TEMPLATE_FILE));
		extSubTask.setDestinationFile(WEBSPHERE_EXT_FILE_NAME);
		ejbDocletTask.addSubTask(extSubTask);

		// execute ejbdoclet task and subtasks
		ejbDocletTask.execute();
	}

	/**
	 * Add a subTask to EjbDocletTask
	 * @param subTaskName: Name of the subTask
	 * @param havingClass: Name of class searched
	 * @param templateURL: URL of the template for generation
	 * @param destinationFile: Name of destination file
	 */
	private void generateEjb( String subTaskName, String havingClass,  String templateURL, String destinationFile){
		TemplateSubTask homeSubTask = new TemplateSubTask();
		homeSubTask.setSubTaskName(subTaskName);
		homeSubTask.setDestDir(javaOutputDir);
		homeSubTask.setOfType(EJB_SERVICE_INTERFACE);
		homeSubTask.setHavingClassTag(havingClass);
		homeSubTask.setTemplateURL(getClass().getResource(templateURL));
		homeSubTask.setDestinationFile(destinationFile);
		ejbDocletTask.addSubTask(homeSubTask);
		
	}
	
	/**
	 * Add a subTask to EjbDocletTask with the specified parameter, The type of class searched is EJB_SERVICE_INTERFACE
	 * @param subTaskName: Name of the subTask
	 * @param templateURL: URL of the template for the generation
	 * @param destinationFile: Name of the destination file
	 */
	private void generateEjb( String subTaskName, String templateURL, String destinationFile) {
		generateEjb(subTaskName,EJB_SERVICE_TAG, templateURL, destinationFile );
	}
}
