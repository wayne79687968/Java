package myjava.homework;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class SynchronizedBuffer implements Buffer{

    //---------------------------------------------------
    private static final int N;

    /***
     * full 产品容量
     * empty 空余容量
     * mutex 读写锁
     */
    private static Semaphore full,empty,mutex;
    //记录当前的产品数量
    private static volatile int count = 0 ;

    static {
        /**
         * full 初始化0个产品
         * empty 初始化有N个空余位置放置产品
         * mutex 初始化每次最多只有一个线程可以读写
         * */
        full = new Semaphore(0);
        empty = new Semaphore(N);
        mutex = new Semaphore(1);
    }
    //---------------------------------------------------

    private int[] buffer;
    public SynchronizedBuffer(int num){
        buffer = new int[num];
        for(int i = 0; i < num; i++){
            buffer[i] = -1;
        }
        N = num;
    }
    private int occupiedCells = 0;
    private int writeIndex = 0;
    private int readIndex = 0;
    private final Lock accessLock = new ReentrantLock();
    private final Condition canWrite = accessLock.newCondition();
    private final Condition canRead = accessLock.newCondition();
    public void set(int value) throws InterruptedException{

        accessLock.lock();
        try {
            while (occupiedCells == buffer.length){
                System.out.printf("Buffer is full. Producer waits.\n");
                canWrite.await();
            }
            buffer[writeIndex] = value;
            writeIndex = (writeIndex + 1) % buffer.length;
            ++occupiedCells;
            displayState("Producer writes " + value);
            canRead.signalAll();
        }
        finally {
            accessLock.unlock();
        }
    }
    public int get() throws InterruptedException{
        accessLock.lock();
        int readValue = buffer[readIndex];
        try {
            while(occupiedCells == 0){
                System.out.printf("Buffer is empty. Consumer waits\n");
                canRead.await();
                readValue = buffer[readIndex];
            }
            readIndex = (readIndex + 1) % buffer.length;
            --occupiedCells;
            displayState("Consumer reads " + readValue);
            canWrite.signalAll();
            canRead.signal();
        }
        finally {
            accessLock.unlock();
        }
        return readValue;
    }
    public void displayState(String operation){
        System.out.printf("%s%s%d)\n%s", operation, " (buffer cells occupied: ", occupiedCells, "buffer cells: ");
        for(int value : buffer)
            System.out.printf("  %2d  ", value);
        System.out.print("\n               ");
        for (int i = 0; i < buffer.length; i++)
            System.out.print("----- ");
        System.out.print("\n                 ");
        for (int i = 0; i < buffer.length; i++){
            if (i == writeIndex &&  i == readIndex)
                System.out.print("WR    ");
            else if (i == writeIndex)
                System.out.print("W     ");
            else if (i == readIndex)
                System.out.print("R     ");
            else
                System.out.print("      ");
        }
        System.out.println("\n");
    }
}
