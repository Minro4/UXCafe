//********************************************************************
// Jet.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde, Guillaume St-Louis
//
// Modèle de jet
//********************************************************************

package src.Modèles;

import java.text.DecimalFormat;

public class Jet extends ComposanteBreuvage{
	
	private final static double proportion = 0.05f;
	private final static double prixParPortion = 0.1f;
	private final static int taillePortion = 3;
	
	public Jet(String nom,String image) {
		super(nom, image);

	}

	public static double getQuantite(double ratio,int tailleVerre) {
		return ratio*tailleVerre*proportion;
	}
	public static double getPrix(double ratio, int tailleVerre) {
		int nbrPortion = (int) Math.ceil(getQuantite(ratio,tailleVerre)/taillePortion);
		return nbrPortion * prixParPortion;
	}
	public static double getProportion() {
		return proportion;
	}
	
	public String rapport(double ratio,int tailleCapacite) {
		DecimalFormat df = new DecimalFormat("#.0"); 
		return df.format(Jet.getQuantite(ratio, tailleCapacite)) + " ml sirop " + getNom() + ":";	
	}
	
}
