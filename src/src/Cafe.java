package src;

public class Cafe {

	private final float ProportionSucreMax = 0.01f;
	private final float ProportionLaitMax = 0.5f;
	private final float ProportionCremeMax = 0.1f;
	private final int QuantiteParPortion = 15;
	
	private Taille taille;
	private int prtnSucre;
	private int prtnLait;
	private int prtnCreme;

	public Cafe() 
	{
		
	}
	
	public int getPrtnSucre() {
		return prtnSucre;
	}

	public boolean setPrtnSucre(int prtnSucre) {
		int portionMax = (int) (taille.getCapacite() * ProportionSucreMax);
		if (portionMax <= prtnSucre) {
			this.prtnSucre = prtnSucre;
			return true;
		}
		return false;
	}

	public int getPrtnLait() {
		return prtnLait;
	}

	public boolean setPrtnLait(int prtnLait) {
		int quantiteLait = prtnLait * QuantiteParPortion;
		int maxQuantiteLait = (int) (ProportionLaitMax * taille.getCapacite());
		if (quantiteLait <= maxQuantiteLait) {
			this.prtnLait = prtnLait;
			return true;
		}
		return false;	
	}

	public int getPrtnCreme() {
		return prtnCreme;
	}

	public boolean setPrtnCreme(int prtnCreme) {
		int quantiteCreme = prtnCreme * QuantiteParPortion;
		int maxQuantiteCreme = (int) (ProportionCremeMax * taille.getCapacite());
		if (quantiteCreme <= maxQuantiteCreme) {
			this.prtnCreme = prtnCreme;
			return true;
		}
		return false;	
	}
}
