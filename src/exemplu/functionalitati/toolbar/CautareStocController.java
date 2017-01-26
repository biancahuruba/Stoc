package exemplu.functionalitati.toolbar;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import exemplu.common.interfaces.ControllerInterface;
import exemplu.common.models.GenericTableModel;
import exemplu.common.models.RowMeta;
import exemplu.functionalitati.stoc.DistributieMagazinModel;
import exemplu.functionalitati.stoc.StocDAOImpl;
import exemplu.functionalitati.stoc.StocModel;

public class CautareStocController implements ControllerInterface, ActionListener {
	private CautareStocView view;
	private StocModel model;
	private StocDAOImpl dao;
	private ApplicationToolBar toolBar;

	public CautareStocController() {
		view = new CautareStocView();
		model = new StocModel();
		dao = new StocDAOImpl();
		toolBar=new ApplicationToolBar();
	}

	public void setMockData() {
		final List<DistributieMagazinModel> tabelModel = model.getTableList();
		final DistributieMagazinModel row = new DistributieMagazinModel();
		tabelModel.add(row);
		final RowMeta metadata = model.getRowMeta();
		metadata.setColumnNames(columnNames());
		view.setTableModel(new GenericTableModel<>(tabelModel, metadata));
	}

	private List<String> columnNames() {
		final List<String> columnNames = new ArrayList<>();
		columnNames.add("Magazin");
		columnNames.add("Localitate");
		columnNames.add("Cantitate");
		columnNames.add("Cod");
		columnNames.add("Data");
		return columnNames;
	}

	@Override
	public Component getView() {
		return view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if ("Stoc".equals(e.getActionCommand())) {
			 List<StocModel> list=dao.search(toolBar.getProdus().getText(), toolBar.getPret().getText());
		 }
		 if ("Angajati".equals(e.getActionCommand())) {
		 }
		 

	}

}
