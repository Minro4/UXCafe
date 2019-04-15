package src.Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class VueUtils {
	public static JPanel getGenericPanel(JPanel[] composantes) {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new FlowLayout());
		jPanel.setBackground(Color.white);
		for (JPanel composante : composantes) {
			jPanel.add(composante);
		}

		return jPanel;
	}

	public static JPanel getMultiplePanel(JPanel[]... composantes) {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(composantes.length, 1));
		jPanel.setBackground(Color.white);

		for (int i = 0; i < composantes.length; i++) {
			jPanel.add(getGenericPanel(composantes[i]));
		}

		return jPanel;
	}
	public static JPanel getSeparatedPanel(JPanel[] jets, int nbPref, ActionListener suivant,
			ActionListener precedant) {
		JPanel paneHolder = new JPanel();
		JPanel buttonHolder = new JPanel();
		ArrayList<JPanel> listSousPane = getSubListPanel(jets, nbPref);

		JButton next = new JButton();
		next.setIcon(new ImageIcon("Images/arrowRight.png"));
		JButton previous = new JButton();
		previous.setIcon(new ImageIcon("Images/arrowLeft.png"));

		buttonHolder.add(next);
		buttonHolder.add(previous);

		return paneHolder;
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
			} else {
				subPanesList.add(j);
				break;
			}
		}

		return subPanesList;
	}
}
