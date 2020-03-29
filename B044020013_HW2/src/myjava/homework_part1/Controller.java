package myjava.homework_part1;
import java.util.*;

public class Controller {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a;
        int count = 1;
        StudentInformation[] Student = new StudentInformation[10];
        while (true) {
            try {
                System.out.println("Type 1: add a student's data(student ID,student name and score).");
                System.out.println("Type 2: show student's data.");
                System.out.println("Type 3: show all student's data.");
                System.out.println("Type 4: Leave.");
                a = scanner.nextInt();
                switch (a){
                    case 1:
                        Student[count] = new StudentInformation();
                        System.out.println("Add new student's data:");
                        System.out.print("student id :");
                        String id = scanner.next();
                        Student[count].setID(id);
                        System.out.print("student name :");
                        String name = scanner.next();
                        Student[count].setName(name);
                        System.out.print("Score :");
                        int score = scanner.nextInt();
                        Student[count].setScore(score);
                        System.out.println("This is student " + count);
                        count ++;
                        break;
                    case 2:
                        System.out.println("To show which student's information");
                        int n = scanner.nextInt();
                        if(n < count){
                            Student[n].showdata();
                            System.out.println("This is student " + n);
                        }
                        else
                            System.out.println("Data no found");
                        break;
                    case 3:
                        int pass = 0;
                        int fail = 0;
                        System.out.println("====Student's data====");
                        for (int i = 1; i < count; i++){
                            System.out.println("Number : " + i);
                            System.out.println("Student id :" + Student[i].getID());
                            System.out.println("Student name :" + Student[i].getName());
                            if (Student[i].getScore() >= 60){
                                pass++;
                                System.out.println(Student[i].getName() + " pass this project");
                            }
                            else{
                                fail++;
                                System.out.println(Student[i].getName() + " fail this project");
                            }
                            System.out.println("");
                        }
                        System.out.println("======================");
                        System.out.println("Pass : " + pass);
                        System.out.println("Not pass : " + fail);
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
