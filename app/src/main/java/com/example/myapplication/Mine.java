package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Mine extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);


        TextView accountTextView=findViewById(R.id.textView7);
        String account;
        int id = getIntent().getIntExtra("id",-1);
        System.out.println("id = "+id);
        DatabaseUtil databaseUtil = new DatabaseUtil();
        User user = databaseUtil.getUserById(id);
        if(user!=null){
            account=user.getAccount();
            accountTextView.setText(account);
        }
        findViewById(R.id.butt_change_info).setOnClickListener(this);
        findViewById(R.id.butt_change_password).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.butt_change_info){
            // go to change info page
            Intent intent=new Intent(this, ChangeInformation.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.butt_change_password){
            Intent intent=new Intent(this,ChangePassword.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.button5){
            // clear all Activity
        }
    }

}
