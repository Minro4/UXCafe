package src.Vue;

import javax.swing.JPanel;

public class VueGenerationBoisson {

	/*
	 * private static String[] nomTitres = {
	 * "Sélectionnez la taille de votre café, ainsi que sa torréfaction",
	 * "Sélectionnez vos jets de saveur", "Personnaliser le tout" }; private static
	 * String[] ongletNoms = { "Taille et Torefaction", "Jets de Saveurs",
	 * "Lait, Crème et Sucre" };
	 */

	public static JPanelTrad[] getCafePanels(JPanelTrad[] tailles, JPanelTrad[] torefs, JPanelTrad[] jets,
			JPanelTrad[] lcs) {
		JPanelTrad[] pnlWindows = new JPanelTrad[3];
		pnlWindows[0] = VueUtils.getMultiplePanel(tailles, torefs);
		pnlWindows[1] = (JPanelTrad) VueUtils.getSeparatedPanel(jets, 6);
		pnlWindows[2] = VueUtils.getGenericPanel(lcs);

		return pnlWindows;
	}

	public static JPanelTrad[] getGenericPanels(JPanelTrad[]... panels) {
		JPanelTrad[] pnlWindows = new JPanelTrad[panels.length];
		for (int i = 0; i < pnlWindows.length; i++) {
			pnlWindows[i] = VueUtils.getGenericPanel(panels[i]);
		}
		return pnlWindows;
	}

}
