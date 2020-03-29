package myjava.homework;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class main {
    public static void main(String[] args){
        ExecutorService application = Executors.newCachedThreadPool();
        Scanner scanner = new Scanner(System.in);
        String num = scanner.nextLine();
        SynchronizedBuffer sharedLocation = new SynchronizedBuffer(Integer.parseInt(num));
        sharedLocation.displayState("Initial State");
        application.execute(new Producer(sharedLocation));
        application.execute(new Consumer(sharedLocation));
        application.shutdown();
    }
}



