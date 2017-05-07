package problem;

import java.util.Random;
import java.io.*;
import java.util.Scanner;

public class LV9Problem
{
	private String string = "";
	private String a = "JavaQuestions/hard/";
	//Read File
	public void ReadFile(String input)
	{
		string = com.Func.getFileToString(new File(input));
	}
	
	
	public char printProblem(LV9Problem LV)
	{
		String input;
		char ans;
		Random random = new Random();
		switch(random.nextInt(10) + 1)
		{
		
			case 1:
				ans = 'C';
				input = a + "hard_11.txt";
				LV.ReadFile(input);
				break;
			case 2:
				ans = 'C';
				input = a + "hard_12.txt";
				LV.ReadFile(input);
				break;
			case 3:
				ans = 'A';
				input = a + "hard_13.txt";
				LV.ReadFile(input);
				break;
			case 4:
				ans = 'D';
				input = a + "hard_14.txt";
				LV.ReadFile(input);
				break;
			case 5:
				ans = 'D';
				input = a + "hard_15.txt";
				LV.ReadFile(input);
				break;
			case 6:
				ans = 'B';
				input = a + "hard_16.txt";
				LV.ReadFile(input);
				break;
			case 7:
				ans = 'C';
				input = a + "hard_17.txt";
				LV.ReadFile(input);
				break;
			case 8:
				ans = 'B';
				input = a + "hard_18.txt";
				LV.ReadFile(input);
				break;
			case 9:
				ans = 'C';
				input = a + "hard_19.txt";
				LV.ReadFile(input);
				break;
			default:
				ans = 'C';
				input = a + "hard_20.txt";
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
