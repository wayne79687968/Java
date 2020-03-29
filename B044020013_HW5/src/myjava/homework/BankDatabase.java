package myjava.homework;

public class BankDatabase {
	private Account[] accounts; // array of Accounts
    // no-argument BankDatabase constructor initializes accounts
	public BankDatabase () {
		accounts = new Account[4];  // just 4 accounts for testing
		accounts[0] = new Account(111, 222, 5000,0,'A');
		accounts[1] = new Account(222, 333, 4000,0,'B');
		accounts[2] = new Account(333, 444, 3000,0,'C');
		accounts[3] = new Account(444, 555, 2000,0,'D');
	}
	/* Fill your code here */			
	public boolean authenticatedUser(int AC,int Pin){
		for(Account x:accounts){
			if(x.getAccountNumber() == AC){
				boolean checkPin = x.validatePin(Pin);
				if(checkPin)
					return true;
			}
		}
		return false;
	}
	public int getTotalBalance(int AN){
		 for(Account x: accounts){
			 if(x.getAccountNumber() == AN){
				 return x.gettotalBalance();
			 }
		 }
		 return 0;
	}
	public int getCreditLevel(int AN){
		for(int i=0;i<accounts.length;i++){
			if(accounts[i].getAccountNumber()==AN){
				int returnCL;
				switch(accounts[i].getcreditLevel()){
					case 'A':
						returnCL=11000-accounts[i].getdebt();
						return returnCL;
					case 'B':
						returnCL=9000-accounts[i].getdebt();
						return  returnCL;
					case 'C':
						returnCL=7000-accounts[i].getdebt();
						return  returnCL;
					case 'D':
						returnCL=5000-accounts[i].getdebt();
						return  returnCL;
				}
			}
		}
		return 0;
	}
	public int getDebt(int AN){
		for(Account x: accounts){
			 if(x.getAccountNumber() == AN){
				 return x.getdebt();
			 }
		 }
		 return -1;
	}

	public void setTotalBalance(int AN, int withdraw){
		for(int i = 0; i < accounts.length; i++) {
			if (accounts[i].getAccountNumber() == AN) {
				Account newAc = new Account(AN, accounts[i].getPin(), accounts[i].gettotalBalance() - withdraw, accounts[i].getcreditLevel());
				accounts[i] = newAc;
			}
		}
	}
	public void setDebt(int AN,int db){
		for(int i = 0; i < accounts.length; i++) {
			if (accounts[i].getAccountNumber() == AN) {
				Account newAc=new Account(AN,accounts[i].getPin(),accounts[i].gettotalBalance(),db,accounts[i].getcreditLevel());
				accounts[i]=newAc;
			}
		}
	}

	public void debit(int AN){
		Keypad input1 = new Keypad();
		Screen print = new Screen();
		print.displayMessage("How much do you want to withdraw : ");
		int withdraw = input1.getInput();
		for(int i = 0; i < accounts.length; i++){
			if(accounts[i].getAccountNumber() == AN){
				if(withdraw <= accounts[i].gettotalBalance()){
					print.displayMessageLine("Transaction Success");
					setTotalBalance(AN,withdraw);
					break;
				}
				else
					print.displayMessageLine("You don't have enough money");
			}
		}
	}
	public void  deposit(int AN){
		Screen print = new Screen();
		print.displayMessage("How much do you want to deposit : ");
		Keypad input2 = new Keypad();
		int InMoney = input2.getInput();
		for(int i = 0; i < accounts.length; i++){
			if(accounts[i].getAccountNumber() == AN){
				print.displayMessageLine("Transaction Success");
				Account newAc = new Account(AN,accounts[i].getPin(), accounts[i].gettotalBalance() + InMoney, accounts[i].getcreditLevel());
				accounts[i] = newAc;
				break;
			}
		}
	}
	public void loan(int AN){
		Keypad input3 = new Keypad();
		Screen print = new Screen();
		int LoanMoney = input3.getInput();
		for(int i = 0; i < accounts.length; i++){
			if(accounts[i].getAccountNumber() == AN){
				switch(accounts[i].getcreditLevel()){
					case 'A':
						if((11000 - accounts[i].getdebt() - LoanMoney) >= 0){
							setDebt(AN, accounts[i].getdebt() + LoanMoney);
							print.displayMessageLine("Transaction Success");
						}
						else
							print.displayMessage("Transaction Error, You have not much Loan Limit");
							print.displayMessageLine(" ");
						break;
					case 'B':
						if((9000 - accounts[i].getdebt() - LoanMoney) >= 0){
							setDebt(AN, accounts[i].getdebt() + LoanMoney);
							print.displayMessageLine("Transaction Success");
						}
						else
							print.displayMessage("Transaction Error, You have not much Loan Limit!");
						break;
					case 'C':
						if((7000 - accounts[i].getdebt() - LoanMoney) >= 0){
							setDebt(AN, accounts[i].getdebt() + LoanMoney);
							print.displayMessageLine("Transaction Success");
						}
						else
							print.displayMessage("Transaction Error, You have not much Loan Limit!");
						break;
					case 'D':
						if((5000 - accounts[i].getdebt() - LoanMoney) >= 0){
							setDebt(AN, accounts[i].getdebt() + LoanMoney);
							print.displayMessageLine("Transaction Success");
						}
						else
							print.displayMessageLine("Transaction Error, You have not much Loan Limit");
						break;
				}
			}
		}
	}

}
