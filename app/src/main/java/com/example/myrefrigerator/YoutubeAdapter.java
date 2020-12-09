package com.example.myrefrigerator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class YoutubeAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<SearchData> youtubeList;

    public YoutubeAdapter(Context context, ArrayList<SearchData> data) {
        mContext = context;
        youtubeList = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return youtubeList.size();
    }

    @Override
    public Object getItem(int position) {
        return youtubeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.youtube_list_layout,parent,false);
        }
        ImageView thumbnail = convertView.findViewById(R.id.img);
        TextView textView_date = convertView.findViewById(R.id.date);
        TextView textView_title = convertView.findViewById(R.id.title);

        SearchData searchData = youtubeList.get(position);
        String url = searchData.getUrl();
        String sUrl = url.substring(0,url.lastIndexOf("/")+1);
        String eUrl = url.substring(url.lastIndexOf("/")+1,url.length());
        try {
            eUrl = URLEncoder.encode(eUrl, "EUC-KR").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String new_url = sUrl + eUrl;

        Glide.with(context).load(new_url).into(thumbnail);
        textView_date.setText(searchData.getPublishedAt());
        textView_title.setText(searchData.getTitle());

        return convertView;
    }
}
