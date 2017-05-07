package player;
import java.util.Random;

public class Skill
{
	Random random = new Random();
	private int skillNum;
	
	public double choiceSkillAtk(int s)	//造成攻擊的%數
	{
		double tmp = 0.0;
		int rand;
		skillNum = s;
		switch(skillNum)
		{
		case 1:		//使用二連擊
			tmp = 1.4;
			break;
		case 2:		//使用專破你這個甲
			tmp = 0.7;
			break;
		case 3:		//使用不是你死就是我亡
			tmp = 2;
			break;
		case 4:		//使用神秘之力
			rand = random.nextInt(10) + 1;
			if(rand < 4) 
				tmp = 5;	
			else
				tmp = 0;
			break;
		}
		return tmp;
	}
	
	public double choiceSkillDeffense(int choice)	//防禦%數
	{
		double tmp;
		switch(choice)
		{
		case 1:
			tmp = 1;
			break;
		case 2:
			tmp = 1.5;
			break;
		case 3:
			tmp = 0.3;
			break;
		default:
			tmp = 1;
			break;
		}
		return tmp;
	}
}
