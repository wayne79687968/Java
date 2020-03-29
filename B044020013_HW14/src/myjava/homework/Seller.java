package myjava.homework;

public class Seller extends StoreEmployee{
    private double salesRate;
    public Seller(double sR, double nOHW, double hR, String sD, double bP, String eN){
        super(nOHW, hR, sD, bP, eN);
        salesRate = sR;
    }
    public double calculateCommission() {
        if (salesRate > 30){
            return getBasePay() * COMMISSION_RATE;
        }
        else
            return 0;
    }
    public double calculatePay() {
        return getBasePay() + calculateCommission() + (getNumberOfHoursWorked() * getHourlyRate());
    }
    public boolean checkPromotionEligibility() {
        if (calculatePay() > 25000 && salesRate >= 0.1)
            return true;
        else
            return false;
    }
    public double getSalesRate() {
        return salesRate;
    }
}
