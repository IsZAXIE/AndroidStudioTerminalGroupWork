package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AfterLogin extends AppCompatActivity implements View.OnClickListener {
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        // Get account from MainActivity or CreateNewUser
        String account = getIntent().getStringExtra("account");
        // use account find user id
        id = DatabaseUtil.getUserId(account);
        System.out.println("id = " + id);
        if (id == -1) {
            System.out.println("Id is wrong");
        }

        findViewById(R.id.analyse).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.record).setOnClickListener(this);
        findViewById(R.id.suggest).setOnClickListener(this);
        findViewById(R.id.setting).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        // All parameters called "id" that are converted in this onClick function are the user's id

        // search function
        if (v.getId() == R.id.button1) {
            // goto Search function
        }
        else if(v.getId()==R.id.suggest){
            Intent intent=new Intent(this,Suggest.class).putExtra("id",id);
            startActivity(intent);
        }
        else if (v.getId() == R.id.setting) {
            Intent intent = new Intent(this, Mine.class).putExtra("id", id);
            startActivity(intent);
        }

        //analyse function
        else if (v.getId() == R.id.analyse) {
            Intent intent = new Intent(this, Analyse.class).putExtra("id", id);
            startActivity(intent);
        }
        // record function
        else if (v.getId() == R.id.record) {
            Intent intent = new Intent(this, RecordActivity.class).putExtra("id", id);
            startActivity(intent);
        }
    }
}