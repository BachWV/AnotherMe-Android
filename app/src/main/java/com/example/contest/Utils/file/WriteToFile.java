package com.example.contest.Utils.file;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.contest.Common.CommonVar;
import com.example.contest.Utils.algorithm.geography.Point;
import com.example.contest.Utils.algorithm.stayPoint.StayPoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.OpenOption;
import java.util.ArrayList;

public class WriteToFile {
    public static  void showRealPoints(FileInputStream in){

        BufferedReader reader=null;
        StringBuilder content=new StringBuilder();

        try{

            reader=new BufferedReader(new InputStreamReader(in));
            String line="";
            while((line=reader.readLine())!=null){
                content.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        JSONObject js;
        String name;
        String type;
        try {
            js = new JSONObject(content.toString());
            name = js.getString("pid");
            type = js.getString("type");
            JSONArray pointsarray = js.getJSONArray("AllRealpoints");
            int lenofpointsarray = pointsarray.length();
            ArrayList<Point> points = new ArrayList<>();
            for (int i = 0; i < lenofpointsarray; i++) {
                double longitude = pointsarray.getJSONObject(i).getDouble("longitude");
                double lan = pointsarray.getJSONObject(i).getDouble("latitude");

                points.add(new Point(longitude, lan));
                Log.d("out points", "+" + longitude + " " + lan);


            }
            if (CommonVar.points == null || CommonVar.points.size() == 0)
                CommonVar.points.addAll(points);


            JSONObject poi = js.getJSONObject("stay_point");
            int numarray = poi.getInt("spnum");
            JSONArray array = poi.getJSONArray("spvalue");


            ArrayList<StayPoint> listsp=new ArrayList<>();

            for(int i=0;i<numarray;i++){
                double longitude=array.getJSONObject(i).getDouble("longitude");
                double lan=array.getJSONObject(i).getDouble("latitude");
                long arrive=array.getJSONObject(i).getLong("arrive");
                long leave=array.getJSONObject(i).getLong("leave");
                StayPoint stayPoint=new StayPoint(longitude,lan,arrive,leave);

                listsp.add(stayPoint);
                Log.d("out", longitude + " " + lan);
            }
            if (CommonVar.sp == null || CommonVar.sp.size() == 0)
                CommonVar.sp.addAll(listsp);

            Log.d("jsobject", name);

            Log.d("toast down", "00");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Log.d("show data",name);


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
            //js.put("city","beijing");
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
            if(writer!=null) writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }



}
