/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.legacy;

import org.highway.lifecycle.Closeable;

/**
 * Represents a session on a legacy server and so the way to connect to the
 * server. A session object is getting from the LegacyServer object:
 * 
 * <pre>
 *   ...
 *   Session session = serverInstance.openSession();
 *   ...
 * </pre>
 * 
 * A session object can create a new request.
 * 
 * @author Olivier Mallassi
 * @since 1.3
 * 
 */
public interface LegacySession extends Closeable
{
    /**
     * Closes all the underlying connections.
     * 
     */
    void close();

    /**
     * Returns the associated server.
     * 
     */
    LegacyServer getServer();

    /**
     * Creates and returns a new request object.
     * 
     */
    LegacyRequest newRequest();
}
