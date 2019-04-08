package src;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.AbstractMap;
import java.util.Map;

import src.CtrlBreuvages.breuvageListener;

public abstract class MdlBoisson {
	
	
	protected Taille taille;
	protected String imgPath;
	protected Map.Entry<Lait, Integer> lait;
	protected Map.Entry<Creme, Integer> creme;
	
	protected PropertyChangeSupport support;
	
	public MdlBoisson(Taille ta, Lait la, Creme cr, String pt) {
		taille=ta;
		lait=new AbstractMap.SimpleEntry<Lait, Integer>(la, 0);
		creme=new AbstractMap.SimpleEntry<Creme, Integer>(cr, 0);
		imgPath=pt;
	}
	
	public Taille getTaille() {
		return taille;
	}
	
	public void setTaille(Taille taille) {
		this.taille = taille;
		CheckAndAdjustCreme();
		CheckAndAdjustLait();
	}
	
	abstract public int addIngredient(ComposanteBreuvage ing, int nbrPortion);
	
	protected int setCremePortion(int nbrPortion) {
		if (creme.getKey().valide(nbrPortion, taille.getCapacite())) {
			creme.setValue(nbrPortion);
			CheckAndAdjustLait();
			return nbrPortion;
		}
		return creme.getValue();
	}
	
	protected int setLaitPortion(int nbrPortion) {
		if (lait.getKey().valide(nbrPortion, getQuantite())) {
			lait.setValue(nbrPortion);
			return nbrPortion;
		}
		return lait.getValue();
	}
	
	// Est utilis� lorsque l'on ajoute un autre ingr�dient, car puisque la quantite
	// de caf� est r�duite,
	// il est possible que la quantite de lait ne soit plus valide
	abstract protected void CheckAndAdjustLait();
	
	abstract protected void CheckAndAdjustCreme();
	
	abstract public int getQuantite();
	
	abstract public String[][] getRapport();

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}
	

}
