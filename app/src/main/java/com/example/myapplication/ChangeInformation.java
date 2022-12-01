package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChangeInformation extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeinform);

        findViewById(R.id.info_button_change_info).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.info_button_change_info) {
            EditText accountText = findViewById(R.id.editTextUsername);
            EditText weightText = findViewById(R.id.editTextChangeWeight);
            EditText heightText = findViewById(R.id.editTextChangeHeight);
            String account = accountText.getText().toString();
            double weight = Double.parseDouble(weightText.getText().toString());
            double height = Double.parseDouble(heightText.getText().toString());

            int id;
            if (checkInput(account, height, weight)) {
                DatabaseUtil databaseUtil = new DatabaseUtil();
                id = DatabaseUtil.getUserId(account);


                if (id != -1) {
                    // this is a legal user
                    User oriUser = databaseUtil.getUserById(id);
                    if (oriUser.getWeight()==weight && oriUser.getWeight()==weight) {
                        Toast.makeText(this, "数据未修改", Toast.LENGTH_LONG).show();
                    }
                    else {
                        oriUser.setWeight(weight);
                        oriUser.setHeight(height);
                        databaseUtil.changeUserInfo(oriUser);
                        Toast.makeText(this, "身高体重修改成功", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this, "用户名输入错误，请检查后重新输入", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "输入错误，请检查后重新输入", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean checkInput(String account, double height, double weight) {
        return (!(account.equals("")// haven't input account or password
                || height < 0 || height > 250 || weight < 0 || weight > 300));
    }
}
