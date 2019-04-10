//********************************************************************
// Sucre.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde, Guillaume St-Louis
//
// Modèle du sucre
//********************************************************************

package src.Modèles;


public class Sucre extends ComposanteBreuvage {

	private double prix;
	private double proportionMax;
	
	

	public Sucre(String nom, String path) {
		super(nom, path);
		prix = 0.05f;
		proportionMax = 0.01;
		
	}
	public Sucre(String image, String nom, double prix, double proportionMax) {
		super(image, nom);
		this.prix = prix;
		this.proportionMax = proportionMax;
	}
	
	public double getPrix() {
		return prix;
	}

	public boolean valide(int portions,int tailleCapacite) {
		return (portions <= (int) (tailleCapacite * proportionMax));
	}
	
	public String rapport(int portion) {
		return portion + " " + getNom() + ((portion > 1)?"s:":":");//Si on a plus de 1 portions, on ajoute un 's'
	}
	
	public int getNbrPortionMax(int tailleCapacite) {
		return (int) (proportionMax * tailleCapacite);
	}
}
