package src.Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VuePanelSelection extends JPanelTrad{

	private static final long serialVersionUID = 1L;
	private ArrayList<PnlChoix> pnlChoixs = new ArrayList<PnlChoix>();
	private JButton btnLangue;
	
	public VuePanelSelection(String[] keys, String[] paths, ActionListener[] actionListeners, ActionListener langueListener) {
		
		Color bgColor = new Color(250, 200, 155);
		setLayout(new GridBagLayout());
		setBackground(bgColor);
		JPanel btnHolder = new JPanel();
		btnHolder.setLayout(new BorderLayout());
		btnLangue = new JButton();
		btnLangue.setPreferredSize(new Dimension(100, 45));
		btnLangue.addActionListener(langueListener);
		btnHolder.add(btnLangue,BorderLayout.EAST);
		btnHolder.setBackground(bgColor);
		//setLayout();
		JPanel pnlSelection = new JPanel();
		pnlSelection.setLayout(new FlowLayout(FlowLayout.CENTER,40,100));
		pnlSelection.setBackground(bgColor);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_END;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty =0; 
		constraints.insets = new Insets(5, 5, 5, 5);
		add(btnHolder,constraints);
		constraints.weighty =1; 
		constraints.gridy = 1;
		add(pnlSelection,constraints);
		
		for (int i = 0; i < keys.length; i++) {
			PnlChoix pnlChoix = new PnlChoix(keys[i], paths[i],bgColor, actionListeners[i]);
			pnlChoix.setPreferredSize(new Dimension(150, 150));
			pnlChoixs.add(pnlChoix);	
			pnlSelection.add(pnlChoix);
		}		
	}
	
	@Override
	public void setTexte(ResourceBundle bdlLangue) {
		for (PnlChoix pnlChoix : pnlChoixs) {
			pnlChoix.setTexte(bdlLangue);
		}
		btnLangue.setText(bdlLangue.getString("langue"));
	}

	
	public class PnlChoix extends JPanelTrad{

		
		private static final long serialVersionUID = 1L;
		private JLabel lbTexte;
		private String langueKey;
		
		public PnlChoix(String key, String path,Color bgColor, ActionListener actionListener) {
			langueKey = key;
			setBackground(bgColor);
			JButton button = new JButton();
			lbTexte = new JLabel();
			lbTexte.setFont(lbTexte.getFont().deriveFont(20.0f));
			//button.setText(txt);
			button.setIcon(VueUtils.setIcon(path, 100));
			button.addActionListener(actionListener);
			
			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.gridx=0;
			c.gridy=0;
			add(button,c);
			c.gridy = 1;
			add(lbTexte,c);
		}
		
		public void setTexte(ResourceBundle bdlLangue) 
		{
			lbTexte.setText(bdlLangue.getString(langueKey));
		}
		
	}
	
}
