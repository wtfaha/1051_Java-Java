package problem;

import java.util.Random;
import java.io.*;
import java.util.Scanner;

public class LV5Problem
{
	private String string = "";
	private String a = "JavaQuestions/middle/";
	//Read File
	public void ReadFile(String input)
	{
		string = com.Func.getFileToString(new File(input));
	}
	
	
	public char printProblem(LV5Problem LV)
	{
		String input;
		char ans;
		Random random = new Random();
		switch(random.nextInt(10) + 1)
		{
			
			case 1:
				ans = 'B';
				input = a + "middle_1.txt";
				LV.ReadFile(input);
				break;
			case 2:
				ans = 'D';
				input = a + "middle_2.txt";
				LV.ReadFile(input);
				break;
			case 3:
				ans = 'B';
				input = a + "middle_3.txt";
				LV.ReadFile(input);
				break;
			case 4:
				ans = 'C';
				input = a + "middle_4.txt";
				LV.ReadFile(input);
				break;
			case 5:
				ans = 'A';
				input = a + "middle_6.txt";
				LV.ReadFile(input);
				break;
			case 6:
				ans = 'D';
				input = a + "middle_7.txt";
				LV.ReadFile(input);
				break;
			case 7:
				ans = 'A';
				input = a + "middle_8.txt";
				LV.ReadFile(input);
				break;
			case 8:
				ans = 'D';
				input = a + "middle_9.txt";
				LV.ReadFile(input);
				break;
			case 9:
				ans = 'D';
				input = a + "middle_10.txt";
				LV.ReadFile(input);
				break;
			default:
				ans = 'D';
				input = a + "middle_11.txt";
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