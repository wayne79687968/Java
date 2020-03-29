package myjava.homework;

public class Account {
	private int accountNumber;
	private	int pin;
	private	int totalbalance;
	private	int debt;
	private	char creditLevel;
	//constructor
	public Account(){
		accountNumber = -1;
		pin = -1;
		totalbalance = 0;
		debt = 0;
		creditLevel = 'E';
	}
	public Account(int AN,int Pin,int TB,char CL){
		accountNumber = AN;
		pin = Pin;
		totalbalance = TB;
		debt = 0;
		creditLevel = CL;
	}
	public Account(int AN, int Pin, int db){
		accountNumber = AN;
		pin = Pin;
		debt = db;
	}
	public Account(int AN,int Pin,int TB,int db,char CL){
		accountNumber = AN;
		pin = Pin;
		totalbalance = TB;
		debt = db;
		creditLevel = CL;
	}
	//method
	public boolean validatePin (int Pin){
		if(pin == Pin)
			return true;
		return false;
	}
	public int getPin(){
		return pin;
	}
	public int getAccountNumber(){
		return accountNumber;
	}
	public int gettotalBalance(){
		return totalbalance;
	}
	public char getcreditLevel(){
		return creditLevel;
	}
	public int getdebt(){
		return debt;
	}
}
