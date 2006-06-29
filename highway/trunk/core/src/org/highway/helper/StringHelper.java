/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.helper;

import java.util.StringTokenizer;

/**
 * @author David Attias
 */
public abstract class StringHelper
{
	public static enum TrimPolicy
	{
		/**
		 * Remove left space characters trim policy.
		 */
		SPACE_LEFT,

		/**
		 * Constant for the remove right space characters trim policy.
		 */
		SPACE_RIGHT,

		/**
		 * Remove left and right space characters trim policy.
		 */
		SPACE_BOTH,

		/**
		 * Remove all unnecessary space characters trim policy.
		 */
		SPACE_ALL,
	};

	private static String[] COMMON_STRINGS = new String[] {"  ", "saint ",
			"sous "};

	/**
	 * Field REPLACEMENT_FOR_COMMON_STRINGS
	 */
	private static String[] REPLACEMENT_FOR_COMMON_STRINGS = new String[] {" ",
			"st ", "ss "};

	/**
	 * Method isNullOrEmpty
	 * 
	 * @param value String
	 * @return boolean
	 */
	public static boolean isNullOrEmpty(String value)
	{
		return (value == null) || (value.length() == 0);
	}

	/**
	 * Method capitalizeNoAccent
	 * 
	 * @param value String
	 * @return String
	 */
	public static String capitalizeNoAccent(String value)
	{
		if (isNullOrEmpty(value))
		{
			return value;
		}

		return capitalize(toNoAccent(value));
	}

	/**
	 * Method capitalize
	 * 
	 * @param value String
	 * @return String
	 */
	public static String capitalize(String value)
	{
		if (isNullOrEmpty(value))
		{
			return value;
		}

		return value.toUpperCase();
	}

	/**
	 * Method capitalizeFirstCharacter
	 * 
	 * @param value String
	 * @return String
	 */
	public static String capitalizeFirstCharacter(String value)
	{
		if (isNullOrEmpty(value))
		{
			return value;
		}

		char[] chars = value.toCharArray();
		chars[0] = Character.toUpperCase(chars[0]);

		return new String(chars);
	}

	/**
	 * Remove all spaces from the string passed as a parameter
	 * 
	 * @param value string to trim
	 * @return the value without any spaces character
	 */
	public static String removeAllSpaces(String value)
	{
		if (isNullOrEmpty(value))
		{
			return value;
		}

		int i = value.indexOf(' ');

		if (i == -1)
		{
			return value;
		}
		else
		{
			StringTokenizer tokens = new StringTokenizer(value, " ");

			StringBuffer buffer = new StringBuffer();

			while (tokens.hasMoreTokens())
			{
				buffer.append(tokens.nextToken());
			}

			return buffer.toString();
		}
	}

	/**
	 * Remove the unnecessary space characters from the string passed as a
	 * parameter.<br>
	 * <br>
	 * Example : <code>"  aa  aaa  " --> "aa aaa"</code><br>
	 * <br>
	 * Returns <code>null</code> if the parameter is null.
	 * 
	 * @param value string to trim
	 * @return the value without any unnecessary space character
	 */
	public static String trimSpaces(String value)
	{
		if (isNullOrEmpty(value))
		{
			return value;
		}

		int index = 0;
		char current;
		boolean triming = true;
		boolean appending = false;
		StringBuffer buffer = new StringBuffer(value.length());

		while (index < value.length())
		{
			current = value.charAt(index);

			if (current == ' ')
			{
				triming = true;
			}
			else
			{
				if (triming && appending)
				{
					buffer.append(' ');
				}

				buffer.append(current);
				triming = false;
				appending = true;
			}

			++index;
		}

		return buffer.toString();
	}

	/**
	 * Trims the string passed as parameter according to the specified trim
	 * policy. This method as no effect if policy is null or unknown. Returns
	 * <code>null</code> if the value parameter is null.
	 */
	public static String trim(TrimPolicy policy, String value)
	{
		if (policy == null || isNullOrEmpty(value))
		{
			return value;
		}
		if (policy == TrimPolicy.SPACE_LEFT)
		{
			return trimLeftSpaces(value);
		}
		if (policy == TrimPolicy.SPACE_RIGHT)
		{
			return trimRightSpaces(value);
		}
		if (policy == TrimPolicy.SPACE_BOTH)
		{
			return value.trim();
		}
		if (policy == TrimPolicy.SPACE_ALL)
		{
			return trimSpaces(value);
		}
		return value;
	}

	/**
	 * Method toSimpleString
	 * 
	 * @param value String
	 * @return String
	 */
	public static String toSimpleString(String value)
	{
		if (isNullOrEmpty(value))
		{
			return value;
		}

		String newStr = value;
		newStr = newStr.toLowerCase(); // minuscules
		newStr = toNoAccent(newStr);
		newStr = removeNonAlphaCharacters(newStr); // remplacement par des
		// espaces
		newStr = removeCommonPortions(new StringBuffer(newStr)).toString(); // cf.
		// tableau
		// COMMON_STRINGS
		newStr = newStr.trim(); // retrait espaces de debut & fin

		return newStr;
	}

	/**
	 * Method toNoAccent
	 * 
	 * @param value String
	 * @return String
	 */
	public static String toNoAccent(String value)
	{
		if (isNullOrEmpty(value))
		{
			return value;
		}

		char[] chars = value.toCharArray();

		for (int i = 0; i < chars.length; i++)
		{
			chars[i] = toNoAccent(chars[i]);
		}

		return new String(chars);
	}

	/**
	 * Method toNoAccent
	 * 
	 * @param oldChar char
	 * @return char
	 */
	private static char toNoAccent(char oldChar)
	{
		char newChar = Character.toLowerCase(oldChar);

		if ((newChar == 'é') || (newChar == 'è') || (newChar == 'ê')
				|| (newChar == 'ë'))
		{
			newChar = 'e';
		}
		else if ((newChar == 'á') || (newChar == 'à') || (newChar == 'â')
				|| (newChar == 'ä') || (newChar == 'å'))
		{
			newChar = 'a';
		}
		else if ((newChar == 'í') || (newChar == 'ì') || (newChar == 'î')
				|| (newChar == 'ï'))
		{
			newChar = 'i';
		}
		else if ((newChar == 'ó') || (newChar == 'ò') || (newChar == 'ô')
				|| (newChar == 'ö'))
		{
			newChar = 'o';
		}
		else if ((newChar == 'ú') || (newChar == 'ù') || (newChar == 'û')
				|| (newChar == 'ü'))
		{
			newChar = 'u';
		}
		else if (newChar == 'ÿ')
		{
			newChar = 'y';
		}
		else if (newChar == 'ç')
		{
			newChar = 'c';
		}
		else if (newChar == 'ñ')
		{
			newChar = 'n';
		}

		if (Character.isUpperCase(oldChar))
		{
			return Character.toUpperCase(newChar);
		}

		return newChar;
	}

	/**
	 * Method removeNonAlphaCharacters
	 * 
	 * @param value String
	 * @return String
	 */
	private static String removeNonAlphaCharacters(String value)
	{
		if (isNullOrEmpty(value))
		{
			return value;
		}

		char[] chars = value.toCharArray();

		for (int i = 0; i < chars.length; i++)
		{
			char oldChar = chars[i];
			char newChar = oldChar;

			// ajouter ici toutes les regles d'elimination des accents
			if ((oldChar == '-') || (oldChar == '/') || (oldChar == '\\')
					|| (oldChar == '&') || (oldChar == '_') || (oldChar == '+')
					|| (oldChar == '=') || (oldChar == ':') || (oldChar == '.')
					|| (oldChar == ',') || (oldChar == '\'')
					|| (oldChar == '*') || (oldChar == '|') || (oldChar == '(')
					|| (oldChar == ')'))
			{
				newChar = ' ';
			}

			chars[i] = newChar;
		}

		return new String(chars);
	}

	/**
	 * Method removeCommonPortions
	 * 
	 * @param str StringBuffer
	 * @return StringBuffer
	 */
	private static StringBuffer removeCommonPortions(StringBuffer str)
	{
		StringBuffer result = str;

		for (int i = 0; i < COMMON_STRINGS.length; i++)
		{
			for (int j = 0; j < (str.length() - COMMON_STRINGS[i].length() + 1); j++)
			{
				String str1 = COMMON_STRINGS[i];
				String str2 = str.substring(j, j + COMMON_STRINGS[i].length());

				if (str2.equals(str1))
				{
					StringBuffer newStr = new StringBuffer(str.substring(0, j));
					newStr.append(REPLACEMENT_FOR_COMMON_STRINGS[i]);
					newStr.append(str
							.substring(j + str1.length(), str.length()));
					result = removeCommonPortions(newStr);

					return result;
				}
			}
		}

		return result;
	}

	/**
	 * Method removeSuffix
	 * 
	 * @param value String
	 * @param suffix String
	 * @return String
	 */
	public static String removeSuffix(String value, String suffix)
	{
		if (isNullOrEmpty(value))
		{
			return value;
		}

		return value.endsWith(suffix) ? value.substring(0, value.length()
				- suffix.length()) : value;
	}

	/**
	 * Method removePrefix
	 * 
	 * @param value String
	 * @param prefix String
	 * @return String
	 */
	public static String removePrefix(String value, String prefix)
	{
		if (isNullOrEmpty(value))
		{
			return value;
		}

		return value.startsWith(prefix) ? value.substring(prefix.length())
				: value;
	}

	/**
	 * Récupère un token à un index particulier.<br>
	 * A utiliser avec précaution ; ne pas utiliser cette méthode dans une
	 * boucle qui fait varier l'index (dans ce cas utiliser directement un
	 * tokenizer et récupérer les différentes valeurs avec la méthode next())
	 * 
	 * @param givenName String
	 * @param separator String
	 * @param index zero-based index
	 * @return String
	 */
	public static String getToken(String givenName, String separator, int index)
	{
		StringTokenizer tokenizer = new StringTokenizer(givenName, separator);
		int i = 0;

		while (i < index)
		{
			if (tokenizer.hasMoreTokens())
			{
				tokenizer.nextToken();
				i++;
			}
			else
			{
				return null;
			}
		}

		if (tokenizer.hasMoreTokens())
		{
			return tokenizer.nextToken();
		}
		else
		{
			return null;
		}
	}

	/**
	 * Returns the specified object converted to a String object. Returns "null"
	 * if the specified value is null. Use <code>safeToString</code> to get an
	 * empty string when the value is null.
	 * 
	 * @see StringHelper#safeToString(Object)
	 */
	public static final String toString(Object o)
	{
		return "" + o;
	}

	/**
	 * Returns the specified boolean value converted to a String object.
	 */
	public static final String toString(boolean b)
	{
		return "" + b;
	}

	/**
	 * Returns the specified float value converted to a String object.
	 */
	public static final String toString(float f)
	{
		return "" + f;
	}

	/**
	 * Returns the specified double value converted to a String object.
	 */
	public static final String toString(double d)
	{
		return "" + d;
	}

	/**
	 * Returns the specified char value converted to a String object.
	 */
	public static final String toString(char c)
	{
		return "" + c;
	}

	/**
	 * Returns the specified short value converted to a String object.
	 */
	public static final String toString(short s)
	{
		return "" + s;
	}

	/**
	 * Returns the specified byte value converted to a String object.
	 */
	public static final String toString(byte b)
	{
		return "" + b;
	}

	/**
	 * Returns the specified long value converted to a String object.
	 */
	public static final String toString(long l)
	{
		return "" + l;
	}

	/**
	 * Returns the specified int value converted to a String object.
	 */
	public static final String toString(int i)
	{
		return "" + i;
	}

	/**
	 * Returns an empty String if the specified value is null. Returns the
	 * specified value otherwise.
	 * 
	 * @deprecated use safeToString
	 * @since 1.4.2
	 */
	public static String getEmptyIfNull(String value)
	{
		return safeToStringOrEmpty(value);
	}

	/**
	 * Returns what the specified value <code>toString</code> method returns.
	 * Returns null if the specified value is null.
	 * 
	 * @since 1.4.6
	 */
	public static String safeToString(Object value)
	{
		if (value == null) return null;
		return value.toString();
	}

	/**
	 * Returns what the specified value <code>toString</code> method returns
	 * but null. Returns the empty string if the specified value is null or if
	 * its <code>toString</code> method returns null.
	 * 
	 * @since 1.4.6
	 */
	public static String safeToStringOrEmpty(Object value)
	{
		if (value == null) return "";
		String string = value.toString();
		return string == null ? "" : string;
	}

	/**
	 * Returns what the specified value <code>toString</code> method returns
	 * but the empty string. Returns null if the specified value is null or if
	 * its <code>toString</code> method returns the empty string.
	 * 
	 * @since 1.4.6
	 */
	public static String safeToStringOrNull(Object value)
	{
		if (value == null) return null;
		String string = value.toString();
		return isNullOrEmpty(string) ? null : string;
	}

	/**
	 * Determines if the two specified strings are equals. Null and the empty
	 * string ARE NOT considered equals.
	 * 
	 * @since 1.4.6
	 */
	public static boolean safeEquals(String value1, String value2)
	{
		return ValueHelper.equals(value1, value2);
	}

	/**
	 * Determines if the two specified strings are equals ignoring the case.
	 * Null and the empty string ARE NOT considered equals.
	 * 
	 * @since 1.4.6
	 */
	public static boolean safeEqualsIgnoreCase(String value1, String value2)
	{
		if (value1 == null || value2 == null)
		{
			return value1 == value2;
		}

		return value1.equalsIgnoreCase(value2);
	}

	/**
	 * Determines if the two specified strings are equals ignoring the empty
	 * string. Null and the empty string ARE considered equals.
	 * 
	 * @since 1.4.6
	 */
	public static boolean safeEqualsIgnoreEmpty(String value1, String value2)
	{
		if (isNullOrEmpty(value1) || isNullOrEmpty(value2))
		{
			return safeToStringOrNull(value1) == safeToStringOrNull(value2);
		}

		return value1.equals(value2);
	}

	/**
	 * Determines if the two specified strings are equals ignoring the case and
	 * the empty string. Null and the empty string ARE considered equals.
	 * 
	 * @since 1.4.6
	 */
	public static boolean safeEqualsIgnoreCaseAndEmpty(String value1,
			String value2)
	{
		if (isNullOrEmpty(value1) || isNullOrEmpty(value2))
		{
			return safeToStringOrNull(value1) == safeToStringOrNull(value2);
		}

		return value1.equalsIgnoreCase(value2);
	}

	/**
	 * Remove the left space characters from the string passed as a parameter.
	 * 
	 * @param value string to trim
	 * @return the left-trimmed value
	 * @since 1.4.6
	 */
	private static String trimLeftSpaces(String value)
	{
		if (isNullOrEmpty(value))
		{
			return value;
		}

		for (int i = 0; i < value.length(); i++)
		{
			if (value.charAt(i) != ' ')
			{
				return value.substring(i, value.length());
			}
		}

		return "";
	}

	/**
	 * Remove the right space characters from the string passed as a parameter.
	 * 
	 * @param value string to trim
	 * @return the right-trimmed value
	 * @since 1.4.6
	 */
	private static String trimRightSpaces(String value)
	{
		if (isNullOrEmpty(value))
		{
			return value;
		}

		for (int i = value.length(); i > 0; i--)
		{
			if (value.charAt(i - 1) != ' ')
			{
				return value.substring(0, i);
			}
		}

		return "";
	}
}
