package com.example.myrefrigerator;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class CheckableLinearLayout extends LinearLayout implements Checkable {
    public CheckableLinearLayout(Context context) {
        super(context);
    }

    public CheckableLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckableLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CheckableLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setChecked(boolean checked) {
        CheckBox cb = (CheckBox) findViewById(R.id.todo_checkbox);
        if(cb.isChecked() != checked){
            cb.setChecked(checked);
        }
    }

    @Override
    public boolean isChecked() {
        CheckBox cb = (CheckBox) findViewById(R.id.todo_checkbox);
        return cb.isChecked();
    }

    @Override
    public void toggle() {
        CheckBox cb = (CheckBox) findViewById(R.id.todo_checkbox);
        setChecked(cb.isChecked()?false:true);
    }
}
