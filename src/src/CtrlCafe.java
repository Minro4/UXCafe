package src;

import java.util.ArrayList;

import com.sun.tracing.dtrace.ProviderAttributes;

public class CtrlCafe {

	private ArrayList<Taille> tailleList;
	private ArrayList<String> torefList;
	private ArrayList<Jet> jetList;
	
	public CtrlCafe() {
		
		//Création de l'array de tailles
		tailleList.add(new Taille("Très petit", 250, 1.55));
		tailleList.add(new Taille("Petit", 250, 1.75));
		tailleList.add(new Taille("Moyen", 250, 1.95));
		tailleList.add(new Taille("Grand", 250, 2.15));
		tailleList.add(new Taille("Très Grand", 250, 2.35));
		//Création de l'array de torréfactions
		torefList.add("Légère");
		torefList.add("Régulation");
		torefList.add("Foncée");
		//Création de la liste de jets
		jetList.add("Moka");
		jetList.add("Caramel");
		jetList.add("Vanille");
		jetList.add("Noisette");
		jetList.add("Menthe poivrée");
		jetList.add("Framboise");
		
		
		
		
		
		
		
	}
	
	
}
