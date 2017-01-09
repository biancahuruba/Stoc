package exemplu.functionalitati.angajati;

import exemplu.common.models.Attribute;
import exemplu.common.models.GenericRowModel;

public class AngajatiRow extends GenericRowModel {
	private Attribute produs;
	private Attribute pret;
	private Attribute cantitate;
	private Attribute comision;

	public AngajatiRow() {
		produs = new Attribute();
		pret = new Attribute();
		cantitate = new Attribute();
		comision = new Attribute();
	}

	public Attribute getProdus() {
		return produs;
	}

	public void setProdus(final Attribute produs) {
		this.produs = produs;
	}

	public Attribute getPret() {
		return pret;
	}

	public void setPret(final Attribute pret) {
		this.pret = pret;
	}

	public Attribute getContitate() {
		return cantitate;
	}

	public void setContitate(final Attribute contitate) {
		this.cantitate = contitate;
	}

	public Attribute getComision() {
		return comision;
	}

	public void setComision(final Attribute comision) {
		this.comision = comision;
	}

	@Override
	public Attribute getAttribute(final int index) {
		switch (index) {
		case 0:
			return produs;
		case 1:
			return pret;
		case 2:
			return cantitate;
		case 3:
			return comision;
		default:
			return null;
		}
	}

	@Override
	public void setAttribute(final int index, final String value) {
		switch (index) {
		case 0:
			produs.setValue(value);
			produs.setChanged(true);
			break;
		case 1:
			pret.setValue(value);
			pret.setChanged(true);
			break;
		case 2:
			cantitate.setValue(value);
			cantitate.setChanged(true);
			break;
		case 3:
			comision.setValue(value);
			comision.setChanged(true);
			break;
		default:
			break;
		}
	}
}
