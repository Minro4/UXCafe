package src.Modèles;

public class MdlComposantesDB {
	private static Taille[] taillesCafe = {
			new Taille("Très petit", 250, 1.55, "Images/cafeTP.png", 34),
			new Taille("Petit", 350, 1.75, "Images/cafeP.png", 34),
			new Taille("Moyen", 500, 1.95, "Images/cafe.png", 34),
			new Taille("Grand", 600, 2.15, "Images/cafeG.png", 34),
			new Taille("Très Grand", 700, 2.35, "Images/cafeTG.png", 34)
	};
	private static Taille[] taillesChocolatChaud = {
			new Taille("Très petit", 250, 1.00, "Images/cafeTP.png", 34),
			new Taille("Petit", 350, 1.15, "Images/cafeP.png", 34),
			new Taille("Moyen", 500, 1.30, "Images/cafe.png", 34),
			new Taille("Grand", 600, 1.45, "Images/cafeG.png", 34),
			new Taille("Très Grand", 700, 1.60, "Images/cafeTG.png", 34)
	};
	private static Taille[] taillesThe = {
			new Taille("Très petit", 250, 1.40, "Images/cafeTP.png", 34),
			new Taille("Petit", 350, 1.55, "Images/cafeP.png", 34),
			new Taille("Moyen", 500, 1.75, "Images/cafe.png", 34),
			new Taille("Grand", 600, 1.90, "Images/cafeG.png", 34),
			new Taille("Très Grand", 700, 2.10, "Images/cafeTG.png", 34)
	};
	
	private static ComposanteBreuvage[] torefs = {
			new ComposanteBreuvage("Légère", "Images/coffeeBean.png"),
			new ComposanteBreuvage("Normale", "Images/CoffeeBean2.png"),
			new ComposanteBreuvage("Foncée", "Images/CoffeeBean3.png")
	};
	private static Jet[] jets = {
			new Jet("Menthe", "Images/menthepoivre.png"),
			new Jet("Moka", "Images/chocolate.png"),
			new Jet("Caramel", "Images/caramel.jpg"),
			new Jet("Vanille", "Images/vanilla.png"),
			new Jet("Framboise", "Images/raspberry.png"),
			new Jet("Noisette", "Images/hazelnut.png")
	};
	private static ComposanteBreuvage[] laits = {
			new Lait("Lait", "Images/lait.png")
	};
	private static ComposanteBreuvage[] cremes = {
			new Creme("Creme", "Images/creme.png")
	};
	private static ComposanteBreuvage[] sucres = {		
			new Sucre("Sucre", "Images/sugar.png"),
			new Sucre("Miel","Images/miel.png",0.05f,true),
			new Sucre("Cassonade", "Images/cassonade.png",0.1f,false),
			new Sucre("Édulcorant", "Images/edulcorant.png",0.05f,true)		
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
