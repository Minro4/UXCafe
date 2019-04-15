//********************************************************************
// VueCafe.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde
//
// Vue du café qui s'occupe de créer l'interface
//********************************************************************

package src.Vue;

import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VueGenerale extends JFrame {

	private static final long serialVersionUID = 1L;

	private VuePanelSelection pnlAccueil;
	private PanelCreation pnlCreation;

	private final static int hSize = 640;
	private final static int vSize = 480;

	public VueGenerale(VuePanelSelection pnlAccueil) {
		setTitle("Cafe-Expresse");
		setSize(hSize, vSize);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		setAccueil(pnlAccueil);

		validate();

	}

	private void setAccueil(VuePanelSelection pnlAccueil) {
		this.pnlAccueil = pnlAccueil;
		add(pnlAccueil);
		pnlAccueil.setVisible(true);

	}

	public void switchToAccueil() {
		pnlAccueil.setVisible(true);
		pnlCreation.setVisible(false);
	}

	public void switchToCreation(PanelCreation creation) {

		if (pnlCreation != null)
			remove(pnlCreation);

		this.pnlCreation = creation;
		add(pnlCreation);

		pnlAccueil.setVisible(false);
		pnlCreation.setVisible(true);

		validate();
		repaint();
	}

	public void setTexte(ResourceBundle bdlLangue) {
		pnlAccueil.setTexte(bdlLangue);
		if (pnlCreation != null)
			pnlCreation.setTexte(bdlLangue);
	}

	public static int getHsize() {
		return hSize;
	}

	public static int getVsize() {
		return vSize;
	}

}
