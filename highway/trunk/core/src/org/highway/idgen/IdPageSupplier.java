/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.idgen;


/**
 * Provides {@link IdPageBasedGenerator} a way to create unique {@link IdPage}s
 *
 * 
 */
public interface IdPageSupplier
{
	IdPage getNextPage(int length, String counterName);
}
