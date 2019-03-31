package src;

import java.awt.Image;

public abstract class Ingredient {
	
<<<<<<< Updated upstream
	private String image; //jsp si cest ca que vs voulez
	private String nom;

	
	public Ingredient(String image, String nom) {
=======
	private String nom, path;

	
	public Ingredient(String nom, String path) {
>>>>>>> Stashed changes
		super();
		this.path = path;
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

<<<<<<< Updated upstream
	public String getImage() {
		return image;
=======
	public String getPath() {
		return path;
>>>>>>> Stashed changes
	}
}
