package com.manpower.socle.database.hibernate;

import java.util.ArrayList;
import java.util.List;

public class MappingTestCharEnum extends Enum
{
	public static final MappingTestCharEnum VALUE_1 = new MappingTestCharEnum('1');
	public static final MappingTestCharEnum VALUE_2 = new MappingTestCharEnum('2');
	public static final MappingTestCharEnum VALUE_3 = new MappingTestCharEnum('3');
	public static final MappingTestCharEnum VALUE_4 = new MappingTestCharEnum('4');

	private MappingTestCharEnum(char code)
	{
		super(code);
	}

	public static List getAll()
	{
		return new ArrayList(getAll(MappingTestCharEnum.class));
	}
}
