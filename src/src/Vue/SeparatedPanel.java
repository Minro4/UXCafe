package src.Vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import sun.security.util.Length;

public class SeparatedPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel paneHolder;
	JPanel buttonHolder;
	JPanel paneJets;
	JButton next;
	JButton previous;
	ArrayList<JPanel> listSousPane;
	
	int visible = 0;
	
	public SeparatedPanel(JPanel[] jets, int nbPref) {
		
		buttonHolder = new JPanel();
		paneJets = new JPanel();
		
		next = new JButton();
		previous = new JButton();
		
		next.setIcon(new ImageIcon("Images/arrowRight.png"));
		previous.setIcon(new ImageIcon("Images/arrowLeft.png"));
		
		listSousPane = getSubListPanel(jets, nbPref);
		
		for(int i = 0; i < listSousPane.size(); i++) 
		{
			if(i== 0) {
				paneJets.add(listSousPane.get(i));
			}
			else {
				listSousPane.get(i).setVisible(false);
				paneJets.add(listSousPane.get(i));
			}
			
		}
		
		buttonHolder.add(next);
		buttonHolder.add(previous);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridy= 0;
		add(paneJets);
		c.gridy =1;
		add(buttonHolder);
		
	}
	public int getNbPane() {
		return listSousPane.size();
	}	
	public JButton getNext() {
		return next;
	}

	public void setNext(JButton next) {
		this.next = next;
	}

	public JButton getPrevious() {
		return previous;
	}

	public void setPrevious(JButton previous) {
		this.previous = previous;
	}

	private static ArrayList<JPanel> getSubListPanel(JPanel[] jets, int nbPrefPerPanel) {
		ArrayList<JPanel> subPanesList = new ArrayList<JPanel>();

		for (int i = 0; i < nbPrefPerPanel; i++) {
			JPanel j = new JPanel();
			int position = i + (subPanesList.size() * nbPrefPerPanel);

			if (position < jets.length - 1) {
				j.add(jets[i + (subPanesList.size() * nbPrefPerPanel)]);
				if (i == nbPrefPerPanel - 1) {
					subPanesList.add(j);
				}
			} 
			else 
			{
				subPanesList.add(j);
				break;
			}
		}

		return subPanesList;
	}
	public void setVisible(int ctr) {
		listSousPane.get(visible).setVisible(false);
		listSousPane.get(ctr).setVisible(true);
		visible = ctr;
	}

}
