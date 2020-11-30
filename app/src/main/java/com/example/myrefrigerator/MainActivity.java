package com.example.myrefrigerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHandler dbHandler = null;
    ListView listView;
    ArrayList<Food> foodList;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler = new DBHandler(this);
        foodList = dbHandler.getItems();

        listView = (ListView)findViewById(R.id.listView);
        final CustomAdapter customAdapter = new CustomAdapter(this,foodList);

        if(foodList !=null) {
            listView.setAdapter(customAdapter);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),FoodClicked.class);
                intent.putExtra("food",foodList.get(position));
                startActivity(intent);
            }
        });

    }
    public void mOnClick(View v){
        switch (v.getId()){
            case R.id.btnAdd:
                Intent intent = new Intent(MainActivity.this,AddFoodActivity.class);
                startActivity(intent);
                break;
        }
    }
}