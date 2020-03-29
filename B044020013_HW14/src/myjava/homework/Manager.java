package myjava.homework;

public class Manager extends StoreEmployee{
    private double bonusRate;
    private double salesDone;
    private double totalStoreSales;
    public Manager(double saD, double tSS, double nOHW, double hR, String stD, double bP, String eN){
        super(nOHW, hR, stD, bP, eN);
        salesDone = saD;
        totalStoreSales = tSS;
        if (salesDone > 25000)
            bonusRate = 0.15;
        else if (salesDone <= 25000 && salesDone >= 5000)
            bonusRate = 0.1;
        else
            bonusRate = 0.01;
    }
    public double calculatePay() {
        return (getBasePay() + getNumberOfHoursWorked() * getHourlyRate()) * (1 + bonusRate);
    }
    public boolean checkPromotionEligibility() {
        if(calculatePay() > 50000 && salesPercentByManager() >= 0.5 && calculateStoreRevenue(totalStoreSales) >= getBasePay()*4)
            return true;
        else
            return false;
    }
    public double getTotalStoreSales() {
        return totalStoreSales;
    }
    public double getSalesDone() {
        return salesDone;
    }
    public double salesPercentByManager(){
        return salesDone / totalStoreSales;
    }
}
