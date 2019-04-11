//********************************************************************
// CtrlCafe.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde, Guillaume St-Louis
//
// Controleur du cafe
//********************************************************************

package src.Controller;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import src.Vue.*;
import src.Modèles.*;

public class CtrlBreuvages implements PropertyChangeListener {

	private MdlBoisson breuvage;
	private VueGenerale vueGenerale;
	private PanelCreation pnlCreation;
	private JPanel pnlChoix;
//	private String imgPath="";
	private ArrayList<Taille> tailleList = new ArrayList<Taille>();
	private ArrayList<ComposanteBreuvage> torefList = new ArrayList<ComposanteBreuvage>();
	private ArrayList<Jet> jetList = new ArrayList<Jet>();
	private ArrayList<ComposanteBreuvage> lcsList = new ArrayList<ComposanteBreuvage>();

	private HashMap<ComposanteBreuvage, JTextField> tfComposantes = new HashMap<ComposanteBreuvage, JTextField>();

	private static String[] nomTitres = { "Sélectionnez la taille de votre café, ainsi que sa torréfaction",
			"Sélectionnez vos jets de saveur", "Personnaliser le tout" };
	private static String[] ongletNoms = { "Taille et Torefaction", "Jets de Saveurs", "Lait, Crème et Sucre" };

	public CtrlBreuvages() {

		JToggleButton[] listeBoutton = new JToggleButton[3];
		setButton("Café", "Images/cafe.png", listeBoutton[0], Cafe.class);
		setButton("Thé", "Images/cup.png", listeBoutton[0], MdlThe.class);
		setButton("Chocolat chaud", "Images/latte.png", listeBoutton[0],MdlChocolatChaut.class);
		
		createToggleGroup(listeBoutton);
	
		
		// Cr�ation de l'array de tailles
		tailleList.add(new Taille("Très petit", 250, 1.55, "Images/cafeTP.png", 34));
		tailleList.add(new Taille("Petit", 350, 1.75, "Images/cafeP.png", 34));
		tailleList.add(new Taille("Moyen", 500, 1.95, "Images/cafe.png", 34));
		tailleList.add(new Taille("Grand", 600, 2.15, "Images/cafeG.png", 34));
		tailleList.add(new Taille("Très Grand", 700, 2.35, "Images/cafeTG.png", 34));
		// Cr�ation de l'array de torr�factions
		torefList.add(new ComposanteBreuvage("Légère", "Images/coffeeBean.png"));
		torefList.add(new ComposanteBreuvage("Normale", "Images/CoffeeBean2.png"));
		torefList.add(new ComposanteBreuvage("Foncée", "Images/CoffeeBean3.png"));
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

		setChoixPanel(listeBoutton);
		vueGenerale = new VueGenerale(pnlChoix);
		creationBreuvage(Cafe.class);
		// vueCafe = new VueGenerale(jetList, tailleList, torefList);

		// pnlCreation.setPanelCafe(tailleList, torefList, cafe, 40, this);
		// pnlCreation.setPanelJet(jetList, 69, this);
		// pnlCreation.setPanelLCS(lait, creme, sucre, 69, this);

		// updateRapport();
	}
	public void setButton(String txt, String path, JToggleButton button,Class<?> valeur) {
		
		button.setText(txt);
		button.setIcon(setIcon(path, 45));
		button.addActionListener(new breuvageListener(valeur));
		
	}
	
	public void createToggleGroup(JToggleButton[] buttonList) {
		
		ButtonGroup buttonGroup = new ButtonGroup();
		
		for(JToggleButton button: buttonList) {
			
			buttonGroup.add(button);
			
		}	
	}
	
	public ImageIcon setIcon(String path, int resizeX) {
			
			ImageIcon imageI = new ImageIcon(path);
			
			java.awt.Image oof = imageI.getImage();
			java.awt.Image resized = oof.getScaledInstance(resizeX, resizeX, java.awt.Image.SCALE_SMOOTH);
			imageI.setImage(resized);		
			
			return imageI;
			
		}
	private void creationBreuvage(Class<?> classe) {

		JPanel[] pnlWindows;
		if (classe == Cafe.class) {

			Cafe cafe = new Cafe(tailleList.get(2), torefList.get(1), "");
			breuvage = cafe;
			pnlWindows = createCafePanels(cafe);
		} else if (classe == MdlThe.class) {
			MdlThe the = new MdlThe(tailleList.get(2), "");
			breuvage = the;
			pnlWindows = createThePanels(the);
		} else {
			MdlChocolatChaut chocolatChaud = new MdlChocolatChaut(tailleList.get(2), "");
			breuvage = chocolatChaud;
			pnlWindows = createChocolatPanels(chocolatChaud);
			breuvage = new Cafe(tailleList.get(2), torefList.get(1), "Images/cafe.png");
		}
		breuvage.addPropertyChangeListener(this);
		
		pnlCreation = new PanelCreation(pnlWindows, ongletNoms, nomTitres);
		pnlCreation.getConfirmationPane().getBtnConfirm().addActionListener(new ConfirmerButtonListener());
		vueGenerale.switchToCreation(pnlCreation);

		updateRapport();
		
	}
	private void setChoixPanel(JToggleButton[] jl) {
		pnlCreation.setLayout(new FlowLayout());
		for(JToggleButton t: jl) {
			pnlChoix.add(t);
		}
	}	
	private JPanel[] createCafePanels(Cafe cafe) {
		return VueGenerationBoisson.getCafePanels(createTailles(), createTorefs(cafe),
				createComposantes(jetList.toArray(new Jet[jetList.size()])),
				createComposantes(lcsList.toArray(new ComposanteBreuvage[lcsList.size()])));
	}

	private JPanel[] createThePanels(MdlThe the) {

		JPanel[] tailles = createTailles();
		JPanel[] lcs = createComposantes(lcsList.toArray(new ComposanteBreuvage[lcsList.size()]));
		return VueGenerationBoisson.getGenericPanels(tailles, lcs);

	}

	private JPanel[] createChocolatPanels(MdlChocolatChaut chocolatChaut) {
		JPanel[] tailles = createTailles();
		JPanel[] lcs = createComposantes(lcsList.toArray(new ComposanteBreuvage[lcsList.size()]));
		return VueGenerationBoisson.getGenericPanels(tailles, lcs);

	}

	private JPanel[] createTailles() {
		JPanel[] tailles = new JPanel[tailleList.size()];
		ButtonGroup bg = new ButtonGroup();
		for (int i = 0; i < tailleList.size(); i++) {
			Taille taille = tailleList.get(i);
			tailles[i] = new PaneauTaille(taille.getNom(), taille.getPath(), taille.getPrix(), taille.getSize(), bg,
					new tailleListener(taille));
		}
		return tailles;
	}

	private JPanel[] createTorefs(Cafe cafe) {
		JPanel[] torefs = new JPanel[torefList.size()];
		ButtonGroup bg = new ButtonGroup();
		for (int i = 0; i < torefList.size(); i++) {
			torefs[i] = new PaneauToref(torefList.get(i).getNom(), torefList.get(i).getPath(), bg,
					new torefListener(torefList.get(i)));
		}
		return torefs;
	}

	private JPanel[] createComposantes(ComposanteBreuvage[] composanteListe) {
		JPanel[] composantes = new JPanel[composanteListe.length];
		for (int i = 0; i < composanteListe.length; i++) {
			ComposanteBreuvage comp = composanteListe[i];
			JTextField tfPortions = new JTextField();
			tfComposantes.put(comp, tfPortions);
			composantes[i] = new ComposantePane(comp.getNom(), comp.getPath(), tfPortions,
					new ObsAddIng(comp, tfPortions, true), new ObsAddIng(comp, tfPortions, false));
		}
		return composantes;
	}

	public class ObsAddIng implements ActionListener {
		ComposanteBreuvage ing;
		int nbrPortions; // 1 pour add et -1 pour remove
		JTextField tfPortions;

		public ObsAddIng(ComposanteBreuvage ing, JTextField tfPortions, boolean add) {
			this.ing = ing;
			this.tfPortions = tfPortions;
			this.nbrPortions = add ? 1 : -1;
		}

		@Override
		public void actionPerformed(ActionEvent evt) {
			int prt = breuvage.addIngredient(ing, nbrPortions);
			tfPortions.setText(String.valueOf(prt));
			updateRapport();
		}
	}

	public class tailleListener implements ActionListener {

		Taille taille;

		public tailleListener(Taille taille) {
			// TODO Auto-generated constructor stub
			this.taille = taille;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			breuvage.setTaille(taille);
			updateRapport();
			// TODO Auto-generated method stub

		}
	}

	public class breuvageListener implements ActionListener {

		Class<?> classe;

		public breuvageListener(Class<?> valeur) {
			this.classe = valeur;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			creationBreuvage(classe);

		}

	}

	public class torefListener implements ActionListener {

		ComposanteBreuvage c;

		public torefListener(ComposanteBreuvage c) {
			// TODO Auto-generated constructor stub
			this.c = c;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			((Cafe) breuvage).setTorefaction(c);
			updateRapport();
			// TODO Auto-generated method stub

		}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		JTextField tf = tfComposantes.get(evt.getOldValue());
		String vString = String.valueOf(evt.getNewValue());
		tf.setText(vString);

	}

	public void updateRapport() {
		if (pnlCreation == null)
			return;
		pnlCreation.getConfirmationPane().update(breuvage.getRapport());
		pnlCreation.validate();
	}

	public class ConfirmerButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			vueGenerale.dispose();
		}
	}

}
