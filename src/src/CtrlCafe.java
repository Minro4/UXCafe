package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JTextField;

public class CtrlCafe implements PropertyChangeListener {
	
	private Cafe cafe;
	
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
		jetList.add(new Jet("Images/chocolate.jpg", "Moka"));
		jetList.add(new Jet("Images/caramel.jpg", "Caramel"));
		jetList.add(new Jet("Images/vanilla.jpg", "Vanille"));
		jetList.add(new Jet("Images/hazelnut.jpg", "Noisette"));
		jetList.add(new Jet("Images/menthepoivre.jpg", "Menthe poivr�e"));
		jetList.add(new Jet("Images/rasberry.jpg", "Framboise"));
		
		cafe = new Cafe(tailleList.get(2));
		cafe.addPropertyChangeListener(this);
			
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

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		//Lait.setText(String.valueOf(evt.getNewValue()));
		
	}	
	
}
