package myjava.homework;
import java.util.*;

public class HW6_main {
    public static void main(String[] args) {
        System.out.println("(1) Pikachu (2) Bulbasaur (3) Charizard");
        System.out.print("Choose your pokemon (By default (1)) :");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                pokemon pikachu = new Pikachu();
                pikachu.action();
                break;
            case "2":
                pokemon bulb = new Bulbasaur();
                bulb.action();
                break;
            case "3":
                pokemon chari = new Charizard();
                chari.action();
                break;
            default:
                pokemon pika = new Pikachu();
                pika.action();
                break;
        }

    }
}
