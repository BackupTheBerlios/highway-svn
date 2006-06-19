package com.manpower.socle.legacy;


public class ConverterMap2Mock extends ConverterMap
{
    public static BasicConverter STRING_BC = new StringConverter(10, " ".charAt(0), StringConverter.LEGACY_PADDING_RIGHT);

    public static AnnotationConverter STRING_AC = new StringConverter(10, " ".charAt(0), StringConverter.LEGACY_PADDING_RIGHT);
}
