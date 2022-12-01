package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Suggest extends Activity {
    @Override
    protected void onCreate(Bundle  savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);

        ListView listView=findViewById(R.id.record_list);
        DatabaseUtil databaseUtil=new DatabaseUtil();
        Calendar calendar=Calendar.getInstance();
        int id= getIntent().getIntExtra("id",-1);
//        String date=""+calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+1+"-"+calendar.get(Calendar.DAY_OF_MONTH);
        String date="2020-1-1";
        System.out.println("date in Suggest is "+date);
        List<ReturnItem> returnItems= databaseUtil.getRecordItems(id,date);
        List<Map<String,Object>> displayList=new ArrayList<>();

        for(ReturnItem item:returnItems){
            Map<String,Object>foodInfo=new HashMap<>();
            foodInfo.put("foodName",item.getName());
            foodInfo.put("quality",item.getQuantity());
            displayList.add(foodInfo);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,displayList,R.layout.listview_display,
                new String[]{"foodName","quality"},new int[]{R.id.food_name_list,R.id.food_quantity_list});
        listView.setAdapter(simpleAdapter);
        TextView textView=findViewById(R.id.text_suggest);


        // will change later
        textView.setText("摄取的食物较少");



    }
}
