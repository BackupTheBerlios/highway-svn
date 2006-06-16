package org.highway.checkstyle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class CheckstyleStatsTask extends Task
{

	private final static String FILE_XML_ELEMENT = "file";

	private final static String ERROR_XML_ELEMENT = "error";

	private final static String NAME_XML_ATTRIBUTE = "name";

	private final static String ERRORS_XML_ATTRIBUTE = "errors";

	private final static String WARNINGS_XML_ATTRIBUTE = "warnings";

	private final static String STATISTICS_XML_ATTRIBUTE = "statistics";

	private final static String PACKAGE_XML_ATTRIBUTE = "package";

	private final static String FILESIZE_XML_ATTRIBUTE = "size";

	private final static String BOGOQUALITY_XML_ATTRIBUTE = "bogoquality";

	private final static String BOGORATIO_XML_ATTRIBUTE = "bogoratio";

	private final static String SEVERITY_XML_ATTRIBUTE = "severity";

	private final static String CHECKNAME_XML_ATTRIBUTE = "source";

	private final static String MESSAGE_XML_ATTRIBUTE = "message";

	private final static String SEVERITY_ERROR = "error";

	private final static String SEVERITY_WARNING = "warning";

	private final static String SEVERITY_INFO = "info";

	private final static String DEFAULT_SUFFIX = "_stats";

	// checkstyle output file to calculate stats for
	private File reportFile;

	// if checkstyle output is file.ext, then create file${suffix}.ext
	private String suffixToAdd;

	// key=package name, value=IncidentReport
	private HashMap incidentCount;

	public CheckstyleStatsTask()
	{
		super();
		this.incidentCount = new HashMap();
		this.suffixToAdd = DEFAULT_SUFFIX;
	}

	public void execute() throws BuildException
	{
		this.log("Starting post-processing on "
			+ this.reportFile.getAbsolutePath(), Project.MSG_INFO);

		// open the report file
		if (!this.reportFile.exists())
		{
			this.log("The report file does not exist: "
				+ this.reportFile.getAbsolutePath(), Project.MSG_ERR);
		}
		else if (!this.reportFile.canRead())
		{
			this.log("The report file is not readable: "
				+ this.reportFile.getAbsolutePath(), Project.MSG_ERR);
		}
		else if (!this.reportFile.canWrite())
		{
			this.log("The report file is not writable: "
				+ this.reportFile.getAbsolutePath(), Project.MSG_ERR);
		}

		try
		{
			// create the DOM representation
			SAXReader reader = new SAXReader();
			Document document = reader.read(this.reportFile);
			Element root = document.getRootElement();

			Iterator fileIterator = root.elementIterator(FILE_XML_ELEMENT);
			while (fileIterator.hasNext())
			{
				this.processFileElement((Element) fileIterator.next());
			}

			// process the model, write the stats back into it
			Element statsElement = root.addElement(STATISTICS_XML_ATTRIBUTE);

			Iterator packageIterator = this.incidentCount.keySet().iterator();
			while (packageIterator.hasNext())
			{
				String packageName = (String) packageIterator.next();
				IncidentReport incidentReport = (IncidentReport) this.incidentCount
					.get(packageName);

				Element packageElement = statsElement
					.addElement(PACKAGE_XML_ATTRIBUTE);
				packageElement.addAttribute(NAME_XML_ATTRIBUTE, packageName);
				packageElement.addAttribute(ERRORS_XML_ATTRIBUTE, ""
					+ incidentReport.getErrors());
				packageElement.addAttribute(WARNINGS_XML_ATTRIBUTE, ""
					+ incidentReport.getWarnings());

				// add this information to the file Element
				packageElement.addAttribute(FILESIZE_XML_ATTRIBUTE, ""
					+ incidentReport.getTotalBytes());

				// calculate a bogoquality
				double bogoquality = this.calcBogoQuality(incidentReport);
				packageElement.addAttribute(BOGOQUALITY_XML_ATTRIBUTE, ""
					+ bogoquality);

				// calculate a bogoratio
				double bogoratio = this.calcBogoRatio(incidentReport);
				packageElement.addAttribute(BOGORATIO_XML_ATTRIBUTE, ""
					+ bogoratio);
			}

			// statistics for the entire project
			double projectBogoratio = this.calcProjectBogoRatio();
			double projectBogoquality = this.calcProjectBogoQuality();

			// write the project's stats on the root element
			statsElement.addAttribute(BOGORATIO_XML_ATTRIBUTE, ""
				+ projectBogoratio);
			statsElement.addAttribute(BOGOQUALITY_XML_ATTRIBUTE, ""
				+ projectBogoquality);

			// write the file with the updated Document
			FileOutputStream out = new FileOutputStream(this
				.modifyFilename(reportFile.getAbsolutePath()));
			// OutputFormat format = OutputFormat.createPrettyPrint();
			// XMLWriter writer = new XMLWriter(out, format);
			XMLWriter writer = new XMLWriter(out);
			writer.write(document);
			writer.close();
		}
		catch (DocumentException de)
		{
			de.printStackTrace();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

	private void processFileElement(Element fileElement)
	{
		// determine the file's package
		String filePath = fileElement.attribute(NAME_XML_ATTRIBUTE).getValue();
		String packageName = this.getPackageName(filePath);

		// if the incident report doesn't already contain this package, create
		// an entry
		if (!this.incidentCount.containsKey(packageName))
		{
			this.incidentCount.put(packageName, new IncidentReport());
		}

		// iterate through all error children
		Iterator errorIterator = fileElement.elementIterator(ERROR_XML_ELEMENT);
		while (errorIterator.hasNext())
		{
			Element errorElement = (Element) errorIterator.next();
			this.processErrorElement(errorElement, packageName);
		}

		// add the current filesize to the cumulated amount of bytes in the
		// package
		long fileSize = this.calcFilesize(filePath);
		((IncidentReport) this.incidentCount.get(packageName))
			.addBytes(fileSize);
	}

	private long calcFilesize(String filePath)
	{
		File file = new File(filePath);
		return file.length();
	}

	private void processErrorElement(Element errorElement, String packageName)
	{
		String severity = errorElement.attribute(SEVERITY_XML_ATTRIBUTE)
			.getValue();
		if (severity.equals(SEVERITY_ERROR))
		{
			((IncidentReport) this.incidentCount.get(packageName))
				.incrementErrors();
		}
		else if (severity.equals(SEVERITY_WARNING))
		{
			((IncidentReport) this.incidentCount.get(packageName))
				.incrementWarnings();
		}
		else if (severity.equals(SEVERITY_INFO))
		{
			String checkName = errorElement
				.attributeValue(CHECKNAME_XML_ATTRIBUTE);

			if (checkName.equals(JavadocToCodeRatioCheck.class.getName()))
			{
				// tokenize the message attribute value: it is supposed to be
				// $nbCommentingLines/$nbTotalLines
				String stats = errorElement
					.attributeValue(MESSAGE_XML_ATTRIBUTE);
				int separatorIndex = stats
					.indexOf(JavadocToCodeRatioCheck.STATS_DELIMITER);
				long commentingLines = Long.parseLong(stats.substring(0,
					separatorIndex));
				long totalLines = Long.parseLong(stats.substring(
					separatorIndex + 1, stats.length()));

				((IncidentReport) this.incidentCount.get(packageName))
					.addCommentingLines(commentingLines);
				((IncidentReport) this.incidentCount.get(packageName))
					.addTotalLines(totalLines);
			}
			else
			{
				this.log("unrecognized INFO message: "
					+ errorElement.attributeValue(MESSAGE_XML_ATTRIBUTE),
					Project.MSG_WARN);
			}
		}
		else
		{
			this.log("unrecognized severity: " + severity, Project.MSG_WARN);
		}
	}

	private double calcBogoQuality(IncidentReport incidentReport)
	{
		// this is the dumb^Wscientific part
		double SCALE = 1000;
		double result = ((double) (incidentReport.getErrors() * 4 + incidentReport
			.getWarnings()))
			/ ((double) incidentReport.getTotalBytes()) * SCALE;
		BigDecimal decimal = (new BigDecimal(result)).setScale(1,
			BigDecimal.ROUND_HALF_UP);
		return decimal.doubleValue();
	}

	private double calcBogoRatio(IncidentReport incidentReport)
	{
		// this is the dumb^Wscientific part
		double SCALE = 10;
		double result = ((double) incidentReport.getCommentingLines())
			/ ((double) incidentReport.getTotalLines()) * SCALE;
		BigDecimal decimal = (new BigDecimal(result)).setScale(1,
			BigDecimal.ROUND_HALF_UP);
		return decimal.doubleValue();
	}

	private double calcProjectBogoQuality()
	{
		double SCALE = 1000;
		double result = 0;
		double incidents = 0;
		double bytes = 0;

		Iterator iterator = this.incidentCount.values().iterator();
		while (iterator.hasNext())
		{
			IncidentReport report = (IncidentReport) iterator.next();
			incidents += report.getErrors() * 4 + report.getWarnings();
			bytes += report.getTotalBytes();
		}

		result = incidents / bytes * SCALE;
		BigDecimal decimal = (new BigDecimal(result)).setScale(1,
			BigDecimal.ROUND_HALF_UP);
		return decimal.doubleValue();
	}

	private double calcProjectBogoRatio()
	{
		double SCALE = 10;
		double result = 0;
		double comments = 0;
		double lines = 0;

		Iterator iterator = this.incidentCount.values().iterator();
		while (iterator.hasNext())
		{
			IncidentReport report = (IncidentReport) iterator.next();
			comments += report.getCommentingLines();
			lines += report.getTotalLines();
		}

		result = comments / lines * SCALE;
		BigDecimal decimal = (new BigDecimal(result)).setScale(1,
			BigDecimal.ROUND_HALF_UP);
		return decimal.doubleValue();
	}

	private String getPackageName(String classfilePath)
	{
		// the "chainsaw" algorithm =)
		// some strings are hardcoded, as this is a throw-away-if-better implem
		StringBuffer result = new StringBuffer(classfilePath);

		// first cut all tokens till *src*$PLATFORM_DELIMITER
		result.delete(0, result.indexOf("src"));
		result.delete(0, result.indexOf(File.separator) + 1);

		// kill the last token, delimiter set to $PLATFORM_DELIMITER
		result.delete(result.lastIndexOf(File.separator), result.length());

		// replace all $PLATFORM_DELIMITER by the std dotted Java package
		// notation
		char DOT = '.';
		for (int i = 0; i < result.length(); i++)
		{
			if (result.charAt(i) == File.separatorChar)
			{
				result.setCharAt(i, DOT);
			}
		}

		return result.toString();
	}

	public void setFile(String reportFile)
	{
		this.reportFile = new File(reportFile);
	}

	public void setSuffix(String suffixToAdd)
	{
		this.suffixToAdd = suffixToAdd;
	}

	private String modifyFilename(String reportFile)
	{
		StringBuffer result = new StringBuffer();
		result.append(reportFile.substring(0, reportFile.lastIndexOf('.')));
		result.append(this.suffixToAdd);
		result.append(reportFile.substring(reportFile.lastIndexOf('.')));

		return result.toString();
	}

	private class IncidentReport
	{
		private int errors;

		private int warnings;

		private long commentingLines;

		private long totalLines;

		private long totalBytes;

		public IncidentReport()
		{
			this.errors = 0;
			this.warnings = 0;
			this.totalBytes = 0L;
			this.totalLines = 0L;
			this.commentingLines = 0L;
		}

		public int getErrors()
		{
			return errors;
		}

		public int getWarnings()
		{
			return warnings;
		}

		public void incrementErrors()
		{
			this.errors++;
		}

		public void incrementWarnings()
		{
			this.warnings++;
		}

		public void addBytes(long fileSize)
		{
			this.totalBytes += fileSize;
		}

		public long getTotalBytes()
		{
			return totalBytes;
		}

		protected long getCommentingLines()
		{
			return commentingLines;
		}

		protected void addCommentingLines(long commentingLines)
		{
			this.commentingLines += commentingLines;
		}

		protected long getTotalLines()
		{
			return totalLines;
		}

		protected void addTotalLines(long totalLines)
		{
			this.totalLines += totalLines;
		}

		public String toString()
		{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("[IncidentReport: errors=");
			stringBuffer.append(this.errors);
			stringBuffer.append(" - warnings=");
			stringBuffer.append(this.warnings);
			stringBuffer.append(" - commentingLines=");
			stringBuffer.append(" - totalLines=");
			stringBuffer.append(this.totalLines);
			stringBuffer.append(" - totalBytes=");
			stringBuffer.append(this.totalBytes);
			stringBuffer.append("]");

			return stringBuffer.toString();
		}
	}
}
