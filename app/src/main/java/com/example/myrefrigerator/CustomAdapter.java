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

        ImageView imageView = (ImageView)view.findViewById(R.id.foodicon);
        TextView foodName = (TextView)view.findViewById(R.id.foodname);
        TextView foodCount = (TextView)view.findViewById(R.id.foodcount);
        TextView foodShelfLife = (TextView)view.findViewById(R.id.foodshelflife);

        //imageView.setImageResource(foodList.get(position).getImage());
        foodName.setText(foodList.get(position).getName());
        String count = Integer.toString(foodList.get(position).getCount());
        foodCount.setText(count);
        foodShelfLife.setText(foodList.get(position).getShelf_life());

        return view;
    }
}
