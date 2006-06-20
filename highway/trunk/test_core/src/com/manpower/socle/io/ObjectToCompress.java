package org.highway.io;

import org.highway.helper.ValueHelper;
import org.highway.validate.Employe;
import org.highway.validate.Human;
import org.highway.vo.Building;
import org.highway.vo.ValueObjectAbstract;

import org.highway.validate.Firme;

public class ObjectToCompress  extends ValueObjectAbstract {

	private Employe employe;
	private Employe employe1;
	private Firme firme;
	private Human human;
	private Building building;
	
	
	public Building getBuilding() {
		return building;
	}
	
	public void setBuilding(Building building) {
		this.building = building;
	}
	
	public Employe getEmploye() {
		return employe;
	}
	
	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
	
	public Employe getEmploye1() {
		return employe1;
	}
	
	public void setEmploye1(Employe employe1) {
		this.employe1 = employe1;
	}
	
	public Firme getFirme() {
		return firme;
	}
	
	public void setFirme(Firme firme) {
		this.firme = firme;
	}
	
	public Human getHuman() {
		return human;
	}
	
	public void setHuman(Human human) {
		this.human = human;
	}
	

	
	
	protected boolean equals2(ValueObjectAbstract obj)
	{
		ObjectToCompress vo = (ObjectToCompress) obj;
		return super.equals2(vo)
			&& ValueHelper.equals(employe, vo.employe)
			&& ValueHelper.equals(employe1, vo.employe1)
			&& ValueHelper.equals(firme, vo.firme)
			&& ValueHelper.equals(building, vo.building)
			&& ValueHelper.equals(human, vo.human);
	}
	
	public int hashCode()
	{
		return super.hashCode()
			+ ValueHelper.hashCode(employe)
			+ ValueHelper.hashCode(employe1)
			+ ValueHelper.hashCode(firme)
			+ ValueHelper.hashCode(building)
			+ ValueHelper.hashCode(human);
	}
}
