package myjava.homework;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class main {

    public static void main(String[] args) throws IOException {

        //讀txt---------------------------
        String str[][] = new String[100][100];
        FileReader fr = new FileReader("inputfile.txt");
        Scanner scanner = new Scanner(fr);
        String manager[][] = new String[20][8];
        String sales[][] = new String[20][7];
        int num = 0;
        int flag = 0;
        int count = 0;
        int man = 0;
        int sal = 0;
        int man_num = 0;
        int sal_num = 0;
        while (scanner.hasNext()){
            str[num] = scanner.next().split(" ");
            if(str[num][0].equals("Manager")){
                count = 0;
                flag = 1;
                man_num++;
            }
            else if (str[num][0].equals("SalesAssociation")){
                count = 0;
                flag = 2;
                sal_num++;
            }
            if (flag == 1){
                manager[man][count] = str[num][0];
                count++;
            }
            else if (flag == 2){
                sales[sal][count] = str[num][0];
                count++;
            }
            if (flag == 1 && count == 8){
                man++;
            }
            if (flag == 2 && count == 7){
                sal++;
            }
            num++;
        }
        fr.close();
        //讀txt---------------------------

        Manager managers[] = new Manager[man_num];
        Seller sellers[] = new Seller[sal_num];
        for (int i = 0; i < man_num; i++){
            managers[i] = new Manager(Integer.parseInt(manager[i][6]), Integer.parseInt(manager[i][7]), Integer.parseInt(manager[i][4]), Integer.parseInt(manager[i][5]), manager[i][1], Integer.parseInt(manager[i][3]), manager[i][2]);
        }
        for (int i = 0; i < sal_num; i++){
            sellers[i] = new Seller(Integer.parseInt(sales[i][6]), Integer.parseInt(sales[i][4]), Integer.parseInt(sales[i][5]), sales[i][1], Integer.parseInt(sales[i][3]), sales[i][2]);
        }





        //寫txt---------------------------
        FileWriter fw = new FileWriter("report.txt");
        fw.write("****************************************************************\r\n");
        fw.write("Number of employees working as MANAGER are: " + man_num + "\r\n");
        fw.write("****************************************************************\r\n");
        for (int i = 0; i < man_num; i++){
            fw.write((i + 1) + ". Manager Details:\r\n");
            fw.write("Store Details: " + managers[i].getStoreDetails() + "\r\n");
            fw.write("Employee Name: " + managers[i].getEmployeeName() + "\r\n");
            fw.write("Base Pay: $" + managers[i].getBasePay() + "\r\n");
            fw.write("Number of Hours worked: " + managers[i].getNumberOfHoursWorked() + "/hr\r\n");
            fw.write("Payment Rate per hour: $" + managers[i].getHourlyRate() + "/hr\r\n");
            fw.write("Total Sales in store: $" + managers[i].getTotalStoreSales() + "\r\n");
            fw.write("Sales done: $" + managers[i].getSalesDone() + "\r\n");
            DecimalFormat df = new DecimalFormat("0.00");
            fw.write("Percentage of sales done: " + df.format(managers[i].salesPercentByManager() * 100) + "%\r\n");
            DecimalFormat df2 = new DecimalFormat("0.0");
            fw.write("Gross Payment: $" + df2.format(managers[i].calculatePay()) + "\r\n");
            fw.write("Remaining store revenue: $" + df2.format(managers[i].calculateStoreRevenue(managers[i].getTotalStoreSales())) + "\r\n");
            fw.write("Is " + managers[i].getEmployeeName() + " eligible for promotion? ");
            if (managers[i].checkPromotionEligibility() == true)
                fw.write("Yes, he/she is.\r\n\r\n");
            else
                fw.write("No, he/she is not.\r\n\r\n");
        }
        fw.write("****************************************************************\r\n");
        fw.write("Number of employees working as SALES ASSOCIATES are: " + sal_num + "\r\n");
        fw.write("****************************************************************\r\n");
        for (int i = 0; i < sal_num; i++){
            fw.write((i + 1) + ". Sales Associate Details:\r\n");
            fw.write("Store Details: " + sellers[i].getStoreDetails() + "\r\n");
            fw.write("Employee Name: " + sellers[i].getEmployeeName() + "\r\n");
            fw.write("Base Pay: $" + sellers[i].getBasePay() + "\r\n");
            fw.write("Number of Hours worked: " + sellers[i].getNumberOfHoursWorked() + "/hr\r\n");
            fw.write("Payment Rate per hour: $" + sellers[i].getHourlyRate() + "/hr\r\n");
            fw.write("Sales Rate: " + sellers[i].getSalesRate() + "%\r\n");
            fw.write("Total commission: $" + sellers[i].calculateCommission() + "\r\n");
            fw.write("Gross Payment: $" + sellers[i].calculatePay() + "\r\n");
            fw.write("Is " + sellers[i].getEmployeeName() + " eligible for promotion? ");
            if (sellers[i].checkPromotionEligibility() == true)
                fw.write("Yes, he/she is.\r\n\r\n");
            else
                fw.write("No, he/she is not.\r\n\r\n");
        }
        fw.flush();
        fw.close();



    }
}
