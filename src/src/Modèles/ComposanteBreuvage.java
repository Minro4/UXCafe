//********************************************************************
// ComposanteCafe.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde, Guillaume St-Louis
//
// Composante formant un café
//********************************************************************
package src.Modèles;


public class ComposanteBreuvage {
	

	private String path; //jsp si cest ca que vs voulez
	private String nomKey;

	
	public ComposanteBreuvage(String nom,String path) {
	
		super();
		this.path = path;
		this.nomKey = nom;
	}

	public String getNom() {
		return nomKey;
	}

	public String getPath() {
		return path;

	}
}
