package src;

import java.awt.Image;

public class Creme extends Ingredient{

	private double proportionMax;
	private int quantiteParPortion;
	private double prixParPortion;
	
<<<<<<< Updated upstream
	public Creme(String image, String nom) {
		super(image, nom);
=======
	public Creme(String nom, String path) {
		super(nom, path);
>>>>>>> Stashed changes
		proportionMax = 0.1f;
		quantiteParPortion = 15;
		prixParPortion = 0.15;
	}
	
<<<<<<< Updated upstream
	public Creme(String image, String nom, float proportionMax, int quantiteParPortion, double prixParPortion) {
		super(image, nom);
=======
	public Creme(String nom, String path, float proportionMax, int quantiteParPortion, double prixParPortion) {
		super(nom , path);
>>>>>>> Stashed changes
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
