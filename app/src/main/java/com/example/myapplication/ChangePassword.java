package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChangePassword extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Button bt = findViewById(R.id.button8);
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button8) {
            EditText usernameText = (EditText) findViewById(R.id.editTextInputUsername);
            EditText oriPasswordText = findViewById(R.id.editTextOldPassword);
            EditText newPasswordText = findViewById(R.id.editTextNwePassword);
            EditText confirmPasswordText = findViewById(R.id.editTextConfirmPassword);
            String account = "";
            try {
                account = usernameText.getText().toString();
                String oldPassword = oriPasswordText.getText().toString();
                String newPassword = newPasswordText.getText().toString();
                String confirmPassword = confirmPasswordText.getText().toString();
                User user = new User();
                user.setAccount(account);
                user.setPsw(oldPassword);
                DatabaseUtil databaseUtil = new DatabaseUtil();
                if (databaseUtil.accountAndPasswordExits(user) && newPassword.equals(confirmPassword)) {
                    if (oldPassword.equals(newPassword)) {
                        Toast.makeText(this, "修改后的密码不可和原来密码相同", Toast.LENGTH_SHORT).show();
                    }
                    //  correct account and password
                    else {
                        int id = DatabaseUtil.getUserId(account);
                        user = databaseUtil.getUserById(id);
                        user.setPsw(newPassword);
                        databaseUtil.changeUserInfo(user);
                        Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "账号或密码输入错误，请检查后重新输入", Toast.LENGTH_SHORT).show();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}
