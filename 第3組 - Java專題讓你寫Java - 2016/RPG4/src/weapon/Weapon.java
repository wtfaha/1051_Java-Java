package weapon;
import interFace.ATK;

public abstract class Weapon implements ATK
{
	public static double MAX_VALUE = 2500; 
	private int offense;
	private int defense;
	private int num;	//武器碎片數量
	private String name;	//武器名稱
	private int LV;
	
	public Weapon()
	{
		offense = 0;
		defense = 0;
		num = 0;
		LV = 0;
	}
	
	public void setWeapon()
	{

	}
	
	public int getOffense()
	{
		return offense;
	}

	public void setOffense(int offense)
	{
		if ((0 < offense) && (offense < MAX_VALUE))
			this.offense += offense;
		else
			this.offense = 0;
	}

	public int getDefense()
	{
		return defense;
	}

	public void setDefense(int defense)
	{
		if ((0 < defense) && (defense < MAX_VALUE))
			this.defense += defense;
		else
			this.defense = 0;
	}

	@Override
	public String toString()
	{
		return "Weapon [offense=" + offense + ", defense=" + defense + "]";
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getNum()
	{
		return num;
	}

	public void setNum(int num)
	{
		this.num += num;
	}

	public int getLV() 
	{
		return LV;
	}

	public void setLV(int lV) 
	{
		LV = lV;
	}
	
	public void lvUp()
	{
		LV++;
	}
}