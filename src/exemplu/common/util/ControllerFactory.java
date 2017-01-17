package exemplu.common.util;

import exemplu.common.interfaces.ControllerInterface;
import exemplu.functionalitati.angajati.AngajatiController;
import exemplu.functionalitati.cautare.CautareController;
import exemplu.functionalitati.stoc.StocController;

public class ControllerFactory {
	public static final String CONTROLLER_STOC = "Controller Stoc";
	public static final String CONTROLLER_ANGAJATI = "Controller Angajati";
	public static final String CONTROLLER_CAUTARE = "Controller Cautare";

	private ControllerFactory() {
	}

	public static ControllerInterface getController(final String controller) {
		switch (controller) {
		case CONTROLLER_STOC:
			return new StocController();
		case CONTROLLER_ANGAJATI:
			return new AngajatiController();

		case CONTROLLER_CAUTARE:
			return new CautareController();
		default:
			return null;
		}

	}
}
