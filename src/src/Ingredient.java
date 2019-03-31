package src;

import java.awt.Image;

public class Ingredient {
	

	private String path; //jsp si cest ca que vs voulez
	private String nom;

	
	public Ingredient( String nom,String path) {
	
		super();
		this.path = path;
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public String getImage() {
		return path;

	}
}
