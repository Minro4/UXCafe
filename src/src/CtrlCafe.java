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

		// Cr�ation de l'array de tailles
		tailleList.add(new Taille("Très petit", 250, 1.55, "Images/coffee.png", 30));
		tailleList.add(new Taille("Petit", 350, 1.75, "Images/coffee.png", 32));
		tailleList.add(new Taille("Moyen", 500, 1.95, "Images/coffee.png", 34));
		tailleList.add(new Taille("Grand", 600, 2.15, "Images/coffee.png", 36));
		tailleList.add(new Taille("Très Grand", 700, 2.35, "Images/coffee.png", 38));
		// Cr�ation de l'array de torr�factions
		torefList.add(new ComposanteCafe("Légère", "Images/coffeeBean.png"));
		torefList.add(new ComposanteCafe("Normale", "Images/CoffeeBean2.png"));
		torefList.add(new ComposanteCafe("Foncée", "Images/CoffeeBean3.png"));
		// Cr�ation de la liste de jets
		jetList.add(new Jet("Menthe", "Images/menthepoivre.png"));
		jetList.add(new Jet("Moka", "Images/chocolate.png"));
		jetList.add(new Jet("Caramel", "Images/caramel.jpg"));
		jetList.add(new Jet("Vanille", "Images/vanilla.png"));
		jetList.add(new Jet("Framboise", "Images/raspberry.png"));
		jetList.add(new Jet("Noisette", "Images/hazelnut.png"));
	
		

		Sucre sucre = new Sucre("Sucre", "Images/sugar.png");
		Lait lait = new Lait("Lait", "Images/lait.png");
		Creme creme = new Creme("Creme", "Images/creme.png");
		lcsList.add(sucre);
		lcsList.add(lait);
		lcsList.add(creme);

		cafe = new Cafe(tailleList.get(2), torefList.get(1),sucre, lait, creme);
		cafe.addPropertyChangeListener(this);

		vueCafe = new VueCafe(jetList, tailleList, torefList);
		vueCafe.getConfirmationPane().getBtnConfirm().addActionListener(new ConfirmerButtonListener());
		new NavigationManager(vueCafe);

		vueCafe.setPanelCafe(tailleList, torefList, cafe,40, this);
		vueCafe.setPanelJet(jetList,  69, this);
		vueCafe.setPanelLCS(lait, creme, sucre,  69, this);
		
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
			updateRapport();
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
				updateRapport();
				// TODO Auto-generated method stub
				
			}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		 vueCafe.getTfLaitPortion().setText(String.valueOf(evt.getNewValue()));

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
