package myjava.homework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class client extends Thread{
    public static void main( String[] args ) throws IOException{
        Socket socket = null;
        Scanner consoleInput = new Scanner( System.in );
        String host = args[2];
        String console = "";
        int port = Integer.parseInt(args[3]);
        try{
            socket = new Socket( host, port );
            DataInputStream input = null;
            DataOutputStream output = null;

            try{
                input = new DataInputStream( socket.getInputStream() );
                output = new DataOutputStream( socket.getOutputStream() );
                System.out.println("IP : " + host + " Port : " + port);
                System.out.println("Resource requirement : < A : " + args[0] + ",B : " + args[1] + " >");
                System.out.println("Connecting...");
                while ( true )
                {
                    output.writeUTF(args[0] + " " + args[1]);
                    output.flush();
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
