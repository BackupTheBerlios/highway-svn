package com.manpower.servicetest.application.gereemploye;

import java.io.Serializable;

import com.manpower.servicetest.access.employe.EmployeSexe;

public class TestEmploye  implements Serializable{

	
	private String  nom;
	private int age;
	private TestFirme firmeTest;
	private EmployeSexe sexe;
	private String status;
	private Telephone telephone;
	private Projet projet;
	private boolean paye ;
	private Long firmeId;
	private byte[] arrayByte ;
	
	public byte[] getArrayByte() {
		return arrayByte;
	}
	

	public void setArrayByte(byte[] arrayByte) {
		this.arrayByte = arrayByte;
	}

	public boolean isPaye() {
		return paye;
	}

	public void setPaye(boolean paye) {
		this.paye = paye;
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	public Telephone getTelephone() {
		return telephone;
	}

	public void setTelephone(Telephone telephone) {
		this.telephone = telephone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TestEmploye(String nom , int age ){
		this.setNom(nom);
		this.setAge(age);
		
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public TestFirme getFirmeTest() {
		return firmeTest;
	}
	
	public void setFirmeTest(TestFirme firmeTest) {
		this.firmeTest = firmeTest;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public EmployeSexe getSexe() {
		return sexe;
	}
	
	public void setSexe(EmployeSexe sexe) {
		this.sexe = sexe;
	}


	public Long getFirmeId() {
		return firmeId;
	}
	


	public void setFirmeId(Long firmeId) {
		this.firmeId = firmeId;
	}
	
	
	
	
}
