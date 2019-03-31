package src;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class ConfirmationPane extends JPanel {

	JButton btnConfirm;
	private JPanel panelRapport;
	
//	private Font font;
	
	
	public ConfirmationPane(ActionListener listener) {

		
		btnConfirm = new JButton("Confirmer");
		btnConfirm.setPreferredSize(new Dimension(170, 75));
		btnConfirm.setFont(btnConfirm.getFont().deriveFont(24f));
		btnConfirm.addActionListener(listener);
		
		panelRapport = new JPanel();		
		///panelRapport.setLayout(new BoxLayout(panelRapport, BoxLayout.Y_AXIS));
		panelRapport.setBackground(Color.white);		

		Font font = new Font("Dialog", Font.BOLD,24);		
		

		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		Insets inset = new Insets(30, 10, 0, 10);
		
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.weightx = 1;
		
		//constraints.insets = normalInset;
		constraints.gridx = 0;
		constraints.gridy = 0;
		add(panelRapport, constraints);
	

		constraints.gridx = 0;
		constraints.gridy = 1;
		//constraints.weighty = 1;
		add(btnConfirm, constraints);

	}

	public void update(String[][] rapport) {
		
		
		int defaultFontSize = 24;
		int nbrRow = 10;	
		
		int fontSize = (int)((float)(defaultFontSize*nbrRow)/rapport.length);
		if (fontSize > defaultFontSize)
			fontSize = defaultFontSize;
		Font font = new Font("Dialog", Font.BOLD,fontSize);
		
		panelRapport.removeAll();
		panelRapport.setLayout(new GridLayout(rapport.length, 2));
		
		for (int i = 0; i < rapport.length; i++) {

			JLabel labelText = new JLabel(rapport[i][0]);
			JLabel labelPrix = new JLabel(rapport[i][1]);
			labelText.setFont(font);
			labelPrix.setFont(font);
			panelRapport.add(labelText);
			panelRapport.add(labelPrix);
			
		}
	}
	
	
	
	
}