import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Client extends Thread{
    public static void main( String[] args ) throws IOException{
        Socket socket = null;
        Scanner consoleInput = new Scanner( System.in );
        String host = args[1];
        String console = "";
        Integer customer = Integer.parseInt(args[0]);
        int port = Integer.parseInt(args[2]);
        try{
            socket = new Socket( host, port );
            DataInputStream input = null;
            DataOutputStream output = null;

            try{
                input = new DataInputStream( socket.getInputStream() );
                output = new DataOutputStream( socket.getOutputStream() );
                System.out.println("IP : " + host + " Port : " + port);
                System.out.println("Connecting...");
                while ( true )
                {
                    Thread[] threads;
                    Random ran = new Random();
                    int amount = ran.nextInt(300) + 100;
                    int transType = ran.nextInt(1);
                    for (int i = 0; i < 5; i++) {
                        output.writeUTF(i + " " + "Java" + i + " " + amount + " " + transType);
                        output.flush();
                    }
                    console = input.readUTF();
                    break;
                }
            }
            catch ( IOException e ){
                System.out.println("Socket connect error!");
            }
            finally{
                if ( input != null )
                    input.close();
                if ( output != null )
                    output.close();
            }
        }
        catch ( IOException e ){
            e.printStackTrace();
        }
        finally{
            System.out.println("");
            System.out.println(console);
            if ( socket != null )
                socket.close();
            if ( consoleInput != null )
                consoleInput.close();
        }
    }
}
