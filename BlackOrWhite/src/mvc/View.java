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
	
	public View() {
		
	}

	public View(Controller controller) {
		this();
		this.controller = controller;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	


	//放置按鈕的panel (開始遊戲按鈕或者連線等等)
	static class ButtonsPanel extends JPanel implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
	
	//放置遊戲畫面的panel
	static class GamePanel extends JPanel{
		public static final int SIZEX = MapBuilder.SIZEX * MapBuilder.SIZE_IMG;  //取得地圖大小* 圖片大小
		public static final int SIZEY = MapBuilder.SIZEY * MapBuilder.SIZE_IMG;
		private MapBuilder builder;// build the map whenever the view got settled the builder
		
		public GamePanel(){
			builder = new BasicMapBuilder();
		}
		@Override
		protected void paintComponent(Graphics g) {
			/* draw here
			 * 1. Use the MapBuilder for map creating
			 * 2. Paint All the roles
			 * 3. Paint All the Bullets 
			 */
			buildMap(g);
			
			super.paintComponent(g);
		}
		
		public void buildMap(Graphics g){
			String[] mapString = null;
			if ( builder == null )
				Log.e("MapBuilder is null !! Should set it before you invoke buildMap() ."); 
			else
				builder.buildMap(mapString, g);
		}
		
		public MapBuilder getBuilder() {
			return builder;
		}

		public void setBuilder(MapBuilder builder) {
			this.builder = builder;
		}
		
		
	}

}
