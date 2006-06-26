package org.highway.io;

import java.util.Date;

import junit.framework.TestCase;

import org.highway.io.StandardZipDeserializer;
import org.highway.io.StandardZipSerializer;

import org.highway.validate.Firme;

public class StandardZipSerializerTest extends TestCase {


	public void testWriteObject(){
		try {
			String s1= "s1";
			StandardZipSerializer standardZipSerializer = new StandardZipSerializer();
			standardZipSerializer.write(s1);
			byte[] bytes =  standardZipSerializer.toByteArray();
			StandardZipDeserializer standardZipDeserializer = new StandardZipDeserializer(bytes);
			String s2 = (String) standardZipDeserializer.readObject();
			assertTrue(s1.equals(s2) );
		} catch (Exception e) {
			fail("exception lauch "+ e);
		}
	}

	
	public void testWriteBoolean(){
		try {
			boolean b = true;
			StandardZipSerializer standardZipSerializer = new StandardZipSerializer();
			standardZipSerializer.write(b);
			byte[] bytes =  standardZipSerializer.toByteArray();
			StandardZipDeserializer standardZipDeserializer = new StandardZipDeserializer(bytes);
			boolean b2 =  standardZipDeserializer.readBoolean();
			assertTrue(b2);
		} catch (Exception e) {
			fail("exception lauch "+ e);
		}
	}
	

	public void testWriteChar(){
		try {
			char c = 'c';
			StandardZipSerializer standardZipSerializer = new StandardZipSerializer();
			standardZipSerializer.write(c);
			byte[] bytes =  standardZipSerializer.toByteArray();
			StandardZipDeserializer standardZipDeserializer = new StandardZipDeserializer(bytes);
			char c2 =  standardZipDeserializer.readChar();
			assertTrue(c == c2);
		} catch (Exception e) {
			fail("exception lauch "+ e);
		}
	}

	public void testWriteDouble(){
		try {
			double d = 25;
			StandardZipSerializer standardZipSerializer = new StandardZipSerializer();
			standardZipSerializer.write(d);
			byte[] bytes =  standardZipSerializer.toByteArray();
			StandardZipDeserializer standardZipDeserializer = new StandardZipDeserializer(bytes);
			double d2 =  standardZipDeserializer.readDouble();
			assertTrue(d == d2);
		} catch (Exception e) {
			fail("exception lauch "+ e);
		}
	}

	public void testWriteFloat(){
		try {
			float f = 250;
			StandardZipSerializer standardZipSerializer = new StandardZipSerializer();
			standardZipSerializer.write(f);
			byte[] bytes =  standardZipSerializer.toByteArray();
			StandardZipDeserializer standardZipDeserializer = new StandardZipDeserializer(bytes);
			float f2 =  standardZipDeserializer.readFloat();
			assertTrue(f == f2);
		} catch (Exception e) {
			fail("exception lauch "+ e);
		}
	}
	

	public void testWriteInt(){
		try {
			int i = 250;
			StandardZipSerializer standardZipSerializer = new StandardZipSerializer();
			standardZipSerializer.write(i);
			byte[] bytes =  standardZipSerializer.toByteArray();
			StandardZipDeserializer standardZipDeserializer = new StandardZipDeserializer(bytes);
			int i2 =  standardZipDeserializer.readInt();
			assertTrue(i == i2);
		} catch (Exception e) {
			fail("exception lauch "+ e);
		}
	}

	public void testWriteLong(){
		try {
			long l = 250;
			StandardZipSerializer standardZipSerializer = new StandardZipSerializer();
			standardZipSerializer.write(l);
			byte[] bytes =  standardZipSerializer.toByteArray();
			StandardZipDeserializer standardZipDeserializer = new StandardZipDeserializer(bytes);
			long l2 =  standardZipDeserializer.readLong();
			assertTrue(l == l2);
		} catch (Exception e) {
			fail("exception lauch "+ e);
		}
	}
	
	public void testWriteManyObjets(){
		try {
			int i = 250;
			String s = "s1";
			long l = 12311111;
			StandardZipSerializer standardZipSerializer = new StandardZipSerializer();
			standardZipSerializer.write(i);
			standardZipSerializer.write(s);
			standardZipSerializer.write(l);
			byte[] bytes =  standardZipSerializer.toByteArray();
			StandardZipDeserializer standardZipDeserializer = new StandardZipDeserializer(bytes);
			int i2 =  standardZipDeserializer.readInt();
			String s2 = (String)standardZipDeserializer.readObject();
			long l2 = standardZipDeserializer.readLong();
			assertTrue(i == i2);
			assertTrue(l == l2);
			assertTrue(s.equals(s2));
		} catch (Exception e) {
			fail("exception lauch "+ e);
		}
	}

	public void testWriteManyObjetsException(){
		try {
			int i = 250;
			String s = "s1";
			long l = 12311111;
			StandardZipSerializer standardZipSerializer = new StandardZipSerializer();
			standardZipSerializer.write(i);
			standardZipSerializer.write(s);
			standardZipSerializer.write(l);
			byte[] bytes =  standardZipSerializer.toByteArray();
			StandardZipDeserializer standardZipDeserializer = new StandardZipDeserializer(bytes);
			String s2 = (String)standardZipDeserializer.readObject();
			int i2 =  standardZipDeserializer.readInt();
			long l2 = standardZipDeserializer.readLong();
			fail("exception not lauch ");
		} catch (Exception e) {
		}
	}
	
	
	public void testWriteBooleanClass(){
		try {
			Boolean b = Boolean.TRUE;
			StandardZipSerializer standardZipSerializer = new StandardZipSerializer();
			standardZipSerializer.write(b, boolean.class);
			byte[] bytes =  standardZipSerializer.toByteArray();
			StandardZipDeserializer standardZipDeserializer = new StandardZipDeserializer(bytes);
			boolean b2 =  standardZipDeserializer.readBoolean();
			assertTrue(b2);
		} catch (Exception e) {
			fail("exception lauch "+ e);
		}
	}

	public void testWriteCharClass(){
		try {
			Character c = new Character('c');
			StandardZipSerializer standardZipSerializer = new StandardZipSerializer();
			standardZipSerializer.write(c, char.class);
			byte[] bytes =  standardZipSerializer.toByteArray();
			StandardZipDeserializer standardZipDeserializer = new StandardZipDeserializer(bytes);
			char c2 =  standardZipDeserializer.readChar();
			assertTrue(c.charValue() == c2);
		} catch (Exception e) {
			fail("exception lauch "+ e);
		}
	}

	public void testWriteDoubleClass(){
		try {
			Double d = new Double( 25);
			StandardZipSerializer standardZipSerializer = new StandardZipSerializer();
			standardZipSerializer.write(d, double.class);
			byte[] bytes =  standardZipSerializer.toByteArray();
			StandardZipDeserializer standardZipDeserializer = new StandardZipDeserializer(bytes);
			double d2 =  standardZipDeserializer.readDouble();
			assertTrue(d.doubleValue() == d2);
		} catch (Exception e) {
			fail("exception lauch "+ e);
		}
	}

	public void testWriteFloatClass(){
		try {
			Float f = new Float(250);
			StandardZipSerializer standardZipSerializer = new StandardZipSerializer();
			standardZipSerializer.write(f, float.class);
			byte[] bytes =  standardZipSerializer.toByteArray();
			StandardZipDeserializer standardZipDeserializer = new StandardZipDeserializer(bytes);
			float f2 =  standardZipDeserializer.readFloat();
			assertTrue(f.floatValue() == f2);
		} catch (Exception e) {
			fail("exception lauch "+ e);
		}
	}
	

	public void testWriteIntClass(){
		try {
			Integer i = new Integer(250);
			StandardZipSerializer standardZipSerializer = new StandardZipSerializer();
			standardZipSerializer.write(i, int.class);
			byte[] bytes =  standardZipSerializer.toByteArray();
			StandardZipDeserializer standardZipDeserializer = new StandardZipDeserializer(bytes);
			int i2 =  standardZipDeserializer.readInt();
			assertTrue(i.intValue() == i2);
		} catch (Exception e) {
			fail("exception lauch "+ e);
		}
	}

	public void testWriteLongClass(){
		try {
			Long l = new Long(250);
			StandardZipSerializer standardZipSerializer = new StandardZipSerializer();
			standardZipSerializer.write(l, long.class);
			byte[] bytes =  standardZipSerializer.toByteArray();
			StandardZipDeserializer standardZipDeserializer = new StandardZipDeserializer(bytes);
			long l2 =  standardZipDeserializer.readLong();
			assertTrue(l.longValue() == l2);
		} catch (Exception e) {
			fail("exception lauch "+ e);
		}
	}
	
	public void testCompressComplexObject(){
		try{
			Employe employeDirecteur = new Employe();
			employeDirecteur.setId(new Long(1));
			employeDirecteur.setNew(true);
			employeDirecteur.setNom("monsieur ");
			employeDirecteur.setPrenom("directeur");
	
			Firme firme = new Firme();
			firme.setDirecteur(employeDirecteur);
			firme.setNom("the firme");
			
			
			Employe employe1 = new Employe();
			employe1.setId(new Long(1));
			employe1.setNew(true);
			employe1.setNom("test");
			employe1.setPrenom("retest");
			employe1.setFirme(firme);
			
	
			Building building = new Building();
			building.setFloorNumber(3);
			building.setName("le touzet");
			building.setConstructionDate(new Date());
			
			Human human = new Human();
			human.setEmailAddress("titi@toto.com");
			human.setFirstName("laurent");
			human.setLastName("pipo");
			
			ObjectToCompress objectToCompress = new ObjectToCompress();
			objectToCompress.setEmploye(employe1);
			objectToCompress.setEmploye1(employeDirecteur);
			objectToCompress.setBuilding(building);
			objectToCompress.setFirme(firme);
			objectToCompress.setHuman(human);
	
			StandardZipSerializer standardZipSerializer = new StandardZipSerializer();
			standardZipSerializer.write(objectToCompress, ObjectToCompress.class);
			byte[] bytes =  standardZipSerializer.toByteArray();
			StandardZipDeserializer standardZipDeserializer = new StandardZipDeserializer(bytes);
			ObjectToCompress objectToDecompress  = (ObjectToCompress) standardZipDeserializer.readObject();
			assertFalse(objectToCompress == objectToDecompress );
			assertTrue( objectToCompress.equals(objectToDecompress));
	
	} catch (Exception e) {
		fail("exception lauch "+ e);
	}
		
		
	}

}
