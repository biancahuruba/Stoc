package exemplu.functionalitati.stoc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import exemplu.common.interfaces.ControllerInterface;

public class StocController implements ActionListener, TableModelListener, ControllerInterface {

	private StocView view;
	private StocModel model;

	public StocView getView() {
		return view;
	}

	public void setView(StocView view) {
		this.view = view;
	}

	public StocModel getModel() {
		return model;
	}

	public void setModel(StocModel model) {
		this.model = model;
	}

	public StocController() {
		view = new StocView();
		model = new StocModel();
		view.setListener(this);
		view.setListenerTable(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (Commands.ENABLE.toString().equals(e.getActionCommand())) {
			view.setAprobat();
		} else {
			view.SaveDataFromFields();
			view.SaveDataFromTable();
			view.addRow();
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		view.display(e);
	}

}
