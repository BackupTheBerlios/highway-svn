/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.legacy;

/**
 * Encapsulates the connection parameters to the legacy server and opens a
 * <code>LegacySession</code>.
 * 
 * @author Olivier Mallassi
 * @since 1.3
 * 
 */
public interface LegacyServer
{
    /**
     * Opens a session.
     * 
     * @throws ConnectorException
     *             if error occurs during session creation
     */
    LegacySession openSession() throws ConnectorException;

    /**
     * Opens a session with credentials.
     * 
     * @param userName
     *            the userName to use for the request
     * @param password
     *            the password to use for the request
     */
    LegacySession openSession(String userName, String password) throws ConnectorException;
}
