package myjava.homework;

import java.io.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int a,b,count,LISTEN_PORT;
    public void setA(int a) {
        this.a = a;
    }
    public void setB(int b){
        this.b = b;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public void setLISTEN_PORT(int LISTEN_PORT) {
        this.LISTEN_PORT = LISTEN_PORT;
    }
    public int getA() {
        return a;
    }
    public int getB() {
        return b;
    }
    public int getCount() {
        return count;
    }
    public int getLISTEN_PORT() {
        return LISTEN_PORT;
    }

    public void listenRequest() {
        ServerSocket serverSocket = null;
        setCount(0);
        ExecutorService threadExecutor = Executors.newCachedThreadPool();
        try
        {
            serverSocket = new ServerSocket( LISTEN_PORT );
            System.out.println("Port:" + LISTEN_PORT);
            System.out.println("Resource:<A : " + getA() + ",B : " + getB() + ">");
            System.out.println("Listening Now");
            while ( true )
            {
                Socket socket = serverSocket.accept();
                threadExecutor.execute( new RequestThread( socket ) );
            }
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        finally
        {
            if ( threadExecutor != null )
                threadExecutor.shutdown();
            if ( serverSocket != null )
                try
                {
                    serverSocket.close();
                }
                catch ( IOException e )
                {
                    e.printStackTrace();
                }
        }
    }

    /**
     * @param args
     */
    public static void main( String[] args ){
        Server server = new Server();
        int A = Integer.parseInt(args[0]);
        int B = Integer.parseInt(args[1]);
        int port = Integer.parseInt(args[2]);
        server.setA(A);
        server.setB(B);
        server.setLISTEN_PORT(port);
        server.listenRequest();
    }

    /**
     * Client_Request
     *
     * @version
     */
    class RequestThread implements Runnable{
        private Socket clientSocket;
        public RequestThread( Socket clientSocket )
        {
            this.clientSocket = clientSocket;
        }

        /* (non-Javadoc)
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run(){
            DataInputStream input = null;
            DataOutputStream output = null;

            try
            {
                input = new DataInputStream( this.clientSocket.getInputStream() );
                output = new DataOutputStream( this.clientSocket.getOutputStream() );
                while ( true )
                {
                    String in2 = input.readUTF();
                    String[] in3 = in2.split(" ");
                    int iA,iB;
                    iA = Integer.parseInt(in3[0]);
                    iB = Integer.parseInt(in3[1]);
                    setCount(getCount() + 1);
                    if (iA <= getA() && iB <= getB()){
                        System.out.println("[Client_" + getCount() + "] : Take Resource -> A : " + iA + ",B : " + iB);
                        setA(getA() - iA);
                        setB(getB() - iB);
                        System.out.println("                   Resource : A : " + getA() + ",B : " + getB());
                        System.out.println("Replenishment -> A : " + iA + ",B : " + iB);
                        setA(getA() + iA);
                        setB(getB() + iB);
                        System.out.println("                   Resource : A : " + getA() + ",B : " + getB());
                        output.writeUTF("Service finish");
                        output.flush();
                    }else {
                        System.out.println("[Client_" + getCount() + "] : Resource insufficient! Error...");
                        System.out.println("Replenishment -> A : 0,B : 0");
                        System.out.println("                   Resource : A : " + getA() + ",B : " + getB());
                        output.writeUTF("[Error]:Resource quantity insufficient");
                        output.flush();
                    }


                    break;
                }
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if ( input != null )
                        input.close();
                    if ( output != null )
                        output.close();
                    if ( this.clientSocket != null && !this.clientSocket.isClosed() )
                        this.clientSocket.close();
                }
                catch ( IOException e )
                {
                    e.printStackTrace();
                }
            }
        }
    }
}