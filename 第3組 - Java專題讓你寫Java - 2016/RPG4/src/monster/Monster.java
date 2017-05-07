package monster;
import java.util.Random;

public class Monster
{
	private String name;
	private int number; //在BattleFrame中用來判斷傳入的圖片依據
	private int LV = 0;
	private int HP = 100;
	private int MAXHP = 0;
	private int defense;
	private int offense = 20;
	private int EXP = 40;
	Random random = new Random();
	
	public Monster() //沒給任何參數的建構元就是 小BOSS  
	{
		setName("PL阮老師");
		setLV(3);
		setHP(2000);
		setMAXHP(2000);
		setOffense(100);
		setDefense(50);
		setEXP(1500);
		number = 11;
	}
	
	public Monster(String middleBoss) //只給String參數的建構元就是中BOSS
	{
		setName("OS蔡老師");
		setLV(7);
		setHP(7000);
		setMAXHP(7000);
		setOffense(200);
		setDefense(100);
		setEXP(5000);
		number = 12;
	}
	
	public Monster(String bigBoss,int LV) //給了String參數跟 int參數(等級)的就是大BOSS
	{
		setName("微積分程老師");
		setLV(10);
		//setHP(15000);
		setHP(15000);
		setMAXHP(15000);
		setOffense(300);
		setDefense(150);
		setEXP(8000);
		number = 13;
	}
	
	public Monster(int lv)	//只給等級的就是小怪
	{
		setName(String.format("LV%s monster",lv));
		setLV(lv);
		setHP(lv * lv * 100);
		setMAXHP(lv * lv * 100);
		setOffense(offense + (LV - 1) * (int)(offense * 1.2));
		//setDefense();
		setEXP(lv * lv * (int)(EXP * 1.8));
		number = LV;
	}
	
	public Monster(int lv,int hp,int deffense,int offense,int exp) //給了一堆參數的...還是小怪
	{
		setName("monster");
		setLV(lv);
		setHP(hp);
		setMAXHP(hp);
		setOffense(offense);
		setDefense(deffense);
		setEXP(exp);
	}
	
	public void monsterProblem()	//根據等級決定出題
	{
		
	}
	
	public int dropWeapon()	//return 0:無掉落武器	return 1:掉落中文書		return 2:掉落原文書		return 3:掉落PPT
	{
		int tmp,turn;
		tmp = random.nextInt(100) + 1;
		if(tmp < 10) turn = 3;
		else if(tmp >= 10 && tmp < 30) turn = 2;
		else if(tmp >= 30 && tmp < 70) turn = 1;
		else turn = 0;
		return turn;
	}
	
	public int dropRecoverDrinks()	//return 0:小型藥水	return 1:中型藥水	return 2:大型藥水	return 3:復活藥水
	{
		int tmp,turn;
		tmp = random.nextInt(50) + 1;
		if(tmp < 5) turn = 3;
		else if(tmp < 10 && tmp >= 5) turn = 2;
		else if(tmp < 30 && tmp >= 10) turn = 1;
		else turn = 0;
		return turn;
	}
	
	public int getLV()
	{
		return LV;
	}
	
	public void setLV(int lV)
	{
		LV = lV;
	}
	
	public int getHP()
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

	public int getOffense()
	{
		return offense;
	}

	public void setOffense(int offense)
	{
		this.offense = offense;
	}

	public int getDefense()
	{
		return defense;
	}

	public void setDefense(int defense)
	{
		this.defense = defense;
	}

	public int getEXP()
	{
		return EXP;
	}

	public void setEXP(int eXP)
	{
		EXP = eXP;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String monsterName) 
	{
		this.name = monsterName;
	}

	public int getMAX_HP() {
		return MAXHP;
	}

	public void setMAXHP(int mAXHP) {
		MAXHP = mAXHP;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
