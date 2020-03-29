package myjava.homework;

public class Loan extends Transaction{
	private	int amount;
	private	BankDatabase bd = null;
	public Loan(BankDatabase tmp,int AN){
		super(AN);
		bd = tmp;
	}
	public void execute() {
		Screen print = new Screen();
		this.amount = bd.getDebt(super.getAccountNumber());
		String debt_str = String.valueOf(this.amount);
		print.displayMessage("Your debt : ");
		print.displayMessageLine(debt_str);
		int creditLevel = bd.getCreditLevel(super.getAccountNumber());
		if(creditLevel <= 0){
			print.displayMessageLine("Sorry, You can't loan any money now.");
			print.displayMessageLine(" ");
		}
		else{
			print.displayMessage("Your loan limit is ");
			String CreditLevel_str = String.valueOf(creditLevel);
			print.displayMessage(CreditLevel_str);
			print.displayMessage(", How much do you want to loan : ");
			bd.loan(super.getAccountNumber());
			print.displayMessageLine("");
		}
	}

}
