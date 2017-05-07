package problem;

import java.util.Random;
import java.io.*;
import java.util.Scanner;

public class LV7Problem
{
	private String string = "";
	private String a = "JavaQuestions/middle/";
	//Read File
	public void ReadFile(String input)
	{
		string = com.Func.getFileToString(new File(input));
	}
	
	
	public char printProblem(LV7Problem LV)
	{
		String input;
		char ans;
		Random random = new Random();
		switch(random.nextInt(10) + 1)
		{
			
			case 1:
				ans = 'C';
				input = a + "middle_24.txt";
				LV.ReadFile(input);
				break;
			case 2:
				ans = 'B';
				input = a + "middle_25.txt";
				LV.ReadFile(input);
				break;
			case 3:
				ans = 'A';
				input = a + "middle_26.txt";
				LV.ReadFile(input);
				break;
			case 4:
				ans = 'D';
				input = a + "middle_27.txt";
				LV.ReadFile(input);
				break;
			case 5:
				ans = 'B';
				input = a + "middle_28.txt";
				LV.ReadFile(input);
				break;
			case 6:
				ans = 'A';
				input = a + "middle_29.txt";
				LV.ReadFile(input);
				break;
			case 7:
				ans = 'D';
				input = a + "middle_30.txt";
				LV.ReadFile(input);
				break;
			case 8:
				ans = 'D';
				input = a + "middle_31.txt";
				LV.ReadFile(input);
				break;
			case 9:
				ans = 'C';
				input = a + "middle_32.txt";
				LV.ReadFile(input);
				break;
			default:
				ans = 'D';
				input = a + "middle_33.txt";
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