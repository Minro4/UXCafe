package src;

import java.awt.Image;

public abstract class Ingredient {
	
	private String image; //jsp si cest ca que vs voulez
	private String nom;

	
	public Ingredient(String image, String nom) {
		super();
		this.image = image;
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public String getImage() {
		return image;
	}
}
