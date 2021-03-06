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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mvc.gameObject.GameObjects;
import role.Role;
import weapon.bullets.Bullet;
import weapon.gameEffects.GameEffect;

public class View {
	private JFrame parent;  //父介面
	private static Controller controller;  
	public GamePanel gamePanel;  //遊戲畫面
	public ButtonsPanel buttonsPanel;  //按鈕畫面
	public PlayerPanel playerPanel;  //玩家狀況
	private MapBuilder builder; // build the map whenever the view got settled the builder
	private Map1Director director; // to direct the builder
	private GameObjects gameObjects = GameObjects.getGameObjects();  //all objects will be painted in the game
	private static boolean startGame = false;  
	public boolean netWorking = false; // true if choose the networking mode
	public static int shootedBulletCount = 0;  //結局時 讓玩家知道他總共射了多少子彈
	
	/**作弊**/
	public static String cheatPassword = "";  //作弊用密碼
	public static boolean crazyMode = false; //是否困難模式
	public static String cheatGunStyle = "";  //作弊槍

	
	public View(JFrame frame) {
		this.parent = frame;
		gamePanel = new GamePanel();
		playerPanel = new PlayerPanel();
		buttonsPanel = new ButtonsPanel(playerPanel);
	}
	
	public void updatePlayerHp(){
		playerPanel.updateText();
	}
	
	public void refreshScreen(){
		//更新畫面
		gamePanel.repaint();
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
	
	public Map1Director getDirector() {
		return director;
	}
	
	public void setDirector(Map1Director director) {
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
 	
 	public void showGameFinalWinMessage(){
		 JOptionPane.showMessageDialog(parent, "你擊敗了最終大魔王，並且闖關成功，恭喜 !!\n 總共射出子彈 : "+shootedBulletCount +"\n點擊Game Tutorial 並輸入:crazy\n進行瘋狂模式吧!");
 	}

   	static class PlayerPanel extends JPanel { 
		private static final String Player1 = "Player1 ";
		private static final String Player2 = "Player2 ";
		private static final String HP = "HP : ";
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
			if( controller.getPlayer1() != null )
			{
				player1HP.setText(Player1+HP+controller.getPlayer1().getHp());
				if ( controller.getPlayer1().getHp() <= 0 )
					player1HP.setText("Game Over");
				if ( controller.getPlayer1().getHp() < 150 )  //警告生命顏色
					player1HP.setForeground(Color.red);
				else
					player1HP.setForeground(Color.black);
			}
			
			if(controller.getPlayer2() == null)
				player2HP.setText("");
			else
			{
				player2HP.setText(Player2+HP+controller.getPlayer2().getHp());
				if ( controller.getPlayer2().getHp() < 150 )
					player2HP.setForeground(Color.red);
				else
					player2HP.setForeground(Color.black);
				if ( controller.getPlayer2().getHp() <= 0 )
					player2HP.setText("Game Over");
			}
				
		}
	}

	//放置按鈕的panel (開始遊戲按鈕或者連線等等)
	class ButtonsPanel extends JPanel implements ActionListener , KeyListener{
		private static final String NET_MESSAGE = "1p TGFH控制走位 C射擊   2p 方向鍵控制走位，L進行射擊，記得切掉中文輸入法。";
		private static final String NET_CONNECT = "連線功能尚未開放.";
		private Dir playerCurDir = Dir.NORTH;  //用來記錄玩家目前面向方位!
		private Dir player2CurDir = Dir.NORTH;  //用來記錄玩家目前面向方位!
		private boolean releaseWhileShooting = true;  //為了讓玩家不能按著空白鍵連射，按下去時為false,放開才為true
		private JButton start; 
		private JButton networkGame;
		private JButton twoPlayerStart;
		private JFrame netFrame;
		private JButton netConnectBTN;
		private JTextField netIpED;
		private JLabel netMessage;
		
		private int player1CommandLevel = 0;  //為了改良halt問題 順暢度 用一個數字紀錄是不是連續方向 是 就不halt
		private int player2CommandLevel = 0;  //為了改良halt問題 順暢度 用一個數字紀錄是不是連續方向 是 就不halt
		
		private Set<Character> commandSet = new HashSet<Character>();
		public ButtonsPanel(JPanel hpPanel){
			add(hpPanel); //將生命欄位設置近來
			// 基本按鈕設置
			start = new JButton("Start Game");
			networkGame = new JButton("Game Tutorial!!");
			twoPlayerStart = new JButton("Two Player Game");
			twoPlayerStart.setBackground(Color.cyan);
			twoPlayerStart.setPreferredSize(new Dimension(200,60));
			twoPlayerStart.setFont(new Font("Arial", Font.PLAIN, 20));
			twoPlayerStart.addActionListener(this);
			start.setBackground(Color.cyan);
			start.setPreferredSize(new Dimension(200,60));
			start.setFont(new Font("Arial", Font.PLAIN, 20));
			start.addActionListener(this);
			networkGame.setFont(new Font("Arial", Font.PLAIN, 20));
			networkGame.setBackground(Color.cyan);
			networkGame.setPreferredSize(new Dimension(350,60));
			networkGame.addActionListener(this);
			add(start);
			add(twoPlayerStart);
			add(networkGame);

			//連線版面設置
			netFrame = new JFrame("遊戲教學");
			netConnectBTN = new JButton("Input IP");
			netConnectBTN.setFont(new Font("Arial", Font.PLAIN, 20));
			netMessage = new JLabel(NET_MESSAGE);
			netIpED = new JTextField();
			netIpED.setPreferredSize(new Dimension(250,30));
			JPanel panel = new JPanel();
			panel.add(netIpED);
			panel.add(netConnectBTN);
			netConnectBTN.addActionListener(this);
			netFrame.getContentPane().add(panel, BorderLayout.NORTH);
			netFrame.getContentPane().add(netMessage,BorderLayout.CENTER);
	
			addKeyListener(this);
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
					parent.setVisible(true);
					controller.startGame();
					new ShootingCommand().start();
				}
				else{
					// End game Event 
					 System.exit(0); //leave
				}
			}
			else if (event.equals(twoPlayerStart)){
				if ( !startGame ){
					// Start game Event
					start.setText("End Game");
					startGame = true; 
					//make playerPanel visible
					parent.setVisible(true);
					controller.startP2PGame();
					new ShootingCommand().start();
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
			else if (event.equals(netConnectBTN))
			{
				if(netIpED.getText().equals("crazy"))
					crazyMode = true;
				else if ( netIpED.getText().equals("MachineEX"))
					cheatGunStyle = "MachineEX";
				else if ( netIpED.getText().equals("Sniper"))
					cheatGunStyle = "Sniper";
				else if ( netIpED.getText().equals("Machine"))
					cheatGunStyle = "Machine";
				else
					cheatPassword = netIpED.getText();
				netIpED.setText("");
			}
			playerPanel.updateText();
		}
		@Override
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			if (!startGame) return; //若遊戲還沒開始 則不反應按鍵
			if (!netWorking)  //single game
			{
				switch(code){
				case KeyEvent.VK_T:
					commandSet.remove('C');
					if(controller.getPlayer1()!=null)
					if(controller.getPlayer1().getCurDir() != Dir.NORTH 
					|| controller.getPlayer1().getCurAct() == ActionType.HALT)
						player1CommandLevel++;
					playerCurDir = Dir.NORTH;
					Log.d("up");
					controller.movePlayer(ActionType.WALK, Dir.NORTH);
					break;
				case KeyEvent.VK_G:
					commandSet.remove('C');
					if(controller.getPlayer1()!=null)
					if( controller.getPlayer1().getCurDir() != Dir.SOUTH 
							|| controller.getPlayer1().getCurAct() == ActionType.HALT)
						player1CommandLevel++;
					playerCurDir = Dir.SOUTH;
					Log.d("down");
					controller.movePlayer(ActionType.WALK, Dir.SOUTH);
					break;
				case KeyEvent.VK_F:
					commandSet.remove('C');
					if(controller.getPlayer1()!=null)
					if(controller.getPlayer1().getCurDir() != Dir.WEST 
							|| controller.getPlayer1().getCurAct() == ActionType.HALT)
						player1CommandLevel++;
					playerCurDir = Dir.WEST;
					Log.d("left");
					controller.movePlayer(ActionType.WALK, Dir.WEST);
					break;
				case KeyEvent.VK_H:
					commandSet.remove('C');
					if(controller.getPlayer1()!=null)
					if(controller.getPlayer1().getCurDir() != Dir.EAST 
							|| controller.getPlayer1().getCurAct() == ActionType.HALT)
						player1CommandLevel++;
					playerCurDir = Dir.EAST;
					controller.movePlayer(ActionType.WALK, Dir.EAST);
					break;
				case KeyEvent.VK_C:  //also shoot
					commandSet.add('C');
					Log.d("shoot");
					if(controller.getPlayer1()!=null)
					if(controller.getPlayer1().getCurAct() != ActionType.SHOOT)
						player1CommandLevel = player1CommandLevel >= 1 ? 1 : player1CommandLevel + 1;
					break;
				case KeyEvent.VK_UP:
					commandSet.remove('L');
					if(controller.getPlayer2()!=null)
					if(controller.getPlayer2().getCurDir() != Dir.NORTH 
					|| controller.getPlayer2().getCurAct() == ActionType.HALT)
						player2CommandLevel++;
					player2CurDir = Dir.NORTH;
					controller.movePlayer2(ActionType.WALK, Dir.NORTH);
					break;
				case KeyEvent.VK_DOWN:
					commandSet.remove('L');
					if(controller.getPlayer2()!=null)
					if(controller.getPlayer2().getCurDir() != Dir.SOUTH 
					|| controller.getPlayer2().getCurAct() == ActionType.HALT)
						player2CommandLevel++;
					player2CurDir = Dir.SOUTH;
					controller.movePlayer2(ActionType.WALK, Dir.SOUTH);
					break;
				case KeyEvent.VK_LEFT:
					commandSet.remove('L');
					if(controller.getPlayer2()!=null)
					if(controller.getPlayer2().getCurDir() != Dir.WEST 
					|| controller.getPlayer2().getCurAct() == ActionType.HALT)
						player2CommandLevel++;
					player2CurDir = Dir.WEST;
					controller.movePlayer2(ActionType.WALK, Dir.WEST);
					break;
				case KeyEvent.VK_RIGHT:
					commandSet.remove('L');
					if(controller.getPlayer2()!=null)
					if(controller.getPlayer2().getCurDir() != Dir.EAST 
					|| controller.getPlayer2().getCurAct() == ActionType.HALT)
						player2CommandLevel++;
					player2CurDir = Dir.EAST;
					controller.movePlayer2(ActionType.WALK, Dir.EAST);
					break;
				case KeyEvent.VK_L:  //also shoot
					if(controller.getPlayer2()!=null)
					if(controller.getPlayer2().getCurAct() != ActionType.SHOOT)
						player2CommandLevel = player2CommandLevel >= 1 ? 1 : player2CommandLevel + 1;
						commandSet.add('L');
					break;
				case KeyEvent.VK_ENTER:  //印出遊戲資訊
					Log.d("Role : " + gameObjects.rolesSize() + "Bullet : " + gameObjects.bulletSize() + 
							" 怪物數量: " + controller.getRemainningMonster() + " 掉落物 : " + gameObjects.fallenItemSize());
					break;
				}
			}
			else  //網路遊戲 要傳指令給server的controller
			{
				
			}
		}
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyReleased(KeyEvent e) {
			int code = e.getKeyCode();
			if (!startGame) return;  //若遊戲還沒開始 則不反應按鍵
			if(( code == KeyEvent.VK_T ||  code == KeyEvent.VK_G || 
					 code == KeyEvent.VK_F || code == KeyEvent.VK_H || code == KeyEvent.VK_C))
			{
				player1CommandLevel = player1CommandLevel == 0 ? 0 :player1CommandLevel - 1;
				Log.d(player1CommandLevel+"");
				if( player1CommandLevel == 0)
				{
					controller.movePlayer(ActionType.HALT, playerCurDir);
					Log.d("halt");
					commandSet.remove('C');
				}

			}

			
			else if (( code == KeyEvent.VK_UP ||  code == KeyEvent.VK_DOWN || 
					 code == KeyEvent.VK_LEFT || code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_L) ) //net work
			{
				player2CommandLevel = player2CommandLevel == 0 ? 0 :player2CommandLevel - 1;
				Log.d(player2CommandLevel+"");
				if( player2CommandLevel == 0)
				{
					controller.movePlayer2(ActionType.HALT, player2CurDir);
					Log.d("halt");
					commandSet.remove('L');
				}
		
			}

				;
		}
		class ShootingCommand extends Thread{
			@Override
			public void run(){
				while(true){
					if(commandSet.contains('C'))
						controller.movePlayer(ActionType.SHOOT, playerCurDir);
					if(commandSet.contains('L'))
						controller.movePlayer2(ActionType.SHOOT, player2CurDir);
						try {
							Thread.sleep(30);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
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
			try{
				Model m;
				Boolean cycle;
				boolean isEffect;
				buildMap(g);
				Iterator<Model> iterator = gameObjects.iterator();
				while(iterator.hasNext())
				{
					m = iterator.next();
					isEffect = m.getParent() instanceof GameEffect ? false : true;  //是特效的話動畫不循環
					Image img = m.getiS().next(isEffect);
					if (img != null)
						g.drawImage( img , m.getcX(), m.getcY(), null );
					else
						m.delete();
				}
				
				buttonsPanel.requestFocusInWindow();
			}catch(Exception err){
				err.printStackTrace();
			}
			
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
		Map1Director director = new Map1Director();
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
		
		//使Game Panel能接收按鍵事件
			frame.addWindowListener(new WindowAdapter(){
				@Override
				 public void windowActivated( WindowEvent e){ 
					buttonsPanel.requestFocusInWindow(); 
	              } 
				@Override
				public void windowOpened( WindowEvent e){ 
					buttonsPanel.requestFocusInWindow(); 
	              } 
					
			});
	}

}
