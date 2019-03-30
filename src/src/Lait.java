package src;

import java.awt.Image;

public class Lait extends Ingredient {

	private double proportionMax;
	private int quantiteParPortion;
	private double prixParPortion;
	
	public Lait(Image image, String nom) {
		super(image, nom);
		proportionMax = 0.5;
		quantiteParPortion = 15;
		prixParPortion = 0.1;
	}

	public Lait(Image image, String nom, double proportionMax, int quantiteParPortion, double prixParPortion) {
		super(image, nom);
		this.proportionMax = proportionMax;
		this.quantiteParPortion = quantiteParPortion;
		this.prixParPortion = prixParPortion;
	}
	
	public boolean valide(int nbrPortion, int quantiteCafe) {
		int quantite = nbrPortion * quantiteParPortion;
		int maxQuantite = (int) (proportionMax * quantiteCafe);
		return (quantite <= maxQuantite && quantite >= 0);
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
