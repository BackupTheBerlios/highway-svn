package org.highway.validate;

import org.highway.bean.ValueObjectAbstract;
import org.highway.helper.ValueHelper;

/**
 * 
 */
public class Firme extends ValueObjectAbstract
{
	private String nom;

	private Employe directeur;

	public Employe getDirecteur()
	{
		return directeur;
	}

	public void setDirecteur(Employe directeur)
	{
		this.directeur = directeur;
	}

	public void setNom(String value)
	{
		nom = value;
	}

	public String getNom()
	{
		return nom;
	}
	
	protected boolean equals2(ValueObjectAbstract obj)
	{
		Firme vo = (Firme) obj;
		return super.equals2(vo)
			&& ValueHelper.equals(nom, vo.nom)
			&& ValueHelper.equals(directeur, vo.directeur);
	}
	public int hashCode()
	{
		return super.hashCode()
			+ ValueHelper.hashCode(directeur)
			+ ValueHelper.hashCode(nom);
	}
	
}
