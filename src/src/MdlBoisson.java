package src;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;

public abstract class MdlBoisson {
	
	
	protected Taille taille;
	protected Map.Entry<Lait, Integer> lait;
	protected Map.Entry<Creme, Integer> creme;
	
	protected PropertyChangeSupport support;
	
	public MdlBoisson() {
		
	}
	
	public Taille getTaille() {
		return taille;
	}
	
	public void setTaille(Taille taille) {
		this.taille = taille;
		CheckAndAdjustCreme();
		CheckAndAdjustLait();
	}
	
	abstract public int addIngredient();
	
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
	protected void CheckAndAdjustLait() {
		while (!lait.getKey().valide(lait.getValue(), getQuantite()) && lait.getValue() > 0) {
			lait.setValue(lait.getValue()-1);		
		}
		support.firePropertyChange("Lait", lait, lait.getValue());
		//lait.setValue(nbrPortion);

	}
	
	private void CheckAndAdjustCreme() {
		while (!creme.getKey().valide(creme.getValue(), taille.getCapacite()) && creme.getValue() > 0) {
			creme.setValue(creme.getValue()-1);		
		}
		support.firePropertyChange("Creme", creme, creme.getValue());

	}
	
	abstract public int getQuantite();
	
	abstract public String[][] getRapport();
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}
	

}
