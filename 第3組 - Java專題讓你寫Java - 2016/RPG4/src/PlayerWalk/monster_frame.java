package PlayerWalk;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BattleFrame.BattleFrame;
import Music.BGM;
import monster.Monster;
import player.Player;

import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

//遇到怪物跳出的視窗
public class monster_frame {
	Player player;
	BGM BGM;
	
	public monster_frame(Player player){
		
		//Player player2 = new Player("海大金衝蹦");
		//各種BOSS跟小怪的new方式，在Monster中有詳細說明 
		this.player = player;
	}
	
	public void setVisible(boolean b, int maplv) {
		Timer t = new Timer();
		t.schedule(new TimerTask(){

			@Override
			public void run() {
				// TODO 自動產生的方法 Stub
				BGM = new BGM("熱き決闘者たち.mp3");
			}
			
		},0);
		Monster monster = new Monster(maplv);            //new 一般小怪
		//Monster monster = new Monster();             //new 小BOSS 
		//Monster monster = new Monster("bogBoss",10); //new 大BOSS 
		//Monster monster = new Monster("middleBoss");   //new 中BOSS
		System.out.print("-----------------------------------------------1\n");
		//System.out.printf("%s",player.getFuck());
		
		BattleFrame battleFrame = new BattleFrame(player,monster,BGM);
		System.out.print("-----------------------------------------------2\n");
		battleFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		battleFrame.setSize(1000,800);
		battleFrame.setResizable(false); //視窗縮放=false
		battleFrame.setVisible(true); //視窗顯示=true
		battleFrame.setLocationRelativeTo(null);
	}
	
}
//Player1.playerGo();