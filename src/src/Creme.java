//********************************************************************
// Creme.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde, Guillaume St-Louis
//
// Modèle de creme
//********************************************************************
package src;

public class Creme extends ComposanteBreuvage{

	private static double proportionMax = 0.1f;
	private static int quantiteParPortion = 15;
	private double prixParPortion;
	
	public Creme(String nom, String path) {
		super(nom, path);

		prixParPortion = 0.15;
	}
	

	public Creme(String image, String nom, double prixParPortion) {
		super(image, nom);

		this.prixParPortion = prixParPortion;
	}

	public boolean valide(int nbrPortion, int tailleCapacite) {
		int quantite = nbrPortion * quantiteParPortion;
		int maxQuantite = (int) (proportionMax * tailleCapacite);
		return quantite <= maxQuantite && quantite >= 0; 

	}
	
	public int getQuantite(int portion) {
		return portion*quantiteParPortion;
	}
	
	public String rapport(int portion) {
		return getQuantite(portion) + " ml de " + getNom() + ":";
	}
	
	public double getPrix(int portion) {
		return portion * prixParPortion;
	}
	
	public int getNbrPortionMax(int tailleCapacite) {
		return (int) (proportionMax * tailleCapacite / quantiteParPortion);
	}


	
}
