package myjava.homework;

public abstract class Transaction {
	private	int accountNumber;
	public Transaction(){
		accountNumber = 0;
	}
	public Transaction(int AN){
		accountNumber = AN;
	}
	public int getAccountNumber(){
		return accountNumber;
	}
	public abstract void execute();
}
