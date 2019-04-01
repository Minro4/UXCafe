package src;

public class Taille extends ComposanteCafe {
	private int capacite, sizeIm;
	private double prix;
	
	public Taille(String nom, int c, double p, String path, int sizeIm) {
		super(nom, path);
		capacite=c;
		prix=p;
		this.sizeIm = sizeIm;
	}
	public int getCapacite() {
		return capacite;
	}
	public double getPrix() {
		return prix;
	}
	public int getSize() {
		return this.sizeIm;
	}
}

