package src.Modèles;

public class MdlComposantesDB {
	private static Taille[] taillesCafe = {
			new Taille("taille_tres_petit", 250, 1.55, "Images/cafeTP.png", 34),
			new Taille("taille_petit", 350, 1.75, "Images/cafeP.png", 34),
			new Taille("taille_moyen", 500, 1.95, "Images/cafe.png", 34),
			new Taille("taille_grand", 600, 2.15, "Images/cafeG.png", 34),
			new Taille("taille_tres_grand", 700, 2.35, "Images/cafeTG.png", 34)
	};
	private static Taille[] taillesChocolatChaud = {
			new Taille("taille_tres_petit", 250, 1.00, "Images/cafeTP.png", 34),
			new Taille("taille_petit", 350, 1.15, "Images/cafeP.png", 34),
			new Taille("taille_moyen", 500, 1.30, "Images/cafe.png", 34),
			new Taille("taille_grand", 600, 1.45, "Images/cafeG.png", 34),
			new Taille("taille_tres_grand", 700, 1.60, "Images/cafeTG.png", 34)
	};
	private static Taille[] taillesThe = {
			new Taille("taille_tres_petit", 250, 1.40, "Images/cafeTP.png", 34),
			new Taille("taille_petit", 350, 1.55, "Images/cafeP.png", 34),
			new Taille("taille_moyen", 500, 1.75, "Images/cafe.png", 34),
			new Taille("taille_grand", 600, 1.90, "Images/cafeG.png", 34),
			new Taille("taille_tres_grand", 700, 2.10, "Images/cafeTG.png", 34)
	};
	
	private static ComposanteBreuvage[] torefs = {
			new ComposanteBreuvage("toref_legere", "Images/coffeeBean.png"),
			new ComposanteBreuvage("toref_normale", "Images/CoffeeBean2.png"),
			new ComposanteBreuvage("toref_foncee", "Images/CoffeeBean3.png")
	};
	private static Jet[] jets = {
			new Jet("jet_menthe", "Images/menthepoivre.png"),
			new Jet("jet_moka", "Images/chocolate.png"),
			new Jet("jet_Caramel", "Images/caramel.jpg"),
			new Jet("jet_vanille", "Images/vanilla.png"),
			new Jet("jet_framboise", "Images/raspberry.png"),
			new Jet("jet_noisette", "Images/hazelnut.png")
	};
	private static ComposanteBreuvage[] laits = {
			new Lait("lait", "Images/lait.png")
	};
	private static ComposanteBreuvage[] cremes = {
			new Creme("creme", "Images/creme.png")
	};
	private static ComposanteBreuvage[] sucres = {		
			new Sucre("sucre_raffine", "Images/sugar.png"),
			new Sucre("sucre_miel","Images/honey.png",0.05f,true),
			new Sucre("sucre_cassonade", "Images/cassonade.png",0.1f,false),
			new Sucre("sucre_edulcorant", "Images/edulcorant.png",0.05f,true)		
	};
	public static Taille[] getTaillesCafe() {
		return taillesCafe;
	}
	public static ComposanteBreuvage[] getTorefs() {
		return torefs;
	}
	public static Jet[] getJets() {
		return jets;
	}
	public static ComposanteBreuvage[] getLaits() {
		return laits;
	}
	public static ComposanteBreuvage[] getCremes() {
		return cremes;
	}
	public static ComposanteBreuvage[] getSucres() {
		return sucres;
	}
	public static Taille[] getTaillesChocolatChaud() {
		return taillesChocolatChaud;
	}
	public static Taille[] getTaillesThe() {
		return taillesThe;
	}
}
