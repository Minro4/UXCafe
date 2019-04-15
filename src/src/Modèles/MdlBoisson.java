package src.Modèles;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;


public abstract class MdlBoisson {
	
	protected Taille taille;
	protected String imgPath;
	protected static ArrayList<ComposanteBreuvage> lcsList = new ArrayList<ComposanteBreuvage>();
	protected HashMap<Lait, Integer> laits = new HashMap<Lait, Integer>();	
	protected PropertyChangeSupport support;
	
	public MdlBoisson(String path) {
		
		Lait lait = new Lait("Lait", "Images/lait.png");
		lcsList.add(lait);
		this.imgPath=path;
		support = new PropertyChangeSupport(this);
	}
	
	public Taille getTaille() {
		return taille;
	}
	public abstract Taille[] getListTaille();
	
	abstract public ComposanteBreuvage[] getListLcs();
	
	public void setTaille(Taille taille) {
		this.taille=taille;
		CheckAndAdjustLait();
		
	}
	
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
