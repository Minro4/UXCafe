package src;

public class Taille {
	private String nom;
	private int capacite;
	private double prix;
	
	public Taille(String n, int c, double p) {
		nom=n;
		capacite=c;
		prix=p;
	}
	public String getNom() {
		return nom;
	}
	public int getCapacite() {
		return capacite;
	}
	public double getPrix() {
		return prix;
	}
}
