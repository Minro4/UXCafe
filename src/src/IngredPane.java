package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import src.CtrlCafe.ObsAddIng;

public class IngredPane extends JPanel {

	private Cafe cafe;
	private ComposanteCafe ingredient;
	private JTextField portions;

	public IngredPane(ComposanteCafe ingred, Cafe cafe, int imHeight, CtrlCafe ctrl) {
		this.cafe = cafe;
		this.ingredient = ingred;
		
		JLabel nom;
		JLabel imageIngred;
		JButton plus;
		JButton moins;
		
		portions = new JTextField();
			
		portions.setText(String.valueOf(0));
			
		int btnHeight = imHeight / 3;

		
	
		portions.setPreferredSize(new Dimension(btnHeight, btnHeight));
		portions.setFont(portions.getFont().deriveFont(20.0f));
		portions.setHorizontalAlignment(JTextField.CENTER);

		plus = new JButton();
		plus.setPreferredSize(new Dimension(btnHeight, btnHeight));
		plus.setMargin(new Insets(5, 5, 5, 5));

		moins = new JButton();
		moins.setPreferredSize(new Dimension(btnHeight, btnHeight));
		moins.setMargin(new Insets(5, 5, 5, 5));

		plus.setText("+");
		plus.setFont(plus.getFont().deriveFont(24.0f));
		moins.setText("-");
		moins.setFont(moins.getFont().deriveFont(24.0f));

		// ajout de bouttonPlusListener aux bouttons plus et moins

		plus.addActionListener(ctrl.new ObsAddIng(ingredient,portions,true));
		moins.addActionListener(ctrl.new ObsAddIng(ingredient,portions,false));

		setBackground(Color.white);
		setPreferredSize(new Dimension(250, 250));

		// set els images d'ingredients
		imageIngred = new JLabel();
		imageIngred.setIcon(new ImageIcon(setIcon(ingred.getPath(), imHeight).getImage()));
		imageIngred.setBorder(new LineBorder(Color.BLACK));

		nom = new JLabel(ingred.getNom());
		nom.setFont(nom.getFont().deriveFont(24.0f));

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 1;
		c.gridy = 0;

		add(plus, c);

		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;

		add(portions, c);
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
	public ImageIcon setIcon(String path, int hauteur) {
		
		ImageIcon imageI = new ImageIcon(path);
		
		java.awt.Image oof = imageI.getImage();
		java.awt.Image resized = oof.getScaledInstance(hauteur, hauteur, java.awt.Image.SCALE_SMOOTH);
		imageI.setImage(resized);		
		
		return imageI;
		
	}
}