package com.manpower.servicetest.access;

import org.highway.init.InitException;
import org.highway.legacy.ConverterMap;
import org.highway.legacy.LegacyServer;
import org.highway.legacy.LegacySession;
import org.highway.service.context.RequestContext;
import org.highway.service.context.RequestContextHome;

public class LegacyAccessImplAbstract
{
    /**
     * Field SESSION_CONTEXT_KEY
     */
    private static final Object SESSION_CONTEXT_LEGACY_SYSTEM_KEY = new Object();

    /**
     * Field database
     */
    private static LegacyServer legacyServer;

    private static ConverterMap convertersMap;

    /**
     * Sets the default Database instance to use in all the access components.
     * 
     * @param database
     *            the Database instance to use as default
     */
    public static void setLegacyServer(LegacyServer server)
    {
        LegacyAccessImplAbstract.legacyServer = server;
    }

    /**
     * enable to set the <code>ConverterMap</code> that will be used all along
     * the application
     * 
     * @param converters
     */
    public static void setConverterMap(ConverterMap converters)
    {
        convertersMap = converters;
    }

    /**
     * 
     * @return
     */
    protected ConverterMap getConverters()
    {
        if (convertersMap == null)
        {
            throw new InitException("no ConverterMap set");
        }

        return convertersMap;
    }

    /**
     * 
     * @return
     * @throws IllegalStateException
     * @throws InitException
     */
    protected LegacySession getSession() throws IllegalStateException, InitException
    {
        RequestContext context = RequestContextHome.getRequestContext();

        if (context == null)
            throw new IllegalStateException("No context found");

        LegacySession session = (LegacySession) context.getResource(SESSION_CONTEXT_LEGACY_SYSTEM_KEY);

        if (session == null)
        {
            if (legacyServer == null)
                throw new InitException("No server set");

            session = legacyServer.openSession();
            context.setResource(SESSION_CONTEXT_LEGACY_SYSTEM_KEY, session);
        }
        return session;
    }

    /**
     * 
     * @return
     * @throws IllegalStateException
     * @throws InitException
     */
    protected LegacySession getSession(String userName, String password) throws IllegalStateException, InitException
    {
        RequestContext context = RequestContextHome.getRequestContext();

        if (context == null)
            throw new IllegalStateException("No context found");

        LegacySession session = (LegacySession) context.getResource(SESSION_CONTEXT_LEGACY_SYSTEM_KEY);

        if (session == null)
        {
            if (legacyServer == null)
                throw new InitException("No server set");

            session = legacyServer.openSession(userName, password);
            context.setResource(SESSION_CONTEXT_LEGACY_SYSTEM_KEY, session);
        }
        return session;
    }
}
