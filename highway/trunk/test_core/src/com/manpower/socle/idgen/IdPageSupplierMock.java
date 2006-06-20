package org.highway.idgen;

import java.util.HashMap;
import java.util.Map;

import org.highway.idgen.IdPage;
import org.highway.idgen.IdPageSupplier;

/**
 * @author David Attias
 */
public class IdPageSupplierMock implements IdPageSupplier
{
	private Map map = new HashMap();
	
	public IdPage getNextPage(int length, String counterName)
	{
		Integer next = (Integer) map.get(counterName);
		if (next == null) next = new Integer(0);
		map.put(counterName, new Integer(next.intValue() + length));
		return new IdPage(next.intValue(), next.intValue() + length - 1);
	}
}
