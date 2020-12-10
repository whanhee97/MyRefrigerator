package com.example.myrefrigerator;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpService extends Service {
    private static String LOG_TAG = "Http Service";
    String result = "";
    String keyword = "";
    IBinder iBinder = new MyBinder();
    Thread thread = null;

    class MyBinder extends Binder {
        HttpService getService(){
            return HttpService.this;
        }
    }

    public HttpService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        keyword = intent.getStringExtra("keyword");
        Log.i(LOG_TAG, "keyword : "+keyword);
        Log.i(LOG_TAG, "Http Service onBind...");
        final String serverKey = "AIzaSyDPxNj4AxbQEcV1uuxi5oZz6thJ1wvsi8Y";
        final String requestUrl = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" +
                keyword +
                "&key=" +
                serverKey +
                "&maxResults=30";

        Log.i(LOG_TAG, "Http Service onCreate...");

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader reader = null;

                try {
                    URL url = new URL(requestUrl);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    if (conn != null) {
                        Log.i(LOG_TAG, "Thread run 메소드..." + conn.toString());
                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                        conn.setRequestProperty("x-waple-authorization", serverKey);
                        int resCode = conn.getResponseCode();
                        Log.i(LOG_TAG, "Thread run 메소드...응답코드 :::: " + String.valueOf(resCode));
                        if (resCode == HttpURLConnection.HTTP_OK) {
                            Log.i(LOG_TAG, "Thread run 메소드 HTTP OK..." + conn.toString());
                            InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                            reader = new BufferedReader(tmp);
                            StringBuffer buffer = new StringBuffer();
                            String inputLine = null;
                            while ((inputLine = reader.readLine()) != null) {
                                buffer.append(inputLine);
                            }
                            result = buffer.toString();
                            reader.close();
                            conn.disconnect();
                        }
                    }
                } catch(Exception ex) {
                    Log.e("Http Service", "Exception in processing response.", ex);
                    ex.printStackTrace();
                }
                //stopSelf(); //태스크가 끝나면 서비스 종료
            }
        });
        thread.start();
        Log.i(LOG_TAG, "Thread Start OK...===> " + result);

        return iBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(LOG_TAG, "리바운드!!");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(LOG_TAG, "Http Service onUnbind...");
        stopSelf();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(thread != null){
            thread.interrupt();
        }
        Log.i(LOG_TAG, "Http Service onDestroy...");
    }

    public String getResult() {
        return result;
    }
}
