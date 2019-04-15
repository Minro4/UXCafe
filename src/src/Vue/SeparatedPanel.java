package src.Vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class SeparatedPanel extends JPanelTrad {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel paneHolder;
	JPanel buttonHolder;
	JPanelTradInterm paneJets;
	JButton next;
	JButton previous;
	ArrayList<JPanelTrad> listSousPane;

	int visible = 0;

	public SeparatedPanel(JPanelTrad[] jets, int nbPref) {

		buttonHolder = new JPanel();
		paneJets = new JPanelTradInterm();

		next = new JButton();
		previous = new JButton();

		next.setIcon(new ImageIcon("Images/arrowRight.png"));
		previous.setIcon(new ImageIcon("Images/arrowLeft.png"));

		listSousPane = getSubListPanel(jets, nbPref);

		for (int i = 0; i < listSousPane.size(); i++) {
			if (i == 0) {
				paneJets.add(listSousPane.get(i));
				paneJets.addTrad(listSousPane.get(i));
			} else {
				listSousPane.get(i).setVisible(false);
				paneJets.add(listSousPane.get(i));
			}

		}

		buttonHolder.add(next);
		buttonHolder.add(previous);

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridy = 0;
		add(paneJets, c);
		c.gridy = 1;
		c.weighty = 0.1;
		add(buttonHolder, c);

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

	private static ArrayList<JPanelTrad> getSubListPanel(JPanelTrad[] jets, int nbPrefPerPanel) {
		ArrayList<JPanelTrad> subPanesList = new ArrayList<JPanelTrad>();
		boolean xd = true;
		while (xd) {
			for (int i = 0; i < nbPrefPerPanel; i++) {
				JPanelTradInterm j = new JPanelTradInterm();
				int position = i + (subPanesList.size() * nbPrefPerPanel);

				if (position < jets.length - 1) {
					j.add(jets[i + (subPanesList.size() * nbPrefPerPanel)]);
					j.addTrad(jets[i + (subPanesList.size() * nbPrefPerPanel)]);
					if (i == nbPrefPerPanel - 1) {
						subPanesList.add(j);
					}
				} else {
					subPanesList.add(j);
					xd = false;
					break;
				}
			}
		}

		return subPanesList;
	}

	public void setVisible(int ctr) {
		listSousPane.get(visible).setVisible(false);
		listSousPane.get(ctr).setVisible(true);
		visible = ctr;
	}

	@Override
	public void setTexte(ResourceBundle bdlLangue) {
		// TODO Auto-generated method stub

	}

}
