package player;
import java.util.ArrayList;

import weapon.JavaChineseBook;
import weapon.JavaEnglishBook;
import weapon.Pet;
import weapon.ShangPinPPT;
import weapon.Weapon;

public class BackPackage
{
	private Pet pet = new Pet();
	private ArrayList<Weapon> weapon = new ArrayList<Weapon>();
	private ArrayList<RecoveryDrinks> recoveryDrinks = new ArrayList<RecoveryDrinks>();
	
	
	public BackPackage()	//初始化
	{
		for(int i = 0;i < 4;i++)
		{
			recoveryDrinks.add(new RecoveryDrinks());
		}
		recoveryDrinks.get(0).setRecoveryDrinksInformation("小型",50,10);	//set 小罐藥水 0
		recoveryDrinks.get(1).setRecoveryDrinksInformation("中型",200,10);	//set 中罐藥水 1
		recoveryDrinks.get(2).setRecoveryDrinksInformation("大型",500,10);	//set 大罐藥水 2
		recoveryDrinks.get(3).setRecoveryDrinksInformation("復活", 2000, 50);	//set 福活藥水 3
		weapon.add(new JavaChineseBook("javaChineseBook"));
		weapon.add(new JavaEnglishBook("javaEnglishBook"));
		weapon.add(new ShangPinPPT("ShangPinPPT"));
	}
	
	public void setRecoveryDrinks(ArrayList<RecoveryDrinks> recoveryDrinks)	//set 背包中的藥水
	{
		this.recoveryDrinks = recoveryDrinks;
	}
	
	public ArrayList<RecoveryDrinks> getRecoveryDrinks()
	{
		return recoveryDrinks;
	}
	
	@Override
	public String toString()	//get 背包內容
	{
		return "";
	}

	public Pet getPet()
	{
		return pet;
	}

	public void setPet(Pet pet)
	{
		this.pet = pet;
	}

	public ArrayList<Weapon> getWeapon()
	{
		return weapon;
	}

	public void setWeapon(ArrayList<Weapon> weapon)
	{
		this.weapon = weapon;
	}

}
