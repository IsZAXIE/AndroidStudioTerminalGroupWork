package com.example.myapplication;

import java.util.List;

public class DatabaseUtil {
    public DatabaseUtil() {
    }

    public boolean accountAndPasswordExits(User user) {
        DBService dbService = new DBService();
        List<User> userList = dbService.getUserList();
        for (User users : userList) {

            if (user.getPsw().equals(users.getPsw()) && user.getAccount().equals(users.getAccount())) {
                return true;
            }
        }
        return false;
    }

    public boolean createNewUser(User user) {
        DBService dbService = new DBService();
        // judge user hasn't been created
        for (User user1 : dbService.getUserList()) {
            if (user.getAccount().equals(user1.getAccount())) {
                return false;
            }
        }

        dbService.insert(user);

        return true;
    }

    public double calculateKcal(String foodName, double quality) {
        DBService dbService = new DBService();
        List<Item> itemList = dbService.getItemList();
        for (Item item : itemList) {
            if (item.getName().replace("/100g","").equals(foodName)) {
                return quality * item.getKcal();
            }

        }
        return 1.5;
    }


    // some problem occurred in insertRecord
    public void insertRecord(int userId, Record record) {
        DBService dbService = new DBService();

        //  get itemId to insert into table
        List<Item> items = dbService.getItemList();
        int itemId = -1;
        for (Item item : items) {
            if (item.getName().replace("/100g","").equals(record.getName())) {
                itemId = item.getId();
                break;
            }
        }
        // storage
        if (itemId == -1) {
            System.out.println("未找到相应Item");
        }
        dbService.insertRecord(userId, itemId, record);


    }


    public ReturnItem getThisDayFoodThing(int userId, String date) {
        System.out.println("Date = "+date);
        DBService dbService = new DBService();
        List<ReturnItem> items = dbService.getRecordItem(userId, date); // getRecordItem 为需要返回存储在数据库里边的,
        // 同一天的包含五个能量信息属性的 ReturnItem对象合集
        // ReturnItem contains:  name           quality(g),   carb(/g),    protein(/g)  fat(/g)     df(/g)  kcal(/g)
        //                 from:  ^record_table   ^record      ^item_table    ^item       ^item       ^item  ^item
        ReturnItem recordItem = new ReturnItem(0, 0.0, 0.0, 0.0, 0.0, 0.0);
        for (ReturnItem item : items) {
            recordItem.setCarb(recordItem.getCarb() + item.getCarb() * item.getQuantity());
            recordItem.setProtein(recordItem.getProtein() + item.getProtein() * item.getQuantity());
            recordItem.setFat(recordItem.getFat() + item.getFat() * item.getQuantity());
            recordItem.setDF(recordItem.getDF() + item.getDF() * item.getQuantity());
            recordItem.setKcal(recordItem.getKcal() + item.getKcal());
        }
        return recordItem;
    }

    // return records name and data
    public List<ReturnItem> getRecordItems(int userId, String date) {
        DBService dbService = new DBService();
        // 同一天的包含五个能量信息属性的 ReturnItem对象合集
        // ReturnItem contains:  name           quality(g),   carb(/g),    protein(/g)  fat(/g)     df(/g)  kcal(/g)
        //                 from:  ^record_table   ^record      ^item_table    ^item       ^item       ^item  ^item
        return dbService.getRecordItem(userId, date);
    }

    public User getUserById(int id) {
        DBService dbService = new DBService();

        for (User user : dbService.getUserList()) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public static int getUserId(String account) {
        DBService dbService = new DBService();
        return dbService.getId(account);
    }




    public void changeUserInfo(User user) {
        DBService dbService = new DBService();
        dbService.updateUser(user);

    }



}
