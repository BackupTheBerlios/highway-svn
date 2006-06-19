package com.manpower.socle.collection;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.highway.collection.MapOfMapImpl;


/**
 * @author David Attias
 */
public class MapOfMapImplTest extends TestCase
{
	public void test()
	{
		MapOfMapImpl europe = new MapOfMapImpl();
		
		europe.put("france", "haut de seine", "meudon");
		assertTrue(europe.containsKey("france"));
		assertTrue(europe.containsKey("france", "haut de seine"));
		assertEquals(europe.get("france", "haut de seine"), "meudon");
		
		Map france = new HashMap();
		france.put("paris", "paris");
		france.put("ain", "pont de vaux");
		france.put("haut de seine", "clamart");
		europe.putAll("france", france);
		assertTrue(europe.containsKey("france", "paris"));
		assertTrue(europe.containsKey("france", "ain"));
		assertTrue(europe.containsKey("france", "haut de seine"));
		assertEquals(europe.get("france", "paris"), "paris");
		assertEquals(europe.get("france", "ain"), "pont de vaux");
		assertEquals(europe.get("france", "haut de seine"), "clamart");
		
		europe.remove("france", "ain");
		europe.putAll("grosland", france);
		assertTrue(europe.containsKey("france", "paris"));
		assertTrue(europe.containsKey("france", "haut de seine"));
		assertEquals(europe.get("france", "paris"), "paris");
		assertEquals(europe.get("france", "haut de seine"), "clamart");
		assertTrue(europe.containsKey("grosland", "paris"));
		assertTrue(europe.containsKey("grosland", "ain"));
		assertTrue(europe.containsKey("grosland", "haut de seine"));
		assertEquals(europe.get("grosland", "paris"), "paris");
		assertEquals(europe.get("grosland", "ain"), "pont de vaux");
		assertEquals(europe.get("grosland", "haut de seine"), "clamart");
	}
}
