package player;

import javax.swing.JTextArea;

public class Player
{
	public static int MAX_LV = 10;	//最大等級
	private int MAX_EXP = 100;	//當前最大經驗值
	private int MAX_HP = 100;	//當前最大血量
	private String nickName;	//匿名
	private String fuck;	//稱號
	private int HP;	//血量
	private int LV;	//等級
	private int EXP = 0;	//經驗值
	private int atk;	//攻擊力
	private JTextArea textArea = new JTextArea();
	private BackPackage backPackage;	//後背包
	
	public Player(String name)	//初始化
	{
		nickName = name;
		fuck = "肥豬";
		LV = 1;
		HP = 100;
		setAtk(50);
		backPackage = new BackPackage();
		//mood = 5;
	}
	
	public Player(String name,int HP,int LV, int atk)	//初始化
	{
		nickName = name;
		fuck = "肥豬";
		setLV(LV);
		setHP(HP);
		setAtk(atk);
		backPackage = new BackPackage();
		//mood = 5;
	}
	
	public int getLV()	//get LV
	{
		return LV;
	}
	
	public void setLV(int a)
	{
		if ((0 < a) && (a < MAX_LV))
			this.LV += a;
		else
			this.LV = 0;
	}
	
	public int getHP()	//get HP
	{
		return HP;
	}
	
	public void setHP(int hp)
	{
		if(hp<0)
			HP = 0;
		else
			HP = hp;
	}
	
	public String getNickName() //get 匿名
	{
		return nickName;
	}
	
	public void setFuck(String name)	//set 抽號
	{
		fuck = name;
	}
	
	public String getFuck() //get 稱號
	{
		return fuck;
	}

	public BackPackage getBackPackage() {
		return backPackage;
	}

	public void setBackPackage(BackPackage backPackage) {
		this.backPackage = backPackage;
	}

	public int getEXP()
	{
		return EXP;
	}

	public String setEXP(int eXP)
	{
		EXP += eXP;
		String string = null;
		while(EXP >= MAX_EXP && LV < MAX_LV)
		{
			EXP -= MAX_EXP;
			LV++;
			string = "恭喜!玩家升等嘍!\n";
			setAtk(atk + 15 * (LV - 1));
			setHP((int)(MAX_HP * 1.2));
			MAX_EXP *= 2;
			setMAX_HP((int)(MAX_HP * 1.2));
		}
		if(LV >= MAX_LV)
		{
			EXP = MAX_EXP;
			string = "你已經封頂啦!\n";
		}
		return string;
	}

	public int getAtk()
	{
		return atk;
	}

	public void setAtk(int atk)
	{
		this.atk = atk;
	}
	
	public int getMaxEXP()
	{
		return MAX_EXP;
	}

	public int getMAX_HP()
	{
		return MAX_HP;
	}

	public void setMAX_HP(int mAX_HP)
	{
		MAX_HP = mAX_HP;
	}

}
