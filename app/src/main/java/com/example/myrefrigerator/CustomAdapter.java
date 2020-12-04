package com.example.myrefrigerator;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<Food> foodList;
    LocalDate currentDate = LocalDate.now();
    LocalDate temp; // 유통기한을 String 에서 변환한 객체
    long leftdays = 0;

    public CustomAdapter(Context context, ArrayList<Food> data) {
        mContext = context;
        foodList = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.food_list_layout,null);
        String icon = foodList.get(position).getIcon();
        if(icon == null){
            icon = "기타";
        }
        ImageView imageView = (ImageView)view.findViewById(R.id.foodicon);
        TextView foodName = (TextView)view.findViewById(R.id.foodname);
        TextView foodCount = (TextView)view.findViewById(R.id.foodcount);
        TextView foodShelfLife = (TextView)view.findViewById(R.id.foodshelflife);

        switch (icon){
            case "과일":
                imageView.setImageResource(R.drawable.fruit);
                break;
            case "채소":
                imageView.setImageResource(R.drawable.vegetable);
                break;
            case "육류":
                imageView.setImageResource(R.drawable.meat);
                break;
            case "생선":
                imageView.setImageResource(R.drawable.seafood);
                break;
            case "음료":
                imageView.setImageResource(R.drawable.drink);
                break;
            case "기타":
                imageView.setImageResource(R.drawable.food);
                break;
        }
        foodName.setText(foodList.get(position).getName());
        String count = Integer.toString(foodList.get(position).getCount());
        foodCount.setText("수량: " + count);

        temp = LocalDate.parse(foodList.get(position).getShelf_life());
        leftdays = ChronoUnit.DAYS.between(currentDate,temp); // 오늘부터 유통기한까지 날짜차이 계산
        if(leftdays < 0){ // 날짜가 0일 이하면 빨간색으로 세팅
            foodShelfLife.setTextColor(Color.parseColor("#ff0000"));
        }
        else if(leftdays <= 3 && leftdays >= 0){ // 유통기한 3일 이하면 주황색
            foodShelfLife.setTextColor(Color.parseColor("#ff8000"));
        }
        else{ //아니면 초록색
            foodShelfLife.setTextColor(Color.parseColor("#009900"));
        }
        foodShelfLife.setText("유통기한: " + foodList.get(position).getShelf_life());
//
        return view;
    }
}
