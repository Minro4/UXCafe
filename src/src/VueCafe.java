package src;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class VueCafe extends JFrame {
	
	private Cafe cafe;

	private JPanel pnlGroupe,// Panel qui contient tout les autres panels qui forment l'interface de création
			pnlOnglets, // Panel qui contient les onglets
			pnlNavigation, // Panel qui contient la barre de navigation au bas de l'écran
			pnlConteneurIng; // Panel qui contient les ingrédients/taille/etc ainsi que le titre.

	private JPanel[] pnlCafe; // Taille, Bouillon Légume, Viande, Nouille, Comfirmation
	private JLabel lbTitre;
	private String[] nomTitres = { "Sélectionnez la taille de votre café, ainsi que sa torréfaction",
			"Sélectionnez vos jets de saveur", "Personnaliser le tout"};

	private int sizeLVFValueX = 120; // Taille des images
	private int sizeLVFValueY = 120;

	private int sizeBouillonX = 252; // Taille des images
	private int sizeBouillonY = 168;
	
	private ArrayList<ImageIcon> imageList;
	
	
	//--------------Navigation--------------------------
	private JPanel pnlSuivRet; // Panel qui contient les bouttons suivant et retour

	private JButton btnSuivant, btnRetour, btnAnnuler;
	private JButton[] btnOnglets;

	private String[] ongletNoms = { "Taille et Torefaction", "Jets de Saveurs", "Lait, Crème et Sucre" };

	private Color selectedColor;
	private Color unselectedColor;
	private Border selectedBorder;
	private Border unselectedBorder;

	public VueCafe(Cafe cafe, ArrayList<Jet> jetList, ArrayList<Taille> tailleList,
			ArrayList<Ingredient> torefList) {
		// ‐‐‐‐‐‐‐‐‐‐‐‐‐‐ Fenetre JFrame ‐‐‐‐‐‐‐‐‐‐‐‐‐‐
		setTitle("Cafe-Expresse");
		setSize(640, 480);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		
		this.cafe = cafe;
		pnlGroupe = new JPanel(new BorderLayout());
		pnlOnglets = new JPanel(new GridBagLayout());
		pnlConteneurIng = new JPanel(new GridBagLayout());
		pnlConteneurIng.setBackground(Color.WHITE);
		pnlNavigation = new JPanel(new BorderLayout());
		pnlNavigation.setBackground(Color.gray);
		pnlNavigation.setPreferredSize(new Dimension(0, 40));
		pnlNavigation.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK),
				new EmptyBorder(4, 4, 4, 4)));
		lbTitre = new JLabel();
		lbTitre.setPreferredSize(new Dimension(0, 100));
		lbTitre.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		lbTitre.setFont(lbTitre.getFont().deriveFont(24.0f));
		lbTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitre.setText(nomTitres[0]);

		pnlCafe = new JPanel[3];

		pnlCafe[0] = new JPanel();
		panelTaille(pnlCafe[0], tailleList, torefList);

		pnlCafe[1] = new JPanel();

		pnlCafe[2] = new JPanel();
		pnlCafe[2].setLayout(new FlowLayout());
		
		


		for (int i = 0; i < pnlCafe.length; i++) {
			pnlCafe[i].setBackground(Color.white);
		}
		// ‐‐‐‐‐‐‐‐‐‐‐‐‐‐ Positionnement ‐‐‐‐‐‐‐‐‐‐‐‐‐‐
		add(pnlGroupe);

		{
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.weightx = 1;
			pnlConteneurIng.add(lbTitre, constraints);

			constraints.fill = GridBagConstraints.BOTH;
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.weightx = 1;
			constraints.weighty = 1;
			constraints.insets = new Insets(15, 10, 10, 10);
			for (JPanel panel : pnlCafe) {
				pnlConteneurIng.add(panel, constraints);
				panel.setVisible(false);
			}
			pnlCafe[0].setVisible(true);

		}
		pnlGroupe.add(pnlOnglets, BorderLayout.NORTH);
		pnlGroupe.add(pnlConteneurIng, BorderLayout.WEST);
		pnlGroupe.add(pnlNavigation, BorderLayout.SOUTH);
		pnlCafe[0].setPreferredSize(new Dimension((int) (getSize().width * 0.65), 1));

		
		//-------------- NAVIGATION ----------------------------------------------------------------------
		selectedColor = Color.white;
		unselectedColor = Color.lightGray;
		selectedBorder = BorderFactory.createMatteBorder(2, 1, 0, 1, Color.BLACK);
		unselectedBorder = BorderFactory.createMatteBorder(2, 1, 2, 1, Color.BLACK);
		// ‐‐‐‐‐‐‐‐‐‐‐‐‐‐ Instanciation des composants ‐‐‐‐‐‐‐‐‐‐‐‐‐‐
		pnlSuivRet = new JPanel(new BorderLayout(4, 0));
		pnlSuivRet.setBackground(Color.gray);

		// ---- Barre de navigation ----
		btnSuivant = new JButton("Suivant");

		btnRetour = new JButton("Retour");
		btnAnnuler = new JButton("Annuler");
		Dimension btnDim = new Dimension(120, 0);
		btnSuivant.setPreferredSize(btnDim);
		btnRetour.setPreferredSize(btnDim);
		btnAnnuler.setPreferredSize(btnDim);

		// ---- Onglet ----
		btnOnglets = new JButton[ongletNoms.length];

		for (int i = 0; i < btnOnglets.length; i++) {
			btnOnglets[i] = new JButton(ongletNoms[i]);
			UnselectOnglet(i);
			btnOnglets[i].setPreferredSize(new Dimension(0, 40));
		}
		selectOnglet(0);

		// ‐‐‐‐‐‐‐‐‐‐‐‐‐‐ Positionnement ‐‐‐‐‐‐‐‐‐‐‐‐‐‐

		// ---- Onglet ----
		{
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			for (int i = 0; i < btnOnglets.length; i++) {
				constraints.gridx = i;
				pnlOnglets.add(btnOnglets[i], constraints);
			}
		}

		// ---- Barre de navigation ----
		{
			pnlSuivRet.add(btnSuivant, BorderLayout.EAST);
			pnlSuivRet.add(btnRetour, BorderLayout.WEST);
			btnRetour.setEnabled(false);
			pnlNavigation.add(btnAnnuler, BorderLayout.WEST);
			pnlNavigation.add(pnlSuivRet, BorderLayout.EAST);
		}
		//----------- END NAVIGATION --------------------------------------------------------------
		
		validate();

	}

	// Change le panel de sélection (lorsque l'utilisateur change d'onglet)
	public void ChangePanelIngrediant(int oldPnlIndex, int newPnlIndex) {
		pnlCafe[newPnlIndex].setPreferredSize(new Dimension((int) (getSize().width * 0.65), 1));
		pnlCafe[oldPnlIndex].setVisible(false);
		pnlCafe[newPnlIndex].setVisible(true);
		lbTitre.setText(nomTitres[newPnlIndex]);
		validate();
		repaint();
	}
	// Change l'aspect visuel de l'onglet lorsqu'il est sélectionné
		void selectOnglet(int index) {
			btnOnglets[index].setBackground(selectedColor);
			btnOnglets[index].setBorder(selectedBorder);
		}

		// Change l'aspect visuel de l'onglet lorsqu'il est désélectionné
		public void UnselectOnglet(int index) {
			btnOnglets[index].setBackground(unselectedColor);
			btnOnglets[index].setBorder(unselectedBorder);
		}
	
	public ImageIcon imageToIconImage(ImageIcon image, int resizeX, int resizeY) 
	{	
		java.awt.Image oof = image.getImage();
		java.awt.Image resized = oof.getScaledInstance(resizeX, resizeY, java.awt.Image.SCALE_SMOOTH);
		image.setImage(resized);
		
		return image;
	}
	// fonction qui gère chaques panel d'ingrédient en les positionants dans un gros
	// panel
	public void setPanelJet(ArrayList<Jet> jetList, Cafe cafe,Integer hauteur, CtrlCafe ctrl ) {
		
		for(Jet j:jetList) {
			
			pnlCafe[2].add(new IngredPane(j,cafe,hauteur, ctrl));
			
		}
	}

	public void setPanelLCS(Lait lait, Creme Creme, Sucre sucre) {
		
		
		
	}
	
	

	void panelTaille(JPanel panel, ArrayList<Taille> taille,ArrayList<Ingredient> toref) {
		ButtonGroup btnGroup = new ButtonGroup();
		panel.setLayout(new FlowLayout());
		panel.setAlignmentY(CENTER_ALIGNMENT);
		for (int i = 0; i < taille.size(); i++) {
			//panel.add(new TaillePane(taille.get(i), cafe, btnGroup));
		}
	}

	public JButton getBtnSuivant() {
		return btnSuivant;
	}

	public void setBtnSuivant(JButton btnSuivant) {
		this.btnSuivant = btnSuivant;
	}

	public JButton getBtnRetour() {
		return btnRetour;
	}

	public JButton getBtnAnnuler() {
		return btnAnnuler;
	}

	public JButton[] getBtnOnglets() {
		return btnOnglets;
	}
	/*
	 * public class ResizeListener extends ComponentAdapter {
	 * 
	 * 
	 * public void componentResized(ComponentEvent e) {
	 * pnlSoupe.setPreferredSize(new Dimension((int) (getSize().width*0.35), 1));
	 * for (int i = 0; i < pnlIngrediants.length; i++) {
	 * pnlIngrediants[i].setPreferredSize(new Dimension(
	 * (int)(getSize().width*0.65), 1)); } repaint(); validate();
	 * 
	 * 
	 * } }
	 */

}

