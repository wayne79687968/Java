package myjava.homework;

import java.io.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.sql.rowset.JdbcRowSet;
import javax.xml.transform.Result;

import com.sun.rowset.JdbcRowSetImpl;

public class Server extends ATM{
    public Socket clientsocket;

    public void sendData(String message) throws Exception{
        DataOutputStream output = null;
        try {
            output = new DataOutputStream(clientsocket.getOutputStream());
            output.writeBytes(message + "\n");
            output.flush();
        }
        catch ( IOException e ){
            e.printStackTrace();
        }
    }
    public String getStreams(){
        String clientText = "";
        try {
            BufferedReader FromClient = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
            clientText = FromClient.readLine();
        }
        catch ( IOException e ){
            e.printStackTrace();
        }
        System.out.println(clientText);
        return clientText;
    }

    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/java_hw13?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static final String USERNAME = "root";
    static final String PASSWORD = "";
    private JdbcRowSet rowSet;
    private ResultSetMetaData metaData;
    private ExecutorService threadExecutor;
    private ServerSocket serverSocket;
    private Statement st;
    private Socket socket;
    private ResultSet rs;
    protected void run(){
        processConnection();
        try{
            while (true){
                String in = getStreams();
                switch (in){
                    case "1":
                        String in2 = getStreams();
                        String[] in3 = in2.split(" ");
                        String add,pwd;
                        add = in3[0];
                        pwd = in3[1];
                        rowSet.setCommand("SELECT password FROM user WHERE id = '" + add + "' AND password = '" + pwd + "';");
                        rowSet.execute();
                        metaData = rowSet.getMetaData();
                        int flag = 0;
                        while (!rowSet.next()){
                            sendData("Wrong username or password");
                            flag = 1;
                            break;
                        }
                        int numberOfColumns = metaData.getColumnCount();
                        if (flag == 0) {
                            int flag2 = 0;
                            sendData("signed in");
                            String in4 = getStreams();
                            switch (in4) {
                                case "1":
                                    String money = getStreams();
                                    rowSet.setCommand("SELECT balance FROM money WHERE user = '" + add + "';");
                                    rowSet.execute();
                                    metaData = rowSet.getMetaData();
                                    numberOfColumns = metaData.getColumnCount();
                                    while (rowSet.next()) {
                                        for (int i = 1; i <= numberOfColumns; i++) {
                                            String balance = String.valueOf(rowSet.getObject(i));
                                            Integer newmoney = Integer.parseInt(balance) + Integer.parseInt(money);
                                            st.executeUpdate("UPDATE money SET balance = '" + newmoney + "' WHERE user = '" + add + "';");
                                            rs = st.getResultSet();
                                            sendData("Deposit $" + money + ", $" + newmoney + " is in your account");
                                        }
                                    }
                                    break;
                                case "2":
                                    String money2 = getStreams();
                                    rowSet.setCommand("SELECT balance FROM money WHERE user = '" + add + "';");
                                    rowSet.execute();
                                    metaData = rowSet.getMetaData();
                                    numberOfColumns = metaData.getColumnCount();
                                    while (rowSet.next()) {
                                        for (int i = 1; i <= numberOfColumns; i++) {
                                            String balance = String.valueOf(rowSet.getObject(i));
                                            if (Integer.parseInt(money2) > Integer.parseInt(balance)) {
                                                sendData("Get cash failed, no enough money.");
                                            }else {
                                                Integer newmoney = Integer.parseInt(balance) - Integer.parseInt(money2);
                                                st.executeUpdate("UPDATE money SET balance = '" + newmoney + "' WHERE user = '" + add + "';");
                                                rs = st.getResultSet();
                                                sendData("Get $" + money2 + ", $" + newmoney + " is in your account");
                                            }
                                        }
                                    }
                                    break;
                                case "3":
                                    rowSet.setCommand("SELECT balance FROM money WHERE user = '" + add + "';");
                                    rowSet.execute();
                                    metaData = rowSet.getMetaData();
                                    numberOfColumns = metaData.getColumnCount();
                                    while (rowSet.next()) {
                                        for (int i = 1; i <= numberOfColumns; i++) {
                                            String balance = String.valueOf(rowSet.getObject(i));
                                            sendData("Balance: $" + balance + " is in your account");
                                        }
                                    }
                                    break;
                                case "4":
                                    break;
                                default:
                                    sendData("Wrong Input");
                                    break;
                            }
                        }
                        break;
                    case "2":
                        System.out.println("create account...");
                        in2 = getStreams();
                        in3 = in2.split(" ");
                        add = in3[0];
                        pwd = in3[1];
                        rowSet.setCommand("SELECT id FROM user WHERE id = '" + add + "';");
                        rowSet.execute();
                        metaData = rowSet.getMetaData();
                        flag = 0;
                        while (rowSet.next()){
                            sendData("account already existed");
                            flag = 1;
                            break;
                        }
                        if (flag == 0) {
                            st.executeUpdate("INSERT INTO user(id,password) VALUES ('" + add + "', '" + pwd + "');");
                            rs = st.getResultSet();
                            st.executeUpdate("INSERT INTO money(user,balance) VALUES ('" + add + "', '0');");
                            rs = st.getResultSet();
                            sendData("account established successfully");
                        }
                        break;
                    default:
                        sendData("Wrong Input");
                        break;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (Exception a){
            a.printStackTrace();
        }
    }
    protected void processConnection(){
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input your port");
            String LISTEN_PORT = scanner.nextLine();
            System.out.println("Connection...");
            serverSocket = new ServerSocket( Integer.parseInt(LISTEN_PORT));
            System.out.println("Port:" + LISTEN_PORT);
            System.out.println("Listening Now");
            clientsocket = serverSocket.accept();
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        catch ( Exception a )
        {
            a.printStackTrace();
        }
    }
    protected void closeConnection(){
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

    /**
     * @param args
     */
    public static void main( String[] args ){
        Server server = new Server();
        try {
            server.rowSet = new JdbcRowSetImpl();
            server.rowSet.setUrl(DATABASE_URL);
            server.rowSet.setUsername(USERNAME);
            server.rowSet.setPassword(PASSWORD);
            Connection conn = DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
            server.st = conn.createStatement();
        }
        catch ( Exception a )
        {
            a.printStackTrace();
        }
        server.run();
        server.closeConnection();
    }


}