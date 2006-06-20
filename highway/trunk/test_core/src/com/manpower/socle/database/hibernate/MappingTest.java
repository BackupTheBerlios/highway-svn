package org.highway.database.hibernate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;

import junit.framework.TestCase;
import net.sf.hibernate.cfg.Configuration;

import org.highway.database.Database;
import org.highway.database.DatabaseSession;
import org.highway.database.SelectQuery;
import org.highway.database.TooManyResultsExeption;
import org.highway.database.hibernate.HibernateDatabase;
import org.highway.debug.DebugHome;
import org.highway.debug.DebugLog;
import org.highway.debug.Log4jDebugLog;
import org.highway.helper.Wrapper;
import org.highway.vo.Decimal;



public class MappingTest extends TestCase
{
	private Database database;

	protected void setUp() throws Exception
	{
		System.setProperty(DebugLog.ENABLE_SYSTEM_PROPERTY, "true");
		DebugHome.setDebugLog(new Log4jDebugLog());
		
		Configuration configuration = new Configuration();
		configuration.setProperty("hibernate.show_sql",                "true");
		configuration.setProperty("hibernate.dialect",                 "net.sf.hibernate.dialect.DB2Dialect");
		configuration.setProperty("hibernate.default_schema",          "db2admin");
		configuration.setProperty("hibernate.connection.driver_class", "com.ibm.db2.jcc.DB2Driver");
		configuration.setProperty("hibernate.connection.url",          "jdbc:db2://w0005354:50000/test");
		configuration.setProperty("hibernate.connection.username",     "db2admin");
		configuration.setProperty("hibernate.connection.password",     "db2admin");
		configuration.setProperty("hibernate.connection.isolation",    "2");
		configuration.configure("/hibernate.cfg.xml");
		
		database = new HibernateDatabase(configuration);
	}

	public void test() throws Exception
	{
		insertAndDeleteTest();
		selectUniqueTest();
		selectEnumTest();
	}
	
	private void insertAndDeleteTest() throws Exception
	{
		DatabaseSession session = database.openSession();
		
		session.delete("from MappingTestVo");
		
		session.insert(newMappingTestVo(1));
		session.insert(new Object[] { newMappingTestVo(2), newMappingTestVo(3), newMappingTestVo(4) });
		
		assertEquals(session.select("from MappingTestVo").size(), 4);
		
		session.delete(MappingTestVo.class, new Long(4));
		
		assertEquals(session.select("from MappingTestVo").size(), 3);
		
		session.getConnection().commit();
	}
	
	private void selectUniqueTest() throws Exception
	{
		DatabaseSession session = database.openSession();
		
		SelectQuery selectQuery = session.createSelectQuery();
		selectQuery.addQueryText("from MappingTestVo vo where vo.longProperty = ?");
		selectQuery.setParameters(Wrapper.toList(new Long(3)));
		MappingTestVo vo = (MappingTestVo) selectQuery.getUnique();
		
		vo.setPrimitiveCharProperty('z');
		session.update(vo);
		
		vo = (MappingTestVo) session.select(MappingTestVo.class, new Long(3));
		assertEquals(vo.getPrimitiveCharProperty(), 'z');
		
		selectQuery = session.createSelectQuery();
		selectQuery.addQueryText("from MappingTestVo vo where vo.booleanProperty = ?");
		selectQuery.setParameters(Wrapper.toList(Boolean.TRUE));
		try
		{
			selectQuery.getUnique();
			// works fine only if it throws an exception
			fail();
		}
		catch (TooManyResultsExeption exc)
		{
		}
		
		session.getConnection().commit();
	}
	
	private void selectEnumTest() throws Exception
	{
		DatabaseSession session = database.openSession();

		MappingTestVo vo = (MappingTestVo) session.select(MappingTestVo.class, new Long(1));
//		vo.setCharEnumProperty(MappingTestCharEnum.VALUE_4);
		vo.setShortEnumProperty(MappingTestShortEnum.VALUE_4);
		vo.setStringEnumProperty(MappingTestStringEnum.VALUE_4);
		session.update(vo);
		
//		assertEquals(
//			session.select(
//				"from MappingTestVo vo where vo.charEnumProperty = ?", 
//				MappingTestCharEnum.VALUE_1
//			).size(),
//			2
//		);
		
		assertEquals(
			session.select(
				"from MappingTestVo vo where vo.shortEnumProperty = ?", 
				MappingTestShortEnum.VALUE_2
			).size(),
			2
		);
		
		assertEquals(
			session.select(
				"from MappingTestVo vo where vo.stringEnumProperty = ?", 
				MappingTestStringEnum.VALUE_3
			).size(),
			2
		);
		
		session.getConnection().commit();
	}
	
	private MappingTestVo newMappingTestVo(long id)
	{
		MappingTestVo result = new MappingTestVo();
		
		result.setPrimitiveLongProperty(id);
		result.setLongProperty(new Long(id));

		long time = System.currentTimeMillis();
		
		result.setBigDecimalProperty(new BigDecimal("123.4"));
		result.setBigIntegerProperty(new BigInteger("123"));
		result.setBooleanProperty(new Boolean(true));
		result.setByteArrayBlobProperty("blob test".getBytes());
		result.setCharacterProperty(new Character('a'));
		result.setCharEnumProperty(MappingTestCharEnum.VALUE_1);
		result.setDecimalProperty(new Decimal(123456, 2));
		result.setDoubleProperty(new Double(123.4));
		result.setFloatProperty(new Float(123));
		result.setIntegerProperty(new Integer(123));
		result.setPrimitiveBooleanProperty(true);
		result.setPrimitiveCharProperty('a');
		result.setPrimitiveDoubleProperty(123.4);
		result.setPrimitiveFloatProperty(123);
		result.setPrimitiveIntProperty(123);
		result.setPrimitiveShortProperty((short) 123);
		result.setShortEnumProperty(MappingTestShortEnum.VALUE_2);
		result.setShortProperty(new Short((short) 123));
		result.setSqlDateProperty(new java.sql.Date(time));
		result.setStringClobProperty("clob test");
		result.setStringEnumProperty(MappingTestStringEnum.VALUE_3);
		result.setStringProperty("string test");
		result.setTimeProperty(new Time(time));
		result.setTimestampProperty(new Timestamp(time));
		result.setUtilDateProperty(new java.util.Date(time));
		
		return result;
	}
}
