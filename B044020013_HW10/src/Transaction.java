public class Transaction {
    private int num;
    private int amount;
    private String tranType;
    private Account account;
    public Transaction(){}
    public Transaction(int num, Account account, int amount, String tranType){
        this.num = num;
        this.account = account;
        this.amount = amount;
        this.tranType = tranType;
    }
    public int getAmount() {
        return amount;
    }
    public Account getAccount() {
        return account;
    }
    public int getNum() {
        return num;
    }
    public String getTranType() {
        return tranType;
    }
}
