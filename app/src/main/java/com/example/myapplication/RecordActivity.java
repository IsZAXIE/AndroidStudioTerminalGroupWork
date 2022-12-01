package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RecordActivity extends AppCompatActivity implements OnClickListener, OnDateChangedListener {
    String date;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        DatePicker datePicker=findViewById(R.id.record_date);
        datePicker.init(2000,1,1,this);
        findViewById(R.id.OkButton).setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        if(v.getId()== R.id.OkButton){
            // user press confirm


            // get Food information and store into database
            // get food information
            EditText foodNameText=findViewById(R.id.record_name);
            EditText amountOfFoodText=findViewById(R.id.record_food);
            String foodName=foodNameText.getText().toString();
            String amountWithUnit=amountOfFoodText.getText().toString();
            String []foodAmount=amountWithUnit.split(" ",2);
            // if has unit obey unit
            double amount = Double.parseDouble(foodAmount[0]);
            if(foodAmount.length==2){
                if(foodAmount[1].equals("l")){
                    //一口约28g
                    amount*=28;
                }
                else if(foodAmount[1].equals("b")){
                    amount*=350;
                }
                else if(foodAmount[1].equals("p")){
                    amount*=400;
                }
            }

            DatabaseUtil databaseUtil=new DatabaseUtil();

            // calculate Kcal
            databaseUtil.calculateKcal(foodName,amount);

            int id= getIntent().getIntExtra("id",-1);

            Record record=new Record(foodName,amount,databaseUtil.calculateKcal(foodName,amount),date);
            databaseUtil.insertRecord(id,record);
        }
    }

    @Override
    public void onDateChanged(DatePicker v,int year, int month, int day){
        // get time information
        date=""+year+"-"+month+"-"+day;
        System.out.println("date = "+date);
    }

}
