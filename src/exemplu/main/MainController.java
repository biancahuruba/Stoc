package exemplu.main;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import exemplu.common.interfaces.ControllerInterface;
import exemplu.common.util.ControllerFactory;
import exemplu.functionalitati.toolbar.AplicationToolBarController;

public class MainController implements ActionListener {
	private MainView view;
	private ControllerInterface currentController;
	private AplicationToolBarController controllerToolbar;

	public void startApplication() {
		final MainFrame mainFrame = new MainFrame();
		view = new MainView(this);
		mainFrame.setContentPane(view);
		mainFrame.setJMenuBar(new ApplicationMenu(this));
		mainFrame.setVisible(true);

		controllerToolbar = new AplicationToolBarController();
		view.setContainerToolBar(controllerToolbar.getView());
	}

	private void setCurrentView(final Component newView) {
		view.setCurrentView(newView);
	}

	@Override
	public void actionPerformed(final ActionEvent event) {
		final String actionCommand = event.getActionCommand();
		switch (actionCommand) {
		case ApplicationMenu.COMMAND_EXIT:
			System.exit(0);
			break;
		default:
			currentController = ControllerFactory.getController(actionCommand);
			setCurrentView(currentController.getView());
			break;
		}
	}
}
