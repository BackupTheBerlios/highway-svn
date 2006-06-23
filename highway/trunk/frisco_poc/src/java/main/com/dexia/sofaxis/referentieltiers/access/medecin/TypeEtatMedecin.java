package com.dexia.sofaxis.referentieltiers.access.medecin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.highway.exception.TechnicalException;


/**
 * Cette classe permet la gestion des types des �tats du m�decin
 */
public final class TypeEtatMedecin implements Serializable {
	private static final long serialVersionUID = 1L;

	/** Le code */
	private String code;

	/** Le libell� */
	private String libelle;

	/** Une collection de Transition */
	private List etatsSuivants = Collections.EMPTY_LIST;

	/** Liste contenant tous les objets */
	private static List liste = new ArrayList();

	/** Map contenant tous les objets par code */
	private final static Map map = new HashMap();

	/** Les instances de la classe */
	public final static TypeEtatMedecin SANSETAT = new TypeEtatMedecin("SANSETAT", "Sans �tat");	
	public final static TypeEtatMedecin AGREE = new TypeEtatMedecin("AGREE", "Agr��");
	public final static TypeEtatMedecin MANDATE = new TypeEtatMedecin("MANDATE", "Mandat�");
	public final static TypeEtatMedecin NONUTILISABLE = new TypeEtatMedecin("NONUTILIS", "Non utilisable");
	public final static TypeEtatMedecin NONAGREE = new TypeEtatMedecin("NONAGREE", "Non Agr��");
	public final static TypeEtatMedecin PLUSAGREE = new TypeEtatMedecin("PLUSAGREE", "Plus Agr��");
	/** Le type AUTRE sert lorsqu'on veut saisir un �tat qui ne se trouve pas dans liste d�finie ci dessus. */
	public final static TypeEtatMedecin AUTRE = new TypeEtatMedecin("AUTRE", "Autre");

	/**
	 * Une petite classe contenant pour une transition donn�e, un message d'avertissement
	 */
	private static class Transition implements Serializable {
		public final TypeEtatMedecin ETATARRIVEE;

		public final String MESSAGE;

		public Transition(TypeEtatMedecin _etatArrivee, String _message) {
			ETATARRIVEE = _etatArrivee;
			MESSAGE = _message;
		}
	}

	static {
		// Initialisation des transitions entre �tats
		String MESSAGE_PLUS_AGREE = "Attention! une fois le m�decin pass� � l'�tat plus agr��, son �tat n'est plus modifiable";

		List temp = new ArrayList();
		//temp.add(new Transition(SANSETAT, null));		
		temp.add(new Transition(AGREE, null));
		temp.add(new Transition(NONAGREE, null));
		SANSETAT.setEtatsSuivants(temp);
		
		temp = new ArrayList();		
		temp.add(new Transition(NONAGREE, null));		
		temp.add(new Transition(AGREE, null));
		temp.add(new Transition(NONUTILISABLE, null));
		NONAGREE.setEtatsSuivants(temp);

		temp = new ArrayList();
		temp.add(new Transition(AGREE, null));
		temp.add(new Transition(MANDATE, null));
		temp.add(new Transition(PLUSAGREE, MESSAGE_PLUS_AGREE));
		AGREE.setEtatsSuivants(temp);

		temp = new ArrayList();
		temp.add(new Transition(MANDATE, null));
		temp.add(new Transition(NONUTILISABLE, null));
		temp.add(new Transition(PLUSAGREE, MESSAGE_PLUS_AGREE));
		MANDATE.setEtatsSuivants(temp);

		temp = new ArrayList();
		temp.add(new Transition(NONUTILISABLE, null));
		// DDU 16/09/05 : ASB a dit que les 2 transitions ci-dessous n'existent pas
		// temp.add(new Transition(MANDATE, "Attention! le m�decin passe de l'�tat non utilisable � l'�tat mandat�"));
		// temp.add(new Transition(PLUSAGREE, MESSAGE_PLUS_AGREE));
		NONUTILISABLE.setEtatsSuivants(temp);

		temp = new ArrayList();
		temp.add(new Transition(PLUSAGREE, MESSAGE_PLUS_AGREE));
		PLUSAGREE.setEtatsSuivants(temp);

		liste = Collections.unmodifiableList(liste);
	}

	/**
	 * Constructor
	 * 
	 * @param _code le code de ce type d'�tat
	 * @param _libelle le libell� de ce type d'�tat
	 */
	private TypeEtatMedecin(String _code, String _libelle) {
		if (_code == null) {
			throw new IllegalArgumentException("Le code ne peut pas �tre nul");
		}

		if (_libelle == null) {
			throw new IllegalArgumentException("Le libell� ne peut pas �tre nul");
		}

		code = _code;
		libelle = _libelle;

		liste.add(this);
		map.put(code, this);
	}

	/** Affecte � l'�tat une Collection de TypeEtat.Transition */
	private void setEtatsSuivants(List _etatsSuivants) {
		etatsSuivants = _etatsSuivants;
	}

	/** Affecte � l'�tat une Collection de TypeEtat.Transition
	 * @throws MultiFieldException si la transition n'est pas valide */
	public String getMessageEtatSuivant(TypeEtatMedecin etatSuivant) throws TechnicalException {
		for (Iterator it = etatsSuivants.iterator(); it.hasNext();) {
			Transition current = (Transition) it.next();
			if (current.ETATARRIVEE.equals(etatSuivant)) {
				return current.MESSAGE;
			}
		}
		// Si on arrive ici, c'est que la transition n'est pas valide
		TechnicalException mfe = new TechnicalException("Impossible de passer de l'�tat " + getLabel() + " � l'�tat "
				+ etatSuivant.getLabel());
		throw mfe;
	}

	/** Renvoie la liste des TypeEtatMedecin possible � partir de cet �tat */
	public Collection getEtatsSuivants() {
		List retour = new ArrayList(etatsSuivants.size());
		for (Iterator it = etatsSuivants.iterator(); it.hasNext(); ) {
			Transition current = (Transition) it.next();
			retour.add(current.ETATARRIVEE);
		}
		return retour;
	}

	/**
	 * Retourne le code
	 * 
	 * @return retourne le code de type d'�tat
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Retourne le libell�
	 * 
	 * @return le libell� de ce type d'�tat
	 */
	public String getLabel() {
		return libelle;
	}

	/**
	 * Retourne le libell� court
	 * 
	 * @return le libell� court de ce type d'�tat
	 */
	public String getShortLabel() {
		return libelle;
	}

	/**
	 * Retourne la liste
	 * 
	 * @return la liste des �tats possibles pour un m�decin
	 */
	public static List getList() {
		return liste;
	}

	/**
	 * cette m�thode retourne le type d'�tat correspondant au code pass� en param�tre
	 * 
	 * @param _code le code
	 * @return retourne le type d'�tat correspondant au code pass�
	 */
	public static TypeEtatMedecin getTypeEtat(String _code) {
		if (map.containsKey(_code)) {
			return ((TypeEtatMedecin) map.get(_code));
		} else {
			throw new IllegalArgumentException("Le code " + _code + " n'existe pas");
		}
	}

	/**
	 * D�termine si cet objet est �gal � celui pass� en param�tre
	 * 
	 * @return Renvoie true si obj et this sont �quivalents.
	 * @param obj objet que l'on veut comparer � celui-ci
	 */
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (obj == null) {
			return false;
		} else {
			Class currentClass = this.getClass();
			String currentClassName = currentClass.getName();
			Class objClass = obj.getClass();
			String objClassName = objClass.getName();

			if (currentClassName.equals(objClassName)) {
				String c = ((TypeEtatMedecin) obj).code;

				return c.equals(code);
			} else {
				return false;
			}
		}
	}

	/**
	 * Retourne une repr�sentation de ce type de Etat
	 * 
	 * @return une repr�sentation
	 */
	public String toString() {

		Class currentClass = this.getClass();
		String chaine = currentClass.getName() + " : \n" + "code : " + code + "\n libell� : " + libelle;

		return chaine;
	}

	/**
	 * Retourne le hashCode de cet objet
	 * 
	 * @return le hashCode
	 */
	public int hashCode() {
		return code.hashCode();
	}

}
