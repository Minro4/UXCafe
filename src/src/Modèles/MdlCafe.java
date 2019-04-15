//********************************************************************
// Cafe.java 		Auteur: William O'Sullivan-Dargis, Simon Paradis, Jimmy Houde
//
// Modèle du café
//********************************************************************

package src.Modèles;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.sun.jmx.mbeanserver.Util;
import com.sun.org.apache.xml.internal.serializer.utils.Utils;

import src.Misc;

public class MdlCafe extends MdlBoisson {

	private ComposanteBreuvage torefaction;
	private HashMap<Sucre, Integer> sucres = new HashMap<Sucre, Integer>();
	private HashMap<Creme, Integer> cremes = new HashMap<Creme, Integer>();
	//protected static ArrayList<Jet> listJet=new ArrayList<Jet>();
	// private Map.Entry<Sucre, Integer> sucre;
	// private Map.Entry<Creme, Integer> creme;
	
	/*private static Taille[] listTaille = {
			new Taille("Très petit", 250, 1.55, "Images/cafeTP.png", 34),
			new Taille("Petit", 350, 1.75, "Images/cafeP.png", 34),
			new Taille("Moyen", 500, 1.95, "Images/cafe.png", 34),
			new Taille("Grand", 600, 2.15, "Images/cafeG.png", 34),
			new Taille("Très Grand", 700, 2.35, "Images/cafeTG.png", 34)
	};
	private static ComposanteBreuvage[] listToref = {
			new ComposanteBreuvage("Légère", "Images/coffeeBean.png"),
			new ComposanteBreuvage("Normale", "Images/CoffeeBean2.png"),
			new ComposanteBreuvage("Foncée", "Images/CoffeeBean3.png")
	};
	private static Jet[] listJet = {
			new Jet("Menthe", "Images/menthepoivre.png"),
			new Jet("Moka", "Images/chocolate.png"),
			new Jet("Caramel", "Images/caramel.jpg"),
			new Jet("Vanille", "Images/vanilla.png"),
			new Jet("Framboise", "Images/raspberry.png"),
			new Jet("Noisette", "Images/hazelnut.png")
	};
	private static ComposanteBreuvage[] listLCS = {
			new Lait("Lait", "Images/lait.png"),
			new Creme("Creme", "Images/creme.png"),
			new Sucre("Sucre", "Images/sugar.png"),
			new Sucre("Miel","Images/miel.png",0.05f,true),
			new Sucre("Cassonade", "Images/cassonade.png",0.1f,false),
			new Sucre("Édulcorant", "Images/edulcorant.png",0.05f,true)		
	};*/
	
	private HashMap<Jet, Integer> jets = new HashMap<Jet, Integer>();	

	/*public Cafe(Taille taille, ComposanteBreuvage torefaction, String imgPath) {
		super(imgPath);
		this.torefaction = torefaction;
		
	}*/
	
	public MdlCafe(String imgPath) {
		super(imgPath);
		
		
		/*listTaille.add(new Taille("Très petit", 250, 1.55, "Images/cafeTP.png", 34));
		listTaille.add(new Taille("Petit", 350, 1.75, "Images/cafeP.png", 34));
		listTaille.add(new Taille("Moyen", 500, 1.95, "Images/cafe.png", 34));
		listTaille.add(new Taille("Grand", 600, 2.15, "Images/cafeG.png", 34));
		listTaille.add(new Taille("Très Grand", 700, 2.35, "Images/cafeTG.png", 34));*/

		/*listJet.add(new Jet("Menthe", "Images/menthepoivre.png"));
		listJet.add(new Jet("Moka", "Images/chocolate.png"));
		listJet.add(new Jet("Caramel", "Images/caramel.jpg"));
		listJet.add(new Jet("Vanille", "Images/vanilla.png"));
		listJet.add(new Jet("Framboise", "Images/raspberry.png"));
		listJet.add(new Jet("Noisette", "Images/hazelnut.png"));*/
		Sucre sucre = new Sucre("Sucre", "Images/sugar.png");
		Creme creme = new Creme("Creme", "Images/creme.png");
		lcsList.add(sucre);
		lcsList.add(creme);
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
		if (sucre.valide(prtnSucre, taille.getCapacite())) {
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

	public String[][] getRapport() {

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
				String text = entry.getKey().rapport(ratio, taille.getCapacite());
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
					text = lait.getKey().rapport(lait.getValue());
					prix = lait.getKey().getPrix(lait.getValue());
					prixTotal += prix;
					rapport[currentIndex][0] = text;
					rapport[currentIndex++][1] = formatter.format(prix);
				}
			}
			for (Map.Entry<Creme, Integer> creme : cremes.entrySet()) {
				if (creme.getValue() > 0) {
					text = creme.getKey().rapport(creme.getValue());
					prix = creme.getKey().getPrix(creme.getValue());
					prixTotal += prix;
					rapport[currentIndex][0] = text;
					rapport[currentIndex++][1] = formatter.format(prix);
				}
			}
			for (Map.Entry<Sucre, Integer> sucre : sucres.entrySet()) {
				if (sucre.getValue() > 0) {
					text = sucre.getKey().rapport(sucre.getValue());
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
	protected <T> int getTotal(HashMap<T, Integer> map) {
		int sum = 0;
		for (Map.Entry<T, Integer> entry : map.entrySet()) {
				sum += entry.getValue();
		}
		return sum;
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
