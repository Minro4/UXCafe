package src;

public class Jet {
	
	private String nom;
	private final static double proportion = 0.05f;
	private final static double prixParPortion = 0.1f;
	private final static int taillePortion = 3;
	
	
	

	public Jet(String nom) {
		super();
		this.nom = nom;
	}

	public static double getQuantite(double ratio,int tailleVerre) {
		return ratio*tailleVerre*proportion;
	}
	public static double getPrix(double ratio, int tailleVerre) {
		int nbrPortion = (int) Math.ceil(getQuantite(ratio,tailleVerre)/taillePortion);
		return nbrPortion * prixParPortion;
	}

	public String getNom() {
		return nom;
	}

	public static double getProportion() {
		return proportion;
	}

	public static double getPrixparportion() {
		return prixParPortion;
	}

	public static int getTailleportion() {
		return taillePortion;
	}	
}
