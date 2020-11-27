package com.example.myrefrigerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddFoodActivity extends AppCompatActivity {

    private EditText editName,editCount,editShelf_life;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        editName = (EditText) findViewById(R.id.edit_name);
        editCount = (EditText) findViewById(R.id.edit_count);
        editShelf_life = (EditText) findViewById(R.id.edit_shelf_life);
    }

    public void mOnClick(View v){
        switch (v.getId()){
            case R.id.button:
                Intent intent = new Intent(AddFoodActivity.this,MainActivity.class);

                String name = editName.getText().toString();
                int count = Integer.parseInt(editCount.getText().toString());
                String shelf_life = editShelf_life.getText().toString();

                DBHandler handler = new DBHandler(this);
                handler.insert(name,count,shelf_life);

                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent2 = new Intent(AddFoodActivity.this,MainActivity.class);
                startActivity(intent2);
                break;
        }
    }
}