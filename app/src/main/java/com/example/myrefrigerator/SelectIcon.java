package com.example.myrefrigerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SelectIcon extends AppCompatActivity {
    private RadioGroup rg;
    private RadioButton selectedRadio;
    String icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_icon);
        rg = (RadioGroup)findViewById(R.id.rg_icon);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedRadio = (RadioButton) group.findViewById(checkedId);
                if(selectedRadio != null){
                    icon = selectedRadio.getText().toString();
                }
            }
        });
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_select:
                Intent intent = new Intent(SelectIcon.this, AddFoodActivity.class);

                intent.putExtra("icon", icon);
                startActivity(intent);
                break;
        }
    }
}