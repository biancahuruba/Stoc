package exemplu.functionalitati.stoc;

import exemplu.common.models.Attribute;

public class DistributieMagazinModel {

	public static final int COLUMN_COUNT = 3;
	private Attribute magazin;
	private Attribute localitate;
	private Attribute cantitate;

	public Attribute getMagazin() {
		return magazin;
	}

	public void setMagazin(Attribute magazin) {
		this.magazin = magazin;
	}

	public Attribute getLocalitate() {
		return localitate;
	}

	public void setLocalitate(Attribute localitate) {
		this.localitate = localitate;
	}

	public Attribute getCantitate() {
		return cantitate;
	}

	public void setCantitate(Attribute cantitate) {
		this.cantitate = cantitate;
	}

	public Attribute getAttribute(int index) {
		switch (index) {
		case 0:
			return magazin;
		case 1:
			return localitate;
		case 2:
			return cantitate;
		}
		return null;
	}

	public void setAttribute(Attribute value, int index) {
		switch (index) {
		case 0:
			magazin = value;
			break;
		case 1:
			localitate = value;
			break;
		case 2:
			cantitate = value;
			break;
		}

	}
	
	

}
