package com.dexia.sofaxis.tools.common;

import java.util.List;

/**
 * Les instances de cette classe sont retournées par les méthodes de recherche 
 * pour indiquer à l'appelant que la liste n'est pas exhaustive. 
 * @param <E> la classe des éléments de la liste. 
 */
public class SearchResult <E> {

	private List<E> result;
	private boolean moreResult;
	
	/**
	 * 
	 * @param result résultat de la recherche.
	 * @param moreResult true si le résultat est exhaustif.
	 */
	public SearchResult(List<E> result, boolean moreResult) {
	  this.result = result;
	  this.moreResult = moreResult;
	}
	
	/**
	 * Retourne la liste des éléments constituant le résultat de la recherche.
	 * @return la liste des éléments constituant le résultat de la recherche.
	 */
	public List<E> getResult() {
	  return result;	
	}
	
	/**
	 * Retourne true si la liste retournée par getResult est exhaustive, false sinon.
	 * @return true si la liste retournée par getResult est exhaustive, false sinon.
	 */
	public boolean hasMoreResult() {
	  return moreResult;
	}
}
