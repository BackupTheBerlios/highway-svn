package org.highway.database.hibernate;


public enum MappingTestStringEnum
{
	VALUE_1 ("01"),
	VALUE_2 ("02"),
	VALUE_3 ("03"),
	VALUE_4 ("04");
	private String code;
	private MappingTestStringEnum(String code)
	{
		this.code = code;
	}

	public String getCode()
	{
		return code;
	}
	public String toString(){
		return code;
	}
}
