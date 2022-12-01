package com.example.myapplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBService {

    List<User> users_list = new ArrayList<>();
    List<Item> items_list = new ArrayList<>();
    List<Record> records_list = new ArrayList<>();
    //private final Connection conn = connThreadGet();
    private Connection conn = null;
    private PreparedStatement ps = null;//操作整合sql语句的对象
    private ResultSet rs = null;//查询结果的集合
    private int id;
    //DBService 对象
    public static DBService dbService = null;

    /**
     * 构造方法 私有化
     */
    protected DBService() {

    }

    /**
     * 获取MySQL数据库单例类对象
     */

    public static DBService getDbService() {
        if (dbService == null) {
            dbService = new DBService();
        }
        return dbService;
    }

    /**
     * 获取信息---查
     **/

    public void getUserData() {
        //结果存放集合
        Connection conn = DBConnect.getConn();
        //MySQL 语句
        String sql = "select * from user";
        //获取链接数据库对象
        try {
            if (conn != null && (!conn.isClosed())) {
                ps = (PreparedStatement) conn.prepareStatement(sql);
                if (ps != null) {
                    Statement st = conn.createStatement();
                    rs = st.executeQuery(sql);
                    if (rs != null) {
                        while (rs.next()) {
                            User u = new User();
                            u.setId(rs.getInt("id"));
                            u.setAccount(rs.getString("account"));
                            u.setPsw(rs.getString("psw"));
                            u.setHeight(rs.getDouble("height"));
                            u.setWeight(rs.getDouble("weight"));
                            users_list.add(u);
                        }
                    } else {
                        System.out.println("rs为空！！！");
                    }
                }
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBConnect.closeAll(conn, ps, rs);//关闭相关操作
    }

    public void getItemData() {
        Connection conn = DBConnect.getConn();
        String sql = "select * from item";
        try {
            if (conn != null && (!conn.isClosed())) {
                ps = (PreparedStatement) conn.prepareStatement(sql);
                if (ps != null) {
                    Statement st = conn.createStatement();
                    rs = st.executeQuery(sql);
                    if (rs != null) {
                        while (rs.next()) {
                            Item i = new Item(rs.getInt("id"), rs.getString("name"), rs.getDouble("kcal"));
                            items_list.add(i);
                        }
                    } else {
                        System.out.println("rs为空！！！");
                    }
                }
            }
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBConnect.closeAll(conn, ps, rs);
    }

    public void getRecordData(int user_id) {
        Connection conn = DBConnect.getConn();
        String sql = "SELECT item.name,item.kcal,record.quality,record.date FROM record " +
                "INNER JOIN item ON item.id = record.item_id WHERE user_id =" + user_id + ";";
        /*
        SELECT user.`account` ,item.`name`,item.`kcal`, `record`.`quality` , `record`.`date`
        FROM (`record` INNER JOIN item ON item.`id` = record.`item_id`)
        INNER JOIN `user`  ON `record`.`user_id` = user.id WHERE `account` = 123456;
         */
        try {
            if (conn != null && (!conn.isClosed())) {
                ps = (PreparedStatement) conn.prepareStatement(sql);
                if (ps != null) {
                    Statement st = conn.createStatement();
                    rs = st.executeQuery(sql);
                    if (rs != null) {
                        while (rs.next()) {
                            Record r = new Record(rs.getString("name"), rs.getDouble("quality"),
                                    rs.getDouble("kcal"), rs.getString("date"));
                            records_list.add(r);
                        }
                    } else {
                        System.out.println("rs为空！！！");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBConnect.closeAll(conn, ps, rs);
    }


    public void getUserID(String account) {
        id=-1;
        conn = DBConnect.getConn();
        String sql = "select user.id from user where user.account = " + account + ";";
        try {
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                Statement st = conn.createStatement();
                rs = st.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        id = rs.getInt("id");
                    }
                } else {
                    System.out.println("rs为空！！！");
                }
            }
            conn.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("获取ID失败！");
            e.printStackTrace();
        }
    }



    /**
     * 以下是提供的方法，上面不是(上面方法不需要调用)
     */

    //返回所有用户
    public List<User> getUserList() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getUserData();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.users_list;
    }

    //返回所有物品
    public List<Item> getItemList() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getItemData();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.items_list;
    }

    //返回对应用户id的所有信息
    public List<Record> getRecordList(int user_id) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getRecordData(user_id);
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.records_list;
    }

    public int getId(String account) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getUserID(account);
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return id;
    }

    //传入User对象 即可自动插入
    public void insert(User user) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String sql = "INSERT INTO chen_2035061512.user(account,psw,height,weight)VALUES(" +
                        user.getAccount() + "," + user.getPsw() + "," + user.getHeight() + "," + user.getWeight() + ");";
                //('account','psw','height','weight')
                System.out.println("正在插入");
                conn = DBConnect.getConn();
                try {
                    ps = conn.prepareStatement(sql);
                    ps.execute();
                    System.out.println("插入一位用户成功！");
                } catch (SQLException e) {
                    System.out.println("插入失败！");
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //删除记录 需要用户id 和 物品id
    public void delete(int user_id, int item_id) {
        //DELETE FROM record where user_id=1 and item_id=1;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String sql = "DELETE FROM chen_2035061512.record WHERE user_id =" + user_id + " and " + "item_id=" + item_id + ";";
                conn = DBConnect.getConn();
                try {
                    ps = conn.prepareStatement(sql);
                    ps.execute();
                    System.out.println("删除一条记录成功");
                } catch (SQLException e) {
                    System.out.println("删除失败！");
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //改-- 用户id 物品id 修改后的质量
    public void updateUserRecord(Integer user_id, Integer item_id, Double newQuality) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (newQuality != null) {
                    //获取链接数据库对象
                    conn = DBConnect.getConn();
                    //MySQL 语句
                    String sql = "update record set quality=? where user_id=? and item_id=?";
                    try {
                        boolean closed = conn.isClosed();
                        if (conn != null && (!closed)) {
                            ps = (PreparedStatement) conn.prepareStatement(sql);
                            ps.setDouble(1, newQuality);//第一个参数
                            ps.setInt(2, user_id);
                            ps.setInt(3, item_id);
                            ps.executeUpdate();//返回1 执行成功
                        }
                    } catch (SQLException e) {
                        System.out.println("数据修改失败！");
                        e.printStackTrace();
                    }
                }
                DBConnect.closeAll(conn, ps);//关闭相关操作
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
        String sql="INSERT INTO user (name,phone,content,state) VALUES (?,?,?,?)";
        ps.setString(1,name);//第一个参数 name 规则同上
        result=ps.executeUpdate();//返回1 执行成功
        return result;
     */

    public static void printMessage() {
        List<User> arrayList;
        arrayList = DBService.getDbService().getUserList();
        if (arrayList.size() != 0) {
            for (User acc : arrayList) {
                System.out.println("id = " + acc.getId() + "    name = " + acc.getAccount() + "\tpassword = " + acc.getPsw());
            }
            System.out.println("Error occurred when get userData");
        } else {
            System.out.println("无数据");
        }
        List<Item> arrayList1 = null;
        arrayList1 = DBService.getDbService().getItemList();
        if (arrayList1.size() != 0) {
            for (Item acc : arrayList1) {
                System.out.println("id = " + acc.getId() + "    account = " + acc.getName());
            }
            System.out.println("Error occurred when get itemList");
        } else {
            System.out.println("无数据");
        }
        List<Record> arrayList2 = null;
        arrayList2 = DBService.getDbService().getRecordList(1);
        if (arrayList2.size() != 0) {
            for (Record acc : arrayList2) {
                System.out.println("name = " + acc.getName() + "    quality = " + acc.getQuality() + "kcal");
            }
            System.out.println("Error occurred when get Record");
        } else {
            System.out.println("无数据");
        }
        System.out.println("获取的用户id为：" + DBService.getDbService().getId("1"));

        /*
         *         DBService.getDbService().updateUserRecord(1,2,80.0);

         * */
        DBService.getDbService().updateUserRecord(1, 2, 80.0);
    }

    // ADD


    /*
    * 以下两个方法需要补齐
    * */

    // 通过用户id，日期查找记录
    public List<ReturnItem> getRecordItem(int userId, String date){
        List<ReturnItem> returnItemList = new ArrayList<ReturnItem>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = DBConnect.getConn();
                String sql = "SELECT * FROM record " +
                        "INNER JOIN item ON item.id = record.item_id WHERE user_id =" + userId + " and " +
                        "date='"+date+"';";
                System.out.println("SQL = "+sql);
                try {
                    if (conn != null && (!conn.isClosed())) {
                        ps = (PreparedStatement) conn.prepareStatement(sql);
                        if (ps != null) {
                            Statement st = conn.createStatement();
                            rs = st.executeQuery(sql);
                            if (rs != null) {
                                while (rs.next()) {
                                    System.out.println("查询到数据");
                                    ReturnItem r = new ReturnItem();
                                    r.setName(rs.getString("name"));
                                    double quality = rs.getDouble("quality");
                                    double kcal = rs.getDouble("kcal");
                                    double total_kcal = kcal*(quality/100);
                                    r.setQuantity(quality);
                                    r.setCarb(rs.getDouble("carb"));
                                    r.setProtein(rs.getDouble("protein"));
                                    r.setFat(rs.getDouble("fat"));
                                    r.setDF(rs.getDouble("df"));
                                    r.setKcal(total_kcal);
                                    returnItemList.add(r);
                                }
                            } else {
                                System.out.println("rs为空！！！");
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                DBConnect.closeAll(conn, ps, rs);
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnItemList;
    }
    // 同一天的包含五个能量信息属性的 ReturnItem对象合集
    // ReturnItem contains:  name           quality(g),   carb(/g),    protein(/g)  fat(/g)     df(/g)  kcal(/g)
    //                 from:  ^record_table   ^record      ^item_table    ^item       ^item       ^item  ^item




    // 通过 用户id，Item id，记录插入
    public void insertRecord(int userId, int itemID, Record record) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String sql = "INSERT INTO chen_2035061512.record(user_id,item_id,quality,date)VALUES(" +
                        userId + "," + itemID + "," + record.getQuality() + "," + record.getDate() + ");";
                System.out.println("正在插入");
                conn = DBConnect.getConn();
                try {
                    ps = conn.prepareStatement(sql);
                    ps.execute();
                    System.out.println("插入一条记录成功！");
                } catch (SQLException e) {
                    System.out.println("插入失败！");
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public void updateUser(User user){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (user != null) {
                    //获取链接数据库对象
                    conn = DBConnect.getConn();
                    //MySQL 语句
                    String sql = "update user set account=?,psw=?,height=?,weight=? where id=?";
                    try {
                        boolean closed = conn.isClosed();
                        if (conn != null && (!closed)) {
                            ps = (PreparedStatement) conn.prepareStatement(sql);
                            ps.setString(1, user.getAccount());//第一个参数
                            ps.setString(2, user.getPsw());
                            ps.setDouble(3, user.getHeight());
                            ps.setDouble(4,user.getWeight());
                            ps.setInt(5,user.getId());
                            ps.executeUpdate();//返回1 执行成功
                        }
                    } catch (SQLException e) {
                        System.out.println("用户数据修改失败！");
                        e.printStackTrace();
                    }
                }
                DBConnect.closeAll(conn, ps);//关闭相关操作
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /*public void updateUser(User user){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String sql = "UPDATE chen_2035061512.USER SET " +
                        "psw = "+user.getPsw()+", " +
                        "height = "+user.getHeight()+"," +
                        "weight = "+user.getWeight() +
                        "where user.id = "+user.getId()+
                        ";";
                System.out.println("正在修改");
                conn = DBConnect.getConn();
                try {
                    ps = conn.prepareStatement(sql);
                    ps.execute();
                    System.out.println("插入一条记录成功！");
                } catch (SQLException e) {
                    System.out.println("插入失败！");
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/







}