package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class NavigationManager {
	private int currentOnglet;
	private VueCafe vuecafe;
	private ConfirmationPane confirmationPane;

	private JPanel pnlSuivRet; // Panel qui contient les bouttons suivant et retour

	private JButton btnSuivant, btnRetour, btnAnnuler;
	private JButton[] btnOnglets;
	private String[] ongletNoms = { "Taille", "Bouillon", "Légume", "Viande", "Nouille", "Comfirmation" };

	// InterfaceCreationSoupe creationSoupe;
	/*
	 * JButton[] btnOnglets; JPanel pnlGroupPanel; JPanel[] panels; JButton
	 * btnSuivant; JButton btnRetour; JPanel pnlSuivRet;
	 */
	Color selectedColor;
	Color unselectedColor;
	Border selectedBorder;
	Border unselectedBorder;

	public NavigationManager(InterfaceCreationSoupe interfaceCreationSoupe, JPanel pnlOnglets, JPanel pnlNavigation, ConfirmationPane confirmation) {
		currentOnglet = 0;
		// this.creationSoupe = creationSoupe;
		confirmationPane = confirmation;
		confirmationPane.setNavigationManager(this);
		this.interfaceCreationSoupe = interfaceCreationSoupe;
		this.selectedColor = Color.white;
		this.unselectedColor = Color.lightGray;
		this.selectedBorder = BorderFactory.createMatteBorder(2, 1, 0, 1, Color.BLACK);
		this.unselectedBorder = BorderFactory.createMatteBorder(2, 1, 2, 1, Color.BLACK);
		// ‐‐‐‐‐‐‐‐‐‐‐‐‐‐ Instanciation des composants ‐‐‐‐‐‐‐‐‐‐‐‐‐‐
		pnlSuivRet = new JPanel(new BorderLayout(10, 10));
		pnlSuivRet.setBackground(Color.gray);

		// ---- Barre de navigation ----
		btnSuivant = new JButton("Suivant");

		btnRetour = new JButton("Retour");
		btnAnnuler = new JButton("Annuler");
		Dimension btnDim = new Dimension(130, 0);
		btnSuivant.setPreferredSize(btnDim);
		btnRetour.setPreferredSize(btnDim);
		btnAnnuler.setPreferredSize(btnDim);

		// ---- Onglet ----
		btnOnglets = new JButton[ongletNoms.length];

		for (int i = 0; i < btnOnglets.length; i++) {
			btnOnglets[i] = new JButton(ongletNoms[i]);
			UnselectOnglet(i);
			btnOnglets[i].setPreferredSize(new Dimension(0, 50));
			btnOnglets[i].addActionListener(new BouttonOngletListener(i));
		}
		selectOnglet(0);

		// Listener
		btnSuivant.addActionListener(new BouttonSuivantListener());
		btnRetour.addActionListener(new BouttonRetourListener());
		btnAnnuler.addActionListener(new BouttonAnnulerListener());

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
	}

	// Lorsque l'onglet change (index du nouvel onglet)
	public void OnClickBtnOnglet(int index) {
		if (index != currentOnglet) {
			UnselectOnglet(currentOnglet);
			selectOnglet(index);
			interfaceCreationSoupe.ChangePanelIngrediant(currentOnglet, index);

			if (index == 0) { // Si le nouvel onglet est le premier, on enlève le btn retour
				btnRetour.setEnabled(false);
			} else if (currentOnglet == 0) { // Si l'onglet était le premier, on ajoute le boutton retour
				btnRetour.setEnabled(true);
			}

			if (index == btnOnglets.length - 1) { // Si le nouvel onglet est le dernier, on change le texte du boutton
												// suivant pour "Confirmer"
				btnSuivant.setEnabled(false);
				confirmationPane.update();
			}
			else if (currentOnglet == btnOnglets.length - 1)
				btnSuivant.setEnabled(true);

			currentOnglet = index;
		}
	}

	public void OnClickSuivant() {
		OnClickBtnOnglet(currentOnglet + 1);
	}

	public void OnClickRetour() {
		OnClickBtnOnglet(currentOnglet - 1);
	}

	// Change l'aspect visuel de l'onglet lorsqu'il est sélectionné
	void selectOnglet(int index) {
		btnOnglets[index].setBackground(selectedColor);
		btnOnglets[index].setBorder(selectedBorder);
		// btnOnglets[index].setEnabled(false);
	}

	// Change l'aspect visuel de l'onglet lorsqu'il est désélectionné
	public void UnselectOnglet(int index) {
		btnOnglets[index].setBackground(unselectedColor);
		btnOnglets[index].setBorder(unselectedBorder);
		// btnOnglets[index].setEnabled(true);
	}
	
	
	
	public class BouttonOngletListener implements ActionListener {
		int index;
		
		public BouttonOngletListener( int index) {
			this.index = index;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			OnClickBtnOnglet(index);	
		}

	}
	public class BouttonRetourListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			OnClickRetour();
		}

	}
	
	public class BouttonSuivantListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			OnClickSuivant();

		}
	}

	public class BouttonAnnulerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			interfaceCreationSoupe.dispose();
			Main.MenuAccueil();

		}
	}

}
