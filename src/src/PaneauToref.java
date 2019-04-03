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


public class PaneauToref extends JPanel{

private ComposanteCafe cafeComp;

  public PaneauToref(ComposanteCafe cafeComp, ButtonGroup buttonGroup, Integer hauteur, CtrlCafe ctrl){

	  	this.cafeComp = cafeComp;
	  
	  
	  	JToggleButton toggleButton= new JToggleButton(setIcon(cafeComp.getPath(), hauteur));
	  	toggleButton.setBackground(Color.WHITE);
		setBackground(Color.white);
		
		JLabel lbNom = new JLabel(cafeComp.getNom());
		lbNom.setFont(lbNom.getFont().deriveFont(12.0f));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		setPreferredSize(new Dimension(100, 100));
		
		toggleButton.addActionListener(ctrl.new torefListener(cafeComp));
		
		c.gridx=0;
		c.gridy=0;
		add(toggleButton,c);
		c.gridy = 1;
		add(lbNom,c);
	  
		if(cafeComp.getNom().equals("Normale")) {
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

