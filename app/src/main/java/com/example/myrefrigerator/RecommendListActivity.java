package com.example.myrefrigerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class RecommendListActivity extends AppCompatActivity {
    String name;
    ArrayList<SearchData> sdata;
    ListView listView;

    private static String LOG_TAG = "RecommendActivity";
    boolean isBoundService = false;
    Intent serviceIntent;
    HttpService mService;
    String keyword;
    String serverKey;
    String resultText = "";

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            HttpService.MyBinder myBinder = (HttpService.MyBinder) service;
            mService = myBinder.getService();
            isBoundService = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBoundService = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_list);

        listView = (ListView)findViewById(R.id.youtubelistview);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        keyword = name + " 요리";
        serverKey = "AIzaSyDPxNj4AxbQEcV1uuxi5oZz6thJ1wvsi8Y";
        sdata = new ArrayList<>();

        Button recommend = (Button) findViewById(R.id.recommend);


    }

    @Override
    protected void onResume() {
        super.onResume();
        serviceIntent = new Intent(this, HttpService.class);
        serviceIntent.putExtra("keyword",keyword);
        startService(serviceIntent);
        bindService(serviceIntent, mServiceConnection,0);
        isBoundService = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBoundService) {
            unbindService(mServiceConnection);
            isBoundService = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBoundService) {
            unbindService(mServiceConnection);
            isBoundService = false;
        }
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.recommend:
                if (isBoundService) {
                    resultText = mService.getResult();
                    Log.i(LOG_TAG, mService.getResult());
                    try {
                        parseJSONData(resultText);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                Log.i(LOG_TAG,Integer.toString(sdata.size()));

                YoutubeAdapter youtubeAdapter = new YoutubeAdapter(this,sdata);
                if(sdata !=null) {
                    listView.setAdapter(youtubeAdapter);
                }

                break;
            case R.id.refresh:
                if (isBoundService) {
                    unbindService(mServiceConnection);
                    isBoundService = false;
                    Log.i(LOG_TAG, "Http Service Stop...");
                }

                break;

        }
    }

    private void parseJSONData(String jsonString) throws Exception{
        sdata.clear();

        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray contacts = jsonObject.getJSONArray("items");

        for (int i = 0; i < contacts.length(); i++) {
            JSONObject c = contacts.getJSONObject(i);
            String kind =  c.getJSONObject("id").getString("kind"); // 종류를 체크하여 playlist도 저장
            String vodid = "";

            if(kind.equals("youtube#video")){
                vodid = c.getJSONObject("id").getString("videoId"); // 유튜브
                // 동영상
                // 아이디
                // 값입니다.
                // 재생시
                // 필요합니다.
            }else{
                vodid = c.getJSONObject("id").getString("channelId"); // 유튜브
            }

            String title = c.getJSONObject("snippet").getString("title"); //유튜브 제목을 받아옵니다
            String changString = "";
            try {
                changString = new String(title.getBytes("8859_1"), "utf-8"); //한글이 깨져서 인코딩 해주었습니다
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            String date = c.getJSONObject("snippet").getString("publishedAt") //등록날짜
                    .substring(0, 10);
            String imgUrl = c.getJSONObject("snippet").getJSONObject("thumbnails")
                    .getJSONObject("default").getString("url");  //썸내일 이미지 URL값

            sdata.add(new SearchData(vodid, changString, imgUrl, date));
        }

    }

}


//class Task extends AsyncTask<String, Void, String>{
//
//    String serverKey = "AIzaSyDPxNj4AxbQEcV1uuxi5oZz6thJ1wvsi8Y";
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        Log.i("test:","실행되고있어");
//    }
//
//    @Override
//    protected String doInBackground(String... strings) {
//        BufferedReader reader = null;
//        String keyword = strings[0];
//        String result = "";
//        try {
//            String requestUrl = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" +
//                    keyword +
//                    "&key=" +
//                    serverKey +
//                    "&maxResults=20";
//            URL url = new URL(requestUrl);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
//            conn.setRequestProperty("x-waple-authorization", serverKey);
//            if (conn.getResponseCode() == conn.HTTP_OK) {
//                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
//                reader = new BufferedReader(tmp);
//                StringBuffer buffer = new StringBuffer();
//                String inputLine = null;
//                while ((inputLine = reader.readLine()) != null) {
//                    buffer.append(inputLine);
//                }
//                result = buffer.toString();
//                Log.i("result:", result);
//                reader.close();
//            } else {
//                Log.i("통신결과:", conn.getResponseCode() + "에러");
//            }
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }
//}
