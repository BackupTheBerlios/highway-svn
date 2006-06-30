/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.helper;

import org.highway.exception.TechnicalException;
import org.highway.exception.UserMessageException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 * 
 * @deprecated use org.highway.io.FileHelper
 */
public class FileHelper
{
	/**
	 * Method read
	 * @param filePath String
	 * @return byte[]
	 */
	public static byte[] read(String filePath)
	{
		try
		{
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fis.read(data);

			return data;
		}
		catch (FileNotFoundException e)
		{
			throw new TechnicalException("Read file error", e);
		}
		catch (IOException e)
		{
			throw new TechnicalException("Read file error", e);
		}
	}

	/**
	 * Method readAndZip
	 * @param filePath String
	 * @return byte[]
	 */
	public static byte[] readAndZip(String filePath)
	{
		try
		{
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			DeflaterOutputStream zipos = new DeflaterOutputStream(bos);
			StreamHelper.write(fis, zipos);
			fis.close();
			zipos.close();

			return bos.toByteArray();
		}
		catch (FileNotFoundException e)
		{
			throw new TechnicalException("Read file error", e);
		}
		catch (IOException e)
		{
			throw new TechnicalException("Read file error", e);
		}
	}

	/**
	 * Method save
	 * @param file File
	 * @param data byte[]
	 */
	public static void save(File file, byte[] data)
	{
		try
		{
			file.createNewFile();

			FileOutputStream fos = new FileOutputStream(file);
			fos.write(data);
			fos.flush();
			fos.close();
		}
		catch (IOException exception)
		{
			throw new UserMessageException(
				"Erreur lors de la sauvegarde du fichier " + file.getName());
		}
	}

	/**
	 * Method unzipAndSave
	 * @param file File
	 * @param data byte[]
	 */
	public static void unzipAndSave(File file, byte[] data)
	{
		try
		{
			file.createNewFile();

			FileOutputStream fos = new FileOutputStream(file);
			InflaterInputStream zipis =
				new InflaterInputStream(new ByteArrayInputStream(data));
			StreamHelper.write(zipis, fos);
			fos.flush();
			fos.close();
			zipis.close();
		}
		catch (IOException exception)
		{
			throw new UserMessageException(
				"Erreur lors de la sauvegarde du fichier " + file.getName());
		}
	}
}
