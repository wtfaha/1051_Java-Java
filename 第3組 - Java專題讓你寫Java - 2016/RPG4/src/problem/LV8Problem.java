package problem;

import java.util.Random;
import java.io.*;
import java.util.Scanner;

public class LV8Problem
{
	private String string = "";
	private String a = "JavaQuestions/hard/";
	//Read File
	public void ReadFile(String input)
	{
		string = com.Func.getFileToString(new File(input));
	}
	
	
	public char printProblem(LV8Problem LV)
	{
		String input;
		char ans;
		Random random = new Random();
		switch(random.nextInt(10) + 1)
		{
			
			case 1:
				ans = 'A';
				input = a + "hard_1.txt";
				LV.ReadFile(input);
				break;
			case 2:
				ans = 'D';
				input = a + "hard_2.txt";
				LV.ReadFile(input);
				break;
			case 3:
				ans = 'C';
				input = a + "hard_3.txt";
				LV.ReadFile(input);
				break;
			case 4:
				ans = 'D';
				input = a + "hard_4.txt";
				LV.ReadFile(input);
				break;
			case 5:
				ans = 'A';
				input = a + "hard_5.txt";
				LV.ReadFile(input);
				break;
			case 6:
				ans = 'B';
				input = a + "hard_6.txt";
				LV.ReadFile(input);
				break;
			case 7:
				ans = 'D';
				input = a + "hard_7.txt";
				LV.ReadFile(input);
				break;
			case 8:
				ans = 'C';
				input = a + "hard_8.txt";
				LV.ReadFile(input);
				break;
			case 9:
				ans = 'C';
				input = a + "hard_9.txt";
				LV.ReadFile(input);
				break;
			default:
				ans = 'C';
				input = a + "hard_10.txt";
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