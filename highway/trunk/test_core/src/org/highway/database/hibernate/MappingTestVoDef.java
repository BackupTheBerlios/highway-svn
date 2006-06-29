package org.highway.database.hibernate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;

import org.highway.bean.Decimal;
import org.highway.bean.ValueObject;
import org.highway.database.Identity;
import org.highway.database.Mapped;
import org.highway.database.MappedOn;
import org.highway.database.MappingSpecialType;

@Mapped
@MappedOn("MAPPING_TEST")
public interface MappingTestVoDef extends ValueObject
{
	@Identity
	@MappedOn("PRIMITIVE_LONG")
	long getPrimitiveLongProperty();
	
	@MappedOn("PRIMITIVE_BOOLEAN")
	boolean getPrimitiveBooleanProperty();
	
	@MappedOn("PRIMITIVE_CHAR")
	char getPrimitiveCharProperty();
	 
	@MappedOn("PRIMITIVE_DOUBLE")
	double getPrimitiveDoubleProperty();
	
	@MappedOn("PRIMITIVE_FLOAT")
	float getPrimitiveFloatProperty();
	
	@MappedOn("PRIMITIVE_INT")
	int getPrimitiveIntProperty();
	
	@MappedOn("PRIMITIVE_SHORT")
	short getPrimitiveShortProperty();
	
	@MappedOn("LONG")
	Long getLongProperty();
	
	@MappedOn("BOOLEAN")
	Boolean getBooleanProperty();
	
	@MappedOn("CHARACTER")
	Character getCharacterProperty();
	
	@MappedOn("DOUBLE")
	Double getDoubleProperty();
	
	@MappedOn("FLOAT")
	Float getFloatProperty();
	
	@MappedOn("INTEGER")
	Integer getIntegerProperty();
	
	@MappedOn("SHORT")
	Short getShortProperty();
	
	@MappedOn("STRING")
	String getStringProperty();
	
	@MappedOn("SQL_DATE")
	java.sql.Date getSqlDateProperty();
	
	@MappedOn("UTIL_DATE")
	java.util.Date getUtilDateProperty();
	 
	@MappedOn("TIME")
	Time getTimeProperty();
	
	@MappedOn("TIMESTAMP")
	Timestamp getTimestampProperty();
	
	@MappedOn("BIG_DECIMAL")
	BigDecimal getBigDecimalProperty();
	
	@MappedOn("BIG_INTEGER")
	BigInteger getBigIntegerProperty();
	
	@MappedOn("DECIMAL")
	Decimal getDecimalProperty();
	
	@MappedOn("CHAR_ENUM")
	MappingTestCharEnum getCharEnumProperty();
	
	@MappedOn("SHORT_ENUM")
	MappingTestShortEnum getShortEnumProperty();
	
	@MappedOn("STRING_ENUM")
	MappingTestStringEnum getStringEnumProperty();
	
	@MappedOn("BLOB")
	@MappingSpecialType(ByteArray2BlobHibernateType.class)
	byte[] getByteArrayBlobProperty();
	
	@MappedOn("CLOB")
	@MappingSpecialType(String2ClobHibernateType.class)
	String getStringClobProperty();
}
