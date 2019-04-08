//********************************************************************
// Cafe.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde, Guillaume St-Louis
//
// Modèle du café
//********************************************************************

package src;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.NumberFormat;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class Cafe {

	private Taille taille;
	private ComposanteCafe torefaction;
	private Map.Entry<Sucre, Integer> sucre;
	private Map.Entry<Lait, Integer> lait;
	private Map.Entry<Creme, Integer> creme;

	private HashMap<Jet, Integer> jets = new HashMap<Jet, Integer>();

	private PropertyChangeSupport support;

	public Cafe(Taille taille, ComposanteCafe torefaction, Sucre sucre, Lait lait, Creme creme) {
		super();
		this.taille = taille;
		this.torefaction = torefaction;
		this.sucre = new AbstractMap.SimpleEntry<Sucre, Integer>(sucre, 0);
		this.lait = new AbstractMap.SimpleEntry<Lait, Integer>(lait, 0);
		this.creme = new AbstractMap.SimpleEntry<Creme, Integer>(creme, 0);
		support = new PropertyChangeSupport(this);
	}

	public Taille getTaille() {
		return taille;
	}

	public void setTaille(Taille taille) {
		this.taille = taille;
		CheckAndAdjustCreme();
		CheckAndAdjustSucre();
		CheckAndAdjustLait();
	}

	public void setTorefaction(ComposanteCafe torefaction) {
		this.torefaction = torefaction;
	}

	public int getPortion(Jet jet) {
		return jets.get(jet);
	}

	public int addIngredient(ComposanteCafe ing, int nbrPortion) {
		if (ing instanceof Jet) {
			int dj = jets.containsKey(ing) ? jets.get(ing) : 0;
			return setJetPortion((Jet) ing, nbrPortion + dj);
		} else if (ing instanceof Lait) {
			return setLaitPortion(nbrPortion + lait.getValue());
		} else if (ing instanceof Creme) {
			return setCremePortion(nbrPortion + creme.getValue());
		} else if (ing instanceof Sucre) {
			return setSucrePortion(nbrPortion + sucre.getValue());
		}
		return 0;
	}

	/*
	 * public boolean addJetPortion(Jet jet, int nbrPortion) { nbrPortion +=
	 * jets.get(jet); return setJetPortion(jet, nbrPortion); }
	 */

	private int setJetPortion(Jet jet, int nbrPortion) {
		if (nbrPortion > 0) {
			jets.put(jet, nbrPortion);
			CheckAndAdjustLait();
			return nbrPortion;
		} else if (nbrPortion == 0) {
			jets.remove(jet);
		}
		return 0;
	}

	private int setSucrePortion(int prtnSucre) {
		if (sucre.getKey().valide(prtnSucre, taille.getCapacite())) {
			sucre.setValue(prtnSucre);
			return prtnSucre;
		}
		return sucre.getValue();
	}

	private int setCremePortion(int nbrPortion) {
		if (creme.getKey().valide(nbrPortion, taille.getCapacite())) {
			creme.setValue(nbrPortion);
			CheckAndAdjustLait();
			return nbrPortion;
		}
		return creme.getValue();
	}

	private int setLaitPortion(int nbrPortion) {
		if (lait.getKey().valide(nbrPortion, getQuantiteCafe())) {
			lait.setValue(nbrPortion);
			return nbrPortion;
		}
		return lait.getValue();
	}

	// Est utilis� lorsque l'on ajoute un autre ingr�dient, car puisque la quantite
	// de caf� est r�duite,
	// il est possible que la quantite de lait ne soit plus valide
	private void CheckAndAdjustLait() {
		while (!lait.getKey().valide(lait.getValue(), getQuantiteCafe()) && lait.getValue() > 0) {
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
	private void CheckAndAdjustSucre() {
		while (!sucre.getKey().valide(sucre.getValue(), taille.getCapacite()) && sucre.getValue() > 0) {
			sucre.setValue(sucre.getValue()-1);		
		}
		support.firePropertyChange("Sucre", sucre, sucre.getValue());

	}

	public int getQuantiteCafe() {
		int quantite = taille.getCapacite();
		if (jets.size() > 0)
			quantite -= Jet.getProportion() * taille.getCapacite();
		quantite -= lait.getKey().getQuantite(lait.getValue());
		quantite -= creme.getKey().getQuantite(creme.getValue());

		return quantite < 0 ? 0 : quantite;
	}

	public String[][] getRapport() {

		NumberFormat formatter = NumberFormat.getCurrencyInstance();

		int nbrPrtnJet = nbrTotPortionsJet();
		int totalLigne = 3 + jets.size() +((jets.size() > 0) ? 1 : 0);
		if (lait.getValue() > 0)
			totalLigne++;
		if (creme.getValue() > 0)
			totalLigne++;
		if (sucre.getValue() > 0)
			totalLigne++;

		String[][] rapport = new String[totalLigne][2];
		int currentIndex = 0;
		double prixTotal = 0;

		rapport[currentIndex][0] = getQuantiteCafe() + " ml cafe:";
		rapport[currentIndex++][1] = formatter.format(taille.getPrix());
		prixTotal += taille.getPrix();

		{
			String text = "Toréfaction: ";

			rapport[currentIndex][0] = text;
			rapport[currentIndex++][1] = torefaction.getNom();
		}

		{ // On ajoute les jets

			for (Map.Entry<Jet, Integer> entry : jets.entrySet()) {
				double ratio = (double) entry.getValue() / nbrPrtnJet;
				String text = entry.getKey().rapport(ratio, taille.getCapacite());
				rapport[currentIndex][0] = text;
				
				if (jets.size() == 1) {
					double prix = Jet.getPrix(1, taille.getCapacite());
					prixTotal += prix;
					rapport[currentIndex++][1] = formatter.format(prix);
				}
				else
				rapport[currentIndex++][1] = "";
			}
			if (jets.size() > 1) {
				double prix = Jet.getPrix(1, taille.getCapacite());
				prixTotal += prix;
				rapport[currentIndex][0] = "Prix des jets:";
				rapport[currentIndex++][1] = formatter.format(prix);
			}

		}

		{// On ajoute le lait, creme et sucre
			String text;
			double prix;
			if (lait.getValue() > 0) {
				text = lait.getKey().rapport(lait.getValue());
				prix = lait.getKey().getPrix(lait.getValue());
				prixTotal += prix;
				rapport[currentIndex][0] = text;
				rapport[currentIndex++][1] = formatter.format(prix);
			}
			if (creme.getValue() > 0) {
				text = creme.getKey().rapport(creme.getValue());
				prix = creme.getKey().getPrix(creme.getValue());
				prixTotal += prix;
				rapport[currentIndex][0] = text;
				rapport[currentIndex++][1] = formatter.format(prix);
			}
			if (sucre.getValue() > 0) {
				text = sucre.getKey().rapport(sucre.getValue());
				prix = sucre.getKey().getPrix();
				prixTotal += prix;
				rapport[currentIndex][0] = text;
				rapport[currentIndex++][1] = formatter.format(prix);
			}
		}

		// On ajoute la torrefaction

		/*
		 * Arrays.sort(rapport, new Comparator<String[]>() { public int compare(String[]
		 * o1, String[] o2) { if (o1[1] == null && o2[1] == null) return 0; if (o1[1] ==
		 * null) return 1; if (o2[1] == null) return -1; return
		 * extractInt(o2[1])-extractInt(o1[1]); }
		 * 
		 * int extractInt(String s) { String num = s.replaceAll("\\D", ""); // return 0
		 * if no digits found return num.isEmpty() ? 0 : Integer.parseInt(num); } });
		 */

		rapport[currentIndex][0] = "Total:";
		rapport[currentIndex++][1] = formatter.format(prixTotal);

		return rapport;
	}
	

	private int nbrTotPortionsJet() {
		int sum = 0;
		for (Map.Entry<Jet, Integer> entry : jets.entrySet()) {
			sum += entry.getValue();
		}
		return sum;
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}

}
