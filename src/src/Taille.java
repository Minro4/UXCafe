//********************************************************************
// Taille.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde, Guillaume St-Louis
//
// Modèle de la taille
//********************************************************************

package src;

public class Taille extends ComposanteBreuvage {
	private int capacite, sizeIm;
	private double prix;
	
	public Taille(String nom, int c, double p, String path, int sizeIm) {
		super(nom, path);
		capacite=c;
		prix=p;
		this.sizeIm = sizeIm;
	}
	public int getCapacite() {
		return capacite;
	}
	public double getPrix() {
		return prix;
	}
	public int getSize() {
		return this.sizeIm;
	}
}

