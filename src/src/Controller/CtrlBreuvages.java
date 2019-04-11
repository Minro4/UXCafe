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

		// Cr�ation de l'array de torr�factions

		// Cr�ation de la liste de jets
	



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

			Cafe cafe = new Cafe(cafe.getListTaille().get(2), cafe.getListToref().get(1), "");
			breuvage = cafe;
			pnlWindows = createCafePanels(cafe);
		} else if (classe == MdlThe.class) {
			MdlThe the = new MdlThe(the.getListTaille().get(2), "");
			breuvage = the;
			pnlWindows = createThePanels(the);
		} else {
			MdlChocolatChaut chocolatChaud = new MdlChocolatChaut(chocolatChaud.getListTaille().get(2), "");
			breuvage = chocolatChaud;
			pnlWindows = createChocolatPanels(chocolatChaud);
		}
		breuvage.addPropertyChangeListener(this);
		
		pnlCreation = new PanelCreation(pnlWindows, ongletNoms, nomTitres);
		pnlCreation.getConfirmationPane().getBtnConfirm().addActionListener(new ConfirmerButtonListener());
		vueGenerale.switchToCreation(pnlCreation);

		updateRapport();
		
	}
	private void setChoixPanel(JToggleButton[] jl) {
		pnlChoix = new JPanel();
		pnlChoix.setLayout(new FlowLayout());
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
		JPanel[] tailles = new JPanel[breuvage.getListTaille().size()];
		ButtonGroup bg = new ButtonGroup();
		for (int i = 0; i < breuvage.getListTaille().size(); i++) {
			Taille taille = breuvage.getListTaille().get(i);
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
