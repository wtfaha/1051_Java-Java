package problem;

import java.util.Random;
import java.io.*;
import java.util.Scanner;

public class LV10Problem {
	private String string = "";
	private String a = "JavaQuestions/hard/";

	// Read File
	public void ReadFile(String input) {
		string = com.Func.getFileToString(new File(input));
	}

	public char printProblem(LV10Problem LV) {
		String input;
		Random random = new Random();
		char ans;
		switch (random.nextInt(10) + 1) {

		case 1:
			ans = 'C';
			input = a + "hard_21.txt";
			LV.ReadFile(input);
			break;
		case 2:
			ans = 'A';
			input = a + "hard_22.txt";
			LV.ReadFile(input);
			break;
		case 3:
			ans = 'D';
			input = a + "hard_23.txt";
			LV.ReadFile(input);
			break;
		case 4:
			ans = 'C';
			input = a + "hard_24.txt";
			LV.ReadFile(input);
			break;
		case 5:
			ans = 'B';
			input = a + "hard_25.txt";
			LV.ReadFile(input);
			break;
		case 6:
			ans = 'D';
			input = a + "hard_26.txt";
			LV.ReadFile(input);
			break;
		case 7:
			ans = 'C';
			input = a + "hard_27.txt";
			LV.ReadFile(input);
			break;
		case 8:
			ans = 'B';
			input = a + "hard_28.txt";
			LV.ReadFile(input);
			break;
		case 9:
			ans = 'C';
			input = a + "hard_29.txt";
			LV.ReadFile(input);
			break;
		default:
			ans = 'C';
			input = a + "hard_30.txt";
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

/*
 * Test public static void main(String[] args) { LV10Problem LV = new
 * LV10Problem(); int num1; Scanner scanner = new Scanner(System.in);
 * System.out.printf("input:"); num1 = scanner.nextInt(); LV.printProblem(num1,
 * LV); }
 */
