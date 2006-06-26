package org.highway.servicetest.access;

import org.highway.legacy.BasicConverter;
import org.highway.legacy.ConverterMap;
import org.highway.legacy.DateConverter;
import org.highway.legacy.IntegerConverter;
import org.highway.legacy.StringConverter;

public class ConverterMap1 extends ConverterMap
{
    public static BasicConverter STRING_40 = new StringConverter(40, " ".charAt(0), StringConverter.LEGACY_PADDING_RIGHT);

    public static BasicConverter STRING_32 = new StringConverter(32, " ".charAt(0), StringConverter.LEGACY_PADDING_RIGHT);

    public static BasicConverter STRING_22 = new StringConverter(22, " ".charAt(0), StringConverter.LEGACY_PADDING_RIGHT);

    public static BasicConverter STRING_20 = new StringConverter(20, " ".charAt(0), StringConverter.LEGACY_PADDING_RIGHT);

    public static BasicConverter STRING_18 = new StringConverter(18, " ".charAt(0), StringConverter.LEGACY_PADDING_RIGHT);

    public static BasicConverter STRING_15 = new StringConverter(15, " ".charAt(0), StringConverter.LEGACY_PADDING_RIGHT);

    public static BasicConverter STRING_10 = new StringConverter(10, " ".charAt(0), StringConverter.LEGACY_PADDING_RIGHT);

    public static BasicConverter STRING_9 = new StringConverter(9, " ".charAt(0), StringConverter.LEGACY_PADDING_RIGHT);

    public static BasicConverter STRING_8 = new StringConverter(8, " ".charAt(0), StringConverter.LEGACY_PADDING_RIGHT);

    public static BasicConverter STRING_5 = new StringConverter(5, " ".charAt(0), StringConverter.LEGACY_PADDING_RIGHT);

    public static BasicConverter STRING_3 = new StringConverter(3, " ".charAt(0), StringConverter.LEGACY_PADDING_RIGHT);

    public static BasicConverter STRING_2 = new StringConverter(2, " ".charAt(0), StringConverter.LEGACY_PADDING_RIGHT);

    public static BasicConverter STRING_1 = new StringConverter(1, " ".charAt(0), StringConverter.LEGACY_PADDING_RIGHT);

    public static BasicConverter INTEGER_10 = new IntegerConverter(10, '0');

    public static BasicConverter INTEGER_5 = new IntegerConverter(5, '0');

    public static BasicConverter INTEGER_4 = new IntegerConverter(4, '0');

    public static BasicConverter INTEGER_2 = new IntegerConverter(2, '0');

    public static BasicConverter DATE = new DateConverter("yyyyMMdd");

    public ConverterMap1()
    {
        super();
        super.setDefaultAnnotationBasedConverter(int.class, new IntegerConverter());
        super.setDefaultAnnotationBasedConverter(String.class, new StringConverter());
    }
}
