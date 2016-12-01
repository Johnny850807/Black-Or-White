package mvc;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import role.Role;
import weapon.bullets.Bullet;

public class View {
	private JFrame parent;  //父介面
	private Controller controller;  
	public GamePanel gamePanel;  //遊戲畫面
	public ButtonsPanel buttonsPanel;  //按鈕畫面
	public PlayerPanel playerPanel;  //玩家狀況
	private MapBuilder builder; // build the map whenever the view got settled the builder
	private MapDirector director; // to direct the builder
	private List<Role> roles; //所有存在角色
	private List<Bullet> bullets;  //所有存在子彈
	private static boolean startGame = false;  
	private boolean netWorking = false; // true if choose the networking mode
	
	public View(JFrame frame) {
		this.parent = frame;
		gamePanel = new GamePanel();
		playerPanel = new PlayerPanel();
		buttonsPanel = new ButtonsPanel(playerPanel);
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

	static class PlayerPanel extends JPanel {
		private static final String Player1 = "Player1 ";
		private static final String Player2 = "Player2 ";
		private static final String HP = "HP : ";
		private int p1Hp = 0;
		private int p2Hp = -1;
		private JLabel player1HP = new JLabel(Player1+HP); 
		private JLabel player2HP = new JLabel(Player2+HP); ;
		//overloading for 1 or 2 players-game
		public PlayerPanel(){
			player1HP.setFont(new Font("Arial", Font.PLAIN, 30));
			player2HP.setFont(new Font("Arial", Font.PLAIN, 30));
			add(player1HP);
			add(player2HP);
		}
		public void updateText(){
			player1HP.setText(Player1+HP+p1Hp);
			if(p2Hp == -1)
				player2HP.setText("");
			else
				player2HP.setText(Player2+HP+p1Hp);
		}
		public int getP1Hp() {
			return p1Hp;
		}
		public void setP1Hp(int p1Hp) {
			this.p1Hp = p1Hp;
			updateText();
		}
		public int getP2Hp() {
			return p2Hp;
		}
		public void setP2Hp(int p2Hp) {
			this.p2Hp = p2Hp;
			updateText();
		}
			
	}

	//放置按鈕的panel (開始遊戲按鈕或者連線等等)
	class ButtonsPanel extends JPanel implements ActionListener{
		private static final String NET_MESSAGE = "請輸入遊戲伺服器IP.";
		private static final String NET_CONNECT = "連線到伺服器中.";
		private JButton start;
		private JButton networkGame;
		private JFrame netFrame;
		private JButton netConnectBTN;
		private JTextField netIpED;
		private JLabel netMessage;
		public ButtonsPanel(JPanel hpPanel){
			add(hpPanel); //將生命欄位設置近來
			// 基本按鈕設置
			start = new JButton("Start Game");
			networkGame = new JButton("Connect to Another Player !!");
			start.setBackground(Color.cyan);
			start.setPreferredSize(new Dimension(200,60));
			start.setFont(new Font("Arial", Font.PLAIN, 20));
			start.addActionListener(this);
			networkGame.setFont(new Font("Arial", Font.PLAIN, 20));
			networkGame.setBackground(Color.cyan);
			networkGame.setPreferredSize(new Dimension(350,60));
			networkGame.addActionListener(this);
			add(start);
			add(networkGame);
			
			//連線版面設置
			netFrame = new JFrame("連線到伺服器");
			netConnectBTN = new JButton("Input IP");
			netConnectBTN.setFont(new Font("Arial", Font.PLAIN, 20));
			netMessage = new JLabel(NET_MESSAGE);
			netIpED = new JTextField();
			netIpED.setPreferredSize(new Dimension(250,30));
			JPanel panel = new JPanel();
			panel.add(netIpED);
			panel.add(netConnectBTN);
			netFrame.getContentPane().add(panel, BorderLayout.NORTH);
			netFrame.getContentPane().add(netMessage,BorderLayout.CENTER);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton event = (JButton)e.getSource();
			if (event.equals(start)){
				if ( !startGame ){
					// Start game Event
					start.setText("End Game");
					startGame = true; 
					//make playerPanel visible
					playerPanel.setP1Hp(500);
					parent.setVisible(true);
				}
				else{
					// End game Event 
					 System.exit(0); //leave
				}
			}
			else if (event.equals(networkGame)){
				//產生Net 視窗 
				netFrame.setLocation(400, 300);
				netFrame.pack();
				netFrame.setVisible(true);
			}
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
		JFrame frame = new JFrame("Black Or White");
		Controller controller = Controller.getController();
		View v = new View(frame);
		MapBuilder builder = new BasicMapBuilder();
		MapDirector director = new Map1Director();
		v.setBuilder(builder);
		v.setDirector(director);
		v.setController(controller);
		controller.setView(v);
		GamePanel game = v.getGamePanel();
		ButtonsPanel buttonsPanel = v.getButtonPanel();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1300,1000);
		frame.getContentPane().add(BorderLayout.CENTER, game);
		frame.getContentPane().add(BorderLayout.SOUTH , buttonsPanel);
		frame.setVisible(true);
		
		//物件設置
		List<Role> roles =  Collections.checkedList( new ArrayList<Role>(), Role.class);
		List<Bullet> bullets = Collections.checkedList( new ArrayList<Bullet>(), Bullet.class);
		controller.setRoles(roles);
		controller.setBullets(bullets);
		v.setRoles(roles);
		v.setBullets(bullets);
		
		//使Game Panel能接收按鍵事件
			frame.addWindowListener(new WindowAdapter(){
				@Override
				public void windowOpened(WindowEvent e) {
					super.windowOpened(e);
					game.requestFocusInWindow();
				}
				@Override
				public void windowClosed(WindowEvent e) {
					super.windowClosed(e);
					game.requestFocusInWindow();
				}
					
			});
	}

}
