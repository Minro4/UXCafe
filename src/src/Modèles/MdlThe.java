package src.Modèles;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class MdlThe extends MdlBoisson {
	private HashMap<Sucre, Integer> sucres = new HashMap<Sucre, Integer>();

	public MdlThe(Taille taille, String imgPath) {
		super(imgPath);
		
		
	}
	
	public MdlThe(String imgPath) {
		super(imgPath);
		
		listTaille.add(new Taille("Très petit", 250, 1.40, "Images/cafeTP.png", 34));
		listTaille.add(new Taille("Petit", 350, 1.55, "Images/cafeP.png", 34));
		listTaille.add(new Taille("Moyen", 500, 1.75, "Images/cafe.png", 34));
		listTaille.add(new Taille("Grand", 600, 1.90, "Images/cafeG.png", 34));
		listTaille.add(new Taille("Très Grand", 700, 2.10, "Images/cafeTG.png", 34));
		Sucre sucre = new Sucre("Sucre", "Images/sugar.png");
		lcsList.add(sucre);
	}
	public void setTaille(Taille taille) {
		this.taille = taille;
		CheckAndAdjustLait();
		CheckAndAdjustSucre();
	}
	
	public int addIngredient(ComposanteBreuvage ing, int nbrPortion) {
		if (ing instanceof Lait) {
			int dj = laits.containsKey(ing) ? laits.get(ing) : 0;
			return setLaitPortion((Lait) ing, nbrPortion + dj);
		} else if (ing instanceof Sucre) {
			int dj = sucres.containsKey(ing) ? sucres.get(ing) : 0;
			return setSucrePortion((Sucre) ing, nbrPortion + dj);
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
	
	public int getQuantite() {
		int quantite = taille.getCapacite();
		
		for (Map.Entry<Lait, Integer> lait : laits.entrySet()) {
			quantite -= lait.getKey().getQuantite(lait.getValue());
		}

		return quantite < 0 ? 0 : quantite;
	}
	
	public String[][] getRapport() {

		NumberFormat formatter = NumberFormat.getCurrencyInstance();

		
		int totalLigne = 3+ laits.size() + sucres.size();

		String[][] rapport = new String[totalLigne][2];
		int currentIndex = 0;
		double prixTotal = 0;

		rapport[currentIndex][0] = getQuantite() + " ml cafe:";
		rapport[currentIndex++][1] = formatter.format(taille.getPrix());
		prixTotal += taille.getPrix();

		
		

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
			
			for (Map.Entry<Sucre, Integer> sucre : sucres.entrySet()) {
				if (sucre.getValue() > 0) {
					text = sucre.getKey().rapport(sucre.getValue());
					prix = sucre.getKey().getPrix();
					prixTotal += prix;
					rapport[currentIndex][0] = text;
					rapport[currentIndex++][1] = formatter.format(prix);
				}
			}
		}
		rapport[currentIndex][0] = "Total:";
		rapport[currentIndex++][1] = formatter.format(prixTotal);

		return rapport;
	}
	
	protected void CheckAndAdjustSucre() {
		for (Map.Entry<Sucre, Integer> sucre : sucres.entrySet()) {

			while (!sucre.getKey().valide(sucre.getValue(), taille.getCapacite()) && sucre.getValue() > 0) {
				sucre.setValue(sucre.getValue() - 1);
			}
			support.firePropertyChange("Sucre", sucre.getKey(), sucre.getValue());
		}

	}
	
	protected void CheckAndAdjustLait() {
		for (Map.Entry<Lait, Integer> lait : laits.entrySet()) {
			while (!lait.getKey().valide(lait.getValue(), getQuantite()) && lait.getValue() > 0) {
				lait.setValue(lait.getValue() - 1);
			}
			support.firePropertyChange("Lait", lait.getKey(), lait.getValue());
		}
		// lait.setValue(nbrPortion);

	}
	

}
