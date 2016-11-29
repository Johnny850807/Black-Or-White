package mvc;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import role.Role;
import weapon.bullets.Bullet;

public class View {
	private Controller controller;
	public GamePanel gamePanel;
	public ButtonsPanel buttonsPanel;
	private MapBuilder builder; // build the map whenever the view got settled the builder
	private MapDirector director; // to direct the builder
	private List<Role> roles;
	private List<Bullet> bullets;
	
	public View() {
		// 從View開始產生 List !!
		roles = Collections.checkedList( new ArrayList<Role>(), Role.class);
		bullets = Collections.checkedList( new ArrayList<Bullet>(), Bullet.class);
		builder = new BasicMapBuilder();
		director = new Map1Director();
		gamePanel = new GamePanel();
		buttonsPanel = new ButtonsPanel();
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
	
	public MapBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(MapBuilder builder) {
		this.builder = builder;
	}
	
	public MapDirector getDirector() {
		return director;
	}
	
	public void setDirector(MapDirector director) {
		this.director = director;
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public ButtonsPanel getButtonPanel() {
		return buttonsPanel;
	}

	public void setButtonPanel(ButtonsPanel buttonPanel) {
		this.buttonsPanel = buttonPanel;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(List<Bullet> bullets) {
		this.bullets = bullets;
	}





	//放置按鈕的panel (開始遊戲按鈕或者連線等等)
	static class ButtonsPanel extends JPanel implements ActionListener{
		public ButtonsPanel(){
			JButton start = new JButton("Start Game");
			JButton networkGame = new JButton("Connect to Another Player !!");
			start.setBackground(Color.cyan);
			start.setPreferredSize(new Dimension(200,60));
			start.setFont(new Font("Arial", Font.PLAIN, 20));
			networkGame.setFont(new Font("Arial", Font.PLAIN, 20));
			networkGame.setBackground(Color.cyan);
			networkGame.setPreferredSize(new Dimension(350,60));
			add(start);
			add(networkGame);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
	
	//放置遊戲畫面的panel
	class GamePanel extends JPanel{
		public static final int SIZEX = MapBuilder.SIZEX * MapBuilder.SIZE_IMG;  //取得地圖大小* 圖片大小
		public static final int SIZEY = MapBuilder.SIZEY * MapBuilder.SIZE_IMG;

		
		public GamePanel(){
			setSize(SIZEX,SIZEY);
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			/* draw here
			 * 1. Use the MapBuilder for map creating
			 * 2. Paint All the roles
			 * 3. Paint All the Bullets 
			 */
			Model m;
			Boolean cycle;
			buildMap(g);
			for ( Role r : roles ){
				m = r.getModel();
				cycle = m.act == ActionType.DIE ? false : true; //死亡不能是循環分鏡圖
				g.drawImage( m.iS.next(cycle), m.cX	, m.cY, null );
			}
			for ( Bullet b : bullets ){
				m = b.getModel();
				g.drawImage( m.iS.next(true), m.cX	, m.cY, null );
			}
			this.requestFocusInWindow();
		}
		
		public void buildMap(Graphics g){
			if ( builder == null )
				Log.e("MapBuilder is null !! Should set it before you invoke buildMap() ."); 
			else
				builder.buildMap(director.getMapString(), g);
		}
		

	}
	
	public static void main(String[] argv){
		View v = new View();
		GamePanel game = v.getGamePanel();
		ButtonsPanel buttonsPanel = v.getButtonPanel();
		JFrame frame = new JFrame("Black Or White");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1300,1000);
		frame.getContentPane().add(BorderLayout.CENTER, game);
		frame.getContentPane().add(BorderLayout.SOUTH , buttonsPanel);
		frame.setVisible(true);
		game.repaint();
		
	}

}
