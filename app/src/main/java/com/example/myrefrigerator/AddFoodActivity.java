package com.example.myrefrigerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AddFoodActivity extends AppCompatActivity {

    private EditText editName,editCount,editShelf_life;
    private ImageView iconImage;
    String icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        Intent intent = getIntent();
        icon = intent.getStringExtra("icon");

        editName = (EditText) findViewById(R.id.edit_name);
        editCount = (EditText) findViewById(R.id.edit_count);
        editShelf_life = (EditText) findViewById(R.id.edit_shelf_life);
        iconImage = (ImageView) findViewById(R.id.imageView);
        if(icon != null){
            switch (icon){
                case "과일":
                    iconImage.setImageResource(R.drawable.fruit);
                    break;
                case "채소":
                    iconImage.setImageResource(R.drawable.vegetable);
                    break;
                case "육류":
                    iconImage.setImageResource(R.drawable.meat);
                    break;
                case "생선":
                    iconImage.setImageResource(R.drawable.seafood);
                    break;
                case "음료":
                    iconImage.setImageResource(R.drawable.drink);
                    break;
                case "기타":
                    iconImage.setImageResource(R.drawable.food);
                    break;
            }
        }

    }

    public void mOnClick(View v){
        switch (v.getId()){
            case R.id.button:
                Intent intent = new Intent(AddFoodActivity.this,MainActivity.class);

                String name = editName.getText().toString();
                int count = Integer.parseInt(editCount.getText().toString());
                String shelf_life = editShelf_life.getText().toString();

                DBHandler handler = new DBHandler(this);
                handler.insert(name,count,shelf_life,icon);

                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent2 = new Intent(AddFoodActivity.this,MainActivity.class);
                startActivity(intent2);
                break;

            case R.id.select_icon:
                Intent intent3 = new Intent(AddFoodActivity.this,SelectIcon.class);
                startActivity(intent3);
                break;
        }
    }
}