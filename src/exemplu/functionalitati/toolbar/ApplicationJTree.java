package exemplu.functionalitati.toolbar;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

@SuppressWarnings("serial")
public class ApplicationJTree extends JTree {

	private DefaultTreeModel tree;
	private DefaultMutableTreeNode meniu;
	private DefaultMutableTreeNode stoc;
	private DefaultMutableTreeNode angajati;
	private DefaultMutableTreeNode produs;
	private DefaultMutableTreeNode categorie;
	private DefaultMutableTreeNode pret;
	private DefaultMutableTreeNode cod;
	private DefaultMutableTreeNode data;
	private DefaultMutableTreeNode magazin;
	private DefaultMutableTreeNode localitate;
	private DefaultMutableTreeNode cantitate;
	private DefaultMutableTreeNode nume;
	private DefaultMutableTreeNode prenume;
	private DefaultMutableTreeNode aProdus;
	private DefaultMutableTreeNode aPret;
	private DefaultMutableTreeNode aCantitate;
	private DefaultMutableTreeNode comision;

	public ApplicationJTree() {
		meniu = new DefaultMutableTreeNode("Meniu");
		stoc = new DefaultMutableTreeNode("Stoc");
		meniu.add(stoc);

		initFieldsStoc();
		initStoc();

		angajati = new DefaultMutableTreeNode("Angajati");
		meniu.add(angajati);

		initFieldsAngajat();
		initAngajati();

		tree = new DefaultTreeModel(meniu);
		this.setModel(tree);
	}

	private void initFieldsStoc() {
		produs = new DefaultMutableTreeNode("Produs");
		categorie = new DefaultMutableTreeNode("Categorie");
		pret = new DefaultMutableTreeNode("Pret");
		cod = new DefaultMutableTreeNode("Cod");
		data = new DefaultMutableTreeNode("Data");
		magazin = new DefaultMutableTreeNode("Magazin");
		localitate = new DefaultMutableTreeNode("Localitate");
		cantitate = new DefaultMutableTreeNode("Cantitate");
	}

	private void initFieldsAngajat() {
		nume = new DefaultMutableTreeNode("Nume");
		prenume = new DefaultMutableTreeNode("Prenume");
		aProdus = new DefaultMutableTreeNode("Produs");
		aPret = new DefaultMutableTreeNode("Pret");
		aCantitate = new DefaultMutableTreeNode("Cantitate");
		comision = new DefaultMutableTreeNode("Comision");
	}

	private void initStoc() {
		stoc.add(produs);
		stoc.add(categorie);
		stoc.add(pret);
		stoc.add(cod);
		stoc.add(data);
		stoc.add(magazin);
		stoc.add(localitate);
		stoc.add(cantitate);
	}

	private void initAngajati() {
		angajati.add(nume);
		angajati.add(prenume);
		angajati.add(aProdus);
		angajati.add(aPret);
		angajati.add(aCantitate);
		angajati.add(comision);
	}
}
