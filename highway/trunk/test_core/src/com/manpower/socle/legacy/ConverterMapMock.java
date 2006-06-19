package com.manpower.socle.legacy;

import com.manpower.socle.legacy.AnnotationConverter;
import com.manpower.socle.legacy.BasicConverter;
import com.manpower.socle.legacy.ConverterMap;

public class ConverterMapMock extends ConverterMap
{
    public static AnnotationConverter NAME_1 = new AnnotationConverterMockImpl();

    public static BasicConverter NAME_2 = new BasicConverterMockImpl();

}
