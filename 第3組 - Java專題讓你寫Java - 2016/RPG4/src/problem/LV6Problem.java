package problem;

import java.util.Random;
import java.io.*;
import java.util.Scanner;

public class LV6Problem
{
	private String string = "";
	private String a = "JavaQuestions/middle/";
	//Read File
	public void ReadFile(String input)
	{
		string = com.Func.getFileToString(new File(input));
	}
	
	
	public char printProblem(LV6Problem LV)
	{
		String input;
		char ans;
		Random random = new Random();
		switch(random.nextInt(10) + 1)
		{
			
			case 1:
				ans = 'B';
				input = a + "middle_13.txt";
				LV.ReadFile(input);
				break;
			case 2:
				ans = 'C';
				input = a + "middle_14.txt";
				LV.ReadFile(input);
				break;
			case 3:
				ans = 'A';
				input = a + "middle_15.txt";
				LV.ReadFile(input);
				break;
			case 4:
				ans = 'B';
				input = a + "middle_17.txt";
				LV.ReadFile(input);
				break;
			case 5:
				ans = 'D';
				input = a + "middle_18.txt";
				LV.ReadFile(input);
				break;
			case 6:
				ans = 'A';
				input = a + "middle_19.txt";
				LV.ReadFile(input);
				break;
			case 7:
				ans = 'A';
				input = a + "middle_20.txt";
				LV.ReadFile(input);
				break;
			case 8:
				ans = 'C';
				input = a + "middle_21.txt";
				LV.ReadFile(input);
				break;
			case 9:
				ans = 'C';
				input = a + "middle_22.txt";
				LV.ReadFile(input);
				break;
			default:
				ans = 'B';
				input = a + "middle_23.txt";
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
