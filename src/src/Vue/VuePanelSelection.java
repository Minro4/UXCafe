package src.Vue;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VuePanelSelection extends JPanel{

	private static final long serialVersionUID = 1L;
	private ArrayList<PnlChoix> pnlChoixs = new ArrayList<PnlChoix>();
	
	public VuePanelSelection(String[] noms, String[] paths, ActionListener[] actionListeners) {
		
		Color bgColor = new Color(250, 200, 155);
		setBackground(bgColor);
		setLayout(new FlowLayout(FlowLayout.CENTER,40,100));
		
		for (int i = 0; i < noms.length; i++) {
			PnlChoix pnlChoix = new PnlChoix(noms[i], paths[i],bgColor, actionListeners[i]);
			pnlChoixs.add(pnlChoix);	
			add(pnlChoix);
		}		
	}
	
	public void setTexte(ResourceBundle bdlLangue) {
		for (PnlChoix pnlChoix : pnlChoixs) {
			pnlChoix.setTexte(bdlLangue);
		}
	}

	
	public class PnlChoix extends JPanel{

		
		private static final long serialVersionUID = 1L;
		private JLabel lbTexte;
		private String langueKey;
		
		public PnlChoix(String txt, String path,Color bgColor, ActionListener actionListener) {
			langueKey = txt;
			setBackground(bgColor);
			JButton button = new JButton();
			lbTexte = new JLabel(txt);
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
		
		public void setTexte(ResourceBundle bdlLangue) {
			lbTexte.setText(bdlLangue.getString(langueKey));
		}
		
	}
	
}
