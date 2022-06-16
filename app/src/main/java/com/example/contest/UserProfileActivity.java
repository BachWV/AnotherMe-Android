package com.example.contest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.contest.Common.CommonVar;
import com.example.contest.Utils.algorithm.TrajectorySimulator.TrajectorySimulator;
import com.example.contest.Utils.algorithm.geography.Point;
import com.example.contest.Utils.algorithm.stayPoint.StayPoint;
import com.example.contest.Utils.file.WriteToFile;
import com.example.contest.Utils.tools.http.MappingTools;
import com.example.contest.ui.HomeFragment;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Button calcu = (Button) findViewById(R.id.calcuvirtual);
        Button showdata = (Button) findViewById(R.id.showdata);
        Button genvitual = (Button) findViewById(R.id.getvirtual);
        Button getType = (Button) findViewById(R.id.getType);
        Button save_virtual = (Button) findViewById(R.id.save_virtual);
        Button virtual_tragectry = (Button) findViewById(R.id.virtual_traject);
        getSupportActionBar().setTitle(R.string.update_virtual_user_profile);
        virtual_tragectry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Point> pois = new ArrayList<Point>();
                for (StayPoint stayPoint : MappingTools.KvirtualmindisPoint.get(0)) {
                    pois.add(Point.phrasePoint(stayPoint));
                }
                ArrayList<Point> points=CommonVar.points;

                TrajectorySimulator simulator = new TrajectorySimulator(points, pois,60,2);
                CommonVar.trajectory= simulator.trajectorySimulate();
                Log.e("pipline", "end");
                Toast.makeText(getApplicationContext(),"成功生成轨迹",Toast.LENGTH_SHORT).show();


            }
        });
        save_virtual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //虚拟点需要保存

                FileOutputStream out = null;
                try {
                    out = getApplicationContext().openFileOutput("vup", Context.MODE_PRIVATE);
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }
                WriteToFile.saveKVirtualPoints(out);

            }
        });
        calcu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int index = 0; index< MappingTools.KvirtualCity_has_allpoint.size(); index++) {
                    MappingTools.caltruepoint(getApplicationContext(),CommonVar.spwType, MappingTools.KvirtualCity_has_allpoint.get(index));
                }
                Toast.makeText(getApplicationContext(),"成功计算虚拟点",Toast.LENGTH_SHORT).show();

            }
        });
        getType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CommonVar.spwType.clear();
                if(CommonVar.sp.size()==0){
                    HomeFragment.getTragetoryFile(getApplicationContext());
                }
                for(int i=0;i<CommonVar.sp.size();i++){
                    StayPoint sp1=CommonVar.sp.get(i);
                    // StayPointwithType spwt=new StayPointwithType(sp1);
                    try {
                        CommonVar.spwType.add(MappingTools.getTypeSynchronized(sp1,i));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(getApplicationContext(),"成功获得兴趣点类型",Toast.LENGTH_SHORT).show();
            }
        });
        genvitual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<CommonVar.cityNameWithAnchorPoints.size();i++) {
                    try {
                        MappingTools.usetypegetPoint(getApplicationContext(), CommonVar.spwType,CommonVar.cityNameWithAnchorPoints.get(i));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(getApplicationContext(),"成功获得虚拟点",Toast.LENGTH_SHORT).show();

            }
        });

        showdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //展示虚拟用户画像
                Intent intent=new Intent(getApplicationContext(), ChooseKVirtualActivity.class);
                startActivity(intent);
              }
        });


    }
}