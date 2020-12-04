package com.example.myrefrigerator;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TodoAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<TodoItem> memoList;

    public TodoAdapter(Context context, ArrayList<TodoItem> data) {
        mContext = context;
        memoList = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return memoList.size();
    }

    @Override
    public Object getItem(int position) {
        return memoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.todo_list_layout,parent,false);
        }

        //View view = mLayoutInflater.inflate(R.layout.todo_list_layout,null);
        CheckBox checkBox = convertView.findViewById(R.id.todo_checkbox);
        TextView textView = convertView.findViewById(R.id.memo_content);

        TodoItem todoItem = memoList.get(position);
        textView.setText(todoItem.getMemo());
        if(todoItem.getChecked() == 1){
            checkBox.setChecked(true);
            textView.setPaintFlags(textView.getPaintFlags()|Paint.STRIKE_THRU_TEXT_FLAG);
            textView.setTextColor(Color.parseColor("#ff0000"));
        }else{
            textView.setPaintFlags(textView.getPaintFlags()&~Paint.STRIKE_THRU_TEXT_FLAG);
            textView.setTextColor(Color.parseColor("#000000"));
        }


        return convertView;
    }
}
