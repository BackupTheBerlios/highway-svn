package org.highway.bean;

import java.util.List;

import org.highway.bean.Enum;

/**
 * 
 */
public class EnumMock extends Enum
{

    public static EnumMock rouge = new EnumMock((short) 0, "rouge");

    public static EnumMock bleu = new EnumMock((short) 27, "bleu");

    public static EnumMock vert = new EnumMock((short) 13, "vert");

    private EnumMock(short code, String description)
    {
        super(code, description);
    }

    public static List getAll()
    {
        return Enum.getAll(EnumMock.class);
    }

}
