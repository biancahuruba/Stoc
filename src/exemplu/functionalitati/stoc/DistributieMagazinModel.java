package exemplu.functionalitati.stoc;

import exemplu.common.models.Attribute;
import exemplu.common.models.GenericRowModel;

public class DistributieMagazinModel extends GenericRowModel {

	public static final int COLUMN_COUNT = 3;
	private Attribute magazin;
	private Attribute localitate;
	private Attribute cantitate;

	public DistributieMagazinModel() {
		magazin = new Attribute();
		localitate = new Attribute();
		cantitate = new Attribute();
	}

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

	public void setAttribute(final int index,final String value ) {
		switch (index) {
		case 0:
			magazin.setValue(value);
			magazin.setChanged(true);
			break;
		case 1:
			localitate.setValue(value);
			localitate.setChanged(true);
			break;
		case 2:
			cantitate.setValue(value);
			cantitate.setChanged(true);
			break;
		default:
			break;

		}
	}

}
