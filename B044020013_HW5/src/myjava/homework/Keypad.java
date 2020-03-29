package myjava.homework;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

public class Keypad {
	private BufferedReader br;
	private int tmp;
	// initializes 
	public Keypad () {
		tmp = -1;
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	/*
	 *  This function may throw some Exception if the user enters non-integer input.
	 *  You must use try...catch to deal with it.
	 */
	public Integer getInput() {
		Screen print = new Screen();
		try{
			tmp = -1;
			br = new BufferedReader(new InputStreamReader(System.in));
			tmp =  Integer.parseInt(br.readLine());
			return tmp;
		}
		catch(InputMismatchException e){
			print.displayMessageLine("Wrong input");
		}
		catch(IOException e){
			print.displayMessageLine("Wrong input");
		}
		catch(NumberFormatException e){
			print.displayMessageLine("Wrong input");
		}
		return tmp;
	}
	
}