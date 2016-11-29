package mvc;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JPanel;

import role.Role;
import weapon.bullets.Bullet;

public class View extends JPanel {
	private Controller controller;
	public GamePanel gamePanel;
	public ButtonsPanel buttonPanel;
	private List<Role> roles;
	private List<Bullet> bullets;
	
	public View() {}

	public View(Controller controller) {
		super();
		this.controller = controller;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	//��m���s��panel (�}�l�C�����s�Ϊ̳s�u����)
	static class ButtonsPanel extends JPanel implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
	
	//��m�C���e����panel
	static class GamePanel extends JPanel{

		@Override
		protected void paintComponent(Graphics g) {
			/* draw here
			 * 1. Use the MapBuilder for map creating
			 * 2. Paint All the roles
			 * 3. Paint All the Bullets 
			 */
			
			super.paintComponent(g);
		}
		
		
	}

}
