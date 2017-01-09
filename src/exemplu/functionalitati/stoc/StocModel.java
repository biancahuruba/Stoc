package exemplu.functionalitati.stoc;

import java.util.ArrayList;
import java.util.List;

import exemplu.common.models.Attribute;

public class StocModel {

	private Attribute produs;
	private Attribute categorie;
	private Attribute pret;
	private Attribute cod;
	private Attribute aprobat;
	private Attribute data;

	private List<DistributieMagazinModel> list;

	public StocModel() {
		list = new ArrayList<>();
	}

	public void addRow(DistributieMagazinModel model, int index) {
		list.add(index, model);
	}

	public void removeRow(int index) {
		list.remove(index);
	}

	public Attribute getValue(int row, int column) {
		return list.get(row).getAttribute(column);
	}

	public void setValue(Attribute value, int row, int column) {
		list.get(row).setAttribute(value, column);
	}

	public int getRowCount() {
		return list.size();
	}

	public int getColumnCount() {
		return DistributieMagazinModel.COLUMN_COUNT;
	}

	public Attribute getProdus() {
		return produs;
	}

	public void setProdus(Attribute produs) {
		this.produs = produs;
	}

	public Attribute getCategorie() {
		return categorie;
	}

	public void setCategorie(Attribute categorie) {
		this.categorie = categorie;
	}

	public Attribute getPret() {
		return pret;
	}

	public void setPret(Attribute pret) {
		this.pret = pret;
	}

	public Attribute getCod() {
		return cod;
	}

	public void setCod(Attribute cod) {
		this.cod = cod;
	}

	public Attribute getAprobat() {
		return aprobat;
	}

	public void setAprobat(Attribute aprobat) {
		this.aprobat = aprobat;
	}

	public Attribute getData() {
		return data;
	}

	public void setData(Attribute data) {
		this.data = data;
	}

}
