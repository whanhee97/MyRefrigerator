package com.example.myrefrigerator;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<Food> foodList;

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
    public View getView(int position, View convertView, ViewGroup parent) {
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
        foodShelfLife.setText("유통기한: " + foodList.get(position).getShelf_life());

        return view;
    }
}
