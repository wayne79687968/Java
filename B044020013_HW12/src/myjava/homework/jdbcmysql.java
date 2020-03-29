package myjava.homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class jdbcmysql
{
    public static void main(String[] args) throws ClassNotFoundException
    {
        try
        {
            //連接MySQL
            String url = "jdbc:mysql://localhost:3306/java?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";     // 連結URL
            String user = "root";
            String pass = "";
            Connection conn = DriverManager.getConnection(url,user,pass);
            Statement st = conn.createStatement();
            System.out.println("與資料庫連線成功");
            String sid;
            Integer id;
            String name;
            String sscore;
            ResultSet rs;
            Integer score;
            while (true){
                System.out.println("1.新增帳號");
                System.out.println("2.刪除帳號");
                System.out.println("3.修改分數");
                System.out.println("4.列出所有");
                System.out.println("5.離開");
                Scanner scanner = new Scanner(System.in);
                System.out.print("輸入選擇 : ");
                String option = scanner.nextLine();
                switch (option){
                    case "1":
                        //新增帳號
                        System.out.print("輸入id : ");
                        sid = scanner.nextLine();
                        id = Integer.parseInt(sid);
                        System.out.print("輸入name : ");
                        name = scanner.nextLine();
                        System.out.print("輸入score : ");
                        sscore = scanner.nextLine();
                        score = Integer.parseInt(sscore);
                        st.executeUpdate("INSERT INTO hw12(id,name,score) VALUES ('" + id + "', '" + name + "', '" + score + "');");
                        rs = st.getResultSet();
                        System.out.println("新增成功");
                        System.out.println("------------------");
                        System.out.println("");
                        break;
                    case "2":
                        //刪除帳號
                        System.out.print("輸入id : ");
                        sid = scanner.nextLine();
                        id = Integer.parseInt(sid);
                        st.executeUpdate("DELETE FROM hw12 WHERE id = " + id + ";");
                        rs = st.getResultSet();
                        System.out.println("刪除成功");
                        System.out.println("------------------");
                        System.out.println("");
                        break;
                    case "3":
                        //修改帳號
                        System.out.print("輸入id : ");
                        sid = scanner.nextLine();
                        id = Integer.parseInt(sid);
                        System.out.print("輸入score : ");
                        sscore = scanner.nextLine();
                        score = Integer.parseInt(sscore);
                        st.executeUpdate("UPDATE hw12 SET score = " + score + " WHERE id = " + id + ";");
                        rs = st.getResultSet();
                        System.out.println("修改成功");
                        System.out.println("------------------");
                        System.out.println("");
                        break;
                    case "4":
                        //顯示所有
                        st.execute("SELECT * FROM hw12");
                        rs = st.getResultSet();
                        System.out.println("id name score");
                        while(rs.next()){
                            System.out.println(rs.getString("id")+" "+rs.getString("name")+" "+rs.getString("score"));
                        }
                        System.out.println("------------------");
                        System.out.println("");
                        break;
                    case "5":
                        System.out.println("離開");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Wrong Input!!");
                        break;
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}