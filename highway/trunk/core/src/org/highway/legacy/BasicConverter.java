/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.legacy;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Interface for basic converter (the one that do not use the javadoc
 * annotations). Simply write the value to the stream or contrary, read the
 * stream to extract an object. If to perform reading or writing operations,
 * details like length, padding strategy... must be known, then, this
 * information must be setted at construction time in order to keep converters
 * stateless (not depending on the client context). <br>
 * <b>No use of javadoc tags can be done in this kind of converters.</b>
 * 
 * <br>
 * <br>
 * <b>Caution:</b><br>
 * All implementations must be <b>stateless</b> and <b>thread safe</b>.
 * 
 * @see AnnotationConverter
 * 
 * @author Olivier Mallassi
 * @since 1.3
 * 
 */
public interface BasicConverter
{
    /**
     * Writes <code>value</code> on the stream. <code>value</code> is padded
     * depending on the converter state.
     * 
     * @param outputStream
     *            the stream where to write
     * @param value
     *            the value to write
     * @return the length
     * @throws ConvertException
     *             if pb occurs during convertion
     * 
     */
    int write(OutputStream outputStream, Object value) throws ConvertException;

    /**
     * Reads the <code>inputStream</code> based on the converter parameters
     * and returns the value as an object.
     * 
     * @param inputStream
     *            the stream where to read.
     * @return returns an <code>java.lang.Object</code> representing by what
     *         has been reads in the stream.
     * @throws ConvertException
     *             if pb occurs during convertion
     */
    Object read(InputStream inputStream) throws ConvertException;
}
