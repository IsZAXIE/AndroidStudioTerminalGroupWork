package com.example.myapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dd implements Runnable {

    private Connection conn;

    @Override
    public void run() {
        System.out.println("连接数据库线程开始！");
        final String driver = "com.mysql.jdbc.Driver";
        final String dbname = "chen_2035061512";
        final String url = "jdbc:mysql://rm-uf6dwiuhh0val89vaho.mysql.rds.aliyuncs.com:3306/"
                + dbname + "?useSSL=true";
        final String user = "chen_2035061512";//用户名
        final String password = "chen_2035061512";//密码

    /*
      连接数据库
      */

        try

        {
            System.out.println("正在尝试连接数据库！");
            Class.forName(driver);//获取MYSQL驱动
            conn = DriverManager.getConnection(url, user, password);//获取连接
        } catch(ClassNotFoundException |
                SQLException e)

        {
            e.printStackTrace();
        }
        if(conn !=null) {
            System.out.println("连接成功！");
        } else {
            System.out.println("连接失败！");
        }
        System.out.println("连接数据库线程结束！");
    }
    public Connection getConn(){
        return this.conn;
    }
}
