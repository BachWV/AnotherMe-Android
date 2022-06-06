package com.example.contest.Utils.button;



import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

//重写了 OnClickListener，用于动态添加button时传入参数，监听点击事件
public class mOnClickListener implements View.OnClickListener{
    int i;
    Button btn[];
    WebView mWebView;


    public mOnClickListener(int a, Button button[],WebView webView){
        i=a;
        btn=button;
        mWebView=webView;

    }
    @Override
    public void onClick(View view) {
        //全新的方法在public String generateReplaceUrl(int i)
      //  DashboardFragment.urlload(replaceurl);
        //mWebView.loadUrl("http://baidu.com");
    }
}

