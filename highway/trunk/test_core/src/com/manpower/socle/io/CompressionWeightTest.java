package org.highway.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import junit.framework.TestCase;

import org.highway.io.StandardZipSerializer;

import org.highway.validate.Firme;

public class CompressionWeightTest extends TestCase {

	
	public void testweight(){
		
		
		try {

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
			

			byte[] byt = "logo".getBytes();
			byte[][]  arrayByte = new byte[1][1];
			arrayByte[0] = byt;
			Building building = new Building();
			building.setFloorNumber(3);
			building.setFirmeLogos(arrayByte);
			
			Human human = new Human();
			human.setEmailAddress("titi@toto.com");
			human.setFirstName("laurent");
			human.setLastName("pîpo");
			
			ObjectToCompress objectToCompress = new ObjectToCompress();
			objectToCompress.setEmploye(employe1);
			objectToCompress.setEmploye1(employeDirecteur);
			objectToCompress.setBuilding(building);
			objectToCompress.setFirme(firme);
			objectToCompress.setHuman(human);
			
			
			
			StandardZipSerializer standardZipSerializer = new StandardZipSerializer();
			standardZipSerializer.write(objectToCompress);
			byte[] bytes =  standardZipSerializer.toByteArray();
			int i = bytes.length;
			
			
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			ObjectOutputStream objectStream = new ObjectOutputStream(buffer);
			objectStream.writeObject(objectToCompress);
			objectStream.flush();
			objectStream.close();
			buffer.close();
			byte[] data = buffer.toByteArray();
			int j = data.length;
			assertTrue(i<j*0.6);
			
		} catch (IOException e) {
			fail();
		}
		
		
		
	}
	
	
}
