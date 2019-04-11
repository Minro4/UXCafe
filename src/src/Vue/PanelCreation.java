//********************************************************************
// VueCafe.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde, Guillaume St-Louis
//
// Vue du café qui s'occupe de créer l'interface
//********************************************************************	
package src.Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;import com.sun.org.apache.xml.internal.utils.SuballocatedByteVector;

public class PanelCreation extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel pnlOnglets, // Panel qui contient les onglets
			pnlNavigation, // Panel qui contient la barre de navigation au bas de l'écran
			pnlConteneurIng; // Panel qui contient les ingrédients/taille/etc ainsi que le titre.

	private ConfirmationPane confirmationPane;

	private JPanel[] pnlWindows; // Taille, Bouillon Légume, Viande, Nouille, Comfirmation
	private static final int panelWidth = 402;
	private JLabel lbTitre;

	private String[] nomPanels;

	// --------------Navigation--------------------------
	private JPanel pnlSuivRet; // Panel qui contient les bouttons suivant et retour

	private JButton btnSuivant, btnRetour, btnAnnuler;
	private JButton[] btnOnglets;

	private Color selectedColor;
	private Color unselectedColor;
	private Border selectedBorder;
	private Border unselectedBorder;

	
	public PanelCreation(JPanel[] pnlWindows, String[] nomOnglets, String[] nomPanels) {

		setLayout(new BorderLayout());
		pnlOnglets = new JPanel(new GridBagLayout());
		pnlConteneurIng = new JPanel(new GridBagLayout());
		pnlConteneurIng.setBackground(Color.WHITE);
		pnlNavigation = new JPanel(new BorderLayout());
		pnlNavigation.setBackground(Color.gray);
		pnlNavigation.setPreferredSize(new Dimension(0, 40));
		pnlNavigation.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK),
				new EmptyBorder(4, 4, 4, 4)));
		lbTitre = new JLabel();
		lbTitre.setPreferredSize(new Dimension(0, 50));
		lbTitre.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		lbTitre.setFont(lbTitre.getFont().deriveFont(14.0f));
		lbTitre.setHorizontalAlignment(SwingConstants.CENTER);
		// lbTitre.setText(nomTitres[0]);

		confirmationPane = new ConfirmationPane();

		// ‐‐‐‐‐‐‐‐‐‐‐‐‐‐ Positionnement ‐‐‐‐‐‐‐‐‐‐‐‐‐‐

		{
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.weightx = 1;
			pnlConteneurIng.add(lbTitre, constraints);

		}
		add(pnlOnglets, BorderLayout.NORTH);
		add(pnlConteneurIng, BorderLayout.WEST);
		add(pnlNavigation, BorderLayout.SOUTH);
		add(confirmationPane, BorderLayout.EAST);

		// -------------- NAVIGATION
		// ----------------------------------------------------------------------
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

		// ‐‐‐‐‐‐‐‐‐‐‐‐‐‐ Positionnement ‐‐‐‐‐‐‐‐‐‐‐‐‐‐

		// ---- Onglet ----
		{
			btnOnglets = new JButton[nomOnglets.length];

			for (int i = 0; i < btnOnglets.length; i++) {
				btnOnglets[i] = new JButton(nomOnglets[i]);
				UnselectOnglet(i);
				btnOnglets[i].setPreferredSize(new Dimension(0, 40));
			}
			selectOnglet(0);
			
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
		// ----------- END NAVIGATION
		// --------------------------------------------------------------
		
		this.pnlWindows = pnlWindows;
		this.nomPanels = nomPanels;

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(15, 10, 10, 10);
		for (JPanel panel : pnlWindows) {
			pnlConteneurIng.add(panel, constraints);
			panel.setVisible(false);
		}
		pnlWindows[0].setVisible(true);
		lbTitre.setText(nomPanels[0]);

		
		new NavigationManager(this);
		
		validate();

	}

	

	// Change le panel de sélection (lorsque l'utilisateur change d'onglet)
	public void ChangePanelIngrediant(int oldPnlIndex, int newPnlIndex) {
		pnlWindows[oldPnlIndex].setVisible(false);
		pnlWindows[newPnlIndex].setVisible(true);
		lbTitre.setText(nomPanels[newPnlIndex]);
		validate();
		repaint();
	}

	// Change l'aspect visuel de l'onglet lorsqu'il est sélectionné
	public void selectOnglet(int index) {
		btnOnglets[index].setBackground(selectedColor);
		btnOnglets[index].setBorder(selectedBorder);
	}

	// Change l'aspect visuel de l'onglet lorsqu'il est désélectionné
	public void UnselectOnglet(int index) {
		btnOnglets[index].setBackground(unselectedColor);
		btnOnglets[index].setBorder(unselectedBorder);
	}

	public ImageIcon imageToIconImage(ImageIcon image, int resizeX, int resizeY) {
		java.awt.Image oof = image.getImage();
		java.awt.Image resized = oof.getScaledInstance(resizeX, resizeY, java.awt.Image.SCALE_SMOOTH);
		image.setImage(resized);

		return image;
	}

	
	public static JPanel getGenericPanel(JPanel[] composantes) {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new FlowLayout());
		jPanel.setBackground(Color.white);
		jPanel.setPreferredSize(new Dimension(panelWidth, 1));
		for (JPanel composante : composantes) {
			jPanel.add(composante);
		}

		return jPanel;
	}

	public static JPanel getMultiplePanel(JPanel[]... composantes) {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(composantes.length, 1));
		jPanel.setBackground(Color.white);

		for (int i = 0; i < composantes.length; i++) {
			jPanel.add(getGenericPanel(composantes[i]));
		}

		return jPanel;
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

	public ConfirmationPane getConfirmationPane() {
		return confirmationPane;
	}



	public static JPanel getSeparatedPanel(JPanel[] jets,int nbPrefPerPanel) {
		JPanel jPanel = new JPanel(new FlowLayout());
		ArrayList<JPanel> subPanesList = new ArrayList<JPanel>();
		
		for(int i = 0; i<nbPrefPerPanel; i++) {
			JPanel j = new JPanel();
			int position = i+(subPanesList.size() * nbPrefPerPanel);
			
			if(position < jets.length - 1) {
				j.add(jets[i+(subPanesList.size() * nbPrefPerPanel)]);
					if(i == nbPrefPerPanel -1) {
						subPanesList.add(j);
					}
			}
			else
				subPanesList.add(j);
				break;
		}
		
		return jPanel;	
		}

}
