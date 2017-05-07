package problem;

import java.util.Random;
import java.io.*;
import java.util.Scanner;


public class LV2Problem
{
	private String string = "";
	private String a = "JavaQuestions/easy/";
	//Read File
	public void ReadFile(String input)
	{
		string = com.Func.getFileToString(new File(input));
	}
	
	
	public char printProblem(LV2Problem LV)
	{
		String input;
		char ans;
		Random random = new Random();
		switch(random.nextInt(10) + 1)
		{
			
			case 1:
				ans = 'B';
				input = a + "easy_7.txt";
				LV.ReadFile(input);
				break;
			case 2:
				ans = 'D';
				input = a + "easy_8.txt";
				LV.ReadFile(input);
				break;
			case 3:
				ans = 'C';
				input = a + "easy_12.txt";
				LV.ReadFile(input);
				break;
			case 4:
				ans = 'B';
				input = a + "easy_16.txt";
				LV.ReadFile(input);
				break;
			case 5:
				ans = 'B';
				input = a + "easy_17.txt";
				LV.ReadFile(input);
				break;
			case 6:
				ans = 'D';
				input = a + "easy_18.txt";
				LV.ReadFile(input);
				break;
			case 7:
				ans = 'D';
				input = a + "easy_33.txt";
				LV.ReadFile(input);
				break;
			case 8:
				ans = 'D';
				input = a + "easy_32.txt";
				LV.ReadFile(input);
				break;
			case 9:
				ans = 'A';
				input = a + "easy_14.txt";
				LV.ReadFile(input);
				break;
			default:
				ans = 'D';
				input = a + "easy_15.txt";
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
