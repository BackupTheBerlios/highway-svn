package org.highway.database.hibernate;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.UserType;

public class ByteArray2BlobHibernateType implements UserType
{
	public int[] sqlTypes()
	{
		return new int[] {Types.BLOB};
	}

	public Class returnedClass()
	{
		return byte[].class;
	}

	public boolean equals(Object x, Object y)
	{
		return (x == y)
				|| (x != null && y != null && java.util.Arrays.equals(
						(byte[]) x, (byte[]) y));
	}

	public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
			throws HibernateException, SQLException
	{
		Blob blob = rs.getBlob(names[0]);
		if (blob == null) return null;
		return blob.getBytes(1, (int) blob.length());
	}

	public void nullSafeSet(PreparedStatement st, Object value, int index)
			throws HibernateException, SQLException
	{
//		st.setBlob(index, Hibernate.createBlob((byte[]) value));
		st.setBytes(index, (byte[]) value);
	}

	public Object deepCopy(Object value)
	{
		if (value == null) return null;
		byte[] bytes = (byte[]) value;
		byte[] result = new byte[bytes.length];
		System.arraycopy(bytes, 0, result, 0, bytes.length);
		return result;
	}

	public boolean isMutable()
	{
		return true;
	}
}