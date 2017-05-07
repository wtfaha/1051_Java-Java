package WarInterface;
import java.awt.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Lose extends JFrame {
	Container ct;
	BackgroundPanel bgp;
	JLabel jlTitle, jPicture;
	ImageIcon monster;
	JTextArea MonsterInfo;
	int LV, defence, HP, attack = 1000;
	int MonsterPictureNumber;
	Timer timer = new Timer();

	public Lose() {
		super("You Lose !!!");
		ct = this.getContentPane();
		this.setLayout(null);

		// 背景圖
		bgp = new BackgroundPanel((new ImageIcon("GUI/backgroundLose.jpg")).getImage());
		bgp.setBounds(0, 0, 1000, 750);
		ct.add(bgp);
		bgp.setLayout(null);

		// Title
		jlTitle = new JLabel();
		jlTitle.setText("¡¡Monster Win¡¡");
		Font TitleFont = new Font("Algerian", Font.BOLD, 50);
		jlTitle.setForeground(Color.WHITE);
		jlTitle.setFont(TitleFont);
		// jlTitle.setBackground(Color.WHITE);
		jlTitle.setBounds(120, -170, 500, 500);
		bgp.add(jlTitle);
		/*
		 * monster圖片要改~~ MonsterPictureNumber = getMonsterPictureNumber(Monster
		 * m); monster = new ImageIcon("monster"+ MonsterPictureNumber +
		 * ".png");
		 */
		monster = new ImageIcon("monster.png"); // 要改用上面註解的
		jPicture = new JLabel(monster);
		jPicture.setBounds(170, 100, 500, 500);
		bgp.add(jPicture);
		setMonsterInfo(MonsterInfo); // monster資訊
		// RebornInfo(); 回復草資訊

		this.setSize(700, 700);
		this.setLocation(0, 0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		timer.schedule(new Change(this), 2500); // 自動關掉Frame
	}

	/*
	 * public void getMonsterInfo(int LV, int HP, int attack, int defence) {
	 * this.LV = LV; this.HP = HP; this.attack = attack; this.defence = defence;
	 * }
	 */

	/*
	 * getMonsterPictureNumber(Monster m) { this.MonsterPictureNumber =
	 * m.MonsterPictureNumber; }
	 */

	// 建左邊monster資訊框框
	public void setMonsterInfo(JTextArea MonsterInfo) {
		MonsterInfo = new JTextArea();
		// getMonsterInfo(LV, HP, attack, defence) //拿怪物資訊
		// MonsterInfo.setBackground(Color.BLUE);
		MonsterInfo.setText("等級:\n" + LV + "\n血量:\n" + HP + "\n攻擊力:\n" + attack + "\n防禦力:\n" + defence);
		MonsterInfo.setBounds(80, 180, 170, 400);
		Font MonsterInfoFont = new Font("王漢宗中隸書繁", Font.BOLD, 36);
		MonsterInfo.setForeground(Color.BLACK);
		MonsterInfo.setFont(MonsterInfoFont);
		MonsterInfo.setEditable(false);
		MonsterInfo.setOpaque(false);
		bgp.add(MonsterInfo);
	}

	/*
	 * public void getMonsterInfo(Monster M) { this.LV = M.LV; this.HP = M.HP;
	 * this.attack = M.attack; this.defence = M.defence; }
	 */

	/*
	 * public void getLevelInfo(Monster M) { this.experience = M.experience;
	 * this.gainExperience = M.gainExperience; }
	 */

	/*
	 * 建回復草資訊框框 public void RebornInfo() {
	 * 
	 * JPanel Reborn = new JPanel(); Reborn.setSize(50,50);
	 * Reborn.setBounds(500, 200, 150, 300); Reborn.setBackground(Color.CYAN);
	 * bgp.add(Reborn); JTextArea LabelInside = new JTextArea();
	 * LabelInside.setText("回復草:\n"); LabelInside.setBackground(Color.CYAN);
	 * Font rebornFont = new Font("王漢宗中隸書繁", Font.BOLD, 30);
	 * LabelInside.setFont(rebornFont); Reborn.add(LabelInside); //回復草圖片
	 * ImageIcon drink1 = new ImageIcon("red.png");
	 * drink1.setImage(drink1.getImage().getScaledInstance(35, 35,
	 * Image.SCALE_DEFAULT));
	 * 
	 * JLabel drink = new JLabel(drink1);
	 * 
	 * Reborn.add(drink); }
	 */
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
