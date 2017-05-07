package player;

public class RecoveryDrinks {
	private String drinkName;	//名稱
	private int amount;	//恢復量
	private int num;	//個數
	
	public RecoveryDrinks()
	{
		num = 0;
	}
	
	public void setRecoveryDrinksInformation(String name,int a,int b)	//set 藥水資訊
	{
		setDrinkName(name);
		setAmount(a);
		setNum(b);
	}
	
	public void setNum(int num)	//set 藥水數量
	{
			this.num += num;
			
	}
	
	public int getNum()	//get 藥水數量
	{
		return num;
	}
	
	@Override
	public String toString()	//get 背包內容
	{
		return "";
	}

	public String getDrinkName()
	{
		return drinkName;
	}

	public void setDrinkName(String drinkName)
	{
		this.drinkName = drinkName;
	}

	public int getAmount()
	{
		return amount;
	}

	public void setAmount(int amount)
	{
		this.amount = amount;
	}
}
