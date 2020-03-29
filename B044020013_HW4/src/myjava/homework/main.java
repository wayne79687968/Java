package myjava.homework;
import java.util.*;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Choose your Four Star Game type : (1:Combination 2:Permutation 3:Exit)");
            int a;
            a = scanner.nextInt();
            switch (a){
                case 1:
                    Combination com = new Combination();
                    System.out.print("Win numbers : ");
                    com.generateWinNums();
                    for (int i = 0; i < 4; i++)
                        System.out.print(com.getWinNums().get(i) + " ");
                    System.out.println(" ");
                    System.out.print("Input four user numbers : ");
                    com.generateUserNums();
                    com.checkOfWin();
                    break;
                case 2:
                    Permutation per = new Permutation();
                    System.out.print("Win numbers : ");
                    per.generateWinNums();
                    for (int i = 0; i < 4; i++)
                        System.out.print(per.getWinNums().get(i) + " ");
                    System.out.println(" ");
                    System.out.print("Input four user numbers : ");
                    per.generateUserNums();
                    per.checkOfWin();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Input error : incorrect option");
                    break;
            }
        }
    }
}
