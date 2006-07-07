package com.dexia.sofaxis.tools.common;

import java.util.List;

/**
 * Les instances de cette classe sont retourn�es par les m�thodes de recherche 
 * pour indiquer � l'appelant que la liste n'est pas exhaustive. 
 * @param <E> la classe des �l�ments de la liste. 
 */
public class SearchResult <E> {

	private List<E> result;
	private boolean moreResult;
	
	/**
	 * 
	 * @param result r�sultat de la recherche.
	 * @param moreResult true si le r�sultat est exhaustif.
	 */
	public SearchResult(List<E> result, boolean moreResult) {
	  this.result = result;
	  this.moreResult = moreResult;
	}
	
	/**
	 * Retourne la liste des �l�ments constituant le r�sultat de la recherche.
	 * @return la liste des �l�ments constituant le r�sultat de la recherche.
	 */
	public List<E> getResult() {
	  return result;	
	}
	
	/**
	 * Retourne true si la liste retourn�e par getResult est exhaustive, false sinon.
	 * @return true si la liste retourn�e par getResult est exhaustive, false sinon.
	 */
	public boolean hasMoreResult() {
	  return moreResult;
	}
}
