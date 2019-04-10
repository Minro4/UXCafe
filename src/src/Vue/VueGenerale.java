//********************************************************************
// VueCafe.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde
//
// Vue du café qui s'occupe de créer l'interface
//********************************************************************

package src.Vue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VueGenerale extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel pnlCreation,pnlAccueil; // Panel qui contient tout les autres panels qui forment l'interface de création


	public VueGenerale() {
		setTitle("Cafe-Expresse"); 
		setSize(640, 480); 
		setVisible(true); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setResizable(false); 
		
		validate();

	}
	
	public void switchToCreation(JPanel creation) {
		if (pnlCreation == null)
			add(creation);
		this.pnlCreation = creation;
		//pnlAccueil.setVisible(false);
		pnlCreation.setVisible(true);
		
		validate();
	}


}
