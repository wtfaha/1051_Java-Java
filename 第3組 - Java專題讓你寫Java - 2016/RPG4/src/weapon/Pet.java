package weapon;
import interFace.ATK;

public class Pet implements ATK
{	
	private int LV;
	private int MAX_EXP = 80;
	private int offense;
	private int deffense;
	private int originalOffense = 10,originalDeffense = 10;
	private int EXP;	//寵物等級
	private int caterpillarPlusOffense = 5, 
				caterpillarPlusDeffense = 5,
				fairyPlusOffense = 25,
				fairyPlusDeffense = 10,
				nerdPlusOffense = 50,
				nerdPlusDeffense = 20;
	static State state;

	static enum State	//毛蟲 < 仙女 < 書呆子
	{
		caterpillar, fairy, nerd
	};
	
	public Pet()
	{
		LV=1;
		offense=originalOffense;
		deffense=originalDeffense;
		state=State.caterpillar;
	}

	public void staticLVup()
	{
		if (state == State.caterpillar)
		{
			setOffense(caterpillarPlusOffense);
			setDeffense(caterpillarPlusDeffense);
		}
		else if (state == State.fairy)
		{
			setOffense(fairyPlusOffense);
			setDeffense(fairyPlusDeffense);
		}
		else
		{
			setOffense(nerdPlusOffense);
			setDeffense(nerdPlusDeffense);
		}
	}

	public int getLV()
	{
		return LV;
	}
	
	public void setOffense(int offense)
	{
		this.offense += offense;
	}
	
	public int getOffense()
	{
		return offense;
	}
	
	public void setDeffense(int deffense)
	{
		this.deffense += deffense;
	}
	
	public int getDeffense()
	{
		return deffense;
	}
	
	protected void advance()
	{
		if (state == State.caterpillar)
			state = State.fairy;
		else
			state = State.nerd;
	}

	@Override
	public int attack()
	{
		return offense;
	}
	
	@Override
	public String toString()
	{
		return String.format("[Pet造成 %.0f 點的傷害]",offense);

	}

	public int getEXP()
	{
		return EXP;
	}

	public String setEXP(int eXP)
	{
		EXP += eXP;
		String string = null;
		while(EXP >= MAX_EXP)
		{
			EXP -= MAX_EXP;
			string = "恭喜!寵物升等嘍!\n";
			LV++;
			this.staticLVup();
			MAX_EXP *= 2;
		}
		if(LV == 3) this.advance();
		if(LV == 7) this.advance();
		return string;
	}
}
