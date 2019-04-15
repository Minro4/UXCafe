//********************************************************************
// NavigationManager.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde
//
// S'occupe de la navigation de la vue
//********************************************************************

package src.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import src.Vue.PanelCreation;
import src.Vue.VueGenerale;


public class CtrNavigation {
	private int currentOnglet;
	private PanelCreation panelCreation;
	private VueGenerale vueGenerale;

	public CtrNavigation(PanelCreation panelCreation,VueGenerale vueGenerale) {
		currentOnglet = 0;
		this.panelCreation = panelCreation;
		this.vueGenerale = vueGenerale;
		panelCreation.getBtnSuivant().addActionListener(new BouttonSuivantListener());
		panelCreation.getBtnRetour().addActionListener(new BouttonRetourListener());
		panelCreation.getBtnAnnuler().addActionListener(new BouttonAnnulerListener());
		
		JButton[] btnOnglets = panelCreation.getBtnOnglets();
		for (int i = 0; i < btnOnglets.length; i++) {
			btnOnglets[i].addActionListener(new BouttonOngletListener(i));
		}
		
	}

	// Lorsque l'onglet change (index du nouvel onglet)
	public void OnClickBtnOnglet(int index) {
		panelCreation.changePanel(currentOnglet, index);
		currentOnglet = index;
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
			vueGenerale.switchToAccueil();

		}
	}

}
