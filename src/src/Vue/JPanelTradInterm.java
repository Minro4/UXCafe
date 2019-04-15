package src.Vue;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class JPanelTradInterm extends JPanelTrad{
	private static final long serialVersionUID = 1L;
	ArrayList<JPanelTrad> pnlTrads = new ArrayList<JPanelTrad>();
	
	public JPanelTradInterm(JPanelTrad[]... pnlTrads) {
		for (int i = 0; i < pnlTrads.length; i++) {
			for (int j = 0; j < pnlTrads[i].length; j++) {
				this.pnlTrads.add(pnlTrads[i][j]);
			}
		}
	}
	
	@Override
	public void setTexte(ResourceBundle bdlLangue) {
		for (JPanelTrad jPanelTrad : pnlTrads) {
			jPanelTrad.setTexte(bdlLangue);
		}
		
	}

}
