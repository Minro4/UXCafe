//********************************************************************
// Sucre.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde, Guillaume St-Louis
//
// Modèle du sucre 
//********************************************************************

package src.Modèles;

import java.util.ResourceBundle;

public class Sucre extends ComposanteBreuvage {

	private double prix;
	private static double proportionMax = 0.01;
	private boolean calcUnite;
	

	public Sucre(String nom, String path) {
		super(nom, path);
		prix = 0.05f;
		calcUnite = false;
		
	}
	public Sucre(String nom,String image,  double prix, boolean calcUnite) {
		super(nom,image);
		this.prix = prix;
		this.calcUnite = calcUnite;
	}
	
	public double getPrix(int portions) {
		return calcUnite? prix*portions:prix;
	}

	public boolean valide(int portions,int tailleCapacite) {
		return (portions <= (int) (tailleCapacite * proportionMax));
	}
	
	public String rapport(int portion ,ResourceBundle bdlLangue) {
		return portion + " " + bdlLangue.getString(getNom()) + ":";//Si on a plus de 1 portions, on ajoute un 's'
	}
	
	public int getNbrPortionMax(int tailleCapacite) {
		return (int) (proportionMax * tailleCapacite);
	}
}
