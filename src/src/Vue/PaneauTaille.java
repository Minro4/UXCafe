//********************************************************************
// PaneauTaille.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde, Guillaume St-Louis
//
// Paneau de la taille
//********************************************************************

package src.Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class PaneauTaille extends JPanelTrad {

	private static final long serialVersionUID = 1L;

	String nomKey;
	JLabel lbNom;

	public PaneauTaille(String nomKey, String path, Double prix, int size, ButtonGroup buttonGroup, boolean setSelected,
			ActionListener listener) {

		this.nomKey = nomKey;

		JToggleButton toggleButton = new JToggleButton(VueUtils.setIcon(path, size));
		toggleButton.setBackground(Color.WHITE);
		setBackground(Color.white);

		lbNom = new JLabel();
		lbNom.setFont(lbNom.getFont().deriveFont(12.0f));
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		JLabel lbPrix = new JLabel(formatter.format(prix));
		lbPrix.setFont(lbPrix.getFont().deriveFont(12.0f));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		setPreferredSize(new Dimension(75, 80));

		toggleButton.addActionListener(listener);

		c.gridx = 0;
		c.gridy = 0;
		add(toggleButton, c);
		c.gridy = 1;
		add(lbNom, c);
		c.gridy = 2;
		add(lbPrix, c);

		toggleButton.setSelected(setSelected);

		buttonGroup.add(toggleButton);
	}

	@Override
	public void setTexte(ResourceBundle bdlLangue) {
		lbNom.setText(bdlLangue.getString(nomKey));
		
	}

}
