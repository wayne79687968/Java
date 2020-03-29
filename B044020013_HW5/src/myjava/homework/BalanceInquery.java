package myjava.homework;

public class BalanceInquery extends Transaction{
	private int amount;
	private	BankDatabase bd = null;
	public BalanceInquery(BankDatabase tmp,int AN) {
		super(AN);
		bd = tmp;
	}
	public void execute(){
		Screen print = new Screen();
		this.amount = bd.getTotalBalance(super.getAccountNumber());
		String TB_Str = String.valueOf(amount);
		print.displayMessageLine("Balance Information");
		print.displayMessageLine("Total Balance : " + TB_Str);
		print.displayMessageLine(" ");
	}
}
