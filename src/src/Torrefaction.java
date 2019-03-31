package src;

import com.sun.org.apache.bcel.internal.generic.ReturnaddressType;

public class Torrefaction extends Ingredient{
	private String nom, path;
	
	public Torrefaction(String nom, String path) {
		super(nom,  path);
		this.nom = nom;
		this.path = path;
	}
	
	public String getNom() {
		return nom;
	}
	public String getPath() {
		return path;
	}
}
