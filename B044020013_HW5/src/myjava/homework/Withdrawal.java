package myjava.homework;

public class Withdrawal extends Transaction {
	private	BankDatabase bd = null;
	public Withdrawal(BankDatabase tmp,int AN){
		super(AN);
		this.bd = tmp;
	}
	public void execute() {
		Screen print = new Screen();
		bd.debit(super.getAccountNumber());
		print.displayMessageLine(" ");
	}
	
}
