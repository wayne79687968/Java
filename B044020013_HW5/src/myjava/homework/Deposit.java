package myjava.homework;

public  class Deposit extends Transaction {
	private	int amount;
	private	BankDatabase bd = null;
	public Deposit(BankDatabase tmp, int AN){
		super(AN);
		this.bd = tmp;
	}
	public void execute() {
		this.amount = super.getAccountNumber();
		Screen print = new Screen();
		bd. deposit(amount);
		print.displayMessageLine(" ");
	}
	
}
