package src;

import java.awt.Image;

public class Jet extends Ingredient{
	
	private final static double proportion = 0.05f;
	private final static double prixParPortion = 0.1f;
	private final static int taillePortion = 3;
	

	public Jet(String image, String nom) {
		super(image, nom);
	}

	public static double getQuantite(double ratio,int tailleVerre) {
		return ratio*tailleVerre*proportion;
	}
	public static double getPrix(double ratio, int tailleVerre) {
		int nbrPortion = (int) Math.ceil(getQuantite(ratio,tailleVerre)/taillePortion);
		return nbrPortion * prixParPortion;
	}
	public static int getTailleportion() {
		return taillePortion;
	}
	
	public String rapport(double ratio,int tailleCapacite) {
		return Jet.getQuantite(ratio, tailleCapacite) + " ml sirop " + getNom();	
	}
	
}
