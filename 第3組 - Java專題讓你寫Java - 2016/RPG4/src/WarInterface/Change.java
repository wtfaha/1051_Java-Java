package WarInterface;
import java.util.TimerTask;

import javax.swing.JFrame;

import PlayerWalk.ReadMapFile;
import PlayerWalk.mainFrame;
import RPG.RPG;
import player.Player;

public class Change extends TimerTask{
	JFrame f;
	public Change(JFrame f) {
		this.f = f;
	}
	@Override
	public void run() { //延遲之後做的事情
		f.setVisible(false);
	}

}