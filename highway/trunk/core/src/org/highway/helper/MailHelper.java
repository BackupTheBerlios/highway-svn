/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.helper;

import org.highway.debug.DebugHome;
import org.highway.exception.TechnicalException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;

/**
 * ...
 *
 * 
 */
public class MailHelper
{
	/**
	 * Field s_session
	 */
	private static Session s_session;

	/**
	 * Field MAIL_HOST<br>
	 * (value is ""mail.host"")
	 */
	private static final String MAIL_HOST = "mail.host";

	/**
	 * Field MAIL_FROM<br>
	 * (value is ""mail.from"")
	 */
	private static final String MAIL_FROM = "mail.from";

	/**
	 * Field MAIL_USER<br>
	 * (value is ""mail.user"")
	 */
	private static final String MAIL_USER = "mail.user";

	/**
	 * Field MAIL_TRANSPORT_PROTOCOL<br>
	 * (value is ""mail.transport.protocol"")
	 */
	private static final String MAIL_TRANSPORT_PROTOCOL =
		"mail.transport.protocol";

	/**
	 * Field MAIL_DEBUG<br>
	 * (value is ""mail.debug"")
	 */
	private static final String MAIL_DEBUG = "mail.debug";

	static
	{
		initMailSession();
	}

	/**
	 * Initialize the mail session parameters.<br>
	 * For now, this method will not work in a no server mode.
	 */
	private static void initMailSession()
	{
		// Le nom de la session mail se configure dans le config.xml ou dans la console Weblogic
		final String mailSessionName = "ValtechMailSession";

		InitialContext ic = null;
		Properties p = new Properties();

		//		if (!CommonProperties.isNoServerMode())
		//		{
		//			try
		//			{
		//				p.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.T3InitialContextFactory");
		//				String clusterUrl = CommonProperties.getClusterUrl();
		//				p.put(Context.PROVIDER_URL, clusterUrl);
		//					
		//				ic = new InitialContext(p);
		//			}
		//			catch(NamingException exc)
		//			{
		//				Debugger.error(MailHelper.class, "Impossible d'intialiser le contexte initial pour la session mail " + mailSessionName + " dans Weblogic", exc);
		//			}
		//			try
		//			{
		//				s_session = (Session) ic.lookup(mailSessionName);
		//				Debugger.debug(MailHelper.class, "Session de mail Weblogic " + mailSessionName + " initialisee");
		//			}
		//			catch(NamingException exc)
		//			{
		//				Debugger.error(MailHelper.class, "Impossible de trouver la session mail " + mailSessionName + " dans Weblogic", exc);
		//			}	
		//		}
		//		else
		//		{
		p.put("mail.from", "toto@valtech.fr");
		p.put("mail.host", "10.100.1.1");
		s_session = Session.getInstance(p);
		DebugHome.getDebugLog().debug(
			"Session de mail intialisée avec les valeurs par défaut");

		//		}
	}

	/**
	 * Send an email.
	 *
	 * @param recipient email address (for example: gbush@usa.gov)
	 * @param subject email subject
	 * @param text email main text
	 */
	public static void sendMail(String recipient, String subject, String text)
	{
		String[] recipients = new String[1];
		recipients[0] = recipient;
		sendMail(recipients, subject, text);
	}

	/**
	 * Send an email.
	 *
	 * @param recipients email addresses (for example: gbush@usa.gov)
	 * @param subject email subject
	 * @param text email main text
	 */
	public static void sendMail(
		String[] recipients, String subject, String text)
	{
		try
		{
			if (s_session == null)
			{
				initMailSession();

				if (s_session == null)
				{
					throw new TechnicalException("Envoi impossible d'un email");
				}
			}

			MimeMessage msg = new MimeMessage(s_session);

			for (int i = 0; i < recipients.length; i++)
			{
				msg.setRecipient(
					Message.RecipientType.TO, new InternetAddress(
						recipients[i]));
			}

			msg.setSubject(subject);
			msg.setText(text);

			Transport.send(msg);
		}
		catch (Exception e)
		{
			DebugHome.getDebugLog().warn("Envoi impossible d'un email", e);
			throw new TechnicalException("Envoi impossible d'un email", e);
		}
	}
}
