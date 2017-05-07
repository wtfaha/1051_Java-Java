package Delay;

import java.util.TimerTask;

public class DateTask extends TimerTask{

	@Override
	public void run() { //延遲之後做的事情
		System.exit(0);
	}

}

