package com.example.contest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.contest.Common.CommonVar;
import com.example.contest.Utils.algorithm.geography.Point;
import com.example.contest.Utils.file.WriteToFile;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ChooseFileActivity extends AppCompatActivity {

   private TextView tv;
   private Button []bts=new Button[100];
   private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_file);
        tv = findViewById(R.id.tv_file);
        File[] files =getApplicationContext().getFilesDir().listFiles();
        for(File file:files){
            Log.e("filename",file.getName());
        }
        linearLayout=findViewById(R.id.choosefile_line);
        LinearLayout.LayoutParams btParams = new  LinearLayout.LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        for(int i=0;i<files.length;i++){
            bts[i]=new Button(getApplicationContext());
            bts[i].setId(3000+ i);
            bts[i].setText(files[i].getName());
            File file=files[i];
            // btParams.width=linearLayout.getWidth()/;
            bts[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FileInputStream in=null;

                    BufferedReader reader=null;
                    try {
                        in = new FileInputStream(file);
                        reader=new BufferedReader(new InputStreamReader(in));
                        CommonVar.trajectory=WriteToFile.readTrajectFile(reader);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Intent intent=new Intent(getApplicationContext(), AMapActivity.class);//打开新activity：
                    startActivity(intent);
                }
            });
            btParams.width=500;
            btParams.height=140;
           bts[i].setTextColor(Color.WHITE);
            bts[i].setWidth(10);
            bts[i].setLayoutParams(btParams);
            linearLayout.addView(bts[i]);
        }


    }
}