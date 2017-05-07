package PlayerWalk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Music.BGM;
import Start.GUIStart;
import player.Player;

/**
 * 游戏主窗体
 * 
 * @author yy
 *
 */
public class mainFrame extends JFrame implements gameConfig {
	// 游戏面板
	JPanel panel;
	int action = 0;
	BGM B;
	Timer t;

	public mainFrame() {
		t = new Timer();
		t.schedule(new TimerTask(){

			@Override
			public void run() {
				// TODO 自動產生的方法 Stub
				B = new BGM("H.mp3");
			}
			
		},0);
		GUIStart frame = new GUIStart();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		frame.setVisible(true);
	}
	
	public void playMusic()
	{
		t = new Timer();
		t.schedule(new TimerTask(){

			@Override
			public void run() {
				// TODO 自動產生的方法 Stub
				B = new BGM("維多利亞港.mp3");
			}
			
		},0);
	}

	/**
	 * 设置窗体
	 */
	public void init(Player p) {
		B.close();
		playMusic();
		this.setTitle(title);
		this.setSize(frameX, frameY);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(3);
		setLocationRelativeTo(null);
		// 创建游戏面板
		panel = setpanel();

		this.add(panel);
		this.setVisible(true);
		// 安装键盘监听器
		PanelListenner plis = new PanelListenner();
		this.addKeyListener(plis);

		// 启动人物移动线程
		Player1 player = new Player1();
		player.setP(p);
		// player.setP(new Player("123"));
		player.start();

		// 启动刷新面板线程
		UpdateThread ut = new UpdateThread(panel);
		ut.start();
	}

	/**
	 * 设置游戏面板
	 */
	public JPanel setpanel() {
		JPanel panel = new MyPanel();
		panel.setPreferredSize(new Dimension(panelX, panelY));
		panel.setLayout(null);
		panel.setBackground(Color.black);

		return panel;
	}

	/**
	 * 内部游戏按键监听类
	 * 
	 * @author yy
	 *
	 */
	class PanelListenner extends KeyAdapter {
		// 当按键按下
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			switch (code) {
			case KeyEvent.VK_UP:
				Player1.up = true;
				Player1.towards = 1;
				changeMap();
				break;
			case KeyEvent.VK_DOWN:
				Player1.down = true;
				Player1.towards = 2;
				changeMap();
				break;
			case KeyEvent.VK_LEFT:
				Player1.left = true;
				Player1.towards = 3;
				changeMap();
				break;
			case KeyEvent.VK_RIGHT:
				Player1.right = true;
				Player1.towards = 4;
				changeMap();
				break;

			default:
				break;
			}
		}

		public void changeMap() {// 判定要不要換地圖
			if (Player1.transport2 == 1) {// 如果要換
				ReadMapFile.reReadfile2();// 重新讀取地圖2的資訊
				Player1.transport2 = 0; // 調回0防止在別張地圖突然傳送
			}
			if (Player1.transport3 == 1) {
				ReadMapFile.reReadfile3();
				Player1.transport3 = 0;
			}
			if (Player1.transport4 == 1) {
				ReadMapFile.reReadfile4();
				Player1.transport4 = 0;
			}
			if (Player1.transport5 == 1) {
				ReadMapFile.reReadfile5();
				Player1.transport5 = 0;
			}
			if (Player1.transport6 == 1) {
				ReadMapFile.reReadfile6();
				Player1.transport6 = 0;
			}
			if (Player1.transport7 == 1) {
				ReadMapFile.reReadfile7();
				Player1.transport7 = 0;
			}
			if (Player1.transport8 == 1) {
				ReadMapFile.reReadfile8();
				Player1.transport8 = 0;
			}
			if (Player1.transport9 == 1) {
				ReadMapFile.reReadfile9();
				Player1.transport9 = 0;
			}
			if (Player1.transport10 == 1) {
				ReadMapFile.reReadfile10();
				Player1.transport10 = 0;
			}
		}

		// 当按键释放
		public void keyReleased(KeyEvent e) {
			int code = e.getKeyCode();
			switch (code) {
			case KeyEvent.VK_UP:
				Player1.up = false;
				Player1.up1 = 0;
				break;
			case KeyEvent.VK_DOWN:
				Player1.down = false;
				Player1.down1 = 0;
				break;
			case KeyEvent.VK_LEFT:
				Player1.left = false;
				Player1.left1 = 0;
				break;
			case KeyEvent.VK_RIGHT:
				Player1.right = false;
				Player1.right1 = 0;
				break;

			default:
				break;
			}
		}
	}

	/**
	 * 自定义内部游戏面板类
	 * 
	 * @author yy
	 *
	 */
	class MyPanel extends JPanel {
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			// 找到角色旁边的素材，上下左右各5格
			for (int i = Player1.getI() - 6; i <= Player1.getI() + 6; i++) {
				for (int j = Player1.getJ() - 6; j <= Player1.getJ() + 6; j++) {
					// 如果这一格没有超界
					if (i >= 0 && j >= 0 && i < ReadMapFile.map1.length && j < ReadMapFile.map1[0].length) {
						// 画第一层元素
						ImageIcon icon = GetMap.int2icon(ReadMapFile.map1[i][j]);
						g.drawImage(icon.getImage(),
								(Player1.px - elesize / 2) + ((j - Player1.getJ()) * elesize) - (Player1.mx % elesize),
								(Player1.py - elesize / 2) + ((i - Player1.getI()) * elesize) - (Player1.my % elesize),
								elesize, elesize, null);
						// 第二层
						ImageIcon icon2 = GetMap.int2icon(ReadMapFile.map2[i][j]);
						g.drawImage(icon2.getImage(),
								(Player1.px - elesize / 2) + ((j - Player1.getJ()) * elesize) - (Player1.mx % elesize),
								(Player1.py - elesize / 2) + ((i - Player1.getI()) * elesize) - (Player1.my % elesize),
								elesize, elesize, null);
						// 第三层
						// ImageIcon icon3 =
						// GetMap.int2icon(ReadMapFile.map3[i][j]);
						// g.drawImage(icon3.getImage(),
						// (Player.px-elesize/2)+((j-Player.getJ())*elesize)-(Player.mx%elesize),
						// (Player.py-elesize/2)+((i-Player.getI())*elesize)-(Player.my%elesize),
						// elesize, elesize, null);
					}
				}
			}
			// g.setColor(Color.black);
			// g.fillRect(0, 0, 50, 650);
			// g.fillRect(0, 0, 650, 50);
			// g.fillRect(600, 0, 50, 650);
			// g.fillRect(0, 600, 650, 50);

			// 由于暂时还没弄好游戏角色的移动图，所以角色先用一个黑色的小球代替一下.... = =！
			// g.fillOval(Player.px-elesize/2, Player.py-elesize/2, elesize,
			// elesize);
			Player1.draw(g);
			// 个人的一个小想法，做一个黑色的图片，然后中间挖空一个圆，加上模糊效果，来模拟人的视野
			g.drawImage(shadow2.getImage(), 0, 0, 650, 650, null);
		}
	}
}