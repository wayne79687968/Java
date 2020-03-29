package myjava.homework;
import java.util.*;

public class fourStarGame {
    public fourStarGame(){}
    private ArrayList<Integer> userNums;
    private ArrayList<Integer> winNums;
    public static boolean isInteger(Object object) {
        if(object instanceof Integer) {
            return true;
        } else {
            String string = object.toString();

            try {
                Integer.parseInt(string);
            } catch(Exception e) {
                return false;
            }
        }

        return true;
    }
    public void generateUserNums(){
        Scanner scanner = new Scanner(System.in);
        String num;
        this.userNums = new ArrayList<Integer>();
        int flag = 0;
        num = scanner.nextLine();
        String[] n;
        n = num.split(" ");
        int flagint = 0;
        if (n.length == 4) {
            for (int i = 0; i < 4; i++) {
                if (isInteger(n[i]) == false)
                    flagint = 1;
            }
        }
        if (flagint == 0 && n.length == 4){
            for (int i = 0; i < 4; i++)
                this.userNums.add(Integer.valueOf(n[i]));
        }
        else {
            flag = 1;
        }
        if (flag == 1){
            System.out.println("Wrong input, try again");
            this.userNums = new ArrayList<Integer>();
        }
    }
    public void generateWinNums(){
        Integer num;
        this.winNums = new ArrayList<Integer>();
        Random ran = new Random();
        for (int i = 0; i < 4; i++) {
            num = ran.nextInt(9) + 1;
            this.winNums.add(num);
        }
    }
    public ArrayList<Integer> getUserNums() {
        return userNums;
    }
    public ArrayList<Integer> getWinNums() {
        return winNums;
    }
}

