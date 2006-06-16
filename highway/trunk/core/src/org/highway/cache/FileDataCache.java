package org.highway.cache;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import org.highway.debug.DebugHome;
import org.highway.exception.Assert;
import org.highway.exception.TechnicalException;
import org.highway.helper.StringHelper;
import org.highway.io.ChecksumHelper;
import org.highway.io.FileHelper;
import org.highway.io.SerializeHelper;

/**
 * <p>
 * A <tt>DataCache</tt> implementation that stores data in local files. For
 * each cache item two files are created in the cache directory : a file
 * containing the data and a file holding the data version.
 * </p>
 * <p>
 * This implementation assumes that data type is <tt>byte[]</tt>.
 * 
 * @author Christian de Bevotte
 * @since 1.4.4
 */
public class FileDataCache implements DataCache
{
	/**
	 * A cache item.
	 */
	private class Item
	{
		/**
		 * The item name represents the common portion of data file name and 
		 * version file name :
		 * <pre>
		 * data file name    = item name + data suffix
		 * version file name = item name + version suffix
		 * </pre>
		 * 
		 * @see FileDataCache#getDataFile(String)
		 * @see FileDataCache#getVersionFile(String)
		 */
		private String name;
		
		/**
		 * The data version as stored in the version file.
		 */
		private DataVersion dataVersion;
		
		/**
		 * The object that provides the data stored in the data file.
		 * Used as mutex when accessing items.
		 * 
		 * @see FileDataCache#getData(Object)
		 * @see FileDataCache#save(Object[], Object[], Object)
		 */
		private Object refreshable;
	}
	
	/**
	 * Constant for the cache directory property 
	 * (value is {@value #DIRECTORY}).
	 */
	public static final String DIRECTORY = "highway.cache.directory";
	
	/**
	 * Constant for the data files and version files prefix property
	 * (value is {@value #PREFIX}).
	 */
	public static final String PREFIX = "highway.cache.prefix";
	
	/**
	 * Constant for the data files suffix property
	 * (value is {@value #SUFFIX_DATA}).
	 */
	public static final String SUFFIX_DATA = "highway.cache.suffix.data";
	
	/**
	 * Constant for the version files suffix property
	 * (value is {@value #SUFFIX_VERSION}).
	 */
	public static final String SUFFIX_VERSION = "highway.cache.suffix.version";
	
	/**
	 * Constant for the time unit property
	 * (value is {@value #TIME_UNIT}).
	 */
	private static final String TIME_UNIT = "highway.cache.time.unit";
	
	/**
	 * The cache directory.
	 */
	private String directory;
	
	/**
	 * The prefix used for data files and version files.
	 */
	private String prefix;
	
	/**
	 * The suffix used for data files.
	 */
	private String suffixData;
	
	/**
	 * The suffix used for version files.
	 */
	private String suffixVersion;
	
	/**
	 * The time unit used for <tt>RefreshableData</tt>'s refresh period.
	 */
	private int timeUnit;
	
	/**
	 * Items managed by this cache. The data id is used as key.
	 */
	private Map items;
	
	/**
	 * Timers managed by this cache. The refreshable is used as key.
	 */
	private Map timers;
	
	/**
	 * Holds a collection of item names to be deleted.
	 */
	private ThreadLocal trashcan;
	
	/**
	 * Returns a number to be used as multiplier in conversion from given time
	 * unit to milliseconds.
	 */
	private static int getTimeUnit(String property)
	{
		if (property.equals("millisecond"))
		{
			return 1;
		}
		if (property.equals("second"))
		{
			return 1000;
		}
		if (property.equals("minute"))
		{
			return 60 * 1000;
		}
		if (property.equals("hour"))
		{
			return 60 * 60 * 1000;
		}
		if (property.equals("day"))
		{
			return 24 * 60 * 60 * 1000;
		}
		throw new TechnicalException("Unknown time unit: " + property);
	}

	/**
	 * <p>
	 * Constructs a <tt>FileDataCache</tt> configured with the default 
	 * properties.
	 * </p>
	 * <p>
	 * Default properties are :
	 * <pre>
	 * highway.cache.directory      = <em>System.getProperty("java.io.tmpdir")</em>
	 * highway.cache.prefix         = cache
	 * highway.cache.suffix.data    = .data
	 * highway.cache.suffix.version = .version
	 * </pre>
	 * </p>
	 */
	public FileDataCache()
	{
		this(null, null, null, null, null);
	}

	/**
	 * Constructs a <tt>FileDataCache</tt> configured with the given cache 
	 * directory and default values for other properties.
	 * 
	 * @see #FileDataCache() for default values
	 */
	public FileDataCache(String directory)
	{
		this(directory, null, null, null, null);
	}

	/**
	 * Constructs a <tt>FileDataCache</tt> configured with the given 
	 * properties.
	 * 
	 * @see #FileDataCache() for default values
	 */
	public FileDataCache(Properties properties)
	{
		this(
			properties.getProperty(DIRECTORY),
			properties.getProperty(PREFIX),
			properties.getProperty(SUFFIX_DATA),
			properties.getProperty(SUFFIX_VERSION),
			properties.getProperty(TIME_UNIT)
		);
	}
	
	/**
	 * Constructs a <tt>FileDataCache</tt> configured with the given
	 * properties.
	 */
	private FileDataCache(String directory, String prefix, String suffixData,
		String suffixVersion, String timeUnit)
	{
		this.directory = directory == null ? 
			System.getProperty("java.io.tmpdir") : directory;
		this.prefix = prefix == null ? "cache" : prefix;
		this.suffixData = suffixData == null ? ".data" : suffixData;
		this.suffixVersion = suffixVersion == null ? ".version" : suffixVersion;
		this.timeUnit = getTimeUnit(timeUnit == null ? "minute" : timeUnit);

		this.items = new HashMap();
		this.timers = new HashMap();

		this.trashcan = new ThreadLocal()
		{
			protected Object initialValue()
			{
				return new ArrayList();
			}
		};

		loadItems();
	}
	
	/**
	 * Deletes from the cache directory all of the files - data and version
	 * files - that do not belong to a registered data any more.
	 */
	public void clean()
	{
		DebugHome.info("Cleaning non-registered cache items");
		
		Iterator iterator = items.values().iterator();
		
		while (iterator.hasNext())
		{
			Item item = (Item) iterator.next();
			if (! isRegistered(item))
			{
				deleteFiles(item.name);
				iterator.remove();
			}
		}
	}

	/*
	 * Implements or overrides an already defined method.
	 */
	public void forceRefresh(RefreshableData refreshableData)
	{
		cancelTimer(refreshableData);
		createTimer(refreshableData, 0L);
	}

	/*
	 * Implements or overrides an already defined method.
	 */
	public void forceRefresh(RefreshableDataSet refreshableDataSet)
	{
		cancelTimer(refreshableDataSet);
		createTimer(refreshableDataSet, 0L);
	}

	/*
	 * Implements or overrides an already defined method.
	 */
	public Object getData(Object id)
	{
		Item item = (Item) items.get(id);
		
		if (! isRegistered(item))
		{
			throw new TechnicalException("No registered data for id: " + id);
		}
		
		synchronized(item.refreshable)
		{
			return FileHelper.read(getDataFile(item.name));
		}
	}
	
	/*
	 * Implements or overrides an already defined method.
	 */
	public void register(RefreshableData refreshableData)
	{
		long delay = register(
			refreshableData,
			new Object[] { refreshableData.getId() }
		);
		
		if (delay == -1)
		{
			refresh(refreshableData);
			delay = timeUnit * refreshableData.getRefreshPeriod();
		}
		else
		{
			delay = timeUnit * refreshableData.getRefreshPeriod() 
				- System.currentTimeMillis() + delay;
		}
		
		createTimer(refreshableData, delay);
	}
	
	/*
	 * Implements or overrides an already defined method.
	 */
	public void register(RefreshableDataSet refreshableDataSet)
	{
		long delay = register(
			refreshableDataSet, refreshableDataSet.getIdSet().toArray()
		);
				
		if (delay == -1)
		{
			refresh(refreshableDataSet);
			delay = timeUnit * refreshableDataSet.getRefreshPeriod();
		}
		else
		{
			delay = timeUnit * refreshableDataSet.getRefreshPeriod() 
				- System.currentTimeMillis() + delay;
		}
		
		createTimer(refreshableDataSet, delay);
	}
	
	/*
	 * Implements or overrides an already defined method.
	 */
	public void unregisterAll()
	{
		DebugHome.info("Removing all of the timers from this cache");
		
		Iterator iterator = timers.values().iterator();
		
		while (iterator.hasNext())
		{
			((Timer) iterator.next()).cancel();
		}
		
		timers.clear();
	}
	
	/**
	 * Initializes this cache from version files read in the cache directory.
	 * Version files with the same data id are deleted except the most recent.
	 */
	private void loadItems()
	{
		String[] files = listVersionFiles();
		for (int i = 0; i < files.length; i++)
		{
			String name = StringHelper.removeSuffix(files[i], suffixVersion);
			DataVersion dataVersion = readVersion(name);
			
			if (dataVersion != null)
			{
				Object id = dataVersion.getDataId();
				Item item = (Item) items.get(id);
				
				if (item == null)
				{
					item = createItem(id);
				}
				else
				{
					if (getLastRefreshed(item.name) > getLastRefreshed(name))
					{
						deleteFiles(name);
						continue;
					}
					else
					{
						deleteFiles(item.name);
					}
				}
				
				item.dataVersion = dataVersion;
				item.name = name;
			}
		}
	}
	
	/**
	 * Registers the given cache items : creates them if necessary and
	 * associates them with the given refreshable.
	 * 
	 * @return the last-refreshed time of the given cache items, or <tt>-1L</tt>
	 *         if items was not registered yet
	 */
	private long register(Object refreshable, Object[] idArray)
	{
		if (timers.containsKey(refreshable))
		{
			throw new TechnicalException(
				"Already registered refreshable: " + refreshable
			);
		}
		
		DebugHome.info("Registering: " + refreshable);
		
		long result = 0L;
		
		for (int i = 0; i < idArray.length; i++)
		{
			Object id = idArray[i];
			Item item = (Item) items.get(id);
			
			if (item == null)
			{
				item = createItem(id);
				result = -1L;
			}
			else
			{
				if (isRegistered(item))
				{
					throw new TechnicalException(
						"Already registered data with id: " + id
					);
				}
				if (result != -1)
				{
					long lastRefreshed = getLastRefreshed(item.name);
					result = result == 0 ? 
						lastRefreshed : Math.min(result, lastRefreshed);
				}
			}
			
			item.refreshable = refreshable;
		}
		
		return result;
	}
	
	/**
	 * Creates a {@link Timer} and schedules a task to refresh the given 
	 * refreshable data.
	 * 
	 * @param refreshableData
	 * @param delay delay in milliseconds before task is to be executed
	 */
	private void createTimer(final RefreshableData refreshableData, long delay)
	{
		TimerTask task = new TimerTask()
		{
			public void run()
			{
				refresh(refreshableData);
			}	
		};
		
		createTimer(refreshableData, task, delay, 
			timeUnit * refreshableData.getRefreshPeriod());
	}
	
	/**
	 * Creates a {@link Timer} and schedules a task to refresh the given 
	 * refreshable data set.
	 * 
	 * @param refreshableDataSet
	 * @param delay delay in milliseconds before task is to be executed
	 */
	private void createTimer(final RefreshableDataSet refreshableDataSet, 
		long delay)
	{
		TimerTask task = new TimerTask()
		{
			public void run()
			{
				refresh(refreshableDataSet);
			}	
		};
		
		createTimer(refreshableDataSet, task, delay,
			timeUnit * refreshableDataSet.getRefreshPeriod());
	}
	
	/**
	 * Create a timer and schedules the given task.
	 * The timer is stored in the timers map with the given refreshable as key.
	 * 
	 * @see Timer#schedule(java.util.TimerTask, long, long)
	 */
	private void createTimer(Object refreshable, TimerTask task, long delay, 
		long period)
	{
		Timer timer = new Timer(true);
		timers.put(refreshable, timer);
		timer.schedule(task, delay < 0 ? 0 : delay, period);
	}
	
	/**
	 * Cancels the timer associated with the given refreshable.
	 */
	private void cancelTimer(Object refreshable)
	{
		Timer timer = (Timer) timers.get(refreshable);

		if (timer == null)
		{
			throw new TechnicalException(
				"Non-registered refreshable: " + refreshable
			);
		}

		timer.cancel();
	}
	
	/**
	 * Refreshes the given refreshable data.
	 */
	private void refresh(RefreshableData refreshableData)
	{
		DebugHome.info("Refreshing: " + refreshableData);
		
		Object id = refreshableData.getId();
		Item item = (Item) items.get(id);
		
		Object data = refreshableData.refresh(item.dataVersion);
		
		save(new Object[] { id }, new Object[] { data }, refreshableData);
	}
	
	/**
	 * Refreshes the given refreshable data set.
	 */
	private void refresh(RefreshableDataSet refreshableDataSet)
	{
		DebugHome.info("Refreshing: " + refreshableDataSet);
		
		// Building an array of data version from the given refreshable data set
		Object[] idArray = refreshableDataSet.getIdSet().toArray();
		DataVersion[] versionArray = new DataVersion[idArray.length];
		for (int i = 0; i < idArray.length; i++)
		{
			Item item = (Item) items.get(idArray[i]);
			versionArray[i] = item.dataVersion;
		}
		
		Object[] dataArray = refreshableDataSet.refresh(versionArray);
		
		save(idArray, dataArray, refreshableDataSet);
	}
	
	/**
	 * Saves the given data array. Associated cache items are updated through a 
	 * synchronized block using the given refreshable as mutex.
	 */
	private void save(Object[] idArray, Object[] dataArray, Object refreshable)
	{
		Assert.check(idArray.length == dataArray.length);
		
		Item[] itemArray = new Item[idArray.length];
		boolean saved = false;
		
		for (int i = 0; i < idArray.length; i++)
		{
			if (dataArray[i] == null)
			{
				Item item = (Item) items.get(idArray[i]);
				setLastRefreshed(item.name);
			}
			else
			{
				itemArray[i] = save(idArray[i], dataArray[i]);
				saved = true;
			}
		}
		
		if (saved)
		{
			synchronized(refreshable)
			{
				for (int i = 0; i < itemArray.length; i++)
				{
					if (itemArray[i] != null)
					{
						Item item = (Item) items.get(idArray[i]);
						
						addToTrashcan(item.name);
						
						item.name = itemArray[i].name;
						item.dataVersion = itemArray[i].dataVersion;
					}
				}
			}
			clearTrashcan();
		}
	}
	
	/**
	 * Saves the given data as a file in the cache directory. The data version
	 * is computed and saved as a file as well.
	 * 
	 * @return an <tt>Item</tt> with name and data version new values
	 */
	private Item save(Object id, Object data)
	{
		Assert.check(
			data instanceof byte[],
			"FileDataCache only deals with data of type byte[]"
		);
		
		try
		{
			Item result = new Item();
			
			File versionFile = File.createTempFile(
				prefix, suffixVersion, new File(directory)
			);
			
			result.name = StringHelper.removeSuffix(
				versionFile.getName(), suffixVersion
			);
			
			DebugHome.info("Saving data with name: " + result.name);
			
			FileHelper.save(getDataFile(result.name), (byte[]) data);
			
			result.dataVersion = new DataVersion();
			result.dataVersion.setDataId(id);
			result.dataVersion.setVersion(
				ChecksumHelper.checksum((byte[]) data)
			);
			
			FileHelper.save(
				versionFile,
				SerializeHelper.toByte(result.dataVersion)
			);
			
			return result;
		}
		catch(IOException exc)
		{
			throw new TechnicalException(exc);
		}
	}
	
	/**
	 * Marks the files associated with the given name for delete.
	 */
	private void addToTrashcan(String name)
	{
		((Collection) trashcan.get()).add(name);
	}
	
	/**
	 * Deletes all the files marked for delete.
	 */
	private void clearTrashcan()
	{
		Collection collection = (Collection) trashcan.get();
		Iterator iterator = collection.iterator();
		while (iterator.hasNext())
		{
			deleteFiles((String) iterator.next());
		}
		collection.clear();
	}
	
	/**
	 * Creates a cache item with the provided id.
	 */
	private Item createItem(Object id)
	{
		Item result = new Item();
		items.put(id, result);
		
		result.dataVersion = new DataVersion();
		result.dataVersion.setDataId(id);
		
		return result;
	}
	
	/**
	 * Returns <tt>true</tt> if the provided cache item is registered.
	 */
	private boolean isRegistered(Item item)
	{
		return item != null && item.refreshable != null
			&& timers.containsKey(item.refreshable);
	}
	
	/**
	 * Returns the <tt>DataVersion</tt> associated with the given name, as it 
	 * is read from the version file. In case of error while reading the file,
	 * the version file and the data file are deleted. 
	 */
	private DataVersion readVersion(String name)
	{
		try
		{
			return (DataVersion) SerializeHelper.toObject(
				FileHelper.read(getVersionFile(name))
			);
		}
		catch (TechnicalException exc)
		{
			deleteFiles(name);
			return null;
		}
	}
	
	/**
	 * Returns the data file associated with the given name.
	 */
	private File getDataFile(String name)
	{
		return new File(directory, name + suffixData);
	}
	
	/**
	 * Returns the version file associated with the given name.
	 */
	private File getVersionFile(String name)
	{
		return new File(directory, name + suffixVersion);
	}
	
	/**
	 * Returns the last-refreshed time of the given cache item.
	 * Actually it is the last-modified time of the associated version file.
	 */
	private long getLastRefreshed(String name)
	{
		return name == null ? 0L : getVersionFile(name).lastModified();
	}
	
	/**
	 * Sets the last-refreshed time of the given cache item to the current time.
	 * Actually it is the last-modified time of the associated version file.
	 */
	private void setLastRefreshed(String name)
	{
		if (name != null &&
			! getVersionFile(name).setLastModified(System.currentTimeMillis())
		) {
			DebugHome.warn(
				"Could not set the last-refreshed time for name: " + name
			);
		}
	}
	
	/**
	 * Deletes the data file and the version file associated with the given 
	 * name.
	 */
	private void deleteFiles(String name)
	{
		if (name != null &&
			! (getDataFile(name).delete() && getVersionFile(name).delete())
		) {
			DebugHome.warn(
				"Could not delete files for name: " + name
			);
		}
	}
	
	/**
	 * Returns the list of existing version files.
	 */
	private String[] listVersionFiles()
	{
		String[] result = new File(directory).list(
			new FilenameFilter()
			{
				public boolean accept(File directory, String name)
				{
					return name.endsWith(suffixVersion);
				}
			}
		);

		// null if the pathname does not denote a directory, 
		// or if an I/O error occurs.
		if (result == null)
		{
			throw new TechnicalException(
				"Could not read directory: " + directory
			);
		}
		
		return result;
	}
}