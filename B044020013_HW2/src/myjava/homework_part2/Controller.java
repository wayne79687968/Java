package myjava.homework_part2;
import java.util.*;

public class Controller {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a;
        ItemList Item = new ItemList();
        while (true) {
            try {
                System.out.println("Type 1: add item to list.");
                System.out.println("Type 2: remove item from list.");
                System.out.println("Type 3: show the list.");
                System.out.println("Type 4: Leave.");
                a = scanner.nextInt();
                switch (a){
                    case 1:
                        System.out.println("add item name:");
                        String anam = scanner.next();
                        Item.addItem(anam);
                        break;
                    case 2:
                        System.out.println("remove item name :");
                        String rnam = scanner.next();
                        Item.Reomove(rnam);
                        break;
                    case 3:
                        System.out.println("show the list");
                        Item.printList();
                        break;
                    case 4:
                        System.out.println("Bye!!!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Input error : incorrect option");
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Input error : ONLY Integers.");
            }
            System.out.println("");
        }
    }
}
