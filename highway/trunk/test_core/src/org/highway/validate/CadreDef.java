package org.highway.validate;


/**
 * @author attias
 */
@org.highway.annotation.ValueObject
public interface CadreDef extends EmployeDef
{
	String getNomService();
	
	int getNombrePersonne();
}
