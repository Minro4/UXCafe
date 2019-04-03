//********************************************************************
// ComposanteCafe.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde, Guillaume St-Louis
//
// Composante formant un café
//********************************************************************
package src;


public class ComposanteCafe {
	

	private String path; //jsp si cest ca que vs voulez
	private String nom;

	
	public ComposanteCafe(String nom,String path) {
	
		super();
		this.path = path;
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public String getPath() {
		return path;

	}
}
