package exemplu.functionalitati.toolbar;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import exemplu.common.interfaces.ControllerInterface;

public class AplicationToolBarController implements ControllerInterface, ActionListener {
	private ApplicationToolBar toolBar;

	public AplicationToolBarController() {
		toolBar = new ApplicationToolBar();
		toolBar.setActionListener(this);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox) e.getSource();
		if (e.getActionCommand().equals(ApplicationToolBar.COMBO_TIP)) {
			switch (String.valueOf(cb.getSelectedItem())) {
			case "Stoc":
				toolBar.addFieldsTipStoc();
				break;
			case "Angajati":
				toolBar.addFieldsTipAngajat();
				break;
			default:
				break;
			}
		}
	}

	public Component getView() {
		return toolBar;
	}
}
