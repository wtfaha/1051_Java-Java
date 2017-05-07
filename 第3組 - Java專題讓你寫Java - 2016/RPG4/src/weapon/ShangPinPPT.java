package weapon;
public class ShangPinPPT extends Weapon
{
	public ShangPinPPT(String name)
	{
		setName(name);
	}
	
	@Override
	public void setWeapon()
	{
		setOffense(1000);
		setDefense(15);
	}
	
	@Override
	public void lvUp()
	{
		setLV(getLV()+1);
		setOffense(getOffense()+50);
		setDefense(getDefense()+1);
	}
	
	@Override
	public int attack()
	{
		return super.getOffense();
	}
}

//weapon 代號	3