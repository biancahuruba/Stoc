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
		GridBagConstraints ctToolBar = new GridBagConstraints();
		ctToolBar.fill = GridBagConstraints.HORIZONTAL;
		ctToolBar.anchor = GridBagConstraints.NORTH;
		ctToolBar.weightx = 0.1;
		ctToolBar.gridwidth = GridBagConstraints.REMAINDER;
		ctToolBar.gridx = 0;
		ctToolBar.gridy = 0;
		containerToolBar = new JScrollPane();
		add(containerToolBar, ctToolBar);
		

		GridBagConstraints ctBar = new GridBagConstraints();
		ctBar = new GridBagConstraints();
		ctBar.fill = GridBagConstraints.VERTICAL;
		ctBar.gridx = 0;
		ctBar.gridy = 1;
		add(new ApplicationBar(listener), ctBar);

		GridBagConstraints ct = new GridBagConstraints();
		ct.fill = GridBagConstraints.VERTICAL;
		ct.gridx = 1;
		ct.gridy = 1;
		JScrollPane cont = new JScrollPane(new ApplicationJTree());
		add(cont, ct);

		ct = new GridBagConstraints();
		ct.fill = GridBagConstraints.BOTH;
		ct.gridx = 2;
		ct.gridy = 1;
		ct.weightx = 1;
		ct.weighty = 1;
		ct.gridwidth = GridBagConstraints.REMAINDER;
		container = new JScrollPane();
		add(container, ct);

		ct = new GridBagConstraints();
		ct.fill = GridBagConstraints.HORIZONTAL;
		ct.gridx = 0;
		ct.gridy = 2;
		JScrollPane container2 = new JScrollPane(new JLabel("  Status  "));
		add(container2, ct);

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

}
