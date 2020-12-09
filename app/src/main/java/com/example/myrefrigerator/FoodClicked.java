package com.example.myrefrigerator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class FoodClicked extends AppCompatActivity {
    private ImageView imageView;
    private TextView nameText;
    private TextView countText;
    private TextView shelfLifeText;

    int id;
    String icon;
    String name;
    int count;
    String shelf_life;
    ArrayList<SearchData> sdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_clicked);

        Intent intent = getIntent();
        Food food = intent.getParcelableExtra("food");

        id= food.getId();
        icon = food.getIcon();
        name = food.getName();
        count = food.getCount();
        shelf_life = food.getShelf_life();

        imageView = (ImageView)findViewById(R.id.detail_icon);
        nameText = (TextView)findViewById(R.id.detail_name);
        countText = (TextView)findViewById(R.id.detail_count);
        shelfLifeText = (TextView)findViewById(R.id.detail_shelf_life);
        if(icon == null){
            icon = "기타";
        }

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
        nameText.setText(name);
        countText.setText("수량: "+Integer.toString(count));
        shelfLifeText.setText("유통기한: "+shelf_life);
    }

    public void mOnClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.btn_update:
                intent = new Intent(FoodClicked.this,ChangeCount.class);
                intent.putExtra("id",id);
                intent.putExtra("count",count);
                startActivity(intent);
                break;
            case R.id.btn_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final DBHandler handler = new DBHandler(this);
                builder.setTitle("경고");
                builder.setMessage("삭제하면 복구 불가능 합니다. 삭제하시겠습니까?");
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(FoodClicked.this,MainActivity.class);
                        handler.removeItem(id);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create().show();
                break;

            case R.id.btn_recommend:
                intent = new Intent(getApplicationContext(),RecommendListActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
                break;
        }
    }

}