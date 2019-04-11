package src.Vue;

import javax.swing.JPanel;

public class VueGenerationBoisson {

	private static String[] nomTitres = { "Sélectionnez la taille de votre café, ainsi que sa torréfaction",
			"Sélectionnez vos jets de saveur", "Personnaliser le tout" };
	private static String[] ongletNoms = { "Taille et Torefaction", "Jets de Saveurs", "Lait, Crème et Sucre" };

	public static JPanel[] getCafePanels(JPanel[] tailles, JPanel[] torefs, JPanel[] jets, JPanel[] lcs) {
		JPanel[] pnlWindows = new JPanel[3];
		pnlWindows[0] = PanelCreation.getMultiplePanel(tailles, torefs);
		pnlWindows[1] = PanelCreation.getSeparatedPanel(jets,6);
		pnlWindows[2] = PanelCreation.getGenericPanel(lcs);

		return pnlWindows;
	}
	
	public static JPanel[] getGenericPanels(JPanel[] ... panels) {
		JPanel[] pnlWindows = new JPanel[panels.length];
		for (int i = 0; i < pnlWindows.length; i++) {
			pnlWindows[0] = PanelCreation.getGenericPanel(panels[i]);
		}
		return pnlWindows;
	}

}
