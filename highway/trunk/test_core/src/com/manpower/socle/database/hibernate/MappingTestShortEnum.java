package org.highway.database.hibernate;

import java.util.ArrayList;
import java.util.List;

public class MappingTestShortEnum extends Enum
{
	public static final MappingTestShortEnum VALUE_1 = new MappingTestShortEnum((short) 1);
	public static final MappingTestShortEnum VALUE_2 = new MappingTestShortEnum((short) 2);
	public static final MappingTestShortEnum VALUE_3 = new MappingTestShortEnum((short) 3);
	public static final MappingTestShortEnum VALUE_4 = new MappingTestShortEnum((short) 4);

	private MappingTestShortEnum(short code)
	{
		super(code);
	}

	public static List getAll()
	{
		return new ArrayList(getAll(MappingTestShortEnum.class));
	}
}
