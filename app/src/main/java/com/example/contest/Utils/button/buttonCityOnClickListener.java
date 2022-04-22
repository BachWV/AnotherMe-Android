package com.example.contest.Utils.button;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contest.ChooseCityActivity;
import com.example.contest.Common.CommonVar;


public class buttonCityOnClickListener implements View.OnClickListener{
    boolean hasPutdown;
    int i;
    Button btn;
    TextView tv;
    Activity ac;

    public buttonCityOnClickListener(int a, Button button, TextView textview, Activity activity){
        ac=activity;
        i=a;
        btn=button;
        tv=textview;
        hasPutdown=false;
    }
    @Override
    public void onClick(View view) {
        btn.setTextColor(Color.WHITE);
        if(hasPutdown==false&&ChooseCityActivity.leastnum>0) {
            hasPutdown=true;
            ChooseCityActivity.leastnum--;

            CommonVar.chosenName.add(CommonVar.cityName.get(i));
            if (ChooseCityActivity.leastnum == 0) {
                StringBuffer sb = new StringBuffer();
                for (int j = 0; j < CommonVar.chosenName.size(); j++) {
                    sb.append(CommonVar.chosenName.get(j) + "\n");
                }
                Toast.makeText(ac, sb.toString(), Toast.LENGTH_LONG).show();


            }
            tv.setText("还需选择"+ ChooseCityActivity.leastnum +"个城市");
            btn.setBackgroundColor(Color.parseColor("#0084ff"));
        }
    }
}
