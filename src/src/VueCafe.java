package src;

import javax.swing.JPanel;

public class VueCafe {

	private static String[] nomTitres = { "Sélectionnez la taille de votre café, ainsi que sa torréfaction",
			"Sélectionnez vos jets de saveur", "Personnaliser le tout" };
	private static String[] ongletNoms = { "Taille et Torefaction", "Jets de Saveurs", "Lait, Crème et Sucre" };

	public static JPanel[] getPanels(JPanel[] tailles, JPanel[] torefs, JPanel[] jets, JPanel[] lcs) {
		JPanel[] pnlCafe = new JPanel[3];
		pnlCafe[0] = PanelCreation.getMultiplePanel(tailles, torefs);
		pnlCafe[1] = PanelCreation.getGenericPanel(jets);
		pnlCafe[2] = PanelCreation.getGenericPanel(lcs);

		return pnlCafe;
	}

	public static String[] getNomTitres() {
		return nomTitres;
	}

	public static String[] getOngletNoms() {
		return ongletNoms;
	}


}
