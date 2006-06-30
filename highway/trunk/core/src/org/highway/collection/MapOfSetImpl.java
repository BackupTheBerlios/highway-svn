/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.collection;

import java.util.Collection;
import java.util.HashSet;

/**
 * 
 */
public class MapOfSetImpl extends MapOfCollectionImpl implements MapOfSet
{
	protected Collection createCollection()
	{
		return new HashSet();
	}
}
