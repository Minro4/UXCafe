package src;

import java.util.ArrayList;

public class CtrlCafe {

	private ArrayList<Taille> tailleList;
	private ArrayList<String> torefList;
	
	public CtrlCafe() {
		
		//Cération de l'array de tailles
		tailleList.add(new Taille("Très petit", 250, 1.55));
		tailleList.add(new Taille("Petit", 250, 1.75));
		tailleList.add(new Taille("Moyen", 250, 1.95));
		tailleList.add(new Taille("Grand", 250, 2.15));
		tailleList.add(new Taille("Très Grand", 250, 2.35));
		//Création de l'array de torréfactions
		torefList.add("Légère");
		torefList.add("Régulation");
		torefList.add("Foncée");
		
		
		
		
		
		
		
	}
	
	
}
