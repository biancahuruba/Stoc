package exemplu.functionalitati.angajati;

import java.util.ArrayList;
import java.util.List;

import exemplu.common.models.Attribute;
import exemplu.common.models.RowMeta;

public class AngajatiModel {
	private Attribute nume;
	private Attribute prenume;

	private List<AngajatiRow> tabelModel;
	private RowMeta rowMeta;

	public AngajatiModel() {
		nume = new Attribute();
		prenume = new Attribute();
		tabelModel = new ArrayList<>();
		rowMeta = new RowMeta();
	}

	public Attribute getNume() {
		return nume;
	}

	public void setNume(Attribute nume) {
		this.nume = nume;
	}

	public Attribute getPrenume() {
		return prenume;
	}

	public void setPrenume(Attribute prenume) {
		this.prenume = prenume;
	}

	public List<AngajatiRow> getTabeModel() {
		return tabelModel;
	}

	public void setTabelModel(List<AngajatiRow> tabelModel) {
		this.tabelModel = tabelModel;
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
		builder.append("Nume: " + nume.getValue() + "\n");
		builder.append("Prenume: " + prenume.getValue() + "\n");
		builder.append("Tabel: \n");
		for (AngajatiRow row : tabelModel) {
			builder.append("  Produs: " + row.getProdus() + "\n");
			builder.append("  Pret: " + row.getPret() + "\n");
			builder.append("  Cantitate: " + row.getContitate() + "\n");
			builder.append("  Comision: " + row.getComision() + "\n");
		}
		return builder.toString();
	}

}
