package src;

import java.util.ArrayList;

import com.sun.tracing.dtrace.ProviderAttributes;

public class CtrlCafe {

	private ArrayList<Taille> tailleList;
	private ArrayList<String> torefList;
	private ArrayList<Jet> jetList;
	
	public CtrlCafe() {
		
		//Cr�ation de l'array de tailles
		tailleList.add(new Taille("Tr�s petit", 250, 1.55));
		tailleList.add(new Taille("Petit", 250, 1.75));
		tailleList.add(new Taille("Moyen", 250, 1.95));
		tailleList.add(new Taille("Grand", 250, 2.15));
		tailleList.add(new Taille("Tr�s Grand", 250, 2.35));
		//Cr�ation de l'array de torr�factions
		torefList.add("L�g�re");
		torefList.add("R�gulation");
		torefList.add("Fonc�e");
		//Cr�ation de la liste de jets
		jetList.add("Moka");
		jetList.add("Caramel");
		jetList.add("Vanille");
		jetList.add("Noisette");
		jetList.add("Menthe poivr�e");
		jetList.add("Framboise");
		
		
		
		
		
		
		
	}
	
	
}
