package com.example.myrefrigerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHandler dbHandler = null;
    ListView listView;
    ArrayList<Food> foodList;
    ArrayList<Food> foodList_timeOver; // 유통기한 지난 푸드리스트
    ArrayList<Food> foodList_left3days; // 유통기한 3일 이하 푸드리스트
    LocalDate currentDate;
    int viewCMD = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DBHandler(this);
        foodList = dbHandler.getItems();

        foodList_timeOver = new ArrayList<>();
        foodList_left3days = new ArrayList<>();

        currentDate = LocalDate.now();
        LocalDate temp; // 유통기한을 String 에서 변환한 객체
        long leftdays = 0;
        if(foodList != null){
            for(Food f : foodList){
                temp = LocalDate.parse(f.getShelf_life());
                leftdays = ChronoUnit.DAYS.between(currentDate,temp); // 오늘부터 유통기한까지 날짜차이 계산
                if(leftdays <= 3 && leftdays >= 0){ // 날짜가 3일 이하면 리스트에 저장
                    foodList_left3days.add(f);
                }
                if(leftdays < 0){ // 날짜가 0일 미만이면 리스트에 저장
                    foodList_timeOver.add(f);
                }
            }
        }

        listView = (ListView)findViewById(R.id.listView);
        listView.setItemsCanFocus(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final CustomAdapter customAdapter;
        switch (viewCMD){
            case 0:
                customAdapter = new CustomAdapter(this,foodList);

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
                break;

            case 1:
                customAdapter = new CustomAdapter(this,foodList_timeOver);
                if(foodList_timeOver !=null) {
                    listView.setAdapter(customAdapter);
                }
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getApplicationContext(),FoodClicked.class);
                        intent.putExtra("food",foodList_timeOver.get(position));
                        startActivity(intent);
                    }
                });
                break;

            case 2:
                customAdapter = new CustomAdapter(this,foodList_left3days);
                if(foodList_left3days !=null) {
                    listView.setAdapter(customAdapter);
                }
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getApplicationContext(),FoodClicked.class);
                        intent.putExtra("food",foodList_left3days.get(position));
                        startActivity(intent);
                    }
                });
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.gotomemo:
                Intent intent = new Intent(this,TodoActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void mOnClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.btnAdd:
                intent = new Intent(MainActivity.this,AddFoodActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_all:
                viewCMD = 0;
                onResume();
                break;
            case R.id.btn_over:
                viewCMD = 1;
                onResume();
                break;
            case R.id.btn_left3:
                viewCMD = 2;
                onResume();
                break;
        }
    }


}