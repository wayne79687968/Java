import java.io.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread{
    private int clerk;

    public void setClerk(int clerk) {
        this.clerk = clerk;
    }

    public int getClerk() {
        return clerk;
    }

    public void listenRequest() {
        ServerSocket serverSocket = null;
        ExecutorService threadExecutor = Executors.newCachedThreadPool();
        try
        {
            serverSocket = new ServerSocket( 54013 );
            System.out.println("Port:" + 54013);
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
        server.setClerk(Integer.parseInt(args[0]));
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
                Thread[] threads;
                while ( true )
                {
                    QueueMachine queue = new QueueMachine();
                    String in = input.readUTF();
                    String[] in2 = in.split(" ");
                    Account Java = new Account();
                    Transaction tran = new Transaction(Integer.parseInt(in2[0]), Java, Integer.parseInt(in2[2]), in2[3]);
                    queue.add(tran);
                    Thread[] clerks;
                    if(!queue.isEmpty()){
                        for (int i = 0; i < clerk; i++) {
                            Transaction temp = new Transaction();
                            temp = queue.get();
                            Account tem = new Account();
                            tem = temp.getAccount();
                            clerks[i] = new tem(temp.getTranType(), temp.getAmount());
                            clerks[i].start();
                        }
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