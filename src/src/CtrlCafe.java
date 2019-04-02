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
	private ArrayList<ComposanteCafe> torefList = new ArrayList<ComposanteCafe>();
	private ArrayList<Jet> jetList = new ArrayList<Jet>();
	private ArrayList<ComposanteCafe> lcsList = new ArrayList<ComposanteCafe>();

	public CtrlCafe() {

		// Création de l'array de tailles
		tailleList.add(new Taille("Très petit", 250, 1.55, "Image/coffee.png", 25));
		tailleList.add(new Taille("Petit", 250, 1.75, "Image/coffee.png", 30));
		tailleList.add(new Taille("Moyen", 250, 1.95, "Image/coffee.png", 35));
		tailleList.add(new Taille("Grand", 250, 2.15, "Image/coffee.png", 40));
		tailleList.add(new Taille("Très Grand", 250, 2.35, "Image/coffee.png", 45));
		// Création de l'array de torréfactions
		torefList.add(new ComposanteCafe("Légère", "Image/coffeeBean.png"));
		torefList.add(new ComposanteCafe("Normale", "Image/CoffeeBean2.png"));
		torefList.add(new ComposanteCafe("Foncée", "Image/CoffeeBean3.png"));
		// Création de la liste de jets
		jetList.add(new Jet("Moka", "Images/chocolate.png"));
		jetList.add(new Jet("Caramel", "Images/caramel.jpg"));
		jetList.add(new Jet("Vanille", "Images/vanilla.png"));
		jetList.add(new Jet("Framboise", "Images/raspberry.png"));
		jetList.add(new Jet("Noisette", "Images/hazelnut.png"));
		jetList.add(new Jet("Menthe poivrée", "Images/menthepoivre.png"));
		

		Sucre sucre = new Sucre("Sucre", "Images/sugar.png");
		Lait lait = new Lait("Lait", "Images/lait.jpg");//existe pas!!!
		Creme creme = new Creme("Creme", "Images/creme.jpg"); //existe pas!!!
		lcsList.add(sucre);
		lcsList.add(lait);
		lcsList.add(creme);

		cafe = new Cafe(tailleList.get(2), torefList.get(1),sucre, lait, creme);
		cafe.addPropertyChangeListener(this);

		vueCafe = new VueCafe(cafe, jetList, tailleList, torefList);
		vueCafe.getConfirmationPane().getBtnConfirm().addActionListener(new ConfirmerButtonListener());
		new NavigationManager(vueCafe);

		vueCafe.setPanelCafe(tailleList, torefList, cafe,30, this);
		vueCafe.setPanelJet(jetList,  30, this);
		vueCafe.setPanelLCS(lait, creme, sucre,  30, this);
		
		updateRapport();
	}

	public class ObsAddIng implements ActionListener {
		ComposanteCafe ing;
		int nbrPortions; // 1 pour add et -1 pour remove
		JTextField tfPortions;

		public ObsAddIng(ComposanteCafe ing, JTextField tfPortions, boolean add) {
			this.ing = ing;
			this.tfPortions = tfPortions;
			this.nbrPortions = add ? 1 : -1;
		}

		@Override
		public void actionPerformed(ActionEvent evt) {
			int prt = cafe.addIngredient(ing, nbrPortions);
			tfPortions.setText(String.valueOf(prt));
			updateRapport();
		}
	}
	
	public class tailleListener implements ActionListener{
		
		Taille taille;
		public tailleListener(Taille taille) {
			// TODO Auto-generated constructor stub
			this.taille = taille;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			
			cafe.setTaille(taille);
			// TODO Auto-generated method stub
			
		}
	}
		public class torefListener implements ActionListener{
			
			ComposanteCafe c;
			
			public torefListener(ComposanteCafe c) {
				// TODO Auto-generated constructor stub
				this.c = c;
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				
				cafe.setTorefaction(c);
				// TODO Auto-generated method stub
				
			}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		// Lait.setText(String.valueOf(evt.getNewValue()));

	}

	public void updateRapport() {
		vueCafe.getConfirmationPane().update(cafe.getRapport());
		vueCafe.validate();
	}

	public class ConfirmerButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			vueCafe.dispose();
		}
	}

}
