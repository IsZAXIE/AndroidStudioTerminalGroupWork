package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity implements OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //调用的时候，按照DBService.getDbService().方法的形式

//        DBService.printMessage();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // add action listener to  Login button  in main page
        findViewById(R.id.login).setOnClickListener(this);
        // add action listener to  Register button  in main page
        findViewById(R.id.create).setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        if(v.getId()== R.id.create){
            Intent intent=new Intent(this,CreateNewUser.class);
            startActivity(intent);
        }
        else if(v.getId()== R.id.login){
            User user=new User();
            DatabaseUtil databaseUtil=new DatabaseUtil();
            // get text field
            EditText accText=findViewById(R.id.editTextTextPersonName5);
            EditText pswText=findViewById(R.id.editTextTextPassword3);
            // get account and psw through text field
            user.setAccount(accText.getText().toString());
            user.setPsw(pswText.getText().toString());
            if(databaseUtil.accountAndPasswordExits(user)) {
                Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show();
                startActivity((new Intent(MainActivity.this, AfterLogin.class))
                        .putExtra("account",user.getAccount()));
            }
            else{
                Toast.makeText(this, "Please check account and password and try again", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

