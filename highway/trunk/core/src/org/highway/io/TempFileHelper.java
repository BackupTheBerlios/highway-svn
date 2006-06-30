/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.io;

import org.highway.debug.DebugHome;
import org.highway.exception.TechnicalException;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

/**
 * ...
 *
 * 
 * 
 */
public abstract class TempFileHelper
{
	/**
	 * Field s_tempDirectory
	 */
	private static File s_tempDirectory;

	/**
	 * Field s_expirationTime
	 */
	private static int s_expirationTime = 7; // default = 7 days

	/**
	 * Field FILE_NAME_SUFFIX<br>
	 * (value is "".tmp"")
	 */
	private static final String FILE_NAME_SUFFIX = ".tmp";

	/**
	 * Field GIVEN_NAME_SUFFIX<br>
	 * (value is "".mpw."")
	 */
	private static final String GIVEN_NAME_SUFFIX = ".mpw.";

	/**
	 * Field DEFAULT_GIVEN_NAME<br>
	 * (value is ""temp"")
	 */
	private static final String DEFAULT_GIVEN_NAME = "temp";

	/**
	 * File filter that checks temp files.
	 */
	private static final FileFilter s_mpwTempFileFilter =
		new FileFilter()
		{
			public boolean accept(File file)
			{
				return TempFileHelper.isMpwTempFile(file);
			}
		};

	/**
	 * Method createTempFile
	 * @return File
	 */
	public static File createTempFile()
	{
		return createTempFile(DEFAULT_GIVEN_NAME);
	}

	/**
	 * Method createTempFile
	 * @param givenName String
	 * @return File
	 */
	public static File createTempFile(String givenName)
	{
		if ((givenName == null) || (givenName.length() == 0))
		{
			throw new IllegalArgumentException("Given name null or empty");
		}

		try
		{
			init();
			deleteExpiredTempFiles();

			return File.createTempFile(
				givenName + GIVEN_NAME_SUFFIX, FILE_NAME_SUFFIX, s_tempDirectory);
		}
		catch (IOException e)
		{
			throw new TechnicalException("Create temp file error", e);
		}
	}

	/**
	 * Method listTempFiles
	 * @return File[]
	 */
	public static File[] listTempFiles()
	{
		init();
		deleteExpiredTempFiles();

		return s_tempDirectory.listFiles(s_mpwTempFileFilter);
	}

	/**
	 * Method getTempFileGivenName
	 * @param file File
	 * @return String
	 */
	public static String getTempFileGivenName(File file)
	{
		String fileName = file.getName();
		int index = fileName.indexOf(GIVEN_NAME_SUFFIX);

		if (index < 0)
		{
			return fileName;
		}
		else
		{
			return fileName.substring(0, index);
		}
	}

	/**
	 * Method init
	 * @param directoryPath String
	 * @param expirationTime String
	 */
	public static void init(String directoryPath, String expirationTime)
	{
		if (directoryPath != null)
		{
			File file = new File(directoryPath);

			if (file.isDirectory())
			{
				s_tempDirectory = file;
			}
		}
		else
		{
			try
			{
				File file = File.createTempFile("mpw.test.", ".tmp");
				s_tempDirectory = file.getParentFile();
			}
			catch (IOException e)
			{
				throw new TechnicalException("Create temp file error", e);
			}
		}

		if (expirationTime != null)
		{
			s_expirationTime = Integer.parseInt(expirationTime);
		}
	}

	/**
	 * Method getExpirationDate
	 * @param creationDate long
	 * @return long
	 */
	public static long getExpirationDate(long creationDate)
	{
		return creationDate + (s_expirationTime * 1000 * 60 * 60 * 24);
	}

	/**
	 * Method deleteExpiredTempFiles
	 */
	private static void deleteExpiredTempFiles()
	{
		long expiredDate =
			System.currentTimeMillis()
			- (s_expirationTime * 1000 * 60 * 60 * 24);
		File[] files = s_tempDirectory.listFiles(s_mpwTempFileFilter);

		for (int i = 0; i < files.length; i++)
		{
			if (files[i].lastModified() < expiredDate)
			{
				DebugHome.getDebugLog().debugValue(
					"Automatic delete of temp file ", files[i].getAbsolutePath());

				if (! files[i].delete())
				{
					throw new TechnicalException(
						"Delete of temp file error for unknown reason");
				}
			}
		}
	}

	/**
	 * Method init
	 */
	private static void init()
	{
		if (s_tempDirectory == null)
		{
			init(null, null);
		}
	}

	/**
	 * Method isMpwTempFile
	 * @param file File
	 * @return boolean
	 */
	private static boolean isMpwTempFile(File file)
	{
		return file.getName().indexOf(GIVEN_NAME_SUFFIX) >= 0;
	}
}
