//********************************************************************
// VueCafe.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde
//
// Vue du café qui s'occupe de créer l'interface
//********************************************************************

package src.Vue;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class VueGenerale extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel pnlCreation,pnlAccueil; // Panel qui contient tout les autres panels qui forment l'interface de création


	public VueGenerale(JPanel pnlChoix) {
		setTitle("Cafe-Expresse"); 
		setSize(640, 480); 
		setVisible(true); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setResizable(false); 
		
		
		
		setAccueil(pnlChoix);
		
		pnlCreation = new JPanel();
	
		
		validate();

	}
	
	private void setAccueil(JPanel pnlChoix) {
		// TODO Auto-generated method stub
		pnlAccueil = pnlChoix;
		add(pnlAccueil);
		pnlAccueil.setVisible(true);
		
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
