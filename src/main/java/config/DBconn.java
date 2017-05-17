package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by Masachi on 2017/5/16.
 */
public class DBconn {
    public static Connection connection;
    public static Statement statement;

    public void setConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");

            String url = "jdbc:mysql://153.125.235.241:31424/graduate?useUnicode=true&characterEncoding=utf8";    //JDBC的URL
            connection = DriverManager.getConnection(url, "root", "sizhaizhenexin");
            if(connection != null) {
                System.out.println("成功连接到数据库！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}