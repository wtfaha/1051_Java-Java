package PlayerWalk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BattleFrame.BattleFrame;
import Music.BGM;
import monster.Monster;
import player.Player;

public class boss_frame {//BOSS面反
	Player player;
	int boss;
	Monster monster;
	Timer t;
	BGM B;
	
	public boss_frame(int boss, Player player) {//BOSS參數設定與到哪知BOSS
		this.player = player;
		this.boss = boss;
	}
	
	public void setVisible(boolean b, int maplv) {
		t = new Timer();
		t.schedule(new TimerTask(){

			@Override
			public void run() {
				// TODO 自動產生的方法 Stub
				B = new BGM("熱き決闘者たち.mp3");
			}
			
		},0);
		//Monster monster = new Monster(maplv);            //new 一般小怪
		if (boss == 1){//遇到BOSS1
			monster = new Monster();   //new 小BOSS
		}
		else if (boss == 2){
			monster = new Monster("middleBoss");   //new 中BOSS
		}
		else if (boss == 3){//遇到BOSS3
			monster = new Monster("bogBoss",10); //new 大BOSS 
		}
		

		System.out.print("-----------------------------------------------1\n");
		//System.out.printf("%s",player.getFuck());
		
		BattleFrame battleFrame = new BattleFrame(player,monster,B);
		System.out.print("-----------------------------------------------2\n");
		if (boss == 1){//BOSS1結束
			Player1.boss1Appear=1;//改為1，使得BOSS1不會再次出現
			
			//將BOSS的圖案清空，實現當戰鬥結束後，BOSS消失
			ReadMapFile.map2[10][28]= 0;	
		}
		else if (boss == 2){//BOSS2結束
			Player1.boss2Appear=1;//改為1，使得BOSS2不會再次出現
			
			//將BOSS的圖案清空，實現當戰鬥結束後，BOSS消失
			ReadMapFile.map2[12][38] = 0;	
		}
		else if (boss == 3){//BOSS2結束
			Player1.boss3Appear=1;//改為1，使得BOSS3不會再次出現
			
			//將BOSS的圖案清空，實現當戰鬥結束後，BOSS消失
			ReadMapFile.map2[28][37]= 0;	
		}
		battleFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		battleFrame.setSize(1000,800);
		battleFrame.setResizable(false); //視窗縮放=false
		battleFrame.setVisible(true); //視窗顯示=true
		battleFrame.setLocationRelativeTo(null);
	}
}

//private JLabel alert;
//private JButton button;


//this.setTitle("Boss");
//this.setSize(400, 300);
//if (boss == 1){//遇到BOSS1
//	alert = new JLabel("Boss 1 appeared!!");
//	Player1.boss1Appear=1;//改為1，使得BOSS1不會再次出現
//}
//else if (boss == 2){
//	alert = new JLabel("Boss 2 appeared!!");
//	Player1.boss2Appear=1;
//}
//else{
//	alert = new JLabel("Boss 3 appeared!!");
//	Player1.boss3Appear=1;
//}
//
//button = new JButton("戰鬥結束");
//button.addActionListener(new ActionListener() {//戰鬥結束要做的事
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if(Player1.boss==1)
//			ReadMapFile.map2[10][28]=ReadMapFile.map1[10][28];
//			//將BOSS的圖案代換為底板的圖案，實現當戰鬥結束後，BOSS消失
//		else if(Player1.boss==2)
//			ReadMapFile.map2[12][38]=ReadMapFile.map1[12][38];
//		else
//			ReadMapFile.map2[28][37]=ReadMapFile.map1[28][37];
//	}
//
//});
//add(alert);
//add(button);
//setDefaultCloseOperation(DISPOSE_ON_CLOSE);