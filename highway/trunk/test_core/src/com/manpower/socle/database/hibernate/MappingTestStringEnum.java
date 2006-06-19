package com.manpower.socle.database.hibernate;

import java.util.ArrayList;
import java.util.List;

public class MappingTestStringEnum extends Enum
{
	public static final MappingTestStringEnum VALUE_1 = new MappingTestStringEnum("01");
	public static final MappingTestStringEnum VALUE_2 = new MappingTestStringEnum("02");
	public static final MappingTestStringEnum VALUE_3 = new MappingTestStringEnum("03");
	public static final MappingTestStringEnum VALUE_4 = new MappingTestStringEnum("04");

	private MappingTestStringEnum(String code)
	{
		super(code);
	}

	public static List getAll()
	{
		return new ArrayList(getAll(MappingTestStringEnum.class));
	}
}
