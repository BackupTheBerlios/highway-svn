package org.highway.servicetest.application.gereemploye;

import java.io.Serializable;

public class TestFirme implements Serializable{

	
	private String nom;
	private String address;
	private Long id;
	private Telephone telephone;
	
	public Telephone getTelephone() {
		return telephone;
	}

	public void setTelephone(Telephone telephone) {
		this.telephone = telephone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TestFirme(String nom, String address) {
		this.setNom(nom);
		this.setAddress(address);
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
