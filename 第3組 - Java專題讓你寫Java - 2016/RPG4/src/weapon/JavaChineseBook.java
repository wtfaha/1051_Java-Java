package weapon;
public class JavaChineseBook extends Weapon
{
	public JavaChineseBook(String name)
	{
		setName(name);
	}
	
	@Override
	public void setWeapon()
	{
		setOffense(10);
		setDefense(5);
	}
	
	@Override
	public void lvUp()
	{
		setLV(getLV()+1);
		setOffense(getOffense()+5);
		setDefense(getDefense()+1);
	}
	
	@Override
	public int attack()
	{
		return super.getOffense();
	}
}


//weapon 代號	1