package src;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Cafe {

	private Taille taille;
	private Map.Entry<Sucre, Integer> sucre;
	private Map.Entry<Lait, Integer> lait;
	private Map.Entry<Creme, Integer> creme;

	private HashMap<Jet, Integer> jets = new HashMap<Jet, Integer>();

	public int getPortion(Jet jet) {
		return jets.get(jet);
	}

	public boolean addJetPortion(Jet jet, int nbrPortion) {
		nbrPortion += jets.get(jet);
		return setJetPortion(jet, nbrPortion);
	}

	public boolean setJetPortion(Jet jet, int nbrPortion) {
		if (nbrPortion > 0) {
			jets.put(jet, nbrPortion);
			return true;
		} else if (nbrPortion == 0) {
			jets.remove(jet);
			return true;
		}
		return false;
	}

	public boolean setSucrePortion(int prtnSucre) {
		if (sucre.getKey().valide(prtnSucre, taille.getCapacite())) {
			sucre.setValue(prtnSucre);
			return true;
		}
		return false;
	}

	public boolean setCremePortion(int nbrPortion) {
		if (creme.getKey().valide(nbrPortion, taille.getCapacite())) {
			creme.setValue(nbrPortion);
			return true;
		}
		return false;
	}

	public boolean setLaitPortion(Jet ingredient, int nbrPortion) {
		if (lait.getKey().valide(nbrPortion, getQuantiteCafe())) {
			lait.setValue(nbrPortion);
			return true;
		}
		return false;
	}

	public int getQuantiteCafe() {
		int quantite = taille.getCapacite();
		if (jets.size() > 0)
			quantite -= Jet.getTailleportion() * taille.getCapacite();
		quantite -= lait.getKey().getQuantite(lait.getValue());
		quantite -= creme.getKey().getQuantite(creme.getValue());

		return quantite < 0 ? 0 : quantite;
	}

	public ArrayList<Map.Entry<String, Double>> getRapport() {
		ArrayList<Map.Entry<String, Double>> rapport = new ArrayList<Map.Entry<String, Double>>();
		rapport.add(createEntry(getQuantiteCafe() + " ml caf�:", taille.getPrix()));

		double prixTotal = 0;
		
		{ // On ajoute les jets
			int nbrPrtnJet = nbrTotPortionsJet();
			for (Map.Entry<Jet, Integer> entry : jets.entrySet()) {
				double ratio = (double) entry.getValue() / nbrPrtnJet;
				String text = entry.getKey().rapport(ratio, taille.getCapacite());
				double prix = Jet.getPrix(ratio, taille.getCapacite());
				prixTotal += prix;
				rapport.add(createEntry(text, prix));
			}
		}

		{// On ajoute le lait, creme et sucre
			String text = lait.getKey().rapport(lait.getValue());
			double prix = lait.getKey().getPrix(lait.getValue());
			prixTotal += prix;
			rapport.add(createEntry(text, prix));

			text = creme.getKey().rapport(creme.getValue());
			prix = creme.getKey().getPrix(creme.getValue());
			prixTotal += prix;
			rapport.add(createEntry(text, prix));

			text = sucre.getKey().rapport(sucre.getValue());
			prix = sucre.getKey().getPrix();
			prixTotal += prix;
			rapport.add(createEntry(text, prix));
		}

		Collections.sort(rapport, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> e1, Map.Entry<String, Double> e2) {
				if (e1.getValue() > e2.getValue())
					return 1;
				if (e1.getValue() == e2.getValue())
					return 0;
				else
					return -1;
			}
		});

		rapport.add(createEntry("Total", prixTotal));

		return rapport;
	}

	private Map.Entry<String, Double> createEntry(String key, Double value) {
		return new AbstractMap.SimpleEntry<String, Double>(key, value);
	}

	private int nbrTotPortionsJet() {
		int sum = 0;
		for (Map.Entry<Jet, Integer> entry : jets.entrySet()) {
			sum += entry.getValue();
		}
		return sum;
	}

}
