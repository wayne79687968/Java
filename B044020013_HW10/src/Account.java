public class Account extends Thread{
    private String name;
    private int balance;
    public Account(){
        name = "Java";
        setBalance(5000);
    }
    public Account(int type, int amt){
        if (type == 0){
            if (balance < amt) {
                System.out.println("Insufficient funds!");
                return;
            }
            int temp = balance;
            temp = temp - amt;
            try {
                Thread.sleep(300); // simulate consumption time
            } catch (InterruptedException ie) {
                System.err.println(ie.getMessage());
            }
            System.out.println("after withdrawl balance = $" + temp);
            balance = temp;
        }else{
            int temp = balance;
            temp = temp + amt;
            try {
                Thread.sleep(300); // simulate production time
            } catch (InterruptedException ie) {
                System.err.println(ie.getMessage());
            }
            System.out.println("after deposit balance = $" + temp);
            balance = temp;
        }
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
}
