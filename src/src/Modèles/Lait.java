//********************************************************************
// Lait.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde, Guillaume St-Louis
//
// Modèle de lait
//********************************************************************

package src.Modèles;

import java.util.ResourceBundle;

public class Lait extends ComposanteBreuvage {

	private static double proportionMax = 1;
	private static int quantiteParPortion = 15;
	private double prixParPortion;
	
	public Lait(String nom, String path) {
		super(nom, path);
		prixParPortion = 0.1;
	}

	public Lait(String nom, String path, double prixParPortion) {
		super(nom, path);
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
	
	public String rapport(int portion ,ResourceBundle bdlLangue) {
		return getQuantite(portion) + bdlLangue.getString("ml") + bdlLangue.getString(getNom()) + ":";
	}
	
	public double getPrix(int portion) {
		return portion * prixParPortion;
	}
}
