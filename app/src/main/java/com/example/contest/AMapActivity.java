package com.example.contest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;


import android.app.Activity;
import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.PolylineOptions;
import com.example.contest.Common.CommonVar;
import com.example.contest.Utils.algorithm.TrajectorySimulator.TrajectorySimulator;
import com.example.contest.Utils.algorithm.geography.Point;

import java.util.ArrayList;
import java.util.List;

public class AMapActivity extends AppCompatActivity {
        private MapView mMapView;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_amap);

             mMapView = (MapView) findViewById(R.id.map);
            mMapView.onCreate(savedInstanceState);// 此方法必须重写
            AMap aMap = mMapView.getMap();

            aMap.setTrafficEnabled(true);// 显示实时交通状况
            //地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT
            aMap.setMapType(AMap.MAP_TYPE_BUS);// 卫星地图模式
            aMap.setMapTextZIndex(1);

            MyLocationStyle myLocationStyle;
            myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
            myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。

            aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
         //   aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
            List<LatLng> latLngs = new ArrayList<LatLng>();

//            for(Point p:MainActivity.trajectory_test){
//                latLngs.add(new LatLng(p.latitude,p.longitude));
//            }

            if(CommonVar.trajectory!=null&&CommonVar.trajectory.size()!=0){
                for(Point p:CommonVar.trajectory){
                    latLngs.add(new LatLng(p.latitude,p.longitude));
                }
            }
            aMap.addPolyline(new PolylineOptions().
                    addAll(latLngs).width(10).color(Color.argb(255, 1, 1, 1)));
        }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

}