package src;

public class IngredientInfo implements Comparable<IngredientInfo>{
	private Ingredient ingredient;
	private int portion;
	
	public IngredientInfo(Ingredient ingredient, int portion) {
		this.ingredient = ingredient;
		this.portion = portion;
	}

	
	public Ingredient getIngredient() {
		return ingredient;
	}
	public void addPortion() {
		portion++;
	}
	public void removePortion() {
		portion--;
	}
	public int getPortion() {
		return portion;
	}


	@Override
	public int compareTo(IngredientInfo o) {
		if (getPortion() > o.getPortion())
			return -1;
		else if (getPortion() < o.getPortion())
			return 1;
		return 0;
	}
}
