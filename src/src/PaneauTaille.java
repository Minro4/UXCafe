//********************************************************************
// PaneauTaille.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde, Guillaume St-Louis
//
// Paneau de la taille
//********************************************************************

package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;


public class PaneauTaille extends JPanel{

	private static final long serialVersionUID = 1L;

public PaneauTaille(Taille taille, ButtonGroup buttonGroup, CtrlBreuvages ctrl){
	  
	  
	  	JToggleButton toggleButton= new JToggleButton(setIcon(taille.getPath(), taille.getSize()));
	  	toggleButton.setBackground(Color.WHITE);
		setBackground(Color.white);
		
		
		JLabel lbNom = new JLabel(taille.getNom());
		lbNom.setFont(lbNom.getFont().deriveFont(12.0f));
		JLabel lbPrix= new JLabel(String.valueOf(taille.getPrix())+"$");
		lbPrix.setFont(lbPrix.getFont().deriveFont(12.0f));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		setPreferredSize(new Dimension(75, 80));
		
		toggleButton.addActionListener(ctrl.new tailleListener(taille));
		
		c.gridx=0;
		c.gridy=0;
		add(toggleButton,c);
		c.gridy = 1;
		add(lbNom,c);
		c.gridy=2;
		add(lbPrix,c);
	  
		if(taille.getNom().equals("Moyen")) {
			toggleButton.setSelected(true);
		}
		
	  buttonGroup.add(toggleButton);
	}
 
    public ImageIcon setIcon(String path, int resizeX) {
		
		ImageIcon imageI = new ImageIcon(path);
		
		java.awt.Image oof = imageI.getImage();
		java.awt.Image resized = oof.getScaledInstance(resizeX, resizeX, java.awt.Image.SCALE_SMOOTH);
		imageI.setImage(resized);		
		
		return imageI;
		
	}

}

