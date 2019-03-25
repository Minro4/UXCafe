package src;

import java.text.NumberFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Cafe {

	private final float ProportionSucreMax = 0.01f;
	private final float ProportionLaitMax = 0.5f;
	private final float ProportionCremeMax = 0.1f;
	private final int QuantiteParPortion = 15;

	private Taille taille;
	private int prtnSucre;
	private int prtnLait;
	private int prtnCreme;

	private HashMap<Jet,Integer> jets = new HashMap<Jet,Integer>();	

	
	public int getPortion(Jet jet) {
		return jets.get(jet);
	}

	public boolean addJetPortion(Jet jet,int nbrPortion) {
		nbrPortion += jets.get(jet);
		return setJetPortion(jet, nbrPortion);
	}
	public boolean setJetPortion(Jet jet,int nbrPortion) {
		if (nbrPortion > 0) {
			jets.put(jet, nbrPortion);
			return true;
		}
		else if (nbrPortion == 0){
			jets.remove(jet);
			return true;
		}
		return false;	
	}
	public boolean setSucrePortion(int prtnSucre) {
		int portionMax = (int) (taille.getCapacite() * ProportionSucreMax);
		if (portionMax <= prtnSucre) {
			this.prtnSucre = prtnSucre;
			return true;
		}
		return false;
	}
	public boolean setCremePortion(int nbrPortion) {
		int quantite = nbrPortion * QuantiteParPortion;
		int maxQuantite = (int) (ProportionCremeMax * taille.getCapacite());
		if (quantite <= maxQuantite && quantite >= 0) {
			prtnCreme = nbrPortion;
			return true;
		}
		return false;	
	}
	public boolean setLaitPortion(Jet ingredient,int nbrPortion) {
		int quantite = nbrPortion * QuantiteParPortion;
		int maxQuantite = (int) (ProportionLaitMax * getQuantiteCafe());
		if (quantite <= maxQuantite && quantite >= 0) {
			jets.put(ingredient, nbrPortion);
			return true;
		}
		return false;	
	}
	
	public int getQuantiteCafe() {
		int quantite = taille.getCapacite();
		if (jets.size() > 0)
			quantite -= Jet.getTailleportion() * taille.getCapacite();
		quantite -= (prtnLait+prtnCreme)*QuantiteParPortion;
		
		/*for (Map.Entry<Jet,Integer> entry : jets.entrySet()) {
			int quantiteIng = entry.getValue() * entry.getKey().getQuantParPortion();
	        quantite -= quantiteIng;
	    }*/
		
		return quantite<0?0:quantite;
	}

	public ArrayList<Map.Entry<String,Double>> getRapport() {
		ArrayList<Map.Entry<String,Double>> rapport = new ArrayList<Map.Entry<String,Double>>();
		rapport.add(createEntry(getQuantiteCafe() + " ml caf�:", taille.getPrix()));
		
		int nbrPrtnJet = nbrTotPortionsJet();
		for (Map.Entry<Jet,Integer> entry : jets.entrySet()) {
			double ratio = (double)entry.getValue()/nbrPrtnJet;
			String text = Jet.getQuantite(ratio, taille.getCapacite()) + " ml sirop " + entry.getKey().getNom();			
			rapport.add(createEntry(text,Jet.getPrix(ratio, taille.getCapacite())));
	    }
		Collections.sort(rapport, new Comparator<Map.Entry<String,Double>>(){
            public int compare(Map.Entry<String,Double> e1, Map.Entry<String,Double> e2) {
            	if (e1.getValue() > e2.getValue())
            		return 1;
            	if (e1.getValue() == e2.getValue())
            		return 0;
            	else
            		return -1;
             }
         });
		return rapport;
	}
	private Map.Entry<String, Double> createEntry(String key, Double value){
		return new AbstractMap.SimpleEntry<String,Double>(key, value); 
	}
	
	private int nbrTotPortionsJet() {
		int sum = 0;
		for (Map.Entry<Jet,Integer> entry : jets.entrySet()) {
		sum+= entry.getValue();
	    }
		return sum;
	}
	
	
}
