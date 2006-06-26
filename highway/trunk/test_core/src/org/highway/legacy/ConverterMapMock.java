package org.highway.legacy;

import org.highway.legacy.AnnotationConverter;
import org.highway.legacy.BasicConverter;
import org.highway.legacy.ConverterMap;

public class ConverterMapMock extends ConverterMap
{
    public static AnnotationConverter NAME_1 = new AnnotationConverterMockImpl();

    public static BasicConverter NAME_2 = new BasicConverterMockImpl();

}
