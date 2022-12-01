package com.example.myapplication;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.InputMismatchException;

public class CreateNewUser extends AppCompatActivity implements View.OnClickListener {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_id);

        findViewById(R.id.button2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        EditText accountText =findViewById(R.id.editTextTextPersonName);
        EditText pswText=findViewById(R.id.editTextTextPassword);
        EditText heightText=findViewById(R.id.editTextHeight);
        EditText weightText=findViewById(R.id.editTextWeight);
        String acc=accountText.getText().toString();
        String psw=pswText.getText().toString();
        try{
            double height=Double.parseDouble(heightText.getText().toString());
            double weight=Double.parseDouble(weightText.getText().toString());
            User user=new User();
            user.setAccount(acc);
            user.setPsw(psw);
            user.setHeight(height);
            user.setWeight(weight);
            user.print();
            DatabaseUtil databaseUtil=new DatabaseUtil();
            if(checkInput(user)){
                if(databaseUtil.createNewUser(user)){
                    // Storage into database
                    Toast.makeText(this,"Welcome", LENGTH_SHORT).show();
                    Intent intent=new Intent(this,AfterLogin.class);

                    startActivity(intent.putExtra("account",user.getAccount()));
                }
                else{
                    Toast.makeText(this,"This account has been registered", LENGTH_SHORT).show();
                }
            }
        }catch (InputMismatchException e){
            Toast.makeText(this,"The type of weight or height are wrong",LENGTH_SHORT).show();
        }
    }

    public boolean checkInput(User user){
        return (!(user.getAccount().equals("")||user.getPsw().equals("") // haven't input account or password
                ||user.getHeight()<0||user.getHeight()>250||user.getWeight()<0||user.getWeight()>300));
    }
}
