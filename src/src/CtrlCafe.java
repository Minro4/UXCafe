package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JTextField;

public class CtrlCafe implements PropertyChangeListener {
	
	private Cafe cafe;
	private VueCafe vueCafe;
	
	private ArrayList<Taille> tailleList = new ArrayList<Taille>();
	private ArrayList<Ingredient> torefList = new ArrayList<Ingredient>();
	private ArrayList<Jet> jetList = new ArrayList<Jet>();
	
	public CtrlCafe() {
		
		//Création de l'array de tailles
		tailleList.add(new Taille("Très petit", 250, 1.55));
		tailleList.add(new Taille("Petit", 250, 1.75));
		tailleList.add(new Taille("Moyen", 250, 1.95));
		tailleList.add(new Taille("Grand", 250, 2.15));
		tailleList.add(new Taille("Très Grand", 250, 2.35));
		//Création de l'array de torréfactions
		torefList.add(new Ingredient("Légère","Image/coffeeBean.png"));
		torefList.add(new Ingredient("Normale","Image/CoffeeBean2.png"));
		torefList.add(new Ingredient("Foncée","Image/CoffeeBean3.png"));
		//Création de la liste de jets
		jetList.add(new Jet("Moka","Images/chocolate.jpg"));
		jetList.add(new Jet("Caramel","Images/caramel.jpg"));
		jetList.add(new Jet("Vanille","Images/vanilla.jpg"));
		jetList.add(new Jet("Noisette","Images/hazelnut.jpg"));
		jetList.add(new Jet("Menthe poivrée","Images/menthepoivre.jpg"));
		jetList.add(new Jet("Framboise","Images/rasberry.jpg"));

		cafe = new Cafe(tailleList.get(2), torefList.get(1));
		cafe.addPropertyChangeListener(this);
		
		vueCafe = new VueCafe(cafe, jetList, tailleList, torefList);
		vueCafe.getConfirmationPane().getBtnConfirm().addActionListener(new ConfirmerButtonListener());
		new NavigationManager(vueCafe);

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
           int prt = cafe.addIngredient(ing, nbrPortions);
           tfPortions.setText(String.valueOf(prt));
           updateRapport();
        }
    }

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		//Lait.setText(String.valueOf(evt.getNewValue()));
		
	}	
	
	public void updateRapport() {
		vueCafe.getConfirmationPane().update(cafe.getRapport());
	}
	public class ConfirmerButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {			
			vueCafe.dispose();
		}
	}
	
	
	
}
