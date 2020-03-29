package myjava.homework;

import java.net.Inet4Address;
import java.util.Random;

public class Producer implements Runnable{
    private final static Random generator = new Random();
    private final Buffer sharedLocation;
    public Producer1(Buffer shared){
        sharedLocation = shared;
    }
    public void run(){
        int sum = 0;
        for(int count = 1; count <= 10; count++){
            try{
                Thread.sleep(generator.nextInt(3000));
                sharedLocation.set(count);
            }
            catch (InterruptedException exception){
                exception.printStackTrace();
            }
        }
        System.out.println("Producer done producing\nTerminating Producer1");
    }
}
