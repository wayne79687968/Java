package myjava.homework;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Client extends ATM{
    public Socket socket;
    public void sendData(String message) throws Exception{
        DataOutputStream output = null;
        try {
            output = new DataOutputStream(socket.getOutputStream());
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
            BufferedReader FromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clientText = FromClient.readLine();
        }
        catch ( IOException e ){
            e.printStackTrace();
        }
        System.out.println(clientText);
        return clientText;
    }
    protected  void run(){}
    protected void processConnection() throws IOException{
        socket = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input your address");
        String add = scanner.nextLine();
        System.out.println("Input your port");
        String port = scanner.nextLine();
        System.out.println("Connection...");
        try {
            socket = new Socket( add, Integer.parseInt(port));
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
    }
    public static boolean isInteger(String value) {
        Pattern pattern = Pattern.compile("^[-+]?\\d+$");
        return pattern.matcher(value).matches();
    }
    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        Client client = new Client();
        client.processConnection();
        System.out.println("Connection");
        while (true) {
            try{
                System.out.println("(1).Sign In");
                System.out.println("(2).Create New Account");
                System.out.println("(3).Exit");
                System.out.println("----------------------------------");
                String option = scanner.nextLine();
                switch (option) {
                    case "1":
                        int flag = 0;
                        client.sendData("1");
                        System.out.println("Input your account");
                        String acc = scanner.nextLine();
                        System.out.println("Input your password");
                        String pwd = scanner.nextLine();
                        while (!isInteger(pwd)) {
                            System.out.println("password must integer,try again");
                            pwd = scanner.nextLine();
                        }
                        while (pwd.length() != 6) {
                            System.out.println("password must consist of 6 digits, try again");
                            pwd = scanner.nextLine();
                        }
                        while (!isInteger(pwd)) {
                            System.out.println("password must integer,try again");
                            pwd = scanner.nextLine();
                        }
                        while (pwd.length() != 6) {
                            System.out.println("password must consist of 6 digits, try again");
                            pwd = scanner.nextLine();
                        }
                        client.sendData(acc + " " + pwd);
                        String signed = client.getStreams();
                        if (signed.equals("signed in")) {
                            System.out.println("(1).Deposit Cash");
                            System.out.println("(2).Get Cash");
                            System.out.println("(3).Balance");
                            System.out.println("(4).Withdraw");
                            System.out.println("----------------------------------");
                            String option2 = scanner.nextLine();
                            switch (option2) {
                                case "1":
                                    client.sendData("1");
                                    System.out.println("Input the amount of money you want to deposit");
                                    String money = scanner.nextLine();
                                    client.sendData(money);
                                    client.getStreams();
                                    break;
                                case "2":
                                    client.sendData("2");
                                    System.out.println("Input the amount of money you want to get");
                                    money = scanner.nextLine();
                                    client.sendData(money);
                                    client.getStreams();
                                    break;
                                case "3":
                                    client.sendData("3");
                                    client.getStreams();
                                    break;
                                case "4":
                                    flag = 1;
                                    break;
                                default:
                                    System.out.println("Wrong Input");
                                    break;
                            }
                            if (flag == 1)
                                break;
                        }
                        break;
                    case "2":
                        client.sendData("2");
                        System.out.println("Input your account");
                        acc = scanner.nextLine();
                        System.out.println("Input your password");
                        pwd = scanner.nextLine();
                        while (!isInteger(pwd)) {
                            System.out.println("password must integer,try again");
                            pwd = scanner.nextLine();
                        }
                        while (pwd.length() != 6) {
                            System.out.println("password must consist of 6 digits, try again");
                            pwd = scanner.nextLine();
                        }
                        while (!isInteger(pwd)) {
                            System.out.println("password must integer,try again");
                            pwd = scanner.nextLine();
                        }
                        while (pwd.length() != 6) {
                            System.out.println("password must consist of 6 digits, try again");
                            pwd = scanner.nextLine();
                        }
                        client.sendData(acc + " " + pwd);
                        client.getStreams();
                        break;
                    case "3":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Wrong Input!");
                        break;
                }
            }
            catch ( IOException e ){
                e.printStackTrace();
            }
            catch (Exception a){
                a.printStackTrace();
            }
            client.closeConnection();
        }
    }
}
