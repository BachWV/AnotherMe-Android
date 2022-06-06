package com.example.contest.Utils.webview;

import android.util.Log;


import com.example.contest.Common.CommonVar;

import java.util.Random;

public class UrlTools {

    public static String buildWindyUrl(double longti,double lanti){
        double splong= ((int)(longti*100))/100.0;
        double splan= ((int)(lanti*100))/100.0;
        String url="https://www.windy.com/?"+splan+","+splong;
        return url;
    }
    public static String replaceCtrip(String url,double longti,double lanti){
//        double splong= ((int)(longti*10000))/10000.0;
//        double splan= ((int)(lanti*10000))/10000.0;

            url = url.replaceAll("&ulocation=.*&ucity=", "&ulocation=" + lanti + "_" + longti + "&ucity=");
            url = url.replaceAll("&ulat=.*&ulon", "&ulat=" + lanti + "&ulon");
            url = url.replaceAll("&ulon=.*&geo", "&ulon=" + longti + "&geo");
        return url;
    }


    public static String buildUrl(double longti, double lanti){
        double splong= ((int)(longti*10000))/10000.0;
        double splan= ((int)(lanti*10000))/10000.0;
        String url="";
        if(CommonVar.lbs_id==0) {


        Random r=new Random();

        url="http://www.caiyunapp.com/wx_share/?"+r.nextInt(100)+"?#"+splong+","+splan+"/";
        }else if(CommonVar.lbs_id==1){
            url="https://www.windy.com/"+splan+"/"+splong;
        }else if(CommonVar.lbs_id==2){
            url="https://www.hotwire.com/hotels/search?destination="+splan+"%2C"+splong+"&rooms=1&adults=2&children=0";

        }else if(CommonVar.lbs_id==3){
            url="https://m.ctrip.com/webapp/hotels/list?atime=20220525&days=1&ulat="+splan+"&ulon="+splong+"&ulocation="+splan+"_"+longti;
            
        }

        return url;
    }
    public static String replaceUrl(double longti,double lanti){
        StringBuilder url=new StringBuilder("https://www.booking.com/searchresults.html?ss=Chengdu&group_adults=2&group_children=0&no_rooms=1&sb_travel_purpose=leisure&ssne=Chengdu&ssne_untouched=Chengdu&label=gen173bo-1DCAEoggI46AdIM1gDaDGIAQKYATG4AQnIARHYAQPoAQH4AQOIAgGYAgKoAgO4AuLksJIGwAIB0gIkOTlmMjEwNDktOWRjMS00YTY1LWFhZDUtOTg2MmMzNzYyZTRl2AIE4AIB&sid=87daccf59882f262d007baadae2bda97&aid=304142&lang=en-us&sb=1&src_elem=sb&src=searchresults&dest_id=-1900349&dest_type=city&checkin=2022-04-05&checkout=2022-04-06&activeTab=search&latitude=");
        //30.75859&longitude=103.4332");
        Random r=new Random();
        lanti=30+r.nextDouble();
        longti=103+r.nextDouble();
        url.append(lanti+"&longitude="+longti);
        Log.d("url replace",url.toString());
        return url.toString();
    }

}
