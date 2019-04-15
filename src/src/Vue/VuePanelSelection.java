package src.Vue;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VuePanelSelection extends JPanel{

	private static final long serialVersionUID = 1L;

	public VuePanelSelection(String[] noms, String[] paths, ActionListener[] actionListeners) {
		
		Color bgColor = new Color(250, 200, 155);
		setBackground(bgColor);
		setLayout(new FlowLayout(FlowLayout.CENTER,40,100));
		
		for (int i = 0; i < noms.length; i++) {
			add(new PnlChoix(noms[i], paths[i],bgColor, actionListeners[i]));
		}
	}

	
	public class PnlChoix extends JPanel{

		private static final long serialVersionUID = 1L;

		public PnlChoix(String txt, String path,Color bgColor, ActionListener actionListener) {
			setBackground(bgColor);
			JButton button = new JButton();
			JLabel label = new JLabel(txt);
			label.setFont(label.getFont().deriveFont(20.0f));
			//button.setText(txt);
			button.setIcon(VueUtils.setIcon(path, 100));
			button.addActionListener(actionListener);
			
			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.gridx=0;
			c.gridy=0;
			add(button,c);
			c.gridy = 1;
			add(label,c);
		}
	}
	
}
