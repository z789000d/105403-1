package com.example.myapplication1050403;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by 104204-1 on 2016/3/8.
 */
public class GetServerMessage {

    public String stringQuery(String url){
        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet method = new HttpGet(url);
            HttpResponse response = httpclient.execute(method);
            HttpEntity entity = response.getEntity();
            if(entity != null){
                return EntityUtils.toString(entity);
            }
            else{
                return "No string.";
            }
        }
        catch(Exception e){
            Log.d("network error", e.toString());
            return "Network problem";
        }
    }
}
