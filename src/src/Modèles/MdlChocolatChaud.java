package src.Modèles;
import java.text.NumberFormat;
import java.util.Map;
import java.util.ResourceBundle;

public class MdlChocolatChaud extends MdlBoisson {
		
	
	public MdlChocolatChaud(String imgPath) {
		super(imgPath);
		taille = MdlComposantesDB.getTaillesChocolatChaud()[2];
		
		
	}
	public void setTaille(Taille taille) {
		this.taille = taille;
		CheckAndAdjustLait();
	}
	public int addIngredient(ComposanteBreuvage ing, int nbrPortion) {
		if (ing instanceof Lait) {
			int dj = laits.containsKey(ing) ? laits.get(ing) : 0;
			return setLaitPortion((Lait) ing, nbrPortion + dj);
		}
		return 0;
	}
	public int getQuantite() {
		int quantite = taille.getCapacite();
		
		for (Map.Entry<Lait, Integer> lait : laits.entrySet()) {
			quantite -= lait.getKey().getQuantite(lait.getValue());
		}

		return quantite < 0 ? 0 : quantite;
	}
	
	public String[][] getRapport(ResourceBundle bdlLangue) {

		NumberFormat formatter = NumberFormat.getCurrencyInstance();

		int totalLigne = 3+ laits.size();

		String[][] rapport = new String[totalLigne][2];
		int currentIndex = 0;
		double prixTotal = 0;

		rapport[currentIndex][0] = getQuantite() + bdlLangue.getString("ml") + bdlLangue.getString("chocolat_chaud") + ":";
		rapport[currentIndex++][1] = formatter.format(taille.getPrix());
		prixTotal += taille.getPrix();

		
		

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
		}
		rapport[currentIndex][0] = bdlLangue.getString("total")+" :";
		rapport[currentIndex++][1] = formatter.format(prixTotal);

		return rapport;
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

	@Override
	public Taille[] getListTaille() {
		return MdlComposantesDB.getTaillesThe();
	}
	@Override
	public ComposanteBreuvage[] getListLcs() {
		return MdlComposantesDB.getLaits();
	}
	public static String getPath(){
		return "Images/latte.png";
	}
	
	
		
	

}
