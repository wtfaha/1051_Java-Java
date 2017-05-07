package weapon;
public class JavaEnglishBook extends Weapon
{
	public JavaEnglishBook(String name)
	{
		setName(name);
	}
	
	@Override
	public void setWeapon()
	{
		setOffense(200);
		setDefense(10);
	}
	
	@Override
	public void lvUp()
	{
		setLV(getLV()+1);
		setOffense(getOffense()+10);
		setDefense(getDefense()+1);
	}
	
	@Override
	public int attack()
	{
		return super.getOffense();
	}
}


//weapon 代號	2