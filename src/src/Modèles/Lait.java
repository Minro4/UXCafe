//********************************************************************
// Lait.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde, Guillaume St-Louis
//
// Modèle de lait
//********************************************************************

package src.Modèles;

public class Lait extends ComposanteBreuvage {

	private double proportionMax;
	private int quantiteParPortion;
	private double prixParPortion;
	
	public Lait(String nom, String path) {
		super(nom, path);
		proportionMax = 1;
		quantiteParPortion = 15;
		prixParPortion = 0.1;
	}

	public Lait(String nom, String path, double proportionMax, int quantiteParPortion, double prixParPortion) {
		super(nom, path);
		this.proportionMax = proportionMax;
		this.quantiteParPortion = quantiteParPortion;
		this.prixParPortion = prixParPortion;
	}
	
	public boolean valide(int nbrPortion, int quantiteCafe) {
		int quantite = nbrPortion * quantiteParPortion;
		int maxQuantite = (int) (proportionMax * quantiteCafe-quantiteParPortion);
		return (quantite <= maxQuantite && quantite >= 0);
	}
	
	public int getNbrPortionMax(int quantiteCafe) {
		return (int) (proportionMax * quantiteCafe / quantiteParPortion);
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
}
