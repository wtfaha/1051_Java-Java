package problem;

import java.util.Random;
import java.io.*;
import java.util.Scanner;

public class LV4Problem
{
	private String string = "";
	private String a = "JavaQuestions/easy/";
	//Read File
	public void ReadFile(String input)
	{
		string = com.Func.getFileToString(new File(input));
	}
	
	
	public char printProblem(LV4Problem LV)
	{
		String input;
		char ans;
		Random random = new Random();
		switch(random.nextInt(10) + 1)
		{
			
			case 1:
				ans = 'D';
				input = a + "easy_19.txt";
				LV.ReadFile(input);
				break;
			case 2:
				ans = 'D';
				input = a + "easy_20.txt";
				LV.ReadFile(input);
				break;
			case 3:
				ans = 'B';
				input = a + "easy_21.txt";
				LV.ReadFile(input);
				break;
			case 4:
				ans = 'C';
				input = a + "easy_22.txt";
				LV.ReadFile(input);
				break;
			case 5:
				ans = 'D';
				input = a + "easy_24.txt";
				LV.ReadFile(input);
				break;
			case 6:
				ans = 'C';
				input = a + "easy_25.txt";
				LV.ReadFile(input);
				break;
			case 7:
				ans = 'B';
				input = a + "easy_26.txt";
				LV.ReadFile(input);
				break;
			case 8:
				ans = 'B';
				input = a + "easy_27.txt";
				LV.ReadFile(input);
				break;
			case 9:
				ans = 'B';
				input = a + "easy_28.txt";
				LV.ReadFile(input);
				break;
			default:
				ans = 'C';
				input = a + "easy_29.txt";
				LV.ReadFile(input);
			    break;
			}
		return ans;
		}


	public String getString() {
		return string;
	}


	public void setString(String string) {
		this.string = string;
	}
}

