package RPG;

import PlayerWalk.ReadMapFile;
import PlayerWalk.mainFrame;
import Start.GUIStart;
import player.Player;

public class RPG {
	public static mainFrame MainFrame;
	public static void main(String[] args) {
		// 首先从地图文件中读入地图数组
		ReadMapFile.readfile();
		// 用读到的地图数组创建游戏窗体，开始游戏
		mainFrame mf = new mainFrame();
		MainFrame = mf;
	}
}
