package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Analyse extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyse);


        int id = getIntent().getIntExtra("id",-1);
        double carbSuggest=-1, fatSuggest=-1, proteinSuggest=-1, dfSuggest=-1, kCalSuggest=-1;
        DatabaseUtil databaseUtil = new DatabaseUtil();

//        Calendar calendar = Calendar.getInstance();
//        String date = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);

        String date="2020-1-1";
        ReturnItem item =databaseUtil.getThisDayFoodThing(id, date);


        User user = databaseUtil.getUserById(id);
        if (user != null) {
//            double BMR = 66 + 13.7 * user.getWeight() + 5 * user.getHeight() - 200;
            proteinSuggest = 10 * user.getWeight();
            kCalSuggest=49.5*user.getWeight();
            carbSuggest=0.56*kCalSuggest;
            fatSuggest=kCalSuggest*0.02;
            dfSuggest=34;
        }



        TextView textViewCarbAct = findViewById(R.id.CHO_intake_act);
        textViewCarbAct.setText(""+item.getCarb());
        TextView textViewCarbSug = findViewById(R.id.CHO_intake_suggest);
        textViewCarbSug.setText(""+carbSuggest);

        TextView textViewProteinAct = findViewById(R.id.PRO_intake_act);
        textViewProteinAct.setText(""+item.getProtein());
        TextView textViewProteinSug = findViewById(R.id.PRO_intake_suggest);
        textViewProteinSug.setText(""+proteinSuggest);

        TextView textViewFatAct = findViewById(R.id.FAT_intake_act);
        textViewFatAct.setText(""+item.getFat());
        TextView textViewFatSug = findViewById(R.id.FAT_intake_suggest);
        textViewFatSug.setText(""+fatSuggest);

        TextView textViewDFAct = findViewById(R.id.DF_intake_act);
        textViewDFAct.setText(""+item.getDF());
        TextView textViewDFSug = findViewById(R.id.DF_intake_suggest);
        textViewDFSug.setText(""+dfSuggest);

        TextView textViewKcalAct = findViewById(R.id.Kcal_intake_act);
        textViewKcalAct.setText(""+item.getKcal());
        TextView textViewKcalSug = findViewById(R.id.Kcal_intake_suggest);
        textViewKcalSug.setText(""+kCalSuggest);
    }

}
