/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.helper;

import org.highway.debug.DebugHome;
import org.highway.exception.TechnicalException;
import sun.net.ftp.FtpClient;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Dans le cas où cette implémentation n'est pas suffisante,
 * revoir la liste des librairies client ftp :
 * http://www.javaworld.com/javaworld/jw-04-2003/jw-0404-ftp.html
 *
 * 
 * @deprecated use org.highway.io.FtpWriter
 */
public class FtpWriter extends Writer
{
	/**
	 * Field m_ftpClient
	 */
	private FtpClient m_ftpClient;
	private Writer m_out;

	/**
	 * @todo (dattias, 6 févr. 2004): nous utilisons actuellement le jdk 1.3 sur
	 * serveur Weblogic, ce qui nous empèche d'utiliser url.openConnection() car
	 * le handler du protocol FTP n'est pas connu. En jdk 1.4, ca marche. Donc
	 * pour l'instant nous utilisons la classe sun.net.ftp.FtpClient. Dès que possible
	 * repasser en implem url.openConnection().
	 * @param ftpServer
	 * @param login
	 * @param password
	 * @param filePathName
	 *
	 */
	public FtpWriter(
		String ftpServer, String login, String password, String filePathName)
	{
		//		StringBuffer buffer = new StringBuffer(100);
		//		buffer.append("ftp://").append(login);
		//		buffer.append(':').append(password);
		//		buffer.append('@').append(ftpServer);
		//		buffer.append('/').append(filePathName);
		//		buffer.append(";type=I");
		//		
		//		try
		//		{
		//			Debugger.debug(this, "Trying to connect to FTP server, URL = ", buffer);
		//			URL url = new URL(buffer.toString());
		//			m_out = new BufferedWriter(new OutputStreamWriter(url.openConnection().getOutputStream()));
		//			Debugger.debug(this, "Connected successfully to FTP server, URL = ", buffer);
		//		}
		//		catch (MalformedURLException e)
		//		{
		//			throw new TechnicalException("FTP error", e);
		//		}
		//		catch (IOException e)
		//		{
		//			throw new TechnicalException("FTP error", e);
		//		}
		try
		{
			DebugHome.getDebugLog().debug(
				"Trying to connect to FTP server ", ftpServer);
			m_ftpClient = new FtpClient();
			m_ftpClient.openServer(ftpServer);
			m_ftpClient.login(login, password);
			m_ftpClient.binary();
			m_out =
				new BufferedWriter(
					new OutputStreamWriter(m_ftpClient.put(filePathName)));
			DebugHome.getDebugLog().debug(
				"Connected successfully to FTP server ", ftpServer);
		}
		catch (IOException e)
		{
			throw new TechnicalException("FTP error", e);
		}
	}

	/**
	 * Method write
	 * @param cbuf char[]
	 * @param off int
	 * @param len int
	 * @throws IOException
	 */
	public void write(char[] cbuf, int off, int len) throws IOException
	{
		m_out.write(cbuf, off, len);
	}

	/**
	 * Method flush
	 * @throws IOException
	 */
	public void flush() throws IOException
	{
		m_out.flush();
	}

	/**
	 * Method close
	 * @throws IOException
	 */
	public void close() throws IOException
	{
		m_out.close();
		m_ftpClient.closeServer();
		DebugHome.getDebugLog().debug(
			this, "FTP connection closed successfully");
	}

	/**
	 * Method main
	 * @param args String[]
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		FtpWriter ftpWriter =
			new FtpWriter("host", "login", "password", "toto.txt");
		ftpWriter.write("toto\n");
		ftpWriter.write("toto\n");
		ftpWriter.write("toto\n");
		ftpWriter.write("toto\n");
		ftpWriter.close();
	}
}
