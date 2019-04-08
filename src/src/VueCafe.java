package src;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class VueCafe {
	
	private String[] nomTitres = { "Sélectionnez la taille de votre café, ainsi que sa torréfaction",
			"Sélectionnez vos jets de saveur", "Personnaliser le tout" };
	private String[] ongletNoms = { "Taille et Torefaction", "Jets de Saveurs", "Lait, Crème et Sucre" };
	
	public JPanel[] getPanels(PaneauTaille[] tailles, ComposantePane[] torefs, ComposantePane[] jets,
			ComposantePane[] lcs) {
		JPanel[] pnlCafe = new JPanel[3];

		pnlCafe[0] = new JPanel();
		pnlCafe[0].setLayout(new GridLayout(2, 1));
		pnlCafe[1] = new JPanel();
		pnlCafe[1].setLayout(new FlowLayout());
		pnlCafe[2] = new JPanel();
		pnlCafe[2].setLayout(new FlowLayout());

		for (int i = 0; i < pnlCafe.length; i++) {
			pnlCafe[i].setBackground(Color.white);
		}

		JPanel taillePane = new JPanel();
		taillePane.setBackground(Color.WHITE);
		addPanels(taillePane, tailles);

		JPanel torefPane = new JPanel();
		torefPane.setBackground(Color.WHITE);
		addPanels(torefPane, torefs);

		taillePane.setLayout(new FlowLayout());
		torefPane.setLayout(new FlowLayout());
 
		pnlCafe[0].add(taillePane);
		pnlCafe[0].add(torefPane);

		for (ComposantePane jet : jets) {
			pnlCafe[1].add(jet);
		}
		for (ComposantePane lc : lcs) {
			pnlCafe[2].add(lc);
		}

		/*
		 * ComposantePane pnlLait = new ComposantePane(lait, hauteur, ctrl);
		 * tfLaitPortion = pnlLait.getTfPortion(); pnlCafe[2].add(pnlLait);
		 * 
		 * ComposantePane pnlCreme = new ComposantePane(creme, hauteur, ctrl);
		 * tfCremePortion = pnlCreme.getTfPortion(); pnlCafe[2].add(pnlCreme);
		 * 
		 * ComposantePane pnlSucre = new ComposantePane(sucre, hauteur, ctrl);
		 * tfSucrePortion = pnlSucre.getTfPortion(); pnlCafe[2].add(pnlSucre);
		 */

		return pnlCafe;

	}

	private void addPanels(JPanel panel, JPanel[] cPanels) {
		// panel.setLayout(new FlowLayout());
		// panel.setAlignmentY(Component.CENTER_ALIGNMENT);

		for (JPanel cPanel : cPanels) {
			panel.add(cPanel);
		}
	}
	/*
	 * public void panelToref(JPanel panel, ComposantePane[] torefs) { ButtonGroup
	 * btnGroup = new ButtonGroup(); panel.setLayout(new FlowLayout());
	 * //panel.setAlignmentY(Component.CENTER_ALIGNMENT);
	 * 
	 * for (ComposantePane toref : torefs) { panel.add(toref); } } public void
	 * setPanelJet(ArrayList<Jet> jetList, Integer hauteur, CtrlBreuvages ctrl) {
	 * 
	 * for (Jet j : jetList) {
	 * 
	 * pnlCafe[1].add(new ComposantePane(j, hauteur, ctrl));
	 * 
	 * } validate(); }
	 */
}
