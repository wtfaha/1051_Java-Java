package problem;

import java.util.Random;
import java.io.*;
import java.util.Scanner;

public class LV1Problem {
	private String string = "";
	private String a = "JavaQuestions/easy/";
	private String input;

	// private char[] cc = {};
	// Read File
	public void ReadFile(String input) {
		string = com.Func.getFileToString(new File(input));
	}

	public char printProblem(LV1Problem LV) {
		// String input;
		char ans;
		Random random = new Random();
		switch (random.nextInt(10) + 1) {

		case 1:
			ans = 'D';
			input = a + "easy_1.txt";
			LV.ReadFile(input);
			break;
		case 2:
			ans = 'A';
			input = a + "easy_2.txt";
			LV.ReadFile(input);
			break;
		case 3:
			ans = 'D';
			input = a + "easy_3.txt";
			LV.ReadFile(input);
			break;
		case 4:
			ans = 'C';
			input = a + "easy_4.txt";
			LV.ReadFile(input);
			break;
		case 5:
			ans = 'D';
			input = a + "easy_5.txt";
			LV.ReadFile(input);
			break;
		case 6:
			ans = 'B';
			input = a + "easy_6.txt";
			LV.ReadFile(input);
			break;
		case 7:
			ans = 'C';
			input = a + "easy_9.txt";
			LV.ReadFile(input);
			break;
		case 8:
			ans = 'D';
			input = a + "easy_10.txt";
			LV.ReadFile(input);
			break;
		case 9:
			ans = 'B';
			input = a + "easy_11.txt";
			LV.ReadFile(input);
			break;
		default:
			ans = 'C';
			input = a + "easy_13.txt";
			LV.ReadFile(input);
			break;
		}
		return ans;
	}

	public String getString() {
		return string;
	}

	public String getInput() {
		return input;
	}

	public void setString(String string) {
		this.string = string;
	}

}
