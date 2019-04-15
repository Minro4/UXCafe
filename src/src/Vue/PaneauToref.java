//********************************************************************
// PaneauToref.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde
//
// Paneau de la torefaction
//********************************************************************

package src.Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;


public class PaneauToref extends JPanelTrad{

	private static final long serialVersionUID = 1L;

	private JLabel lbNom;
	private String nomKey;

public PaneauToref(String nomKey,String path, ButtonGroup buttonGroup, boolean selected,ActionListener listener){  
	  
	this.nomKey = nomKey;
	  	JToggleButton toggleButton= new JToggleButton(setIcon(path, 40));
	  	toggleButton.setBackground(Color.WHITE);
		setBackground(Color.white);
		
		lbNom = new JLabel();
		lbNom.setFont(lbNom.getFont().deriveFont(12.0f));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		setPreferredSize(new Dimension(100, 100));
		
		toggleButton.addActionListener(listener);
		
		c.gridx=0;
		c.gridy=0;
		add(toggleButton,c);
		c.gridy = 1;
		add(lbNom,c);
	  
			toggleButton.setSelected(selected);
		
		
	  buttonGroup.add(toggleButton);
	}
 
  
  public ImageIcon setIcon(String path, int resizeX) {
		
		ImageIcon imageI = new ImageIcon(path);
		
		java.awt.Image oof = imageI.getImage();
		java.awt.Image resized = oof.getScaledInstance(resizeX, resizeX, java.awt.Image.SCALE_SMOOTH);
		imageI.setImage(resized);		
		
		return imageI;
		
	}
  @Override
  public void setTexte(ResourceBundle bdlLangue) {
	  lbNom.setText(bdlLangue.getString(nomKey));
  }
}

