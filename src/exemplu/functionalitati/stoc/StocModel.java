package exemplu.functionalitati.stoc;

import java.util.ArrayList;
import java.util.List;

import exemplu.common.models.Attribute;
import exemplu.common.models.RowMeta;

public class StocModel {

	private Attribute produs;
	private Attribute categorie;
	private Attribute pret;
	private Attribute cod;
	private Attribute aprobat;
	private Attribute data;

	private List<DistributieMagazinModel> tableList;
	private RowMeta rowMeta;

	public StocModel() {
		initFields();
		tableList = new ArrayList<>();
		rowMeta = new RowMeta();
	}

	private void initFields() {
		produs = new Attribute();
		categorie = new Attribute();
		pret = new Attribute();
		cod = new Attribute();
	}

	public void addRow(DistributieMagazinModel model, int index) {
		tableList.add(index, model);
	}

	public void removeRow(int index) {
		tableList.remove(index);
	}

	public List<DistributieMagazinModel> getTableList() {
		return tableList;
	}

	public void setTableList(List<DistributieMagazinModel> tableList) {
		this.tableList = tableList;
	}

	public Attribute getValue(int row, int column) {
		return tableList.get(row).getAttribute(column);
	}

	public void setValue(String value, int row, int column) {
		tableList.get(row).setAttribute(value, column);
	}

	public int getRowCount() {
		return tableList.size();
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

	public RowMeta getRowMeta() {
		return rowMeta;
	}

	public void setRowMeta(RowMeta rowMeta) {
		this.rowMeta = rowMeta;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Produs: " + produs.getValue() + "\n");
		builder.append("Categorie: " + categorie.getValue() + "\n");
		builder.append("Pret: " + pret.getValue() + "\n");
		builder.append("Cod: " + cod.getValue() + "\n");
		builder.append("Tabel: \n");
		for (DistributieMagazinModel row : tableList) {
			builder.append("  Magazin: " + row.getMagazin() + "\n");
			builder.append("  Localitate: " + row.getLocalitate() + "\n");
			builder.append("  Cantitate: " + row.getCantitate() + "\n");
		}
		return builder.toString();
	}
}
