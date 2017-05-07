package WarInterface;
import java.awt.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Win extends JFrame {
	Container ct;
	BackgroundPanel bgp;
	JLabel jlTitle, jPicture;
	ImageIcon player;
	int HP = 100, LV = 100, attack = 100, defence = 100, experience = 100, gainExperience = 100;
	JTextArea PlayerInfo, LevelInfo;
	Timer timer = new Timer();
	int PlayerPictureNumber;

	public Win() {
		super("You Win !!!");
		ct = this.getContentPane();
		this.setLayout(null);

		// 背景圖
		bgp = new BackgroundPanel((new ImageIcon("GUI/bgp2.jpg")).getImage());
		bgp.setBounds(0, 0, 1000, 750);
		ct.add(bgp);

		bgp.setLayout(null);

		// Title
		jlTitle = new JLabel(); 
		   jlTitle.setText("!!You Win!!");
	       Font TitleFont = new Font("Algerian", Font.BOLD, 72);
	       jlTitle.setForeground(Color.YELLOW);
	       jlTitle.setFont(TitleFont);
	       jlTitle.setBounds(130, -130, 500, 500);
	       bgp.add(jlTitle); 

		/*
		 * player圖片要改~~ PlayerPictureNumber = getPlayerPictureNumber(Player P);
		 * PlayerPictureNumber = new ImageIcon("player"+ PlayerPictureNumber +
		 * ".png");
		 */
		player = new ImageIcon("GUI/彥勳.png"); // 要改用上面註解的
		jPicture = new JLabel(player);
		jPicture.setBounds(75, 68, 500, 500);
		bgp.add(jPicture);

		setPlayerInfo(PlayerInfo); 
		//setLevelInfo(); // 建左邊升等資訊

		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);
		//timer.schedule(new Change(this), 1000); // 自動關掉Frame
		
	}

	/*
	 * public void getPlayerInfo(Player P) { this.LV = P.LV; this.HP = P.HP;
	 * this.attack = P.attack; this.defence = P.defence; }
	 */

	/*
	 * public void getLevelInfo(Player P) { this.experience = P.experience;
	 * this.gainExperience = P.gainExperience; }
	 */

	/*
	 * public int getPlayerPictureNumber(Player P) { this.PlayerPictureNumber =
	 * P.PlayerPictureNumber; }
	 */
	// 建右邊Player資訊框框
	public void setPlayerInfo(JTextArea PlayerInfo) {
		PlayerInfo = new JTextArea();
		// getPlayerInfo(LV, HP, attack, defence);
		// PlayerInfo.setBackground(Color.WHITE);
		 PlayerInfo.setText(" 恭喜你成為肥宅中的霸主 \n       那是什麼?\n      還是肥宅!!!!!");
		 PlayerInfo.setBounds(90, 475, 500, 150);
		PlayerInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		Font PlayerInfoFont = new Font("王漢宗中隸書繁", Font.BOLD, 36);
		PlayerInfo.setForeground(Color.BLACK);
		PlayerInfo.setBackground(Color.WHITE);
		PlayerInfo.setOpaque(false);
		PlayerInfo.setFont(PlayerInfoFont);
		PlayerInfo.setEditable(false);
		bgp.add(PlayerInfo);
	}

	
	// 建左邊升等資訊框框
	public void setLevelInfo() {
		JPanel InsidePanel = new JPanel();
		JTextArea LevelInfo = new JTextArea();
		// PlayerInfo.setBackground(Color.WHITE);
		LevelInfo.setText("獲得經驗值:\n" + gainExperience + "\n獲得物品:\n");
		LevelInfo.setBounds(30, 230, 160, 200);
		LevelInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 6));
		Font LevelInfoFont = new Font("王漢宗中隸書繁", Font.BOLD, 25);
		LevelInfo.setForeground(Color.BLACK);
		LevelInfo.setOpaque(false);
		LevelInfo.setFont(LevelInfoFont);
		LevelInfo.setEditable(false);
		bgp.add(LevelInfo);
	}
	class BackgroundPanel extends JPanel {
		Image im;

		public BackgroundPanel(Image im) {
			this.im = im;
			this.setOpaque(true);
		}

		// Draw the back ground.
		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);

		}
	}
}



// }
