//********************************************************************
// CtrlCafe.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde, Guillaume St-Louis
//
// Controleur du cafe
//********************************************************************

package src.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.Vue.*;
import src.Modèles.*;

public class CtrlBreuvages implements PropertyChangeListener {

	private MdlBoisson breuvage;
	private VueGenerale vueGenerale;
	private PanelCreation pnlCreation;
//	private String imgPath="";

	private HashMap<ComposanteBreuvage, JTextField> tfComposantes = new HashMap<ComposanteBreuvage, JTextField>();

	private static String[] nomTitres = { "Sélectionnez la taille de votre café, ainsi que sa torréfaction",
			"Sélectionnez vos jets de saveur", "Personnaliser le tout" };
	private static String[] ongletNoms = { "Taille et Torefaction", "Jets de Saveurs", "Lait, Crème et Sucre" };

	public CtrlBreuvages() {

		/*setButton("Café", "Images/cafe.png", listeBoutton[0], MdlCafe.class);
		setButton("Thé", "Images/cup.png", listeBoutton[1], MdlThe.class);
		setButton("Chocolat chaud", "Images/latte.png", listeBoutton[2], MdlChocolatChaud.class);

		createToggleGroup(listeBoutton);*/

		// Cr�ation de l'array de tailles

		// Cr�ation de l'array de torr�factions

		// Cr�ation de la liste de jets

		String[] noms = { "Café", "Thé", "Chocolat chaud" };
		String[] paths = { MdlCafe.getPath(), MdlThe.getPath(), MdlChocolatChaud.getPath() };
		ActionListener[] actionListeners = { new breuvageListener(MdlCafe.class), new breuvageListener(MdlThe.class),
				new breuvageListener(MdlChocolatChaud.class) };

		vueGenerale = new VueGenerale(new VuePanelSelection(noms, paths, actionListeners));
		// creationBreuvage(MdlThe.class);
		// vueCafe = new VueGenerale(jetList, tailleList, torefList);

		// pnlCreation.setPanelCafe(tailleList, torefList, cafe, 40, this);
		// pnlCreation.setPanelJet(jetList, 69, this);
		// pnlCreation.setPanelLCS(lait, creme, sucre, 69, this);

		// updateRapport();
	}

	/*public void setButton(String txt, String path, JToggleButton button, Class<?> valeur) {

		button.setText(txt);
		button.setIcon(setIcon(path, 45));
		button.addActionListener(new breuvageListener(valeur));

	}

	public void createToggleGroup(JToggleButton[] buttonList) {

		ButtonGroup buttonGroup = new ButtonGroup();

		for (JToggleButton button : buttonList) {

			buttonGroup.add(button);

		}
	}

	public ImageIcon setIcon(String path, int resizeX) {

		ImageIcon imageI = new ImageIcon(path);

		java.awt.Image oof = imageI.getImage();
		java.awt.Image resized = oof.getScaledInstance(resizeX, resizeX, java.awt.Image.SCALE_SMOOTH);
		imageI.setImage(resized);

		return imageI;

	}*/

	private void creationBreuvage(Class<?> classe) {

		JPanel[] pnlWindows;
		if (classe == MdlCafe.class) {
			MdlCafe cafe = new MdlCafe("");
			breuvage = cafe;
			pnlWindows = createCafePanels(cafe);
		} else if (classe == MdlThe.class) {
			MdlThe the = new MdlThe("");
			breuvage = the;
			pnlWindows = createThePanels(the);
		} else {
			MdlChocolatChaud chocolatChaud = new MdlChocolatChaud("");
			breuvage = chocolatChaud;
			pnlWindows = createChocolatPanels(chocolatChaud);
		}
		breuvage.addPropertyChangeListener(this);

		pnlCreation = new PanelCreation(pnlWindows, ongletNoms, nomTitres);
		pnlCreation.getConfirmationPane().getBtnConfirm().addActionListener(new ConfirmerButtonListener());
		vueGenerale.switchToCreation(pnlCreation);
		new CtrNavigation(pnlCreation, vueGenerale);
		updateRapport();

	}

	private JPanel[] createCafePanels(MdlCafe cafe) {

		SeparatedPanel sp = new SeparatedPanel(createComposantes(cafe.getListJet()), 6);
		CtrlJetsNav cj = new CtrlJetsNav(0, sp);
		
		return VueGenerationBoisson.getCafePanels(createTailles(), createTorefs(cafe),
				createComposantes(cafe.getListLcs()),sp);
	}
	private JPanel[] createThePanels(MdlThe the) {

		JPanel[] tailles = createTailles();
		JPanel[] lcs = createComposantes(the.getListLcs());
		return VueGenerationBoisson.getGenericPanels(tailles, lcs);
	}
	private JPanel[] createChocolatPanels(MdlChocolatChaud chocolatChaut) {
		JPanel[] tailles = createTailles();
		JPanel[] lcs = createComposantes(chocolatChaut.getListLcs());
		return VueGenerationBoisson.getGenericPanels(tailles, lcs);
	}
	private JPanel[] createTailles() {
		JPanel[] tailles = new JPanel[breuvage.getListTaille().length];
		ButtonGroup bg = new ButtonGroup();
		for (int i = 0; i < breuvage.getListTaille().length; i++) {
			Taille taille = breuvage.getListTaille()[i];
			tailles[i] = new PaneauTaille(taille.getNom(), taille.getPath(), taille.getPrix(), taille.getSize(), bg,
					new tailleListener(taille));
		}
		return tailles;
	}
	private JPanel[] createTorefs(MdlCafe cafe) {
		JPanel[] torefs = new JPanel[MdlCafe.getListToref().length];
		ButtonGroup bg = new ButtonGroup();
		for (int i = 0; i < MdlCafe.getListToref().length; i++) {
			torefs[i] = new PaneauToref(MdlCafe.getListToref()[i].getNom(), MdlCafe.getListToref()[i].getPath(), bg,
					new torefListener(MdlCafe.getListToref()[i]));
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

			((MdlCafe) breuvage).setTorefaction(c);
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
