package exemplu.main;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import exemplu.common.util.ControllerFactory;

@SuppressWarnings("serial")
public class ApplicationToolbar extends JToolBar {
	public ApplicationToolbar(final ActionListener listener) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		final JButton buttonStoc = new JButton("Stoc");
		buttonStoc.setActionCommand(ControllerFactory.CONTROLLER_STOC);
		buttonStoc.addActionListener(listener);
		buttonStoc.setMaximumSize(getMaxSize(buttonStoc));
		add(buttonStoc);

		final JButton buttonAngajati = new JButton("Angajati");
		buttonAngajati.setActionCommand(ControllerFactory.CONTROLLER_ANGAJATI);
		buttonAngajati.addActionListener(listener);
		buttonAngajati.setMaximumSize(getMaxSize(buttonAngajati));
		add(buttonAngajati);
		setOrientation(SwingConstants.VERTICAL);
	}

	private Dimension getMaxSize(final JButton button) {
		return new Dimension(Short.MAX_VALUE, button.getPreferredSize().height);
	}
}
