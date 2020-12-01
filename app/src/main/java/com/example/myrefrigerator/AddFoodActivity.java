package com.example.myrefrigerator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class AddFoodActivity extends AppCompatActivity {

    private EditText editName,editCount;
    private TextView editShelf_life;
    private ImageView iconImage;
    String icon;
    String shelf_life = "2020-01-01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        Intent intent = getIntent();
        icon = intent.getStringExtra("icon");

        editName = (EditText) findViewById(R.id.edit_name);
        editCount = (EditText) findViewById(R.id.edit_count);
        editShelf_life = (TextView) findViewById(R.id.edit_shelf_life);
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
                String edit_count = editCount.getText().toString();
                int count = 0;
                if(edit_count != ""){
                    count = Integer.parseInt(edit_count);
                }

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
            case R.id.btn_date:
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(this,mDateSetListener, year,month,day).show();
                break;
        }
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            if(dayOfMonth < 10 && month < 10){
                shelf_life = String.format("%d-0%d-0%d",year,month+1,dayOfMonth);
            }else if(dayOfMonth < 10){
                shelf_life = String.format("%d-%d-0%d",year,month+1,dayOfMonth);
            }else if(month < 10){
                shelf_life = String.format("%d-0%d-%d",year,month+1,dayOfMonth);
            }else {
                shelf_life = String.format("%d-%d-%d",year,month+1,dayOfMonth);
            }
            editShelf_life.setText(shelf_life);
        }
    };
}