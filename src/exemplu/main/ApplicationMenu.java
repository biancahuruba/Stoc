package exemplu.main;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class ApplicationMenu extends JMenuBar {
	public static final String COMMAND_EXIT = "Command Exit";
	private ActionListener actionListener;

	public ApplicationMenu(final ActionListener listener) {
		actionListener = listener;
		add(getFileMenu());
	}

	private JMenu getFileMenu() {
		final JMenu menuFile = new JMenu("File");
		JMenuItem menuExit = new JMenuItem("Exit");
		menuExit.addActionListener(actionListener);
		menuExit.setActionCommand(COMMAND_EXIT);
		menuFile.add(menuExit);
		return menuFile;
	}
}
