package src;

import java.awt.Image;

public class Sucre extends ComposanteCafe {

	private double prix;
	private double proportionMax;
	
	

	public Sucre(String nom, String path) {
		super(nom, path);
		prix = 0.05f;
		proportionMax = 0.01f;
		
	}
	public Sucre(String image, String nom, double prix, double proportionMax) {
		super(image, nom);
		this.prix = prix;
		this.proportionMax = proportionMax;
	}
	
	public double getPrix() {
		return prix;
	}

	public boolean valide(int portions,int tailleCapacite) {
		return (portions <= (int) (tailleCapacite * proportionMax));
	}
	
	public String rapport(int portion) {
		return portion + " " + getNom() + ((portion > 1)?"s:":":");//Si on a plus de 1 portions, on ajoute un 's'
	}
}
