package com.example.contest.Utils.file;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.contest.Common.CommonVar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.OpenOption;

public class WriteToFile {
    public static  void showRealPoints(){


    }
    public static void  saveRealPoints(FileOutputStream out){

        BufferedWriter writer=null;
        StringBuilder sb=new StringBuilder();
        JSONObject js=new JSONObject();
        JSONObject sp=new JSONObject();
        JSONArray spArray= new JSONArray();
        JSONArray pointsArray=new JSONArray();

        try {
            js.put("pid","profile_real_001");
            js.put("type","real");
            js.put("city","beijing");
            for(int i = 0; i< CommonVar.points.size(); i++){
                JSONObject tmp=new JSONObject();
                tmp.put("longitude",CommonVar.points.get(i).longitude);
                tmp.put("latitude",CommonVar.points.get(i).latitude);
                pointsArray.put(tmp);
            }
            for(int i=0;i<CommonVar.sp.size();i++){
                JSONObject tmp=new JSONObject();
                tmp.put("longitude",CommonVar.sp.get(i).longitude);
                tmp.put("latitude",CommonVar.sp.get(i).latitude);
                tmp.put("arrive",CommonVar.sp.get(i).arriveTime);
                tmp.put("leave",CommonVar.sp.get(i).leaveTime);

                spArray.put(tmp);
            }
            int spnum=CommonVar.sp.size();
            sp.put("spnum",spnum);

            sp.put("spvalue",spArray);
            js.put("stay_point",sp);
            js.put("AllRealpoints",pointsArray);
            Log.e("js",js.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try{

            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(js.toString());

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(writer!=null) writer.close();

            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }



}
