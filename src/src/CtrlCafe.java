package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;


public class CtrlCafe {
	
	private Cafe cafe;
	
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
<<<<<<< Updated upstream
		jetList.add(new Jet("Images/chocolate.jpg", "Moka"));
		jetList.add(new Jet("Images/caramel.jpg", "Caramel"));
		jetList.add(new Jet("Images/vanilla.jpg", "Vanille"));
		jetList.add(new Jet("Images/hazelnut.jpg", "Noisette"));
		jetList.add(new Jet("Images/menthepoivre.jpg", "Menthe poivrée"));
		jetList.add(new Jet("Images/rasberry.jpg", "Framboise"));
			
=======
		jetList.add(new Jet("Moka","Images/chocolate.png"));
		jetList.add(new Jet("Caramel","Images/caramel.jpg"));
		jetList.add(new Jet("Vanille","Images/vanilla.png"));
		jetList.add(new Jet("Noisette","Images/hazelnut.jpg"));
		jetList.add(new Jet("Menthe poivrée","Images/menthepoivre.png"));
		jetList.add(new Jet("Framboise","Images/raspberry.png"));
		
		
		
>>>>>>> Stashed changes
	}
	
    class ObsAddIng implements ActionListener {
    	Ingredient ing;
    	int nbrPortions; //1 pour add et -1 pour remove
    	JTextField tfPortions;
    	public ObsAddIng(Ingredient ing,JTextField tfPortions, boolean add) {
    		this.ing = ing;
    		this.tfPortions = tfPortions;
    		this.nbrPortions = add?1:-1;
    	}
    	
        @Override
        public void actionPerformed(ActionEvent evt) {
           int prt=cafe.addIngredient(ing, nbrPortions);
           tfPortions.setText(String.valueOf(prt));
        }
    }	
	
}
