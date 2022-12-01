package com.example.myapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBOpenHelper {


    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String dbname = "chen_2035061512";
    private static final String url = "jdbc:mysql://rm-uf6dwiuhh0val89vaho.mysql.rds.aliyuncs.com:3306/"
            +dbname+"?useSSL=true";
    private static final String user = "chen_2035061512";//用户名
    private static final String password = "chen_2035061512";//密码

    /**
     * 连接数据库
     * */

    public static Connection getConn(){

        Connection conn = null;
        try {
            System.out.println("正在尝试连接数据库！");
            Class.forName(driver);//获取MYSQL驱动
            conn = DriverManager.getConnection(url, user, password);//获取连接

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        if(conn!=null){
            System.out.println("连接成功！");
        }else{
            System.out.println("连接失败！");
        }
        return conn;
    }

    /**
     * 关闭数据库
     * */

    public static void closeAll(Connection conn, PreparedStatement ps){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭数据库
     * */

    public static void closeAll(Connection conn, PreparedStatement ps, ResultSet rs){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
