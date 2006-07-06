package org.highway.database.hibernate;


public enum MappingTestCharEnum 
{
	VALUE_1 ('1'),
	VALUE_2 ('2'),
	VALUE_3 ('3'),
	VALUE_4 ('4');
	private char code ;
	private MappingTestCharEnum(char code)
	{
		this.code = code;
	}

	public char getCode()
	{
		return code;
	}
	public String toString(){
		return ""+code;
	}
}