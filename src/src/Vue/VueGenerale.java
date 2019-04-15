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

	private final static int hSize = 640;
	private final static int vSize = 480;

	public VueGenerale(JPanel pnlChoix) {
		setTitle("Cafe-Expresse"); 
		setSize(hSize, vSize); 
		setVisible(true); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setResizable(false); 
		
		
		
		setAccueil(pnlChoix);
			
		validate();

	}
	
	

	private void setAccueil(JPanel pnlChoix) {
		pnlAccueil = pnlChoix;
		add(pnlAccueil);
		pnlAccueil.setVisible(true);
		
	}

	public void switchToAccueil() {
		pnlAccueil.setVisible(true);
		pnlCreation.setVisible(false);
	}
	public void switchToCreation(JPanel creation) {
		
		if (pnlCreation == null) {
			this.pnlCreation = creation;
			add(pnlCreation);
			}
		else
			this.pnlCreation = creation;
		pnlAccueil.setVisible(false);
		pnlCreation.setVisible(true);
		
		
		validate();
		repaint();
	}
	
	public static int getHsize() {
		return hSize;
	}

	public static int getVsize() {
		return vSize;
	}


}
