package BattleFrame;
//以下為BattleFrame含有的所有method功能

//Button() 令按鈕變成原始狀態
//atkButton() 令按鈕變成技能狀態
//battle(double,double) 戰鬥係數判別
//playerAttack(int,int,int,double) 玩家攻擊怪物
//monsterAttack(int,int) 怪物攻擊玩家
//playerDead() 如果玩家死亡
//finished() 金手指
//systemRemind() 系統提醒是否合成或強化裝備
//recovery(int) 喝水方法
//line:523 需要一個EndGame()指令，去呼叫許家瑀的 GAME OVER 畫面!!

//2個private的內層class用來延遲monster或player的移動

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Music.BGM;
//import BattleFrame.BattleFrame.DateTask;
import PlayerWalk.Player1;
import WarInterface.Change;
import WarInterface.Dead;
import WarInterface.Win;
//import Delay.DateTask;
import interFace.Recovery;
import interFace.WarEnd;
import interFace.battle;
import monster.Monster;
import player.Player;
import player.Skill;
import problem.LV10Problem;
import problem.LV1Problem;
import problem.LV2Problem;
import problem.LV3Problem;
import problem.LV4Problem;
import problem.LV5Problem;
import problem.LV6Problem;
import problem.LV7Problem;
import problem.LV8Problem;
import problem.LV9Problem;
import PlayerWalk.Player1;

public class BattleFrame extends JFrame implements battle, WarEnd, Recovery {
	private LV1Problem lv1Problem = new LV1Problem();
	private LV2Problem lv2Problem = new LV2Problem();
	private LV3Problem lv3Problem = new LV3Problem();
	private LV4Problem lv4Problem = new LV4Problem();
	private LV5Problem lv5Problem = new LV5Problem();
	private LV6Problem lv6Problem = new LV6Problem();
	private LV7Problem lv7Problem = new LV7Problem();
	private LV8Problem lv8Problem = new LV8Problem();
	private LV9Problem lv9Problem = new LV9Problem();
	private LV10Problem lv10Problem = new LV10Problem();
	private JPanel panel;
	private Icon playerIcon, monsterIcon;
	private JLabel label1, label2, label3;
	private JButton[] buttons;
	private JButton[] skillButtons;
	private JButton[] drinkButtons;
	private JTextArea textArea;
	private JTextField playerName, monsterName, pMaxBlood, pBlood, mMaxBlood, mBlood, pHP, mHP, pMaxEXP, pEXP, EXP;
	private JScrollPane scrollPane;
	private Random random = new Random();
	private Skill skill = new Skill();
	private Timer timer = new Timer();
	private int tmpPlayerHP = 0, tmpMonsterHP = 0, PlayerAtk = 0, MonsterAtk = 0, PlayerDeffense = 0,
			MonsterDeffense = 0, tmplv;
	private static final String[] names = { "攻擊", "金手指", "回復", "逃跑" };
	private static final String[] skills = { "二連擊", "專破你這個甲", "不是你死就是我亡", "神秘之力" };
	private static final String[] drinks = { "藥水(大)", "藥水(中)", "藥水(小)", "取消" };
	private static final String[] ans = { "A", "B", "C", "D" };
	private char tmpAnswer;
	private final int hpAndExpMaxLength = 250; // 玩家血條，怪物血條，經驗值的長度皆是250XP
	private int remainingPlayerHP = 1; // 儲存當前玩家剩餘的HP%數(250是分母)
	private int remainingMonsterHP = hpAndExpMaxLength; // 儲存當前怪物剩餘的HP%數(250是分母)
	private int remainingPlayerExp = 1; // 儲存當前玩家經驗值的%數(250是分母)

	private Player player;
	private Monster monster;
	BGM B;

	private class DateTask extends TimerTask {

		@Override
		public void run() { // 延遲之後做的事情
			// System.exit(0);
			Player1.playerGo();
			dispose();
		}

	}

	public BattleFrame(Player player, Monster monster,BGM BGM) {
		super("Player VS Monster!");
		this.player = player;
		this.monster = monster;
		this.B = BGM;

		remainingPlayerHP = (int) (hpAndExpMaxLength * player.getHP() / player.getMAX_HP());
		remainingPlayerExp = (int) (hpAndExpMaxLength * player.getEXP() / player.getMaxEXP());
		buttons = new JButton[4];
		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false); // 令panel透明化
		
		playerIcon = new ImageIcon(("彥勳.PNG"));
		Icon background = new ImageIcon(("bgp2.PNG"));

		label1 = new JLabel(playerIcon);
		label1.setBounds(-40, 220, playerIcon.getIconWidth(), playerIcon.getIconHeight());
		add(label1);

		label2 = new JLabel(background);
		label2.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		label2.setOpaque(false);
		panel.add(label2);
//		System.out.printf("monster%d.PNG", monster.getNumber());
//		monsterIcon = new ImageIcon(getClass().getResource(String.format("monster%d.PNG", monster.getNumber())));
		Random ran=new Random();
		int random=ran.nextInt(3);
		switch(random){
		case 0:
			monsterIcon = new ImageIcon((String.format("monster%d-1.png", monster.getNumber())));
			break;
		case 1:
			monsterIcon = new ImageIcon((String.format("monster%d-2.png", monster.getNumber())));
			break;
		default:
			monsterIcon = new ImageIcon((String.format("monster%d-3.png", monster.getNumber())));
			break;
		}

		label3 = new JLabel(monsterIcon);
		label3.setBounds(600, 30, monsterIcon.getIconWidth(), monsterIcon.getIconHeight());
		add(label3);

		pMaxBlood = new JTextField();
		pMaxBlood.setBounds(87, 207, hpAndExpMaxLength + 6, 26);
		pMaxBlood.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		pMaxBlood.setOpaque(false);
		pMaxBlood.setEditable(false);
		add(pMaxBlood);

		pBlood = new JTextField();
		pBlood.setBounds(90, 210, remainingPlayerHP, 20);
		pBlood.setBorder(BorderFactory.createEmptyBorder());
		pBlood.setEditable(false);
		pBlood.setBackground(Color.red);
		add(pBlood);

		pHP = new JTextField("HP:");
		pHP.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
		pHP.setBounds(50, 210, 40, 20);
		pHP.setOpaque(false); // 透明化
		pHP.setBorder(BorderFactory.createEmptyBorder()); // 去邊框
		pHP.setEditable(false); // 不可編輯
		add(pHP);

		pMaxEXP = new JTextField();
		pMaxEXP.setBounds(87, 237, hpAndExpMaxLength + 6, 26);
		pMaxEXP.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		pMaxEXP.setOpaque(false);
		pMaxEXP.setEditable(false);
		add(pMaxEXP);

		pEXP = new JTextField();
		pEXP.setBounds(90, 240, remainingPlayerExp, 20);
		pEXP.setBorder(BorderFactory.createEmptyBorder());
		pEXP.setEditable(false);
		pEXP.setBackground(Color.orange);
		add(pEXP);

		EXP = new JTextField("EXP:");
		EXP.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
		EXP.setBounds(40, 240, 50, 20);
		EXP.setOpaque(false); // 透明化
		EXP.setBorder(BorderFactory.createEmptyBorder()); // 去邊框
		EXP.setEditable(false); // 不可編輯
		add(EXP);

		playerName = new JTextField(
				String.format("%s : %s/%s", player.getNickName(), player.getHP(), player.getMAX_HP()));
		playerName.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 25)); // 設定字體大小
		playerName.setBounds(60, 160, 400, 30);
		playerName.setOpaque(false); // 透明化
		playerName.setBorder(BorderFactory.createEmptyBorder()); // 去邊框
		playerName.setEditable(false); // 不可編輯
		add(playerName);

		mMaxBlood = new JTextField();
		mMaxBlood.setBounds(647, 417, hpAndExpMaxLength + 6, 26);
		mMaxBlood.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		mMaxBlood.setOpaque(false);
		mMaxBlood.setEditable(false);
		add(mMaxBlood);

		mBlood = new JTextField();
		mBlood.setBounds(650, 420, hpAndExpMaxLength, 20);
		mBlood.setBorder(BorderFactory.createEmptyBorder());
		mBlood.setEditable(false);
		mBlood.setBackground(Color.red);
		add(mBlood);

		mHP = new JTextField("HP:");
		mHP.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
		mHP.setBounds(610, 420, 40, 20);
		mHP.setOpaque(false); // 透明化
		mHP.setBorder(BorderFactory.createEmptyBorder()); // 去邊框
		mHP.setEditable(false); // 不可編輯
		add(mHP);

		monsterName = new JTextField(
				String.format("%s : %s/%s", monster.getName(), monster.getHP(), monster.getMAX_HP()));
		monsterName.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 25)); // 設定字體顯示效果
																			// Font
																			// mf
																			// =
																			// new
																			// Font(String
																			// 字體，int
																			// 風格，int
																			// 大小);
																			// 風格有3個常量:Font.PLAIN,
																			// Font.BOLD,
																			// Font.ITALIC
		monsterName.setBounds(620, 376, 400, 30);
		monsterName.setOpaque(false); // 透明化
		monsterName.setBorder(BorderFactory.createEmptyBorder()); // 去邊框
		monsterName.setEditable(false); // 不可編輯
		add(monsterName);

		buttons[0] = new JButton(names[0]);
		buttons[0].setBounds(600, 575, 200, 100);
		add(buttons[0]);
		buttons[1] = new JButton(names[1]);
		buttons[1].setBounds(800, 575, 200, 100);
		add(buttons[1]);
		buttons[2] = new JButton(names[2]);
		buttons[2].setBounds(600, 675, 200, 100);
		add(buttons[2]);
		buttons[3] = new JButton(names[3]);
		buttons[3].setBounds(800, 675, 200, 100);
		add(buttons[3]);

		if (monster.getName() == "bigBoss")
			textArea = new JTextArea("你遭遇到了微積分程老師!\n你要怎麼做?\n");
		else if (monster.getName() == "littleBoss")
			textArea = new JTextArea("你遭遇到了PL阮老師!\n你要怎麼做?\n");
		else if (monster.getName() == "middleBoss")
			textArea = new JTextArea("你遭遇到了OS蔡老師!\n你要怎麼做?\n");
		else
			textArea = new JTextArea("你遭遇到了敵人!\n你要怎麼做?\n");

		textArea.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 15));
		textArea.setEditable(false);
		textArea.setOpaque(false);
		// textArea.setBackground(Color.CYAN);
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(0, 575, 600, 200); // 這時textArea已經鑲嵌進scrolPane中，所以setBound要用csrollPane設定，不可以使用textArea.setBound()!!!
		add(scrollPane);
		add(panel);

		ButtonHandler handler = new ButtonHandler();
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].addActionListener(handler);
		}

		MonsterAtk = monster.getOffense(); // 敵人總傷害
		MonsterDeffense = monster.getDefense(); // 敵人防禦
	}

	private class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getActionCommand() == "攻擊") {
				switch (monster.getLV()) {
				case 1:
					tmpAnswer = lv1Problem.printProblem(lv1Problem);
					textArea.setText(lv1Problem.getInput() + lv1Problem.getString());
					lv1Problem.setString("");
					break;
				case 2:
					tmpAnswer = lv2Problem.printProblem(lv2Problem);
					textArea.setText(lv2Problem.getString());
					lv2Problem.setString("");
					break;
				case 3:
					tmpAnswer = lv3Problem.printProblem(lv3Problem);
					textArea.setText(lv3Problem.getString());
					lv3Problem.setString("");
					break;
				case 4:
					tmpAnswer = lv4Problem.printProblem(lv4Problem);
					textArea.setText(lv4Problem.getString());
					lv4Problem.setString("");
					break;
				case 5:
					tmpAnswer = lv5Problem.printProblem(lv5Problem);
					textArea.setText(lv5Problem.getString());
					lv5Problem.setString("");
					break;
				case 6:
					tmpAnswer = lv6Problem.printProblem(lv6Problem);
					textArea.setText(lv6Problem.getString());
					lv6Problem.setString("");
					break;
				case 7:
					tmpAnswer = lv7Problem.printProblem(lv7Problem);
					textArea.setText(lv7Problem.getString());
					lv7Problem.setString("");
					break;
				case 8:
					tmpAnswer = lv8Problem.printProblem(lv8Problem);
					textArea.setText(lv8Problem.getString());
					lv8Problem.setString("");
					break;
				case 9:
					tmpAnswer = lv9Problem.printProblem(lv9Problem);
					textArea.setText(lv9Problem.getString());
					lv9Problem.setString("");
					break;
				case 10:
					tmpAnswer = lv10Problem.printProblem(lv10Problem);
					textArea.setText(lv10Problem.getString());
					lv10Problem.setString("");
					break;
				}

				for (int i = 0; i < ans.length; i++) {
					buttons[i].setLabel(ans[i]);
				}
				// 先答對java問題才會切換按鈕!
			}

			else if (event.getActionCommand() == "A") {
				if (tmpAnswer == 'A') {
					atkButton();
					textArea.setText(
							"姜姜姜蔣~~~回答正確\n\n請選擇要使用的技能\n1.二連擊(快速的打兩下)\n2.專破你這個甲(使敵人防禦降低)\n3.不是你死就是我亡(造成極大傷害，若無擊殺敵人將感到非常憤怒)\n4.神秘之力\n");
				} else {
					initButton();
					textArea.setText("回答錯誤了......\n可以再多讀點書嗎?\n\n");
					monsterAttack(MonsterAtk, PlayerDeffense);
					playerDead();
				}

			} else if (event.getActionCommand() == "B") {
				if (tmpAnswer == 'B') {
					atkButton();
					textArea.setText(
							"姜姜姜蔣~~~回答正確\n\n請選擇要使用的技能\n1.二連擊(快速的打兩下)\n2.專破你這個甲(使敵人防禦降低)\n3.不是你死就是我亡(造成極大傷害，若無擊殺敵人將感到非常憤怒)\n4.神秘之力\n");
				} else {
					initButton();
					textArea.setText("回答錯誤了......\n可以再多讀點書嗎?\n\n");
					monsterAttack(MonsterAtk, PlayerDeffense);
					playerDead();
				}
			} else if (event.getActionCommand() == "C") {
				if (tmpAnswer == 'C') {
					atkButton();
					textArea.setText(
							"姜姜姜蔣~~~回答正確\n\n請選擇要使用的技能\n1.二連擊(快速的打兩下)\n2.專破你這個甲(使敵人防禦降低)\n3.不是你死就是我亡(造成極大傷害，若無擊殺敵人將感到非常憤怒)\n4.神秘之力\n");
				} else {
					initButton();
					textArea.setText("回答錯誤了......\n可以再多讀點書嗎?\n\n");
					monsterAttack(MonsterAtk, PlayerDeffense);
					playerDead();
				}
			} else if (event.getActionCommand() == "D") {
				if (tmpAnswer == 'D') {
					atkButton();
					textArea.setText(
							"姜姜姜蔣~~~回答正確\n\n請選擇要使用的技能\n1.二連擊(快速的打兩下)\n2.專破你這個甲(使敵人防禦降低)\n3.不是你死就是我亡(造成極大傷害，若無擊殺敵人將感到非常憤怒)\n4.神秘之力\n");
				} else {
					initButton();
					textArea.setText("回答錯誤了......\n可以再多讀點書嗎?\n\n");
					monsterAttack(MonsterAtk, PlayerDeffense);
					playerDead();
				}
			}

			else if (event.getActionCommand() == "回復") {
				for (int i = 0; i < drinks.length; i++) {
					buttons[i].setLabel(drinks[i]);
				}
				textArea.setText(String.format("請選擇要喝甚麼?\n1.小型藥水 x %d\n2.中型藥水 x %d\n3.大型藥水 x %d\n",
						player.getBackPackage().getRecoveryDrinks().get(0).getNum(),
						player.getBackPackage().getRecoveryDrinks().get(1).getNum(),
						player.getBackPackage().getRecoveryDrinks().get(2).getNum()));

			}

			else if (event.getActionCommand() == "金手指") // 金手指密碼為30678
			{
				String passwordString = JOptionPane.showInputDialog(BattleFrame.this, "Enter the password!");
				int password = Integer.parseInt(passwordString);
				if (password == 30678) {
					String lvString = JOptionPane.showInputDialog(BattleFrame.this, "Enter the LV which you want!");
					finished(Integer.parseInt(lvString));
				} else
					JOptionPane.showMessageDialog(BattleFrame.this, "密碼錯誤");
				remainingPlayerHP = (int) (hpAndExpMaxLength * player.getHP() / player.getMAX_HP());
				if (remainingPlayerHP < 0) remainingPlayerHP = 0;
				
				pBlood.setBounds(90, 210, remainingPlayerHP, 20);
				remainingPlayerExp = (int) (hpAndExpMaxLength * player.getEXP() / player.getMaxEXP());
				pEXP.setBounds(90, 240, remainingPlayerExp, 20);
			}

			else if (event.getActionCommand() == "逃跑") {
				int randomEscapeNumber = random.nextInt(2);
				
				if(randomEscapeNumber == 10 || monster.getName() == "PL阮老師"
						|| monster.getName() == "OS蔡老師"|| monster.getName() == "微積分程老師") {
					textArea.setText("逃跑失敗!!\n");
					monsterAttack(MonsterAtk, PlayerDeffense);
					playerDead();
				}
				else{
					textArea.setText("成功逃跑啦~\n");
					timer.schedule(new DateTask(), 1000);
					BGM.close();
				}
			}

			else if (event.getActionCommand() == "藥水(大)") {
				recovery(3);
				initButton();
			} else if (event.getActionCommand() == "藥水(中)") {
				recovery(2);
				initButton();
			} else if (event.getActionCommand() == "藥水(小)") {
				recovery(1);
				initButton();
			} else if (event.getActionCommand() == "取消") {
				initButton();
			} else if (event.getActionCommand() == "二連擊") {
				battle(skill.choiceSkillAtk(1), skill.choiceSkillDeffense(1), 1);
				initButton();
			}

			else if (event.getActionCommand() == "專破你這個甲") {
				battle(skill.choiceSkillAtk(2), skill.choiceSkillDeffense(2), 1);
				initButton();
			}

			else if (event.getActionCommand() == "不是你死就是我亡") {
				battle(skill.choiceSkillAtk(3), skill.choiceSkillDeffense(3), 1);
				initButton();
			}

			else if (event.getActionCommand() == "神秘之力") {
				double skill4Tmp = skill.choiceSkillAtk(4);
				battle(skill4Tmp, skill.choiceSkillDeffense(4), skill4Tmp); // 因為每呼叫skill.choiceSkillAtk(4)一次，回傳的Tmp就會隨機一次，所以只呼叫一次並把
				initButton(); // Tmp存起來。這個skill4Tmp的值會用在playerAttack中去判斷是否"什麼事也沒有發生!"
			} // 因為判斷的依據為是否為0，所以除了這個技能以外的技能的此變數都設為1。
		}
	}

	private void initButton() {
		for (int i = 0; i < drinks.length; i++) {
			buttons[i].setLabel(names[i]);
		}
	}

	private void atkButton() {
		for (int i = 0; i < skills.length; i++) {
			buttons[i].setLabel(skills[i]);
		}
	}

	@Override
	public void battle(double PlayerAtkCoefficient, double PlayerDeffenseCoefficient, double skill4Tmp) {
		double s = skill4Tmp;
		int tmp = 0;
		PlayerAtk = 0;
		PlayerDeffense = 0;
		for (int i = 0; i < player.getBackPackage().getWeapon().size(); i++) // 玩家武器總傷害
			PlayerAtk += player.getBackPackage().getWeapon().get(i).getOffense();
		PlayerAtk += player.getBackPackage().getPet().getOffense(); // 玩家總傷害(加上寵物)
		PlayerAtk += player.getAtk(); // 總傷害(加上基礎攻擊)

		PlayerAtk *= PlayerAtkCoefficient;
		if (skill.choiceSkillAtk(2) == 0.7)
			tmp = 3; // 選擇破甲

		for (int i = 0; i < player.getBackPackage().getWeapon().size(); i++) // 玩家武器防禦
			PlayerDeffense += player.getBackPackage().getWeapon().get(i).getDefense();
		PlayerDeffense += player.getBackPackage().getPet().getDeffense(); // 玩家總防禦

		PlayerDeffense *= PlayerDeffenseCoefficient;

		if (player.getHP() > 0) {
			playerAttack(PlayerAtk, MonsterDeffense, tmp, s);
			if (player.getHP() > 0 && monster.getHP() <= 0) {
				if(monster.getName() != "微積分程老師")
				{
					switch (monster.dropWeapon()) {
					case 0:
						break;
					case 1:
						player.getBackPackage().getWeapon().get(0).setNum(1);
						textArea.append(String.format("您獲得了%s碎片!\n", player.getBackPackage().getWeapon().get(0).getName()));
						break;
					case 2:
						player.getBackPackage().getWeapon().get(1).setNum(1);
						textArea.append(String.format("您獲得了%s碎片!\n", player.getBackPackage().getWeapon().get(1).getName()));
						break;
					default:
						player.getBackPackage().getWeapon().get(2).setNum(1);
						textArea.append(String.format("您獲得了%s碎片!\n", player.getBackPackage().getWeapon().get(2).getName()));
						break;
					}
					for (int i = 0; i < random.nextInt(3) + 1; i++) {
						switch (monster.dropRecoverDrinks()) {
						case 0:
							player.getBackPackage().getRecoveryDrinks().get(0).setNum(1);
							textArea.append(String.format("您獲得了%s藥水\n",
									player.getBackPackage().getRecoveryDrinks().get(0).getDrinkName()));
							break;
						case 1:
							player.getBackPackage().getRecoveryDrinks().get(1).setNum(1);
							textArea.append(String.format("您獲得了%s藥水\n",
									player.getBackPackage().getRecoveryDrinks().get(1).getDrinkName()));
							break;
						case 2:
							player.getBackPackage().getRecoveryDrinks().get(2).setNum(1);
							textArea.append(String.format("您獲得了%s藥水\n",
									player.getBackPackage().getRecoveryDrinks().get(2).getDrinkName()));
							break;
						default:
							player.getBackPackage().getRecoveryDrinks().get(3).setNum(1);
							System.out.printf("您獲得了%s藥水\n",
									player.getBackPackage().getRecoveryDrinks().get(3).getDrinkName());
							break;
						}
					}
					JOptionPane.showMessageDialog(null, textArea.getText());
					int preExp = player.getEXP();
					if (player.setEXP(monster.getEXP()) != null && player.getLV() != 10) // 執行player.setEXP(monster.getEXP())會回傳一個字串，如果回傳的字串!=null
																	// 就呼叫showMessageDialog
					{
						if (player.getEXP() != preExp)
						JOptionPane.showMessageDialog(BattleFrame.this,
								String.format("%s等級提升為%d!!", player.getNickName(), player.getLV()), "LV up!!!",
								JOptionPane.PLAIN_MESSAGE);
					}
					
					// if (player.getBackPackage().getPet().setEXP(monster.getEXP())
					// != null)	
					textArea.setText(String.format("擊倒了對手！\n獲得%d經驗值\n\n", monster.getEXP()));
					double precent = monster.getEXP() * 1.0 / player.getMaxEXP();
					
					remainingPlayerExp = remainingPlayerExp + (int) (hpAndExpMaxLength * precent);
					
					if (remainingPlayerExp >= hpAndExpMaxLength) {
						pEXP.setBounds(90, 240, remainingPlayerExp-hpAndExpMaxLength, 20);

						if(player.getLV()!=10)
						{
							pBlood.setBounds(90, 210, hpAndExpMaxLength, 20);
							playerName.setText(String.format("%s : %s/%s", player.getNickName(), player.getHP(), player.getMAX_HP()));
						}
						remainingPlayerExp = 0;
						//System.out.println("1. player.getMaxEXP() : " + player.getMaxEXP() + " player.getEXP() : "+ player.getEXP());
					} else {
						pEXP.setBounds(90, 240, remainingPlayerExp, 20);
						//System.out.println("2222222222");
					}
					// JOptionPane.showMessageDialog(BattleFrame.this,
					// String.format(player.getBackPackage().getPet().setEXP(monster.getEXP())),
					// "Pet LV up!!!",
					// JOptionPane.PLAIN_MESSAGE);
					systemRemind();
				}
				if (monster.getHP() <= 0 && monster.getName() == "littleBoss") {
					player.setFuck("會一點程式的肥豬\n");
					JOptionPane.showMessageDialog(BattleFrame.this, (String.format("稱號變更為%s\n", player.getFuck())),
							"稱號變啦!!!", JOptionPane.PLAIN_MESSAGE);
				} else if (monster.getHP() <= 0 && monster.getName() == "middleBoss") {
					player.setFuck("948794狂\n");
					JOptionPane.showMessageDialog(BattleFrame.this, (String.format("稱號變更為%s\n", player.getFuck())),
							"稱號變啦!!!", JOptionPane.PLAIN_MESSAGE);
				} else if (monster.getHP() <= 0 && monster.getName() == "微積分程老師") {
					player.setFuck("雖臉蕭博駿\n");
					JOptionPane.showMessageDialog(BattleFrame.this, (String.format("稱號變更為%s\n", player.getFuck())),
							"稱號變啦!!!", JOptionPane.PLAIN_MESSAGE);
					Win win = new Win();
					if(monster.getName() == "微積分程老師") {
						Timer timer = new Timer();
						timer.schedule(new Change(this), 4000); //自動關掉Frame---->還是回到遊戲開始畫面?
						//System.exit(0);
					}
					win.setVisible(true);
				}
				timer.schedule(new DateTask(), 1500);
				B.close();
				// dispose();
			}
		}

		if (monster.getHP() > 0) {
			monsterAttack(MonsterAtk, PlayerDeffense);
			playerDead();
		}
	}

	public void playerAttack(int Player, int MonsterDeffense, int tmp, double s) // 玩家攻擊系統
	{
		textArea.setText("玩家攻擊!\n");
		label1.setBounds(60, 220, playerIcon.getIconWidth(), playerIcon.getIconHeight());
		timer.schedule(new playerMove(), 200);
		// dispose();
		if (s == 0)
			textArea.append("什麼事也沒有發生......\n\n");
		else {
			if (PlayerAtk > MonsterDeffense) {
				int damage = PlayerAtk - MonsterDeffense;
				double precent = damage * 1.0 / monster.getMAX_HP();
				remainingMonsterHP = remainingMonsterHP - (int) (hpAndExpMaxLength * precent);
				if (remainingMonsterHP <= 0) {
					mBlood.setBounds(650, 420, 0, 20);
					remainingMonsterHP = 0;
				} else
					mBlood.setBounds(650, 420, remainingMonsterHP, 20);
				monster.setHP(monster.getHP() - damage);
				monsterName.setText(
						String.format("%s HP: %s/%s", monster.getName(), monster.getHP(), monster.getMAX_HP()));
				textArea.append(String.format("玩家攻擊對手 %d 點!\n\n", damage));
			} else {
				textArea.append(String.format("效果奇差!!!\n怪物沒有絲毫損傷\n\n"));
			}
		}
		if (monster.getDefense() - tmp >= 0)
			monster.setDefense(monster.getDefense() - tmp);
		else
			monster.setDefense(0);
	}

	public void monsterAttack(int MonsterAtk, int PlayerDeffense) // 敵人攻擊系統
	{
		textArea.append("對手攻擊!\n");
		label3.setBounds(500, 30, monsterIcon.getIconWidth(), monsterIcon.getIconHeight());
		timer.schedule(new monsterMove(), 200);
		// dispose();
		if (MonsterAtk > PlayerDeffense) {
			int damage = MonsterAtk - PlayerDeffense;
			double precent = damage * 1.0 / player.getMAX_HP();
			remainingPlayerHP = remainingPlayerHP - (int) (hpAndExpMaxLength * precent);
			if (remainingPlayerHP <= 0) 
			{
				pBlood.setBounds(90, 210, 0, 20);
				remainingPlayerHP = 0;
			} else
				pBlood.setBounds(90, 210, remainingPlayerHP, 20);
			player.setHP(player.getHP() - damage);
			textArea.append(String.format("對手攻擊玩家 %d 點!\n\n", damage));
			playerName.setText(String.format("%s : %s/%s", player.getNickName(), player.getHP(), player.getMAX_HP()));
		} else {
			textArea.append(String.format("效果奇差!!!完全不會痛!\n......你開掛吧!\n\n"));
		}
		textArea.append(String.format("-----------MA:%d  MD:%d  PA:%d  PD:%d-----------\n", MonsterAtk, MonsterDeffense,
				PlayerAtk, PlayerDeffense));

	}

	private class monsterMove extends TimerTask {

		@Override
		public void run() { // 延遲之後做的事情
			label3.setBounds(600, 30, monsterIcon.getIconWidth(), monsterIcon.getIconHeight());
		}
	}

	private class playerMove extends TimerTask {

		@Override
		public void run() { // 延遲之後做的事情
			label1.setBounds(-40, 220, playerIcon.getIconWidth(), playerIcon.getIconHeight());
		}
	}

	public void playerDead() {
		if (monster.getHP() > 0 && player.getHP() <= 0) {
			textArea.append("玩家被擊倒了！\n\n");
			if (player.getBackPackage().getRecoveryDrinks().get(3).getNum() > 0) {
				int choice = JOptionPane.showConfirmDialog(BattleFrame.this, "要不要使用復活藥劑\n貼心小提醒:不使用的話就GAME OVER囉!\n",
						"復活", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (choice == 0) {
					if (player.getHP() + player.getBackPackage().getRecoveryDrinks().get(3).getAmount() > player
							.getMAX_HP()) // 若>最大血量則為最大血量
						player.setHP(player.getMAX_HP());
					player.getBackPackage().getRecoveryDrinks().get(3).setNum(-1);
					textArea.append(String.format("使用了復活藥劑!\n玩家換了一顆新鮮的肝!(血量已回滿)\n你的復活藥劑剩下  %d 個!\n",
							player.getBackPackage().getRecoveryDrinks().get(3).getNum()));
					remainingPlayerHP = hpAndExpMaxLength;
					pBlood.setBounds(90, 210, remainingPlayerHP, 20);
					playerName.setText(
							String.format("%s HP: %s/%s", player.getNickName(), player.getHP(), player.getMAX_HP()));
				} 
				else {
					JOptionPane.showMessageDialog(BattleFrame.this, String.format("你不珍惜最後的換肝機會......"), "GAME OVER",
							JOptionPane.PLAIN_MESSAGE);
					// System.exit(0);
					// setDefaultCloseOperation(HIDE_ON_CLOSE);
					Dead d = new Dead();
					if(monster.getName() == "微積分程老師" || monster.getName() == "OS蔡老師" || monster.getName() == "PL阮老師")
					{
						//timer.schedule(new DateTask(), 1500);
						System.exit(0);
					}
					else
					{
						dispose();
					}
					// 需要一個真正的EndGame()的指令... 並不是只單純關掉BattleFrame
					B.close();
				}
			} 
			else {
				JOptionPane.showMessageDialog(BattleFrame.this, String.format("您的肝已經壞死......"), "GAME OVER",
						JOptionPane.PLAIN_MESSAGE);
				// System.exit(0);
				// setDefaultCloseOperation(HIDE_ON_CLOSE);
				Dead d = new Dead();

				if(monster.getName() == "微積分程老師" || monster.getName() == "OS蔡老師" || monster.getName() == "PL阮老師")
				{
					//timer.schedule(new DateTask(), 1500);
					System.exit(0);
				}
				else
				{
					dispose();
				}
				// 需要一個真正的EndGame()的指令... 並不是只單純關掉BattleFrame
				B.close();
			}
		}
	}

	@Override
	public void finished(int choice) // 金手指
	{
		int n = choice;
		int temp = n - player.getLV();	//要再生幾等
		player.getBackPackage().getWeapon().get(0).setWeapon();
		player.getBackPackage().getWeapon().get(1).setWeapon();
		player.getBackPackage().getWeapon().get(2).setWeapon();
		player.getBackPackage().getPet().setDeffense(50);
		player.getBackPackage().getPet().setOffense(400);
		for (int i = 0; i < temp; i++)
			player.setEXP(player.getMaxEXP());
		JOptionPane.showMessageDialog(BattleFrame.this, String.format("你的等級提升為%d等", n));
		playerName.setText(String.format("%s HP: %s/%s", player.getNickName(), player.getHP(), player.getMAX_HP()));
		// textArea.setText(String.format("你的等級提升為%d等",n));
	}

	@Override
	public void systemRemind() {
		if (player.getBackPackage().getWeapon().get(0).getDefense() == 0
				&& player.getBackPackage().getWeapon().get(0).getNum() >= 3) // javaChineseBook
																				// 合成
		{
			int choice1 = JOptionPane.showConfirmDialog(BattleFrame.this,
					String.format("要不要合成 %s?", player.getBackPackage().getWeapon().get(0).getName()), "武器合成",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			switch (choice1) {
			case 0: // 即確認按鈕為"是"的狀態
				textArea.setText(String.format("恭喜 %s 合成成功!", player.getBackPackage().getWeapon().get(0).getName()));
				player.getBackPackage().getWeapon().get(0).setNum(-3);
				player.getBackPackage().getWeapon().get(0).setWeapon();
				break;
			case 1: // 即確認按鈕為"否"的狀態
				textArea.setText("就不要讓我看到你合成喔!\n");
				break;
			}
		}
		if (player.getBackPackage().getWeapon().get(0).getDefense() > 0
				&& player.getBackPackage().getWeapon().get(0).getNum() >= 1) // javaChineseBook
																				// 強化
		{
			int choice2 = JOptionPane.showConfirmDialog(BattleFrame.this,
					String.format("要不要強化 %s?", player.getBackPackage().getWeapon().get(0).getName()), "武器強化",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			// System.out.printf("要不要強化
			// %s?\n1.要\n2.不要勒\n",player.getBackPackage().getWeapon().get(1).getName());
			switch (choice2) {
			case 0:
				textArea.setText(String.format("恭喜 %s 強化成功!", player.getBackPackage().getWeapon().get(0).getName()));
				player.getBackPackage().getWeapon().get(0).setNum(-1);
				player.getBackPackage().getWeapon().get(0).lvUp();
				break;
			case 1:
				textArea.setText("就不要讓我看到你強化喔!\n");
				break;
			}
		}
		if (player.getBackPackage().getWeapon().get(1).getDefense() == 0
				&& player.getBackPackage().getWeapon().get(1).getNum() >= 3) // javaEnglishBook
																				// 合成
		{
			int choice3 = JOptionPane.showConfirmDialog(BattleFrame.this,
					String.format("要不要合成 %s?", player.getBackPackage().getWeapon().get(1).getName()), "武器合成",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			switch (choice3) {
			case 0:
				textArea.setText(String.format("恭喜 %s 合成成功!", player.getBackPackage().getWeapon().get(1).getName()));
				player.getBackPackage().getWeapon().get(1).setNum(-3);
				player.getBackPackage().getWeapon().get(1).setWeapon();
				break;
			case 1:
				textArea.setText("就不要讓我看到你合成喔!\n");
				break;
			}
		}
		if (player.getBackPackage().getWeapon().get(1).getDefense() > 0
				&& player.getBackPackage().getWeapon().get(1).getNum() >= 1) // javaEnglishBook
																				// 強化
		{
			int choice4 = JOptionPane.showConfirmDialog(BattleFrame.this,
					String.format("要不要強化 %s?", player.getBackPackage().getWeapon().get(1).getName()), "武器強化",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			switch (choice4) {
			case 0:
				textArea.setText(String.format("恭喜 %s 強化成功!", player.getBackPackage().getWeapon().get(1).getName()));
				player.getBackPackage().getWeapon().get(1).setNum(-1);
				player.getBackPackage().getWeapon().get(1).lvUp();
				break;
			case 1:
				textArea.setText("就不要讓我看到你強化喔!\n");
				break;
			}
		}
		if (player.getBackPackage().getWeapon().get(2).getDefense() == 0
				&& player.getBackPackage().getWeapon().get(2).getNum() >= 3) // shangPinPPT
																				// 合成
		{
			int choice5 = JOptionPane.showConfirmDialog(BattleFrame.this,
					String.format("要不要合成 %s?", player.getBackPackage().getWeapon().get(2).getName()), "武器和程",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			switch (choice5) {
			case 0:
				textArea.setText(String.format("恭喜 %s 合成成功!", player.getBackPackage().getWeapon().get(2).getName()));
				player.getBackPackage().getWeapon().get(2).setNum(-3);
				player.getBackPackage().getWeapon().get(2).setWeapon();
				break;
			case 1:
				textArea.setText("就不要讓我看到你合成喔!\n");
				break;
			}
		}
		if (player.getBackPackage().getWeapon().get(2).getDefense() > 0
				&& player.getBackPackage().getWeapon().get(2).getNum() >= 1) // shangPinPPT
																				// 強化
		{
			int choice6 = JOptionPane.showConfirmDialog(BattleFrame.this,
					String.format("要不要強化 %s?", player.getBackPackage().getWeapon().get(2).getName()), "武器強化",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			switch (choice6) {
			case 0:
				textArea.setText(String.format("恭喜 %s 強化成功!", player.getBackPackage().getWeapon().get(2).getName()));
				player.getBackPackage().getWeapon().get(2).setNum(-1);
				player.getBackPackage().getWeapon().get(2).lvUp();
				break;
			case 1:
				textArea.setText("就不要讓我看到你強化喔!\n");
				break;
			}
		}
	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		Player1.up = Player1.down = Player1.left = Player1.right = false;
	}

	@Override
	public void recovery(int ch) {
		if (player.getBackPackage().getRecoveryDrinks().get(0).getNum() > 0
				|| player.getBackPackage().getRecoveryDrinks().get(1).getNum() > 0
				|| player.getBackPackage().getRecoveryDrinks().get(2).getNum() > 0) {
			int choice;

			choice = ch;
			tmpPlayerHP = player.getHP();
			switch (choice) {
			case 1:
				if (player.getBackPackage().getRecoveryDrinks().get(0).getNum() > 0) {
					tmpPlayerHP = player.getHP();
					double precent = player.getBackPackage().getRecoveryDrinks().get(0).getAmount() * 1.0
							/ player.getMAX_HP();
					remainingPlayerHP = remainingPlayerHP + (int) (hpAndExpMaxLength * precent);
					if (remainingPlayerHP >= hpAndExpMaxLength) {
						pBlood.setBounds(90, 210, hpAndExpMaxLength, 20);
						remainingPlayerHP = hpAndExpMaxLength;
					} else
						pBlood.setBounds(90, 210, remainingPlayerHP, 20);
					if (player.getHP() + player.getBackPackage().getRecoveryDrinks().get(0).getAmount() > player
							.getMAX_HP()) // 若>最大血量則為最大血量
						player.setHP(player.getMAX_HP());
					else
						player.setHP(player.getHP() + player.getBackPackage().getRecoveryDrinks().get(0).getAmount());
					textArea.setText(String.format("使用小型藥水!\n恢復了%d 點!\n玩家的血從 %d 變成 %d\n\n",
							player.getBackPackage().getRecoveryDrinks().get(0).getAmount(), tmpPlayerHP,
							player.getHP()));
					player.getBackPackage().getRecoveryDrinks().get(0).setNum(-1);
				} else {
					textArea.setText("喔喔!你沒此種藥水了!\n");
				}
				break;
			case 2:
				if (player.getBackPackage().getRecoveryDrinks().get(1).getNum() > 0) {
					tmpPlayerHP = player.getHP();
					double precent = player.getBackPackage().getRecoveryDrinks().get(1).getAmount() * 1.0
							/ player.getMAX_HP();
					remainingPlayerHP = remainingPlayerHP + (int) (hpAndExpMaxLength * precent);
					if (remainingPlayerHP > hpAndExpMaxLength) {
						pBlood.setBounds(90, 210, hpAndExpMaxLength, 20);
						remainingPlayerHP = hpAndExpMaxLength;
					} else
						pBlood.setBounds(90, 210, remainingPlayerHP, 20);
					if (player.getHP() + player.getBackPackage().getRecoveryDrinks().get(1).getAmount() > player
							.getMAX_HP()) // 若>最大血量則為最大血量
						player.setHP(player.getMAX_HP());
					else
						player.setHP(player.getHP() + player.getBackPackage().getRecoveryDrinks().get(1).getAmount());
					textArea.setText(String.format("使用中型藥水!\n恢復了%d 點!\n玩家的血從 %d 變成 %d\n\n",
							player.getBackPackage().getRecoveryDrinks().get(1).getAmount(), tmpPlayerHP,
							player.getHP()));
					player.getBackPackage().getRecoveryDrinks().get(1).setNum(-1);
				} else {
					textArea.setText("喔喔!你沒此種藥水了!\n");
				}
				break;
			case 3:
				if (player.getBackPackage().getRecoveryDrinks().get(2).getNum() > 0) {
					tmpPlayerHP = player.getHP();
					double precent = player.getBackPackage().getRecoveryDrinks().get(2).getAmount() * 1.0
							/ player.getMAX_HP();
					remainingPlayerHP = remainingPlayerHP + (int) (hpAndExpMaxLength * precent);
					if (remainingPlayerHP > hpAndExpMaxLength) {
						pBlood.setBounds(90, 210, hpAndExpMaxLength, 20);
						remainingPlayerHP = hpAndExpMaxLength;
					} else
						pBlood.setBounds(90, 210, remainingPlayerHP, 20);
					if (player.getHP() + player.getBackPackage().getRecoveryDrinks().get(2).getAmount() > player
							.getMAX_HP()) // 若>最大血量則為最大血量
						player.setHP(player.getMAX_HP());
					else
						player.setHP(player.getHP() + player.getBackPackage().getRecoveryDrinks().get(2).getAmount());
					textArea.setText(String.format("使用大型藥水!\n恢復了%d 點!\n玩家的血從 %d 變成 %d\n\n",
							player.getBackPackage().getRecoveryDrinks().get(2).getAmount(), tmpPlayerHP,
							player.getHP()));
					player.getBackPackage().getRecoveryDrinks().get(2).setNum(-1);
				} else {
					textArea.setText("喔喔!你沒此種藥水了!\n");
				}
				break;
			}
		} else
			textArea.setText("你沒藥水啦!可以去死了...\n掰掰!慢走不送~");
		monsterAttack(MonsterAtk, PlayerDeffense);
		playerDead();
	}
}
