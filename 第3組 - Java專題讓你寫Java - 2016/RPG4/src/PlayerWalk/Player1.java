package PlayerWalk;

import java.awt.Graphics;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Music.BGM;
import RPG.RPG;

import player.Player;

/**
 * 角色类
 * 
 * @author yy
 *
 */
public class Player1 extends Thread implements gameConfig {
	private static Player p;

	boolean alertBoss1 = false;
	boolean alertBoss2 = false; 
	boolean alertBoss3 = false; 
	
	static final int boosRate = 19;
	// 角色中点相对游戏面板的位置(在游戏中是不变的)
	static int px = panelX / 2;
	static int py = panelY / 2;
	// 角色中点在整张地图中的位置(设置人最开始中点的位置一定要是一个元素中心的位置，要不然这种移动就会出问题 - -！)
	static int x = 375;
	static int y = 375;
	// 角色的偏移量（实现像素点移动关键的部分,一定要给个初始值，要不然到边界出现负数哭死，害我找错误找了一个晚上）
	static int mx = 50;
	static int my = 50;
	// 角色的步长
	static int step = 5;
	// 角色是否移动
	static public boolean up = false;
	static public boolean down = false;
	static public boolean left = false;
	static public boolean right = false;
	// 角色的朝向 1,2,3,4分别代表上下左右(用来处理角色不移动时的朝向问题，后面要写与npc对话之类的估计用得上)
	static int towards = 2;
	// 角色的移动累积量（这个就是用来控制循环的变化4张角色图片来达成动态移动的）
	static int up1 = 0;
	static int down1 = 0;
	static int left1 = 0;
	static int right1 = 0;
	// 是否要換地圖;
	static int transport2 = 0;// 判定要不要換地圖，transport為換到第二張地圖，以此類推
	static int transport3 = 0;
	static int transport4 = 0;
	static int transport5 = 0;
	static int transport6 = 0;
	static int transport7 = 0;
	static int transport8 = 0;
	static int transport9 = 0;
	static int transport10 = 0;
	static int map = 1;// 紀錄現在在第幾張地圖
	static boolean l = false, r = false, u = false, d = false, lu = false, ld = false, ru = false, rd = false;
	// 這是判斷要不要覆寫周圍8格的地圖物件
	Random ran = new Random();
	static int boss = 0;// 判定遇到第幾隻BOSS
	static int boss1Appear = 0;// BOSS1出現，戰鬥跳出後改為1，使得BOSS1只會出現一次
	static int boss2Appear = 0;
	static int boss3Appear = 0;
	int monster;// 判斷是否遇到怪物
	monster_frame mon;// 怪物面板
	boss_frame bos;// BOSS面板
	
	private static BGM B;
	static Timer t;

	@Override
	public void run() {
		while (true) {
			moveUD();
			moveLR();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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
	 * 角色上下移动的方法
	 */
	private void meetMonster() {
		mon = new monster_frame(p);// 彈出怪物視窗
		Double d = Math.sqrt(ran.nextInt(map * map));
		mon.setVisible(true, d.intValue() + 1);
		up = down = right = left = false;
		monster = 1;// 調回判定值，不然會一直重複跳出視窗
	}
	
	public void moveUD() {
		if (up) {
			// 当按住上键时，给up1加1，当up1大于20时候又置为0，达成循环
			up1++;
			if (up1 >= 20) {
				up1 = 0;
			}
			// 如果角色当前位置上方的数组值不为0（角色上方有物体挡着）：这里处理的是角色一个格子内部的移动，不能移动到上面一格
			if (ReadMapFile.map2[y / elesize - 1][x / elesize] != 0) {
				int y1 = (y / elesize - 1) * elesize + elesize / 2;
				int x1 = (x / elesize) * elesize + elesize / 2;
				if ((y - y1) * (y - y1) >= elesize * elesize) {
					y = y - step;
					my = my - step;
					boss(map, y / elesize - 1, x / elesize);// 如果撞到東西，判斷那個東西是不是BOSS
				}
			} else if (ReadMapFile.map2[y / elesize - 1][x / elesize] == 0) {// 上方没物体，可以继续向上移动
				y = y - step;
				my = my - step;
				ifTransport(y / elesize, x / elesize);// 移動後，首先判斷要不要換地圖
				monster = ran.nextInt(200);// 若不用換，則RANDOM決定是否遇到怪物，這邊機率調5%
				if (monster == boosRate) {
					B.close();
					meetMonster();
					playerStop(y / elesize, x / elesize);
				}
			}
		} else if (down) {
			down1++;
			if (down1 >= 20) {
				down1 = 0;
			}
			if (ReadMapFile.map2[y / elesize + 1][x / elesize] != 0) {
				int y1 = (y / elesize + 1) * elesize + elesize / 2;
				int x1 = (x / elesize) * elesize + elesize / 2;
				if ((y - y1) * (y - y1) >= elesize * elesize) {
					y = y + step;
					my = my + step;
					boss(map, y / elesize + 1, x / elesize);
				}
			} else if (ReadMapFile.map2[y / elesize + 1][x / elesize] == 0) {
				y = y + step;
				my = my + step;
				ifTransport(y / elesize, x / elesize);
				monster = ran.nextInt(200);
				if (monster == boosRate) {
					B.close();
					meetMonster();
					playerStop(y / elesize, x / elesize);
				}
			}
		}
	}

	/**
	 * 角色左右移动的方法
	 */
	public void moveLR() {
		if (left) {
			left1++;
			if (left1 >= 20) {
				left1 = 0;
			}
			if (ReadMapFile.map2[y / elesize][x / elesize - 1] != 0) {
				int y1 = (y / elesize) * elesize + elesize / 2;
				int x1 = (x / elesize - 1) * elesize + elesize / 2;
				if ((x - x1) * (x - x1) >= elesize * elesize) {
					x = x - step;
					mx = mx - step;
					boss(map, y / elesize, x / elesize - 1);
				}
			} else if (ReadMapFile.map2[y / elesize][x / elesize - 1] == 0) {
				x = x - step;
				mx = mx - step;
				ifTransport(y / elesize, x / elesize);
				monster = ran.nextInt(200);
				if (monster == boosRate) {
					B.close();
					meetMonster();
					playerStop(y / elesize, x / elesize);
				}
			}
		} else if (right) {
			right1++;
			if (right1 >= 20) {
				right1 = 0;
			}
			if (ReadMapFile.map2[y / elesize][x / elesize + 1] != 0) {
				int y1 = (y / elesize) * elesize + elesize / 2;
				int x1 = (x / elesize + 1) * elesize + elesize / 2;
				if ((x - x1) * (x - x1) >= elesize * elesize) {
					x = x + step;
					mx = mx + step;
					boss(map, y / elesize, x / elesize + 1);
				}
			} else if (ReadMapFile.map2[y / elesize][x / elesize + 1] == 0) {
				x = x + step;
				mx = mx + step;
				ifTransport(y / elesize, x / elesize);
				monster = ran.nextInt(200);
				if (monster == boosRate) {
					B.close();
					meetMonster();
					playerStop(y / elesize, x / elesize);
				}
			}
		}
	}

	public void playerStop(int i, int j) {// 將腳色周圍8格擋起來，不然腳色會維持方向繼續走
//		if (ReadMapFile.map2[i + 1][j] == 0) {
//			u = true;
//			ReadMapFile.map2[i + 1][j] = ReadMapFile.map1[i + 1][j];
//		}
//		if (ReadMapFile.map2[i - 1][j] == 0) {
//			d = true;
//			ReadMapFile.map2[i - 1][j] = ReadMapFile.map1[i - 1][j];
//		}
//		if (ReadMapFile.map2[i][j + 1] == 0) {
//			r = true;
//			ReadMapFile.map2[i][j + 1] = ReadMapFile.map1[i][j + 1];
//		}
//		if (ReadMapFile.map2[i][j - 1] == 0) {
//			l = true;
//			ReadMapFile.map2[i][j - 1] = ReadMapFile.map1[i][j - 1];
//		}
//		if (ReadMapFile.map2[i + 1][j + 1] == 0) {
//			ru = true;
//			ReadMapFile.map2[i + 1][j + 1] = ReadMapFile.map1[i + 1][j + 1];
//		}
//		if (ReadMapFile.map2[i + 1][j - 1] == 0) {
//			lu = true;
//			ReadMapFile.map2[i + 1][j - 1] = ReadMapFile.map1[i + 1][j - 1];
//		}
//		if (ReadMapFile.map2[i - 1][j + 1] == 0) {
//			rd = true;
//			ReadMapFile.map2[i - 1][j + 1] = ReadMapFile.map1[i + 1][j + 1];
//		}
//		if (ReadMapFile.map2[i - 1][j - 1] == 0) {
//			ld = true;
//			ReadMapFile.map2[i - 1][j - 1] = ReadMapFile.map1[i + 1][j - 1];
//		}
		keyReleaseSim();
		up = down = right = left = false;
		RPG.MainFrame.setVisible(false);
	}
	
	private void keyReleaseSim() {
		try {
	        Robot robot = new Robot();
	        robot.keyRelease(KeyEvent.VK_UP);
	        robot.keyRelease(KeyEvent.VK_DOWN);
	        robot.keyRelease(KeyEvent.VK_RIGHT);
	        robot.keyRelease(KeyEvent.VK_LEFT);
		} catch (Exception e) { e.printStackTrace(); }
	}

	public static void playerGo() {// 戰鬥結束後會呼叫的函式，如果是true(有被覆寫過)，則改回，使得戰鬥結束後人物可以繼續在地圖上移動
//		if (u == true)
//			ReadMapFile.map2[y / elesize + 1][x / elesize] = 0;
//		if (d == true)
//			ReadMapFile.map2[y / elesize - 1][x / elesize] = 0;
//		if (r == true)
//			ReadMapFile.map2[y / elesize][x / elesize + 1] = 0;
//		if (l == true)
//			ReadMapFile.map2[y / elesize][x / elesize - 1] = 0;
//		if (ru == true)
//			ReadMapFile.map2[y / elesize + 1][x / elesize + 1] = 0;
//		if (lu == true)
//			ReadMapFile.map2[y / elesize + 1][x / elesize - 1] = 0;
//		if (rd == true)
//			ReadMapFile.map2[y / elesize - 1][x / elesize + 1] = 0;
//		if (ld == true)
//			ReadMapFile.map2[y / elesize - 1][x / elesize - 1] = 0;
		int[] tx = {21, 35, 16, 11, 18, 28, 24, 7, 18};
		int[] ty = {20, 18, 12, 21, 33, 23, 10, 20, 27};
		
		if (map > p.getLV() - 1 && map < 10) {
			ReadMapFile.map2[ty[map - 1]][tx[map - 1]] = 105;
		} else if (map < 10) ReadMapFile.map2[ty[map - 1]][tx[map - 1]] = 0; 
		RPG.MainFrame.setVisible(true);
		
		t = new Timer();
		t.schedule(new TimerTask(){

			@Override
			public void run() {
				// TODO 自動產生的方法 Stub
				B = new BGM("維多利亞港.mp3");
			}
			
		},0);
		
	}

	public void boss(int map, int y, int x) {// 判斷是吼有遇到BOSS
		if (map == 3 && y == 10 && x == 28 && boss1Appear == 0) {
			// 如果在BOSS1的地圖，撞到的東西座標為BOSS1的座標，BOSS1還沒出現過
			boss = 1;// BOSS1出現
			B.close();
			bos = new boss_frame(boss, p);// 開啟BOSS戰鬥
			bos.setVisible(true, this.map);
		} else if (map == 7 && y == 12 && x == 38 && boss2Appear == 0) {
			boss = 2;
			B.close();
			bos = new boss_frame(boss, p);
			bos.setVisible(true, this.map);
		} else if (map == 10 && y == 28 && x == 37 && boss3Appear == 0) {
			boss = 3;
			B.close();
			bos = new boss_frame(boss, p);
			bos.setVisible(true, this.map);
		}
	}

	public void ifTransport(int y, int x) {// 判定是否要換地圖
		RPG.MainFrame.setTitle("Map" + String.valueOf(map));
		int[] tx = {21, 35, 16, 11, 18, 28, 24, 7, 18};
		int[] ty = {20, 18, 12, 21, 33, 23, 10, 20, 27};
		
		if (map == 10) return ;
		if (map > p.getLV() - 1) {
			ReadMapFile.map2[ty[map - 1]][tx[map - 1]] = 105;
			return ;
		} else ReadMapFile.map2[ty[map - 1]][tx[map - 1]] = 0; 
		
		if (map == 1 && y == 20 && x == 21) {// (21,20)是地圖一往地圖二的黑洞座標，如果現在在地圖一，且位置在黑洞上，則按下任一方向鍵就會換地圖
			map = 2;
			transport2 = 1;
			//B.close();
		}
		if (map == 2 && y == 18 && x == 35) {
			map = 3;
			transport3 = 1;
			//B.close();
		}
		if (map == 3 && y == 12 && x == 16) {
			if (boss1Appear == 0 && !alertBoss1) {
				alertBoss1 = true;
				JOptionPane.showMessageDialog(null, "要先打過這張圖的Boss!");
				keyReleaseSim();
			}
			if (boss1Appear == 0) return ;
			map = 4;
			transport4 = 1;
			//B.close();
		}
		if (map == 4 && y == 21 && x == 11) {
			map = 5;
			transport5 = 1;
			//B.close();
		}
		if (map == 5 && y == 33 && x == 18) {
			map = 6;
			transport6 = 1;
			//B.close();
		}
		if (map == 6 && y == 23 && x == 28) {
			map = 7;
			transport7 = 1;
			//B.close();
		}
		if (map == 7 && y == 10 && x == 24 ) {
			if (boss2Appear == 0 && !alertBoss2) {
				alertBoss2 = true;
				JOptionPane.showMessageDialog(null, "要先打過這張圖的Boss!");
				keyReleaseSim();
			}
			if (boss2Appear == 0) return ;
			map = 8;
			transport8 = 1;
			//B.close();
		}
		if (map == 8 && y == 20 && x == 7) {
			map = 9;
			transport9 = 1;
			//B.close();
		}
		if (map == 9 && y == 27 && x == 18) {
			map = 10;
			transport10 = 1;
			//B.close();
		}
	}

	public static void draw(Graphics g) {
		// 如果角色不在移动中
		if (!up && !down && !left && !right) {
			if (towards == 1) {// 如果角色移动的最后朝向为上
				g.drawImage(walk1.getImage(), Player1.px - elesize / 2 - 15, Player1.py - elesize / 2 - 25,
						Player1.px - elesize / 2 + 65, Player1.py - elesize / 2 + 55, 0, 96 * 3, 96, 96 * 4, null);
			} else if (towards == 2) {// 最后移动朝向下
				g.drawImage(walk1.getImage(), Player1.px - elesize / 2 - 15, Player1.py - elesize / 2 - 25,
						Player1.px - elesize / 2 + 65, Player1.py - elesize / 2 + 55, 0, 0, 96, 96, null);
			} else if (towards == 3) {// 最后移动朝向左
				g.drawImage(walk1.getImage(), Player1.px - elesize / 2 - 15, Player1.py - elesize / 2 - 25,
						Player1.px - elesize / 2 + 65, Player1.py - elesize / 2 + 55, 0, 96, 96, 96 * 2, null);
			} else if (towards == 4) {// 最后移动朝向右
				g.drawImage(walk1.getImage(), Player1.px - elesize / 2 - 15, Player1.py - elesize / 2 - 25,
						Player1.px - elesize / 2 + 65, Player1.py - elesize / 2 + 55, 0, 96 * 2, 96, 96 * 3, null);
			}
		} else {// 如果角色在移动中
			if (up) {
				// 通过up1的值，来决定画哪一张图片
				if (up1 < 5) {
					g.drawImage(walk.getImage(), Player1.px - elesize / 2 - 15, Player1.py - elesize / 2 - 25,
							Player1.px - elesize / 2 + 65, Player1.py - elesize / 2 + 55, 0, 96 * 3, 96, 96 * 4, null);
				} else if (up1 < 10) {
					g.drawImage(walk.getImage(), Player1.px - elesize / 2 - 15, Player1.py - elesize / 2 - 25,
							Player1.px - elesize / 2 + 65, Player1.py - elesize / 2 + 55, 96, 96 * 3, 96 * 2, 96 * 4,
							null);
				} else if (up1 < 15) {
					g.drawImage(walk.getImage(), Player1.px - elesize / 2 - 15, Player1.py - elesize / 2 - 25,
							Player1.px - elesize / 2 + 65, Player1.py - elesize / 2 + 55, 96 * 2, 96 * 3, 96 * 3,
							96 * 4, null);
				} else {
					g.drawImage(walk.getImage(), Player1.px - elesize / 2 - 15, Player1.py - elesize / 2 - 25,
							Player1.px - elesize / 2 + 65, Player1.py - elesize / 2 + 55, 96 * 3, 96 * 3, 96 * 4,
							96 * 4, null);
				}
			} else if (down) {
				if (down1 < 5) {
					g.drawImage(walk.getImage(), Player1.px - elesize / 2 - 15, Player1.py - elesize / 2 - 25,
							Player1.px - elesize / 2 + 65, Player1.py - elesize / 2 + 55, 0, 0, 96, 96, null);
				} else if (down1 < 10) {
					g.drawImage(walk.getImage(), Player1.px - elesize / 2 - 15, Player1.py - elesize / 2 - 25,
							Player1.px - elesize / 2 + 65, Player1.py - elesize / 2 + 55, 96, 0, 96 * 2, 96, null);
				} else if (down1 < 15) {
					g.drawImage(walk.getImage(), Player1.px - elesize / 2 - 15, Player1.py - elesize / 2 - 25,
							Player1.px - elesize / 2 + 65, Player1.py - elesize / 2 + 55, 96 * 2, 0, 96 * 3, 96, null);
				} else {
					g.drawImage(walk.getImage(), Player1.px - elesize / 2 - 15, Player1.py - elesize / 2 - 25,
							Player1.px - elesize / 2 + 65, Player1.py - elesize / 2 + 55, 96 * 3, 0, 96 * 4, 96, null);
				}
			} else if (left) {
				if (left1 < 5) {
					g.drawImage(walk.getImage(), Player1.px - elesize / 2 - 15, Player1.py - elesize / 2 - 25,
							Player1.px - elesize / 2 + 65, Player1.py - elesize / 2 + 55, 0, 96, 96, 96 * 2, null);
				} else if (left1 < 10) {
					g.drawImage(walk.getImage(), Player1.px - elesize / 2 - 15, Player1.py - elesize / 2 - 25,
							Player1.px - elesize / 2 + 65, Player1.py - elesize / 2 + 55, 96, 96, 96 * 2, 96 * 2, null);
				} else if (left1 < 15) {
					g.drawImage(walk.getImage(), Player1.px - elesize / 2 - 15, Player1.py - elesize / 2 - 25,
							Player1.px - elesize / 2 + 65, Player1.py - elesize / 2 + 55, 96 * 2, 96, 96 * 3, 96 * 2,
							null);
				} else {
					g.drawImage(walk.getImage(), Player1.px - elesize / 2 - 15, Player1.py - elesize / 2 - 25,
							Player1.px - elesize / 2 + 65, Player1.py - elesize / 2 + 55, 96 * 3, 96, 96 * 4, 96 * 2,
							null);
				}

			} else if (right) {
				if (right1 < 5) {
					g.drawImage(walk.getImage(), Player1.px - elesize / 2 - 15, Player1.py - elesize / 2 - 25,
							Player1.px - elesize / 2 + 65, Player1.py - elesize / 2 + 55, 0, 96 * 2, 96, 96 * 3, null);
				} else if (right1 < 10) {
					g.drawImage(walk.getImage(), Player1.px - elesize / 2 - 15, Player1.py - elesize / 2 - 25,
							Player1.px - elesize / 2 + 65, Player1.py - elesize / 2 + 55, 96, 96 * 2, 96 * 2, 96 * 3,
							null);
				} else if (right1 < 15) {
					g.drawImage(walk.getImage(), Player1.px - elesize / 2 - 15, Player1.py - elesize / 2 - 25,
							Player1.px - elesize / 2 + 65, Player1.py - elesize / 2 + 55, 96 * 2, 96 * 2, 96 * 3,
							96 * 3, null);
				} else {
					g.drawImage(walk.getImage(), Player1.px - elesize / 2 - 15, Player1.py - elesize / 2 - 25,
							Player1.px - elesize / 2 + 65, Player1.py - elesize / 2 + 55, 96 * 3, 96 * 2, 96 * 4,
							96 * 3, null);
				}
			}
		}
	}

	// 得到角色在数组中的位置I
	public static int getI() {
		return (y - (playersize / 2)) / 50;
	}

	// 得到角色在数组中的位置J
	public static int getJ() {
		return (x - (playersize / 2)) / 50;
	}

	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}
}
