package src;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Arrays;

public class ConfirmationPane extends JPanel {

	private Cafe cafe;
	private JFrame frame;
	private NavigationManager navigationManager;
	private JPanel panelIng;
	private JPanel panelBouillon;
	private JLabel lbPrix;
	private JLabel lbTaille;
	
//	private Font font;
	
	
	public ConfirmationPane(Cafe cafe,JFrame frame) {
		this.cafe = cafe;
		this.frame = frame;
		JButton confirm;
		confirm = new JButton("Confirmer");
		confirm.setPreferredSize(new Dimension(170, 75));
		confirm.setFont(confirm.getFont().deriveFont(24f));
		confirm.addActionListener(new ConfirmerButtonListener());
		
		panelIng = new JPanel();
		panelIng.setLayout(new BoxLayout(panelIng, BoxLayout.Y_AXIS));
		panelIng.setBackground(Color.white);

		panelBouillon = new JPanel();
		panelBouillon.setLayout(new BoxLayout(panelBouillon, BoxLayout.Y_AXIS));
		panelBouillon.setBackground(Color.white);

		Font font = new Font("Dialog", Font.BOLD,24);
		
		JLabel labelBouillon = new JLabel("Bouillon: ", SwingConstants.RIGHT);
		labelBouillon.setFont(font);
		
		JLabel labelIng = new JLabel("Ingrédients: ", SwingConstants.RIGHT);
		labelIng.setFont(font);
			
		JLabel labelTaille = new JLabel("Taille: ", SwingConstants.RIGHT);
		labelTaille.setFont(font);
		
		JLabel labelPrix = new JLabel("Prix: ", SwingConstants.RIGHT);
		labelPrix.setFont(font);
		
		lbTaille = new JLabel();
		lbTaille.setFont(font);
		
		lbPrix = new JLabel("0.00$"); //Celui la a le chiffre en $
		lbPrix.setFont(font);

		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		Insets inset = new Insets(30, 10, 0, 10);
		//Insets topInset = new Insets(20, 10, 0, 10);
		
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		
		constraints.insets = inset;
		constraints.gridx = 1;
		constraints.gridy = 0;
		add(panelBouillon, constraints);
		
		//constraints.insets = normalInset;
		constraints.weighty = 0;
		constraints.gridx = 1;
		constraints.gridy = 1;
		add(panelIng, constraints);
	
		constraints.gridx = 0;
		constraints.gridy = 1;
		add(labelIng, constraints);
		
		//constraints.insets = topInset;
		constraints.gridx = 0;
		constraints.gridy = 0;
		add(labelBouillon, constraints);

		//constraints.insets = normalInset;
		constraints.gridx = 1;
		constraints.gridy = 3;
		//constraints.weighty = 0.3f;
		add(lbPrix, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		add(labelPrix, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		add(labelTaille, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		add(lbTaille, constraints);
		

		constraints.gridy = 4;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		//constraints.weighty = 1;
		add(confirm, constraints);

	}

	public void update() {
		Ingredient[] bouillons = soupe.getBouillons();
		IngredientInfo[] ingredients = soupe.getIngredients();
		
		//-------Ajuster la taille de la font en fonction du nombre d'ingredient
		int defaultFontSize = 24;
		int nbrRow = 10; //Nombre de colonne avant que la font soit rapetissé
		
		int nbrBouillon = (bouillons.length > 0)? bouillons.length: 1;
		int nbrIngred =  (ingredients.length > 0)? ingredients.length: 1;	//Va toujour prendre un moin une rangé
		
		int total = nbrBouillon + nbrIngred;
		int fontSize = (int)((float)(defaultFontSize*nbrRow)/total);
		if (fontSize > defaultFontSize)
			fontSize = defaultFontSize;
		Font font = new Font("Dialog", Font.BOLD,fontSize);
		
		
		panelBouillon.removeAll();
		for (int i = 0; i < bouillons.length; i++) {
			JLabel label = new JLabel((int)(100f/bouillons.length) + "% " + bouillons[i].getNom());
			panelBouillon.add(label);
			label.setFont(font);

		}
		if (bouillons.length == 0) {
		
			JLabel label = new JLabel("Aucun");
			panelBouillon.add(label);
			label.setFont(font);
		}
		
		Arrays.sort(ingredients);
		float[] portions = soupe.getGrammes(ingredients);
		panelIng.removeAll();
		for (int i = 0; i < ingredients.length; i++) {

			JLabel label = new JLabel((int)(portions[i]) + "g " + ingredients[i].getIngredient().getNom());
			panelIng.add(label);
			label.setFont(font);
		}
		if (ingredients.length == 0) {
		
			JLabel label = new JLabel("Aucun");
			panelIng.add(label);
			label.setFont(font);
		}
			
		lbTaille.setText(soupe.getTaille().getNom() );

		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format(soupe.getTaille().getPrix());
		lbPrix.setText(moneyString);
	}

	public void setNavigationManager(NavigationManager navigationManager) {
		this.navigationManager = navigationManager;
	}
	
	public class ConfirmerButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (soupe.hasBouillon()) {
				frame.dispose();
			}
			else {
				JOptionPane.showMessageDialog(frame,"Vous devez sélectionner au moin un bouillon.", "Attention", JOptionPane.DEFAULT_OPTION);
				navigationManager.OnClickBtnOnglet(1);
			}
			
		}
	}
	
	
}/*
	 * } public Ingredient[] getIngredients() { //
	 * System.out.println(ingredients.toArray(new Ingredient[0])); return
	 * ingredients.toArray(new Ingredient[0]);
	 */