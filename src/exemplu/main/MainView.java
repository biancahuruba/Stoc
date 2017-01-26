package exemplu.main;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import exemplu.functionalitati.toolbar.ApplicationJTree;

@SuppressWarnings("serial")
public class MainView extends JPanel {
	private JScrollPane container;
	private JScrollPane containerToolBar;

	public MainView(final ActionListener listener) {
		setLayout(new GridBagLayout());

		addToolBar();
		add(new ApplicationBar(listener), constraints(0, 1, 3));
		add(new JScrollPane(new ApplicationJTree()), constraints(1, 1, 3));
		addContainer();
		GridBagConstraints ctStatusBar = constraints(0, 2, 2);
		add(new JScrollPane(new JLabel("  Status  ")), ctStatusBar);

	}

	private void addToolBar() {
		GridBagConstraints ctToolBar = constraints(0, 0, 2, 0.1, 0);
		ctToolBar.anchor = GridBagConstraints.NORTH;
		containerToolBar = new JScrollPane();
		add(containerToolBar, ctToolBar);
	}

	private void addContainer() {
		GridBagConstraints ctContainer = constraints(2, 1, 1, 1, 0);
		ctContainer.weighty = 1;
		container = new JScrollPane();
		add(container, ctContainer);
	}

	public JScrollPane getContainerToolBar() {
		return containerToolBar;
	}

	public void setContainerToolBar(Component jPanel) {
		this.containerToolBar.setViewportView(jPanel);
		revalidate();
	}

	public void setCurrentView(final Component view) {
		container.setViewportView(view);
	}

	private GridBagConstraints constraints(int x, int y, int fill) {
		GridBagConstraints ct = new GridBagConstraints();
		ct.fill = fill;
		ct.gridx = x;
		ct.gridy = y;
		return ct;
	}

	private GridBagConstraints constraints(int x, int y, int fill, double weightx, int gridwidth) {
		GridBagConstraints ct = new GridBagConstraints();
		ct.fill = fill;
		ct.gridx = x;
		ct.gridy = y;
		ct.weightx = weightx;
		ct.gridwidth = gridwidth;
		return ct;
	}
}
