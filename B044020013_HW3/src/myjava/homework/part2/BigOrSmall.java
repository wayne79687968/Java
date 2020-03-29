package myjava.homework.part2;
import java.util.*;

public class BigOrSmall {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Race Race;
        RaceKind RaceKind = new RaceKind();
        while(true){
            System.out.println("Which game you want? (You can input big or small to play, or input exit to quit.");
            String a;
            a = scanner.nextLine();
            switch (a){
                case "big":
                    Race = new Race(RaceKind.BIG);
                    Race.start();
                    break;
                case "small":
                    Race = new Race(RaceKind.SMALL);
                    Race.start();
                    break;
                case "exit":
                    System.out.println("Game Over!!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Input error : incorrect option");
                    break;
            }
            System.out.println(" ");
        }
    }
}
