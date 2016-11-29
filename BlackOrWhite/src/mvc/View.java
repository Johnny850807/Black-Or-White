package mvc;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class View extends JPanel {
	private Controller controller;

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public View(Controller controller) {
		super();
		this.controller = controller;
	}

}
