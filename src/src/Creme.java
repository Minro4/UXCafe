package src;

import java.awt.Image;

public class Creme extends Ingredient{

	private double proportionMax;
	private int quantiteParPortion;
	private double prixParPortion;
	
	public Creme(Image image, String nom) {
		super(image, nom);
		proportionMax = 0.1f;
		quantiteParPortion = 15;
		prixParPortion = 0.15;
	}
	
	public Creme(Image image, String nom, float proportionMax, int quantiteParPortion, double prixParPortion) {
		super(image, nom);
		this.proportionMax = proportionMax;
		this.quantiteParPortion = quantiteParPortion;
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
		return getQuantite(portion) + " ml de " + getNom();
	}
	
	public double getPrix(int portion) {
		return portion * prixParPortion;
	}

	
}