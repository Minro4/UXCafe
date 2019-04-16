//********************************************************************
// Cafe.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde
//
// Modèle du café
//********************************************************************

package src.Modèles;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import src.Misc;

public class MdlCafe extends MdlBoisson {

	private ComposanteBreuvage torefaction;
	private HashMap<Sucre, Integer> sucres = new HashMap<Sucre, Integer>();
	private HashMap<Creme, Integer> cremes = new HashMap<Creme, Integer>();
	
	private HashMap<Jet, Integer> jets = new HashMap<Jet, Integer>();	
	
	public MdlCafe(String imgPath) {
		super(imgPath);
		
		taille=MdlComposantesDB.getTaillesCafe()[2];
		torefaction=MdlComposantesDB.getTorefs()[1];

		// this.sucre = new AbstractMap.SimpleEntry<Sucre, Integer>(sucre, 0);
		//support = new PropertyChangeSupport(this);
	}
	
	public void setTaille(Taille taille) {
		this.taille = taille;
		CheckAndAdjustLait();
		CheckAndAdjustCreme();
		CheckAndAdjustSucre();
	}

	public void setTorefaction(ComposanteBreuvage torefaction) {
		this.torefaction = torefaction;
	}

	public int getPortion(Jet jet) {
		return jets.get(jet);
	}
	public static ComposanteBreuvage[] getListToref(){
		return MdlComposantesDB.getTorefs();
	}
	public static Jet[] getListJet(){
		return MdlComposantesDB.getJets();
	}
	
	
	

	public int addIngredient(ComposanteBreuvage ing, int nbrPortion) {
		if (ing instanceof Jet) {
			int dj = jets.containsKey(ing) ? jets.get(ing) : 0;
			return setJetPortion((Jet) ing, nbrPortion + dj);
		} else if (ing instanceof Lait) {
			int dj = laits.containsKey(ing) ? laits.get(ing) : 0;
			return setLaitPortion((Lait) ing, nbrPortion + dj);
		} else if (ing instanceof Creme) {
			int dj = cremes.containsKey(ing) ? cremes.get(ing) : 0;
			return setCremePortion((Creme) ing, nbrPortion + dj);
		} else if (ing instanceof Sucre) {
			int dj = sucres.containsKey(ing) ? sucres.get(ing) : 0;
			return setSucrePortion((Sucre) ing, nbrPortion + dj);
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

	private int setSucrePortion(Sucre sucre, int prtnSucre) {
		int totalSucre = getTotal(sucres);
		int diff = prtnSucre - sucres.get(sucre);
		if (sucre.valide(totalSucre + diff, taille.getCapacite())) {
			sucres.put(sucre, prtnSucre);
			// sucre.setValue(prtnSucre);
			return prtnSucre;
		}
		return sucres.containsKey(sucre) ? sucres.get(sucre) : 0;
	}

	private int setCremePortion(Creme creme, int nbrPortion) {
		if (creme.valide(nbrPortion, taille.getCapacite())) {
			cremes.put(creme, nbrPortion);
			// creme.setValue(nbrPortion);
			CheckAndAdjustLait();
			return nbrPortion;
		}
		return cremes.containsKey(creme) ? cremes.get(creme) : 0;
	}

	// Est utilis� lorsque l'on ajoute un autre ingr�dient, car puisque la quantite
	// de caf� est r�duite,
	// il est possible que la quantite de lait ne soit plus valide

	

	public int getQuantite() {
		int quantite = taille.getCapacite();
		if (jets.size() > 0)
			quantite -= Jet.getProportion() * taille.getCapacite();
		for (Map.Entry<Lait, Integer> lait : laits.entrySet()) {
			quantite -= lait.getKey().getQuantite(lait.getValue());
		}
		for (Map.Entry<Creme, Integer> creme : cremes.entrySet()) {
			quantite -= creme.getKey().getQuantite(creme.getValue());
		}

		return quantite < 0 ? 0 : quantite;
	}

	public String[][] getRapport(ResourceBundle bdlLangue) {

		NumberFormat formatter = NumberFormat.getCurrencyInstance();

		int nbrPrtnJet = nbrTotPortionsJet();
		int totalLigne = 3 + jets.size() + ((jets.size() > 0) ? 1 : 0) + laits.size() + sucres.size() + cremes.size();

		String[][] rapport = new String[totalLigne][2];
		int currentIndex = 0;
		double prixTotal = 0;

		rapport[currentIndex][0] = getQuantite() + " ml cafe:";
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
				String text = entry.getKey().rapport(ratio, taille.getCapacite(),bdlLangue);
				rapport[currentIndex][0] = text;

				if (jets.size() == 1) {
					double prix = Jet.getPrix(1, taille.getCapacite());
					prixTotal += prix;
					rapport[currentIndex++][1] = formatter.format(prix);
				} else
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
			for (Map.Entry<Lait, Integer> lait : laits.entrySet()) {
				if (lait.getValue() > 0) {
					text = lait.getKey().rapport(lait.getValue(),bdlLangue);
					prix = lait.getKey().getPrix(lait.getValue());
					prixTotal += prix;
					rapport[currentIndex][0] = text;
					rapport[currentIndex++][1] = formatter.format(prix);
				}
			}
			for (Map.Entry<Creme, Integer> creme : cremes.entrySet()) {
				if (creme.getValue() > 0) {
					text = creme.getKey().rapport(creme.getValue(),bdlLangue);
					prix = creme.getKey().getPrix(creme.getValue());
					prixTotal += prix;
					rapport[currentIndex][0] = text;
					rapport[currentIndex++][1] = formatter.format(prix);
				}
			}
			for (Map.Entry<Sucre, Integer> sucre : sucres.entrySet()) {
				if (sucre.getValue() > 0) {
					text = sucre.getKey().rapport(sucre.getValue(),bdlLangue);
					prix = sucre.getKey().getPrix(sucre.getValue());
					prixTotal += prix;
					rapport[currentIndex][0] = text;
					rapport[currentIndex++][1] = formatter.format(prix);
				}
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
	protected void CheckAndAdjustSucre() {
		for (Map.Entry<Sucre, Integer> sucre : sucres.entrySet()) {

			while (!sucre.getKey().valide(getTotal(sucres), taille.getCapacite()) && sucre.getValue() > 0) {
				sucre.setValue(sucre.getValue() - 1);
			}
			support.firePropertyChange("Sucre", sucre.getKey(), sucre.getValue());
		}

	}
	
	protected void CheckAndAdjustLait() {
		for (Map.Entry<Lait, Integer> lait : laits.entrySet()) {
			while (!lait.getKey().valide(getTotal(laits), getQuantite()) && lait.getValue() > 0) {
				lait.setValue(lait.getValue() - 1);
			}
			support.firePropertyChange("Lait", lait.getKey(), lait.getValue());
		}
		// lait.setValue(nbrPortion);

	}

	protected void CheckAndAdjustCreme() {
		for (Map.Entry<Creme, Integer> creme : cremes.entrySet()) {
			while (!creme.getKey().valide(getTotal(cremes), taille.getCapacite()) && creme.getValue() > 0) {
				creme.setValue(creme.getValue() - 1);
			}
			support.firePropertyChange("Creme", creme.getKey(), creme.getValue());
		}

	}	

	@Override
	public Taille[] getListTaille() {
		return MdlComposantesDB.getTaillesCafe();
	}

	@Override
	public ComposanteBreuvage[] getListLcs() {
		return  Misc.combine(MdlComposantesDB.getSucres(),MdlComposantesDB.getCremes(),MdlComposantesDB.getSucres());
	}	
	
	public static String getPath() {
		return "Images/cafe.png";
	}

}
