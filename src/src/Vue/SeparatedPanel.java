package src.Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;


public class SeparatedPanel extends JPanelTrad {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel buttonHolder;
	JPanelTradInterm paneJets;
	JButton next;
	JButton previous;
	ArrayList<JPanelTrad> listSousPane;

	int visible = 0;

	public SeparatedPanel(JPanelTrad[] jets, int nbPref) {

		this.setBackground(Color.white);
		buttonHolder = new JPanel();
		buttonHolder.setBackground(Color.white);
		paneJets = new JPanelTradInterm();
		paneJets.setLayout(new FlowLayout());
		paneJets.setBackground(Color.white);

		next = new JButton();
		previous = new JButton();

		next.setIcon(setIc("Images/arrowRight.png", 16));
		previous.setIcon(setIc("Images/arrowLeft.png",16));
		

		listSousPane = getSubListPanel(jets, nbPref);

		for (int i = 0; i < listSousPane.size(); i++) {
			if (i == 0) {
				paneJets.add(listSousPane.get(i));
				paneJets.addTrad(listSousPane.get(i));
			} else {
				paneJets.add(listSousPane.get(i));
				listSousPane.get(i).setVisible(false);	
				paneJets.addTrad(listSousPane.get(i));
			}

		}
		buttonHolder.add(previous);
		buttonHolder.add(next);
		
		
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(paneJets);
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

	public ImageIcon setIc(String path, int hauteur) {
		
		ImageIcon imageI = new ImageIcon(path);
		
		java.awt.Image oof = imageI.getImage();
		java.awt.Image resized = oof.getScaledInstance(hauteur, hauteur, java.awt.Image.SCALE_SMOOTH);
		imageI.setImage(resized);		
		
		return imageI;
		
	}
	
	private static ArrayList<JPanelTrad> getSubListPanel(JPanelTrad[] jets, int nbPrefPerPanel) {
		ArrayList<JPanelTrad> subPanesList = new ArrayList<JPanelTrad>();
		boolean xd = true;
		while (xd) {
				JPanelTradInterm j = new JPanelTradInterm();
				j.setLayout(new FlowLayout());
				j.setPreferredSize(new Dimension(360, 252));
				j.setBackground(Color.white);
			for (int i = 0; i < nbPrefPerPanel; i++) {
				
				int position = i + (subPanesList.size() * nbPrefPerPanel);

				if (position < jets.length) {
					j.add(jets[position]);
					j.addTrad(jets[position]);
					
					if (i == nbPrefPerPanel - 1) {
						subPanesList.add(j);
					}
				}
				else {
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
		for (JPanelTrad jPanelTrad : listSousPane) {
			jPanelTrad.setTexte(bdlLangue);
		}

	}

}
