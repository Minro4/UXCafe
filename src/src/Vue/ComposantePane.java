//********************************************************************
// IngredPane.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde, Guillaume St-Louis
//
// Paneau qui représente un composant du café
//********************************************************************

package src.Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


public class ComposantePane extends JPanel {

	private static final long serialVersionUID = 1L;

	public ComposantePane(String ingNom,String ingImPath,JTextField tfPortions,ActionListener plusListener,ActionListener moinListener) {
	
		JLabel nom;
		JLabel imageIngred;
		JButton plus;
		JButton moins;
			
		tfPortions.setText(String.valueOf(0));
			
		int imHeight = 69;
		int btnHeight = imHeight / 3;

		
	
		tfPortions.setPreferredSize(new Dimension(btnHeight, btnHeight));
		tfPortions.setFont(tfPortions.getFont().deriveFont(20.0f));
		tfPortions.setHorizontalAlignment(JTextField.CENTER);

		plus = new JButton();
		plus.setPreferredSize(new Dimension(btnHeight, btnHeight));
		plus.setMargin(new Insets(5, 5, 5, 5));

		moins = new JButton();
		moins.setPreferredSize(new Dimension(btnHeight, btnHeight));
		moins.setMargin(new Insets(5, 5, 5, 5));

		plus.setText("+");
		plus.setFont(plus.getFont().deriveFont(12.0f));
		moins.setText("-");
		moins.setFont(moins.getFont().deriveFont(12.0f));

		// ajout de bouttonPlusListener aux bouttons plus et moins

		plus.addActionListener(plusListener);
		moins.addActionListener(moinListener);

		setBackground(Color.white);
		setPreferredSize(new Dimension(110, 120));

		// set els images d'ingredients
		imageIngred = new JLabel();
		imageIngred.setIcon(new ImageIcon(setIc(ingImPath, imHeight).getImage()));
		imageIngred.setBorder(new LineBorder(Color.BLACK));

		nom = new JLabel(ingNom);
		nom.setFont(nom.getFont().deriveFont(16.0f));

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 1;
		c.gridy = 0;

		add(plus, c);

		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;

		add(tfPortions, c);
		c.fill = GridBagConstraints.NONE;
		c.gridx = 1;
		c.gridy = 2;

		add(moins, c);

		c.gridx = 0;
		c.gridy = 3;

		add(nom, c);

		c.anchor = GridBagConstraints.NORTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 3;
		
		add(imageIngred, c);

		validate();
	}
	public ImageIcon setIc(String path, int hauteur) {
		
		ImageIcon imageI = new ImageIcon(path);
		
		java.awt.Image oof = imageI.getImage();
		java.awt.Image resized = oof.getScaledInstance(hauteur, hauteur, java.awt.Image.SCALE_SMOOTH);
		imageI.setImage(resized);		
		
		return imageI;
		
	}
}