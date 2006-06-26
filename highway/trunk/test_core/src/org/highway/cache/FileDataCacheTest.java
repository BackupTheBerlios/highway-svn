package org.highway.cache;

import java.util.Properties;

import junit.framework.TestCase;

import org.highway.cache.DataCacheHome;
import org.highway.cache.FileDataCache;
import org.highway.debug.DebugHome;
import org.highway.debug.DebugLog;
import org.highway.debug.Log4jDebugLog;


/**
 * Unit tests for {@link org.highway.cache.FileDataCache}.
 * 
 * @see org.highway.cache.MyRefreshableData
 * @see org.highway.cache.MyRefreshableDataSet
 * 
 * @author Christian de Bevotte
 * @since 1.4.5
 */
public class FileDataCacheTest extends TestCase
{
	/**
	 * <tt>FileDataCache</tt> properties.
	 */
	private Properties properties;
	
	protected void setUp()
	{
		// DebugLog
		System.setProperty(DebugLog.ENABLE_SYSTEM_PROPERTY, "true");
		DebugHome.setDebugLog(new Log4jDebugLog());

		// DataCache
		properties = new Properties();
		properties.setProperty("socle.cache.time.unit", "millisecond");
		DataCacheHome.setDataCache(new FileDataCache(properties));
		DataCacheHome.clean();
	}

	protected void tearDown() throws Exception
	{
		DataCacheHome.unregisterAll();
		DataCacheHome.clean();
	}
	
	/**
	 * Runs all tests.
	 */
	public void testAll()
	{
		errorTest();
		
		loadFromFileTest();
		
		refreshDataTest();
		
		refreshDataSetTest();
		
		forceRefreshDataTest();
		
		forceRefreshDataSetTest();
	}

	private void errorTest()
	{
		DebugHome.debug("********** Test : error cases **********");
		
		// Should throw an exception since there is no registered data
		try
		{
			DataCacheHome.getData("data");
			fail();
		}
		catch (Exception exc)
		{
		}
	}

	private void loadFromFileTest()
	{
		DebugHome.debug("********** Test : load data from file **********");

		DataCacheHome.unregisterAll();
		DataCacheHome.clean();

		// We set a long refresh period to be sure the timer will not run
		// during the test
		MyRefreshableData data = new MyRefreshableData("data", 100000, 0,
			new String[] {"Black"});

		DataCacheHome.register(data);

		// Data was refreshed once when registering : count is 1
		assertEquals(getData("data"), "Black");
		assertEquals(data.refreshCount, 1);

		// The cache is reinitialized but not cleaned so data is loaded from
		// file
		DataCacheHome.unregisterAll();
		DataCacheHome.setDataCache(new FileDataCache(properties));
		DataCacheHome.register(data);

		// Data was not refreshed but read from file : count is still 1
		assertEquals(getData("data"), "Black");
		assertEquals(data.refreshCount, 1);
	}

	private void refreshDataTest()
	{
		DebugHome.debug("********** Test : refresh data **********");

		DataCacheHome.unregisterAll();
		DataCacheHome.clean();

		int refreshPeriod = 1000;
		int transferTime = 200;

		MyRefreshableData data = new MyRefreshableData("data", refreshPeriod,
			transferTime, new String[] {"Black", "White", "White"});

		DataCacheHome.register(data);

		// First refresh occurred during registration : data is transfered
		assertEquals(getData("data"), "Black");
		assertEquals(data.refreshCount, 1);
		assertTrue(data.transfered);

		sleep(refreshPeriod + transferTime / 2);

		// data is transfering : count is 2 but data is still "Black"
		assertEquals(getData("data"), "Black");
		assertEquals(data.refreshCount, 2);
		assertFalse(data.transfered);

		sleep(refreshPeriod / 2);

		// Second refresh and transfer occurred : data is now "White"
		assertEquals(getData("data"), "White");
		assertEquals(data.refreshCount, 2);
		assertTrue(data.transfered);

		sleep(refreshPeriod);

		// Third refresh occurred but no transfer since data was not obsolete
		assertEquals(getData("data"), "White");
		assertEquals(data.refreshCount, 3);
		assertFalse(data.transfered);
	}

	private void refreshDataSetTest()
	{
		DebugHome.debug("********** Test : refresh data set **********");

		DataCacheHome.unregisterAll();
		DataCacheHome.clean();

		int refreshPeriod = 1000;
		int transferTime = 200;

		MyRefreshableData data1 = new MyRefreshableData("data1", 0,
			transferTime, new String[] {"Black", "White", "White"});
		MyRefreshableData data2 = new MyRefreshableData("data2", 0,
			transferTime, new String[] {"Chapi", "Chapi", "Chapo"});

		MyRefreshableDataSet dataSet = new MyRefreshableDataSet("dataSet",
			refreshPeriod);
		dataSet.add(data1);
		dataSet.add(data2);

		DataCacheHome.register(dataSet);

		// First refresh occurred during registration : data1 and data2 are
		// transfered
		assertEquals(getData("data1"), "Black");
		assertEquals(data1.refreshCount, 1);
		assertTrue(data1.transfered);
		assertEquals(getData("data2"), "Chapi");
		assertEquals(data2.refreshCount, 1);
		assertTrue(data2.transfered);

		sleep(refreshPeriod + transferTime / 2);

		// data1 is transfering : count is 2 but data1 is still "Black"
		assertEquals(getData("data1"), "Black");
		assertEquals(data1.refreshCount, 2);
		assertFalse(data1.transfered);

		sleep(transferTime);

		// Second refresh occurred : data1 is now "White" and data2 is still
		// "Chapi"
		assertEquals(getData("data1"), "White");
		assertEquals(data1.refreshCount, 2);
		assertTrue(data1.transfered);
		assertEquals(getData("data2"), "Chapi");
		assertEquals(data2.refreshCount, 2);
		assertFalse(data2.transfered);

		sleep(refreshPeriod);

		// Third refresh occurred : data1 is still "White" and data2 is now
		// "Chapo"
		assertEquals(getData("data1"), "White");
		assertEquals(data1.refreshCount, 3);
		assertFalse(data1.transfered);
		assertEquals(getData("data2"), "Chapo");
		assertEquals(data2.refreshCount, 3);
		assertTrue(data2.transfered);
	}
	
	private void forceRefreshDataTest()
	{
		DebugHome.debug("********** Test : force refresh data **********");

		DataCacheHome.unregisterAll();
		DataCacheHome.clean();

		// We set a long refresh period to be sure the timer will not run
		// during the test
		MyRefreshableData data = new MyRefreshableData("data", 100000, 0,
			new String[] {"Black", "White"});

		DataCacheHome.register(data);

		// First refresh occurred during registration : data is transfered
		assertEquals(getData("data"), "Black");
		assertEquals(data.refreshCount, 1);
		assertTrue(data.transfered);

		DataCacheHome.forceRefresh(data);

		sleep(1000);

		// Force refresh occurred : data is now "White"
		assertEquals(getData("data"), "White");
		assertEquals(data.refreshCount, 2);
		assertTrue(data.transfered);
	}

	private void forceRefreshDataSetTest()
	{
		DebugHome.debug("********** Test : force refresh data set **********");

		DataCacheHome.unregisterAll();
		DataCacheHome.clean();

		MyRefreshableData data1 = new MyRefreshableData("data1", 0,
			0, new String[] {"Black", "White"});
		MyRefreshableData data2 = new MyRefreshableData("data2", 0,
			0, new String[] {"Chapi", "Chapo"});

		// We set a long refresh period to be sure the timer will not run
		// during the test
		MyRefreshableDataSet dataSet = new MyRefreshableDataSet("dataSet",
			100000);
		dataSet.add(data1);
		dataSet.add(data2);

		DataCacheHome.register(dataSet);

		// First refresh occurred during registration : data1 and data2 are
		// transfered
		assertEquals(getData("data1"), "Black");
		assertEquals(data1.refreshCount, 1);
		assertTrue(data1.transfered);
		assertEquals(getData("data2"), "Chapi");
		assertEquals(data2.refreshCount, 1);
		assertTrue(data2.transfered);

		DataCacheHome.forceRefresh(dataSet);

		sleep(1000);

		// Force refresh occurred : data1 is now "White" and data2 is now
		// "Chapo"
		assertEquals(getData("data1"), "White");
		assertEquals(data1.refreshCount, 2);
		assertTrue(data1.transfered);
		assertEquals(getData("data2"), "Chapo");
		assertEquals(data2.refreshCount, 2);
		assertTrue(data2.transfered);
	}

	private void sleep(long time)
	{
		try
		{
			Thread.sleep(time);
		}
		catch (InterruptedException exc)
		{
		}
	}

	private String getData(Object id)
	{
		return new String((byte[]) DataCacheHome.getData(id));
	}
}
