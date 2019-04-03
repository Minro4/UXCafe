package src;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

public class ConfirmationPane extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnConfirm;

	private JPanel panelRapport;
	
//	private Font font;
	
	
	public ConfirmationPane() {

		setPreferredSize(new Dimension(203, 0));
		JLabel lbTitre = new JLabel("Votre Cafe");
		lbTitre.setFont(lbTitre.getFont().deriveFont(20f));
		
		btnConfirm = new JButton("Confirmer");
		btnConfirm.setPreferredSize(new Dimension(140,40));
		btnConfirm.setFont(btnConfirm.getFont().deriveFont(16f));
		
		panelRapport = new JPanel();		
		panelRapport.setLayout(new GridBagLayout());
		panelRapport.setBackground(Color.lightGray);
		setBackground(Color.lightGray);

		Font font = new Font("Dialog", Font.BOLD,24);		
		

		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.weighty = 1;
		
		
		//constraints.insets = normalInset;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets =  new Insets(40, 0, 0, 0);
		add(lbTitre, constraints);
		//constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets =  new Insets(0, 0, 0, 0);
		add(panelRapport, constraints);
	
		constraints.fill = GridBagConstraints.NONE;
		constraints.weightx = 0;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weighty = 0;
		
		constraints.insets =  new Insets(0, 0, 40, 0);
		//constraints.weighty = 1;
		//constraints.anchor = GridBagConstraints.PAGE_END;
		add(btnConfirm, constraints);

	}

	public void update(String[][] rapport) {
		
		int defaultFontSize = 13;
		int nbrRow = 10;	
		
		int fontSize = (int)((float)(defaultFontSize*nbrRow)/rapport.length);
		if (fontSize > defaultFontSize)
			fontSize = defaultFontSize;
		Font font = new Font("Dialog", Font.PLAIN,fontSize);
		Font totFont = new Font("Dialog", Font.BOLD,fontSize);
		
		panelRapport.removeAll();
		//panelRapport.setLayout(new GridLayout(rapport.length, 2));
		GridBagConstraints constraints = new GridBagConstraints();
		//constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		Insets insetLeft = new Insets(2, 8, 2, 1);
		Insets insetRight = new Insets(2, 1, 2, 8);

		for (int i = 0; i < rapport.length; i++) {

			JLabel labelText = new JLabel(rapport[i][0], SwingConstants.LEFT);
			labelText.setPreferredSize(new Dimension(1000, 0));
			JLabel labelPrix = new JLabel(rapport[i][1]);
			if (i!= rapport.length -1) {
			labelText.setFont(font);
			labelPrix.setFont(font);
			}
			else {
				labelText.setFont(totFont);
				labelPrix.setFont(totFont);
			}
			constraints.gridx = 0;
			constraints.gridy = i;
			constraints.anchor = GridBagConstraints.LINE_START;
			constraints.insets = insetLeft;
			panelRapport.add(labelText,constraints);
			constraints.gridx = 1;
			constraints.anchor = GridBagConstraints.LINE_END;
			constraints.insets = insetRight;
			panelRapport.add(labelPrix,constraints);
			
		}
		
		repaint();
	}
	
	public JButton getBtnConfirm() {
		return btnConfirm;
	}
	
	
	
}