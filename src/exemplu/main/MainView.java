package exemplu.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class MainView extends JPanel {
	private JScrollPane container;

	public MainView(final ActionListener listener) {
		setLayout(new BorderLayout());
		add(new ApplicationToolbar(listener), BorderLayout.LINE_START);
		container = new JScrollPane();
		add(container, BorderLayout.CENTER);
	}

	public void setCurrentView(final JPanel view) {
		container.setViewportView(view);
	}
}
