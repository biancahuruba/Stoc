package exemplu.main;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private static final String APP_TITLE = "Aplicatie";
	private static final String ICON_PATH = "resources/Wolf.png";

	public MainFrame() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(new Dimension(800, 700));
		setIconImage(getApplicationIcon());
		setTitle(APP_TITLE);
	}

	private Image getApplicationIcon() {
		return new ImageIcon(ICON_PATH).getImage();
	}
}
