package src;

import java.util.ArrayList;

public class CtrlCafe {

	private ArrayList<Taille> tailleList;
	private ArrayList<String> torefList;
	
	public CtrlCafe() {
		
		//C�ration de l'array de tailles
		tailleList.add(new Taille("Tr�s petit", 250, 1.55));
		tailleList.add(new Taille("Petit", 250, 1.75));
		tailleList.add(new Taille("Moyen", 250, 1.95));
		tailleList.add(new Taille("Grand", 250, 2.15));
		tailleList.add(new Taille("Tr�s Grand", 250, 2.35));
		//Cr�ation de l'array de torr�factions
		torefList.add("L�g�re");
		torefList.add("R�gulation");
		torefList.add("Fonc�e");
		
		
		
		
		
		
		
	}
	
	
}
