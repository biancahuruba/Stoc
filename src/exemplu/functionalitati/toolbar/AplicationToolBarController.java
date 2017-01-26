package exemplu.functionalitati.toolbar;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import exemplu.common.interfaces.ControllerInterface;

public class AplicationToolBarController implements ControllerInterface, ActionListener {
	private ApplicationToolBar toolBar;
	private String caseCombo;

	public AplicationToolBarController() {
		toolBar = new ApplicationToolBar();
		toolBar.setActionListener(this);
		caseCombo = null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(ApplicationToolBar.COMBO_TIP)) {
			JComboBox cb = (JComboBox) e.getSource();
			switch (String.valueOf(cb.getSelectedItem())) {
			case "Stoc":
				toolBar.addFieldsTipStoc();
				caseCombo = "Stoc";
				break;
			case "Angajati":
				toolBar.addFieldsTipAngajat();
				caseCombo = "Angajati";
				break;
			default:
				break;
			}
		}
		if (e.getActionCommand().equals(ApplicationToolBar.TBCLEAR)) {
			if ("Stoc".equals(caseCombo)) {
				toolBar.getProdus().setText("");
				toolBar.getPret().setText("");
			}
			if ("Angajati".equals(caseCombo)) {
				toolBar.getNume().setText("");
				toolBar.getPrenume().setText("");
			}
		}
		if (e.getActionCommand().equals(ApplicationToolBar.TBSEARCH)) {
			CautareStocController controller = new CautareStocController();
			controller.actionPerformed(e);
		}
	}

	public Component getView() {
		return toolBar;
	}
}
