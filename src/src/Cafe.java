package src;

public class Cafe {
	
	private final float ProportionSucreMax = 0.1f;
	
	private int prtnSucre;
	private int prtnLait;
	private int prtnCreme;
	
	public int getPrtnSucre() {
		return prtnSucre;
	}
	public void setPrtnSucre(int prtnSucre) {
		
		this.prtnSucre = prtnSucre;
	}
	public int getPrtnLait() {
		return prtnLait;
	}
	public void setPrtnLait(int prtnLait) {
		this.prtnLait = prtnLait;
	}
	public int getPrtnCreme() {
		return prtnCreme;
	}
	public void setPrtnCreme(int prtnCreme) {
		this.prtnCreme = prtnCreme;
	}
}
