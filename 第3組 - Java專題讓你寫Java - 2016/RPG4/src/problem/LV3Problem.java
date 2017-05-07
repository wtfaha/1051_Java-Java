package problem;

import java.util.Random;
import java.io.*;
import java.util.Scanner;

public class LV3Problem
{
	private String string = "";
	private String a = "JavaQuestions/easy/";
	//Read File
	public void ReadFile(String input)
	{
		string = com.Func.getFileToString(new File(input));
	}
	
	
	public char printProblem(LV3Problem LV)
	{
		String input;
		Random random = new Random();
		char ans;
		switch(random.nextInt(10) + 1)
		{
			case 1:
				ans = 'C';
				input = a + "easy_12.txt";
				LV.ReadFile(input);
				break;
			case 2:
				ans = 'A';
				input = a + "easy_2.txt";
				LV.ReadFile(input);
				break;
			case 3:
				ans = 'B';
				input = a + "easy_16.txt";
				LV.ReadFile(input);
				break;
			case 4:
				ans = 'C';
				input = a + "easy_4.txt";
				LV.ReadFile(input);
				break;
			case 5:
				ans = 'B';
				input = a + "easy_6.txt";
				LV.ReadFile(input);
				break;
			case 6:
				ans = 'B';
				input = a + "easy_7.txt";
				LV.ReadFile(input);
				break;
			case 7:
				ans = 'A';
				input = a + "easy_23.txt";
				LV.ReadFile(input);
				break;
			case 8:
				ans = 'D';
				input = a + "easy_31.txt";
				LV.ReadFile(input);
				break;
			case 9:
				ans = 'C';
				input = a + "easy_30.txt";
				LV.ReadFile(input);
				break;
			default:
				ans = 'B';
				input = a + "easy_11.txt";
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
