//********************************************************************
// NavigationManager.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde, Guillaume St-Louis
//
// Controleur qui s'occupe de la navigation
//********************************************************************

package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class NavigationManager {
	private int currentOnglet;
	private VueCafe vueCafe;

	public NavigationManager(VueCafe vueCafe) {
		currentOnglet = 0;
		this.vueCafe = vueCafe;
		vueCafe.getBtnSuivant().addActionListener(new BouttonSuivantListener());
		vueCafe.getBtnRetour().addActionListener(new BouttonRetourListener());
		vueCafe.getBtnAnnuler().addActionListener(new BouttonAnnulerListener());
		
		JButton[] btnOnglets = vueCafe.getBtnOnglets();
		for (int i = 0; i < btnOnglets.length; i++) {
			btnOnglets[i].addActionListener(new BouttonOngletListener(i));
		}
		
	}

	// Lorsque l'onglet change (index du nouvel onglet)
	public void OnClickBtnOnglet(int index) {
		if (index != currentOnglet) {
			vueCafe.UnselectOnglet(currentOnglet);
			vueCafe.selectOnglet(index);
			vueCafe.ChangePanelIngrediant(currentOnglet, index);

			if (index == 0) { // Si le nouvel onglet est le premier, on enlève le btn retour
				vueCafe.getBtnRetour().setEnabled(false);
			} else if (currentOnglet == 0) { // Si l'onglet était le premier, on ajoute le boutton retour
				vueCafe.getBtnRetour().setEnabled(true);
			}

			if (index == vueCafe.getBtnOnglets().length - 1) { // Si le nouvel onglet est le dernier, on change le texte du boutton
												// suivant pour "Confirmer"
				vueCafe.getBtnSuivant().setEnabled(false);
				//confirmationPane.update();
			}
			else if (currentOnglet == vueCafe.getBtnOnglets().length - 1)
				vueCafe.getBtnSuivant().setEnabled(true);

			currentOnglet = index;
		}
	}

	public void OnClickSuivant() {
		OnClickBtnOnglet(currentOnglet + 1);
	}

	public void OnClickRetour() {
		OnClickBtnOnglet(currentOnglet - 1);
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
			//interfaceCreationSoupe.dispose();
			//Main.MenuAccueil();

		}
	}

}
