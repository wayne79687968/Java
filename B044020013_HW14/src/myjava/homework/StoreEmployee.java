package myjava.homework;

public class StoreEmployee implements Employee,Shop {
    private double numberOfHoursWorked;
    private double hourlyRate;
    private String storeDetails;
    private double basePay;
    private String employeeName;
    public StoreEmployee(double nOHW, double hR, String sD, double bP, String eN){
        numberOfHoursWorked = nOHW;
        hourlyRate = hR;
        storeDetails = sD;
        basePay = bP;
        employeeName = eN;
    }
    public double calculatePay(){return calculatePay();}
    public boolean checkPromotionEligibility(){return checkPromotionEligibility();}
    public double calculateCommission(){return  calculateCommission();}
    public double calculateStoreRevenue(double tR){
        double stax;
        if (tR > 255000)
            stax = 0.05;
        else if(tR >= 155000 && tR <= 255000)
            stax = 0.03;
        else
            stax = 0.01;
        return tR - (tR * TAX) - calculatePay() - (tR * stax);
    }
    public double getBasePay(){
        return basePay;
    }
    public String getEmployeeName(){
        return employeeName;
    }
    public double getHourlyRate(){
        return hourlyRate;
    }
    public double getNumberOfHoursWorked(){
        return numberOfHoursWorked;
    }
    public String getStoreDetails() {
        return storeDetails;
    }
}

