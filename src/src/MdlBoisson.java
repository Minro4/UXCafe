package src;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import src.CtrlBreuvages.breuvageListener;

public abstract class MdlBoisson {
	
	
	protected Taille taille;
	protected String imgPath;
	protected HashMap<Lait, Integer> laits = new HashMap<Lait, Integer>();
	
	protected PropertyChangeSupport support;
	
	public MdlBoisson(Taille taille, String path) {
		this.taille=taille;
		//lait=new AbstractMap.SimpleEntry<Lait, Integer>(la, 0);
		//creme=new AbstractMap.SimpleEntry<Creme, Integer>(cr, 0);
		this.imgPath=path;
		support = new PropertyChangeSupport(this);
	}
	
	public Taille getTaille() {
		return taille;
	}
	
	abstract public void setTaille(Taille taille);
	
	abstract public int addIngredient(ComposanteBreuvage ing, int nbrPortion);
	
	
	
	protected int setLaitPortion(Lait lait,int nbrPortion) {
		if (lait.valide(nbrPortion, getQuantite())) {
			laits.put(lait, nbrPortion);
			return nbrPortion;
		}
		return laits.containsKey(lait) ? laits.get(lait) : 0;
	}
	
	
	// Est utilis� lorsque l'on ajoute un autre ingr�dient, car puisque la quantite
	// de caf� est r�duite,
	// il est possible que la quantite de lait ne soit plus valide
	abstract protected void CheckAndAdjustLait();
	
	
	
	abstract public int getQuantite();
	
	abstract public String[][] getRapport();

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}
	

}
