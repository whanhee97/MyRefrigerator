package com.example.myrefrigerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class ChangeCount extends AppCompatActivity {
    SeekBar seekbar;
    TextView outcome;
    public int count = 0;
    public int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_count);

        Intent intent = getIntent();
        count = intent.getIntExtra("count",0);
        id = intent.getIntExtra("id",0);
        seekbar = (SeekBar)findViewById(R.id.count_seekBar);
        outcome = (TextView)findViewById(R.id.count_text);

        outcome.setText(Integer.toString(count));

        seekbar.setProgress(count);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                count = seekbar.getProgress();
                update();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                count = seekbar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                count = seekbar.getProgress();
            }
        });
    }

    public void update(){
        outcome.setText(new StringBuilder().append(count));
        // seekbar의 값에 따라 TextView로 보여주고 화면을 바꾸기 위해 정의한 함수
    }

    public void mOnClick(View v){
        switch (v.getId()){
            case R.id.btn_update_complete:
                Intent intent = new Intent(ChangeCount.this,MainActivity.class);
                DBHandler handler = new DBHandler(this);
                handler.updateCount(id,count);
                startActivity(intent);
                break;
        }
    }
}