package src.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import src.Vue.SeparatedPanel;

public class CtrlJetsNav {

	private int ctr;
	private SeparatedPanel sp;
	
	public CtrlJetsNav(int ctr, SeparatedPanel sp) {
		this.ctr = ctr;
		this.sp = sp;
		sp.getNext().addActionListener(new BouttonSuivantListener());
		sp.getPrevious().addActionListener(new BouttonRetourListener());
	}

	public void OnClickSuivant() {
		OnClickBtnOnglet(ctr + 1);
	}

	public void OnClickRetour() {
		OnClickBtnOnglet(ctr - 1);
	}	

	public void OnClickBtnOnglet(int index) {
		if(index < 0)
			ctr = 0;
		else if(index > sp.getNbPane() -1)
			ctr = sp.getNbPane() -1;
		else
			ctr = index;
		
		sp.setVisible(ctr);
		
		
	}
	public class BouttonSuivantListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			OnClickSuivant();
		}
	}
	public class BouttonRetourListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			OnClickRetour();
		}
	}
}
