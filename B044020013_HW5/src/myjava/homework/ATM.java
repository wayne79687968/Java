package myjava.homework;

public class ATM {
	private boolean userAuthenticated = false;
	private	int AccountNum;
	private	int Pin;
	private	BankDatabase tmp= new BankDatabase();
	public void run(){
		while(true) {
			do {
				System.out.println("Wellcome !");
				System.out.print("Please enter your account number : ");
				Keypad input = new Keypad();
				AccountNum = input.getInput();
				System.out.print("Please enter your pin : ");
				Pin = input.getInput();
				userAuthenticated = tmp.authenticatedUser(AccountNum, Pin);
				if (!userAuthenticated) {
					System.out.println("AccountNumber or Pin Error");
				}
				System.out.println(" ");
			} while (!userAuthenticated);

			while (userAuthenticated) {
				Screen print = new Screen();
				print.displayMessageLine("Main menu : ");
				print.displayMessageLine("1. View my balance");
				print.displayMessageLine("2. Withdraw");
				print.displayMessageLine("3. Deposit");
				print.displayMessageLine("4. Loan");
				print.displayMessageLine("5. Exit");
				print.displayMessage("Enter the choice : ");
				Keypad input1 = new Keypad();
				int option1 = input1.getInput();
				switch (option1) {
					case 1:
						BalanceInquery BI = new BalanceInquery(tmp, AccountNum);
						BI.execute();
						break;
					case 2:
						Withdrawal Wd = new Withdrawal(tmp, AccountNum);
						Wd.execute();
						break;
					case 3:
						Deposit Dp = new Deposit(tmp, AccountNum);
						Dp.execute();
						break;
					case 4:
						Loan l = new Loan(tmp, AccountNum);
						l.execute();
						break;
					case 5:
						userAuthenticated = false;
						System.out.println(" ");
						break;
					default:
						print.displayMessageLine("Wrong Input! Please enter the number between 1~5");
						break;
				}
			}
		}
	 }

}
