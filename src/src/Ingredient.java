package src;

import java.awt.Image;

public abstract class Ingredient {
	
	private Image image; //jsp si cest ca que vs voulez
	private String nom;

	public String getNom() {
		return nom;
	}

	public Image getImage() {
		return image;
	}
}