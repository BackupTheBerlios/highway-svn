/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.highway.bean.Identifiable;

/**
 */
public abstract class CollectionHelper
{
	/**
	 * Returns 0 if the specified collection is null or the collection size
	 * if not null.
	 *
	 * @param c the collection to size
	 * @return the size of the collection
	 */
	public static int size(Collection c)
	{
		return (c == null) ? 0 : c.size();
	}

	/**
	 * Indique si une collection est null ou vide.
	 *
	 * @param c
	 * @return boolean
	 */
	public static boolean isNullOrEmpty(Collection c)
	{
		if (c == null)
		{
			return true;
		}

		return c.isEmpty();
	}

	/**
	 * Ajoute un élement à la collection si et seulement si
	 * cet élément est non nul.
	 *
	 * @param c
	 * @param element
	 */
	public static void addIfNotNull(Collection c, Object element)
	{
		if (element != null)
		{
			c.add(element);
		}
	}

	/**
	 * Ajoute les éléments d'une collection à une autre collection
	 * si et seulement si cette collection est non nulle.
	 * @param dest Collection
	 * @param src Collection
	 */
	public static void addAllIfNotNull(Collection dest, Collection src)
	{
		if (src != null)
		{
			dest.addAll(src);
		}
	}

	/**
	 * Method merge
	 * @param dest Collection
	 * @param src Collection
	 * @return Collection
	 */
	public static Collection merge(Collection dest, Collection src)
	{
		if (src == null)
		{
			return dest;
		}

		if (dest == null)
		{
			return src;
		}

		dest.addAll(src);

		return dest;
	}

	/**
	 * Transforme une collection d'objets Identifiable (qui possède un ObjectId)
	 * en une Map dont les clés correspondent aux ObjectId des éléments de
	 * la liste
	 * @param identifiables Collection
	 * @return Map
	 */
	public static Map toMap(Collection identifiables)
	{
		if (identifiables == null)
		{
			return null;
		}

		if (identifiables.isEmpty())
		{
			return new HashMap();
		}

		Map map = new Hashtable(identifiables.size());

		for (Iterator iter = identifiables.iterator(); iter.hasNext();)
		{
			Identifiable identifiable = (Identifiable) iter.next();
			map.put(identifiable.getIdentity(), identifiable);
		}

		return map;
	}

	/**
	  * Retourne la collection des objets passés en argument sous forme de map.<br>
	 * Chaque objet est indexé par la valeur de la propriété spécifiée en paramètre.
	 * @param collection Collection
	 * @param propertyName String
	 * @return Map
	 */
	public static Map toMap(Collection collection, String propertyName)
	{
		if (collection == null)
		{
			return null;
		}

		if (collection.isEmpty())
		{
			return new HashMap();
		}

		Map map = new HashMap(collection.size());

		for (Iterator iter = collection.iterator(); iter.hasNext();)
		{
			Object element = (Object) iter.next();
			Object propertyValue =
				ReflectHelper.getProperty(element, propertyName);
			map.put(propertyValue, element);
		}

		return map;
	}

	/**
	 * Supprime les doublons d'une liste.<br>
	 * <br>
	 * Attention : cette méthode est basée sur le equals des objets de la liste.<br>
	 * Deux objets forment un doublon si equals retourne vrai.
	 *
	 * @param list List
	 */
	public static void removeDuplicates(List list)
	{
		if ((list != null) && ! list.isEmpty())
		{
			Set set = new HashSet(list.size());

			for (Iterator iter = list.iterator(); iter.hasNext();)
			{
				Object element = iter.next();

				if (set.contains(element))
				{
					iter.remove();
				}
				else
				{
					set.add(element);
				}
			}
		}
	}

	/**
	 * Method toObjectIdList
	 * @param identifiableList List
	 * @return List
	 */
	public static List toObjectIdList(List identifiableList)
	{
		if (identifiableList == null)
		{
			return null;
		}

		if (identifiableList.isEmpty())
		{
			return new ArrayList(0);
		}

		List oidList = new ArrayList(identifiableList.size());

		for (Iterator iter = identifiableList.iterator(); iter.hasNext();)
		{
			oidList.add(((Identifiable) iter.next()).getIdentity());
		}

		return oidList;
	}

	/**
	 * Method filterByClass
	 * @param collection Collection
	 * @param filterClass Class
	 * @return Collection
	 */
	public static Collection filterByClass(
		Collection collection, Class filterClass)
	{
		for (Iterator iter = collection.iterator(); iter.hasNext();)
		{
			if (! filterClass.equals(iter.next().getClass()))
			{
				iter.remove();
			}
		}

		return collection;
	}

	/**
	 * Method filterActive
	 * @param collection Collection
	 * @return Collection
	 */
	public static Collection filterActive(Collection collection)
	{
		//		for (Iterator iter = collection.iterator(); iter.hasNext();)
		//		{
		//			Object element = iter.next();
		//			if (element instanceof Inactivable)
		//			{
		//				if (!((Inactivable) element).isActif())
		//				{
		//					iter.remove();
		//				}
		//			}
		//		}
		//		return collection;
		return null;
	}

	/**
	 * Returns an iterator on a collection even null.
	 * @param collection Collection
	 * @return Iterator
	 */
	public static Iterator iterator(Collection collection)
	{
		if (collection == null)
		{
			return Collections.EMPTY_LIST.iterator();
		}
		else
		{
			return collection.iterator();
		}
	}
}
