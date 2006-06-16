package org.highway.legacy;

/**
 * Can be used for asynchronous call to Legacy server.
 * 
 * @author Olivier Mallassi
 * @since 1.3
 * 
 */
public interface LegacyResponseCallback
{
    /**
     * Is called when the response is sent back.
     * 
     * @param response
     *            the returned response
     */
    void onResponse(LegacyResponse response);
}