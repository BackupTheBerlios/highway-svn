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


@MappedOn("MAPPING_TEST")
@Mapped
public interface MappingTestVoDef extends ValueObject
{
	@Identity
	@MappedOn("TYPE_PRIMITIVE_LONG")
	long getPrimitiveLongProperty();
	
	@MappedOn("TYPE_PRIMITIVE_BOOLEAN")
	boolean getPrimitiveBooleanProperty();
	
	@MappedOn("TYPE_PRIMITIVE_CHAR")
	char getPrimitiveCharProperty();
	 
	@MappedOn("TYPE_PRIMITIVE_DOUBLE")
	double getPrimitiveDoubleProperty();
	
	@MappedOn("TYPE_PRIMITIVE_FLOAT")
	float getPrimitiveFloatProperty();
	
	@MappedOn("TYPE_PRIMITIVE_INT")
	int getPrimitiveIntProperty();
	
	@MappedOn("TYPE_PRIMITIVE_SHORT")
	short getPrimitiveShortProperty();
	
	@MappedOn("TYPE_LONG")
	Long getLongProperty();
	
	@MappedOn("TYPE_BOOLEAN")
	Boolean getBooleanProperty();
	
	@MappedOn("TYPE_CHARACTER")
	Character getCharacterProperty();
	
	@MappedOn("TYPE_DOUBLE")
	Double getDoubleProperty();
	
	@MappedOn("TYPE_FLOAT")
	Float getFloatProperty();
	
	@MappedOn("TYPE_INTEGER")
	Integer getIntegerProperty();
	
	@MappedOn("TYPE_SHORT")
	Short getShortProperty();
	
	@MappedOn("TYPE_STRING")
	String getStringProperty();
	
	@MappedOn("TYPE_SQL_DATE")
	java.sql.Date getSqlDateProperty();
	
	@MappedOn("TYPE_UTIL_DATE")
	java.util.Date getUtilDateProperty();
	 
	@MappedOn("TYPE_TIME")
	Time getTimeProperty();
	
	@MappedOn("TYPE_TIMESTAMP")
	Timestamp getTimestampProperty();
	
	@MappedOn("TYPE_BIG_DECIMAL")
	BigDecimal getBigDecimalProperty();
	
	@MappedOn("TYPE_BIG_INTEGER")
	BigInteger getBigIntegerProperty();
	
	@MappedOn("TYPE_DECIMAL")
	Decimal getDecimalProperty();
	
	@MappedOn("TYPE_CHAR_ENUM")
	MappingTestCharEnum getCharEnumProperty();
	
	@MappedOn("TYPE_SHORT_ENUM")
	MappingTestShortEnum getShortEnumProperty();
	
	@MappedOn("TYPE_STRING_ENUM")
	MappingTestStringEnum getStringEnumProperty();
	
	@MappedOn("TYPE_BLOB")
	@MappingSpecialType(ByteArray2BlobHibernateType.class)
	byte[] getByteArrayBlobProperty();
	
//	@MappedOn("TYPE_CLOB")
//	@MappingSpecialType(String2ClobHibernateType.class)
//	String getStringClobProperty();
}
